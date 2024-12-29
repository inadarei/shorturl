# shorturl
URL Shortener API - Open Source

## Installation

### Docker Dependency

This project requires a working Postgres to function. The included Makefile and default configuration use docker-provided postgres installation, which means you will need to have docker properly set up on your machine to run this project as-is

### Running the project

To run the project, you can use the included Makefile. The following commands are available:

- `make` - This will start the project in development mode
- `make logs` - This will show logs of the Docker container that runs postgres DB
- `make test` - This will run the tests
