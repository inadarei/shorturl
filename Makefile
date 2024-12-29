.PHONY: help start-app start-db stop stop-app stop-db restart clean logs ps default

default: start-db start-app

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

stop: stop-db stop-app

stop-app:
	@echo "Stopping the application..."
	@mvn clean

stop-db:
	@echo "Stopping PostgreSQL container..."
	@docker-compose -f docker-compose-postgres.yml down

restart: stop start

clean:
	@echo "Cleaning up PostgreSQL container, network, and volume..."
	@docker-compose -f docker-compose-postgres.yml down -v

logs:
	@docker-compose -f docker-compose-postgres.yml logs -f

ps:
	@docker-compose -f docker-compose-postgres.yml ps
