#!/bin/bash
set -e

# One-time script to setup codox deploy to github pages.
# https://github.com/weavejester/codox/wiki/Deploying-to-GitHub-Pages
# Be sure to create a branch named gh-pages first..
cd `dirname $0`
cd ..

rm -rf doc && mkdir doc
git clone git@github.com:szoerner/euler.git doc
cd doc
git symbolic-ref HEAD refs/heads/gh-pages
rm .git/index
git clean -fdx