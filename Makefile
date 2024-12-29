.PHONY: help start-app start-db start-zipkin test stop stop-app stop-db stop-zipkin restart clean logs ps default

default: start-db start-zipkin start-app

help:
	@echo "Available commands:"
	@echo "  make          : Start the PostgreSQL container (same as 'make start')"
	@echo "  make start    : Start the PostgreSQL container"
	@echo "  make stop     : Stop the PostgreSQL container"
	@echo "  make restart  : Restart the PostgreSQL container"
	@echo "  make clean    : Stop and remove container, network, and volume"
	@echo "  make logs     : Show container logs"
	@echo "  make ps       : Show container status"

start-app: 
	@echo "Re/Starting the application..."
	@mvn clean
	@mvn package
	@mvn spring-boot:run

start-db:
	@echo "Starting PostgreSQL container..."
	@docker-compose -f docker-compose-postgres.yml up -d
	@echo "PostgreSQL is running on localhost:5432"
	@echo "  Username: dev"
	@echo "  Password: devpwd"
	@echo "  Database: devdb"

start-zipkin:
	@echo "Starting Zipkin container..."
	@docker-compose -f docker-compose-zipkin.yml up -d
	@echo "Zipkin is running on localhost:9411"

stop: stop-app stop-db stop-zipkin

stop-app:
	@echo "Stopping the application..."
	@mvn clean

stop-db:
	@echo "Stopping PostgreSQL container..."
	@docker-compose -f docker-compose-postgres.yml down

stop-zipkin:
	@echo "Stopping Zipkin container..."
	@docker-compose -f docker-compose-zipkin.yml down

restart: stop start

test: start-db
	@echo "Running tests..."
	@mvn test

clean:
	@echo "Cleaning up PostgreSQL container, network, and volume..."
	@docker-compose -f docker-compose-postgres.yml down -v

logs:
	@docker-compose -f docker-compose-postgres.yml logs -f

ps:
	@docker-compose -f docker-compose-postgres.yml ps
