#!/bin/bash

COVERALLS_URL='https://coveralls.io/api/v1/jobs'
CLOVERAGE_VERSION='1.0.7-SNAPSHOT' lein2 cloverage -o cov --coveralls --codecov
curl -F 'json_file=@cov/coveralls.json' "$COVERALLS_URL"
curl -s https://codecov.io/bash
