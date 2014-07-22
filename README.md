# euler

[![Build
Status](https://travis-ci.org/SZoerner/euler.svg?branch=master)](https://travis-ci.org/SZoerner/euler)
[![Coverage Status](https://img.shields.io/coveralls/SZoerner/euler.svg)](https://coveralls.io/r/SZoerner/euler)

My Project Euler solutions, written in Clojure

## Testing

This project uses example-based testing (with clojure.test) as well as property-based testing (using clojure.test.check).

- To exeute only unit tests, run ``lein test test/euler/euler*_test.clj``.
- To run the generative tests, run ``lein test test/euler/euler*_quickcheck.clj``.

