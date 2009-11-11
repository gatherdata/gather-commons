gather-commons
==============
Core infrastructure, examples and some generically useful libraries.

Building
--------
All GATHERdata components are built with maven2 and java 1.5+. 

To build gather-commons:

    `cd gather-commons`   
    `mvn clean install`

### Notes

The build uses [maven-license-plugin][1] to generate and verify
that a license header is present on all source code. So, when
adding new files you'll need to run `mvn license:format` to
add the header.


Running
-------
As a collection of core libraries, gather-commons doesn't
do much by itself. Still, you can run it in an OSGi environment
as a sanity check that everything is provisioning properly.

`mvn pax:provision`

References
----------

* [EclipseLink](http://www.eclipse.org/eclipselink/)
* [Neo4j](http://neo4j.org/)
* [Db4o](http://www.db4o.com/)
* [Apache Felix](http://felix.apache.org)
* [Google Guice+Peaberry](http://code.google.com/p/peaberry/)
* [Maven Pax Plugin](http://www.ops4j.org/projects/pax/construct/maven-pax-plugin/)

[1]: http://code.google.com/p/maven-license-plugin/wiki/Configuration "Maven License Plugin"
