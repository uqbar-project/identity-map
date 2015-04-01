IdentityMap [![Build Status](https://travis-ci.org/uqbar-project/identity-map.svg?branch=master)](https://travis-ci.org/uqbar-project/identity-map)
===========

This module provides a simple implementation of a Scala immutable Map, based on identity instead of equality.

This means an **IdentityMap** will treat two equal (but non identical) objects as separate keys.

Setup
-----

To include this module in your *SBT* project, just add the following lines to your `.sbt` project definition:

```scala
libraryDependencies += "org.uqbar" %% "identity-map" % "latest.integration"
```

Or, if you are using Uqbar's [SBT Flexible Dependencies Plugin](https://github.com/uqbar-project/sbt-flexible-dependencies-plugin):

```scala
lazy val project = FDProject(
	"org.uqbar" %% "identity-map" % "latest.integration"
)
```

Contributions
-------------

Yes, please! Pull requests are always welcome, just try to keep it small and clean.

License
-------

This code is open source software licensed under the [LGPL v3 License](https://www.gnu.org/licenses/lgpl.html) by [The Uqbar Foundation](http://www.uqbar-project.org/). Feel free to use it accordingly.