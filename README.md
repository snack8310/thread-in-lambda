# thread-in-lambda
test processor nums in lambda

## build layer
set -eo pipefail
gradle -q packageLibs

## build function
set -eo pipefail
gradle clean build