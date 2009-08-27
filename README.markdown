gather-commons
==============
Core infrastructure and some generically useful libraries.

Dependencies
------------
* neo4j apoc-bundle - A package of OSGi-friendly Neo4j Components

Building
--------
1. Build neo4j apoc-bundle
    svn co https://svn.neo4j.org/laboratory/components/apoc-bundle
    cd apoc-bundle
    mvn clean install

2. Build gather-commons
    cd gather-commons
    mvn clean install


Running
-------
As a collection of core libraries, gather-commons doesn't
do much by itself. Still, you can run it in an OSGi environment
as a sanity check that everything is provisioning properly.

`mvn pax:provision`

