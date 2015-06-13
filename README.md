[![Stories in Ready](https://badge.waffle.io/SZoerner/euler.png?label=ready&title=Ready)](https://waffle.io/SZoerner/euler)
# euler

<img src="https://projecteuler.net/profile/SZoerner.png"/>

[![Build
Status](https://travis-ci.org/SZoerner/euler.svg?branch=master)](https://travis-ci.org/SZoerner/euler)
[![Coverage Status](https://coveralls.io/repos/SZoerner/euler/badge.svg?branch=master)](https://coveralls.io/r/SZoerner/euler?branch=master)
[![Dependencies Status](http://jarkeeper.com/szoerner/euler/status.svg)](http://jarkeeper.com/szoerner/euler)
[![API codox](http://b.repl.ca/v1/doc-API-blue.png)](http://szoerner.github.io/euler/codox/)
[![marginalia docs](http://b.repl.ca/v1/doc-marginalia-blue.png)](http://szoerner.github.io/euler/uberdoc)

My Project Euler solutions, written in Clojure

## Code Quality

- This project runs example-based tests using [Midje](https://github.com/marick/Midje), as well as property-based tests via [clojure.test.check](https://github.com/clojure/test.check).
- [Test coverage](https://coveralls.io/r/SZoerner/euler?branch=master) ist tracked via [Cloverage](https://github.com/lshift/cloverage) (for compatibility, midje tests are wrapped in ``deftest`` blocks).
- [Dependencies](http://jarkeeper.com/szoerner/euler) are checked for updates with [Jarkeeper](http://jarkeeper.com/).

## Documentation
> No one will give a crap about your crap if your documentation is crap  
> – [fogus](http://blog.fogus.me/2011/01/05/the-marginalia-manifesto/)

- [Marginalia](https://github.com/gdeer81/marginalia) supports (almost) [literate programming](http://szoerner.github.io/euler/uberdoc).
- [Codox](https://github.com/weavejester/codox) is used for [API documentation](http://szoerner.github.io/euler/codox/).

## Inspiration

- [Clojure Code Quality Tools](http://blog.mattgauger.com/blog/2014/09/15/clojure-code-quality-tools/)
- [Adding Coveralls Test Coverage](http://blog.bfontaine.net/2014/02/15/using-coveralls-with-clojure/)
