#!/usr/bin/env bash
set -eu

# Usage:
#   ./parallel_curls.sh <url>
# Examples:
#   ./parallel_curls.sh http://localhost:8080/employees
#   ./parallel_curls.sh "http://localhost:8080/employee/{}"    # {} -> 1..10

URL="${1:-http://localhost:9090/employees}"
PARALLEL=20

echo "Calling $URL in $PARALLEL parallel requests..."

for i in $(seq 1 $PARALLEL); do
  (
    if [[ "$URL" == *"{}"* ]]; then
      call_url="${URL//\{\}/$i}"
    else
      call_url="$URL"
    fi

    # silent body, append HTTP status on its own line
    resp="$(curl -s -w $'\n::HTTP_STATUS::%{http_code}' "$call_url")"
    echo "== response $i ($call_url) =="
    echo "$resp"
  ) &
done
