#!/bin/bash
set -e

# Script to generate docs and push to github pages.
# https://github.com/weavejester/codox/wiki/Deploying-to-GitHub-Pages
cd `dirname $0`

# git fetch --tags
# latestTag=$(git describe --tags `git rev-list --tags --max-count=1`)
# git checkout $latestTag

# To be sure you're on the right branch
git checkout gh-pages 
cd ../doc

# remove everything except the .git directory
ls -1 | grep -v '.git' | xargs rm -rf 

# generate the docs
lein doc
lein marg -d "./doc"

# create a new commit
git add .
git commit -am "new documentation push."
git push -u origin gh-pages
cd ..
git checkout -