#!/bin/bash
set -e

# Script to push a release with lein-release and then push docs.
# https://github.com/Prismatic/plumbing/tree/master/bin
cd `dirname $0`
lein release
./push_docs.sh