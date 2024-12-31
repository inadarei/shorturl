default: start

.PHONY: builddoc lint start stop

builddoc:
	- docker run --rm -v ${PWD}:/spec redocly/cli build-docs openapi.yaml --output index.html

lint:
	- docker run --rm -v ${PWD}:/spec redocly/cli lint openapi.yaml 

start:
	- @docker run -d --rm --name shorten-docs -p 3939:80 -v ${PWD}/openapi.yaml:/usr/share/nginx/html/swagger.yaml -e SPEC_URL=swagger.yaml redocly/redoc
	- @echo "server started at: http://0.0.0.0:3939"

stop:
	- docker stop shorten-docs

help:
	@echo "Available commands:"
	@echo "  make           		: Start server with rendered spec at http://0.0.0.0:3939"
	@echo "  make builddoc     	: Build index.html from openapi.yaml"
	@echo "  make lint     			: Analyze openapi.yaml for errors"
	@echo "  make stop      		: Stop server"
