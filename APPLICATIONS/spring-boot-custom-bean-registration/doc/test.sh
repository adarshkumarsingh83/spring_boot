#!/usr/bin/env bash
set -euo pipefail

HOST="localhost"
PORT="8080"
BASE_URL="http://${HOST}:${PORT}"
TIMEOUT=10

curl_opts=(-sS --max-time "${TIMEOUT}" -H "Accept: text/plain")

call() {
  local path="$1"
  echo
  echo "==> GET ${BASE_URL}${path}"
  curl "${curl_opts[@]}" -w "\n\nHTTP_STATUS:%{http_code}\n" "${BASE_URL}${path}"
}

# Change HOST/PORT if needed:
# HOST="your-host"; PORT="your-port"; BASE_URL="http://${HOST}:${PORT}"

call "/wish"
call "/communicate/message?type=email"
call "/communicate/message?type=sms"
call "/communicate/message?type=default"
call "/communicate/message"
