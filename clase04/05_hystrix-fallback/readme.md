Levantar un WebServer con Python

`python -m SimpleHTTPServer 8090`

Poner carga:

`wrk -c 5 -d 1200s -t 5 http://localhost:8080/person\?url\=http://localhost:8090/`
