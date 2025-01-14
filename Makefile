.PHONY: help build start-app start-db start-zipkin test test-cucumber stop stop-app stop-db stop-zipkin restart clean logs ps default

default: start-db start-zipkin start-app

help:
	@echo "Available commands:"
	@echo "  make           		: Start application with PostgreSQL and Zipkin running in Docker"
	@echo "  make build     		: Build the application"
	@echo "  make start-app  		: Re/Start the app only"
	@echo "  make stop      		: Stop application, PostgreSQL, and Zipkin"
	@echo "  make restart   		: Restart the application, PostgreSQL, and Zipkin"
	@echo "  make clean     		: Stop and remove container, network, and volume"
	@echo "  make logs      		: Show database container logs"
	@echo "  make ps        		: Show database container status"


build: 
	@echo "Building the application..."
	@mvn package

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

test: 
	@echo "Running all tests..."
#	@mvn clean 
#	@stop-db && start-db 
#	@stop-zipkin && start-zipkin 
	@mvn test

test-cucumber: start-db start-zipkin
	@echo "Running components tests only..."
	@mvn test -Dtest=CucumberIntegrationTest
# Run with specific cucumber tags
# @mvn test -Dcucumber.filter.tags="@smoke"	



clean:
	@echo "Cleaning up PostgreSQL container, network, and volume..."
	@mvn clean install -U
	@docker-compose -f docker-compose-postgres.yml down -v
	@docker-compose -f docker-compose-zipkin.yml down -v

logs:
	@docker-compose -f docker-compose-postgres.yml logs -f

ps:
	@docker-compose -f docker-compose-postgres.yml ps
