gather-commons
==============
Core infrastructure and some generically useful libraries.

Dependencies
------------
* neo4j apoc-bundle - A package of OSGi-friendly Neo4j Components

Building
--------
1. Build neo4j apoc-bundle

    `svn co https://svn.neo4j.org/laboratory/components/apoc-bundle`   
    `cd apoc-bundle`  
    `mvn clean install`  

2. Build gather-commons

    `cd gather-commons`   
    `mvn clean install`

### Notes

The build uses [maven-license-plugin][1] to generate and verify
that license header are present on all source code. So, when
adding new files you'll need to run `mvn license:format` to
add the headers.


Running
-------
As a collection of core libraries, gather-commons doesn't
do much by itself. Still, you can run it in an OSGi environment
as a sanity check that everything is provisioning properly.

`mvn pax:provision`

[1] http://code.google.com/p/maven-license-plugin/wiki/Configuration
