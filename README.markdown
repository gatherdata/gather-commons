gather-commons
==============
Core infrastructure and some generically useful libraries.

Building
--------
Requires maven2 and java 1.5+

`mvn clean install`


Running
-------
As a collection of core libraries, gather-commons doesn't
do much by itself. Still, you can run it in an OSGi environment
as a sanity check that everything is provisioning properly.

`mvn pax:provision`

