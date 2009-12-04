/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
package org.gatherdata.commons.command.hello;

import java.util.List;

import org.apache.felix.gogo.commands.Command;
import org.apache.felix.gogo.commands.Option;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.karaf.shell.console.OsgiCommandSupport;
import org.osgi.service.command.CommandSession;

@Command(scope = "gather", name = "hello", description="Says hello")
public class HelloShellCommand extends OsgiCommandSupport {

    @Option(name = "--bye", aliases = {}, description = "Says good-bye instead.", required = false, multiValued = false)
    private boolean sayGoodbye = false;

    @Argument(index = 0, name = "subject", description="To whom the greeting should be addressed", required = false, multiValued = true)
    private List<String> args;

    @Override
    protected Object doExecute() throws Exception {
    	final String greeting = (sayGoodbye ? "Good-bye" : "Hello");
    	String subject = "OSGi Shell";
    	
    	if (args != null) {
            boolean first = true;
            StringBuffer subjectFromArgs = new StringBuffer();
            for (String arg : args) {
                if (first) {
                    first = false;
                } else {
                	subjectFromArgs.append(" ");
                }
                subjectFromArgs.append(arg);
            }
            subject = subjectFromArgs.toString();
    	}
    	
		System.out.println(greeting + ", " + subject + "!");
        return null;
    }

    
}