#!/bin/bash
set -e

# Script to generate docs and push to github pages.
# https://github.com/weavejester/codox/wiki/Deploying-to-GitHub-Pages
cd `dirname $0`

# git fetch --tags
# latestTag=$(git describe --tags `git rev-list --tags --max-count=1`)
# git checkout $latestTag

# To be sure you're on the right branch
cd ../doc
git checkout gh-pages 

# remove everything except the .git directory
ls -1 | grep -v '.git' | xargs rm -rf 

# generate the docs
lein doc 				# codox API
lein marg -d "./doc"	# marginalia code documentation

# create a new commit
git add .
git commit -am "new documentation push."
git push -u origin gh-pages
cd ..
git checkout -