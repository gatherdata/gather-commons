/**
 * Copyright (C) 2009 AED <info@gatherdata.org>
 *
 * OSI compliant license pending.
 *
 * http://www.opensource.org/licenses
 */
package org.gatherdata.commons.db.hsqldb.internal;

import java.io.PrintWriter;
import java.util.logging.Logger;

import org.gatherdata.commons.db.hsqldb.spi.HsqldbService;
import org.hsqldb.Server;

public class HsqldbServiceImpl implements HsqldbService {

	Logger log = Logger.getLogger(HsqldbServiceImpl.class.getName());
	
	private boolean isNetwork = true;
	private String url = null;
	private Server server;

	private String serverProps = "database.1=file:gather;dbname.1=gather";
	
	public void start() {
        if (this.isNetwork) {
            if (this.url == null) {
                this.url = "jdbc:hsqldb:hsql://localhost/test";
            }

            if (server == null) {
            	server = new Server();
            }
            
            server.putPropertiesFromString(serverProps);

            server.setDatabaseName(0, "test");
            server.setDatabasePath(0, "mem:test;sql.enforce_strict_size=true");
            
            server.start();
        } else {
            if (url == null) {
                url = "jdbc:hsqldb:mem:test;sql.enforce_strict_size=true";
            }
        }
	}
	
	public void stop() {
		if (server != null) {
			server.stop();
		}
	}
	
}
