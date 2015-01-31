# euler

<img src="https://projecteuler.net/profile/SZoerner.png"/>

[![Build
Status](https://travis-ci.org/SZoerner/euler.svg?branch=master)](https://travis-ci.org/SZoerner/euler)
[![Coverage Status](https://img.shields.io/coveralls/SZoerner/euler.svg)](https://coveralls.io/r/SZoerner/euler)
[![Dependencies Status](http://jarkeeper.com/szoerner/euler/status.svg)](http://jarkeeper.com/szoerner/euler)

My Project Euler solutions, written in Clojure

## Code Quality

This project runs example-based tests using [Midje](https://github.com/marick/Midje), as well as property-based tests via [clojure.test.check](https://github.com/clojure/test.check).  
Test coverage ist tracked via [Cloverage](https://github.com/lshift/cloverage) (for compatibility, midje tests are wrapped in ``deftest`` blocks).  
Dependencies are checked for updates with [VersionEye](https://www.versioneye.com/).

## Inspiration

- [Clojure Code Quality Tools](http://blog.mattgauger.com/blog/2014/09/15/clojure-code-quality-tools/)
- [Adding Coveralls Test Coverage](http://blog.bfontaine.net/2014/02/15/using-coveralls-with-clojure/)
