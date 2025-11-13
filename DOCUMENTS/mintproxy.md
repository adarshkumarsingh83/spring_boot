
## offical site 
* https://docs.mitmproxy.org/stable/overview/installation/

## To install mitmproxy 
* $ brew install --cask mitmproxy

## create proxy config in vi editor 
* 

```
from mitmproxy import http

TARGET_HOST = "127.0.0.1"
TARGET_PORT = 9090
TARGET_SCHEME = "http"

def request(flow: http.HTTPFlow) -> None:
    # Preserve original method, path, headers, and body
    flow.request.host = TARGET_HOST
    flow.request.port = TARGET_PORT
    flow.request.scheme = TARGET_SCHEME

    # Optional: update Host header to match the backend
    flow.request.headers["Host"] = f"{TARGET_HOST}:{TARGET_PORT}"

    # Optional: add X-Forwarded headers
    flow.request.headers["X-Forwarded-For"] = flow.client_conn.address[0]
    flow.request.headers["X-Forwarded-Host"] = flow.request.headers.get("Host", "")
    flow.request.headers["X-Forwarded-Proto"] = flow.request.scheme
                                                                  
```
* esc:wq

## to start mitm proxy server 
* $ mitmweb -s proxy.py --listen-host 0.0.0.0 --listen-port 8080 --mode regular 

## to shutdown 
* ctr+c


## execute cmd or hit the api and trace int he proxy console 
```
$ curl -X POST http://localhost:8080/employees/ -d '{"name": "adarsh kumar", "email": "adarsh@kumar.com"}' -H "Content-Type: application/json"

$ curl -X GET http://localhost:8000/employees/1
```

