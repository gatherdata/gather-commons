/**
 * The contents of this file are subject to the AED Public Use License Agreement, Version 1.0 (the "License");
 * use in any manner is strictly prohibited except in compliance with the terms of the License.
 * The License is available at http://gatherdata.org/license.
 *
 * Copyright (c) AED.  All Rights Reserved
 */
public interface Db4oService {

	/**
	 * Creates a fresh {@link Configuration Configuration} instance.
	 * 
	 * @return a fresh, independent configuration with all options set to their default values
	 */
	Configuration newConfiguration();

	/**
	 * Operates just like {@link Db4o#openClient(Configuration, String, int, String, String)}, but uses
	 * a newly created, vanilla {@link Configuration Configuration} context.
	 * 
	 * opens an {@link ObjectContainer ObjectContainer}
	 * client and connects it to the specified named server and port.
	 * <br><br>
	 * The server needs to
	 * {@link ObjectServer#grantAccess allow access} for the specified user and password.
	 * <br><br>
	 * A client {@link ObjectContainer ObjectContainer} can be cast to 
	 * {@link ExtClient ExtClient} to use extended
	 * {@link ExtObjectContainer ExtObjectContainer} 
	 * and {@link ExtClient ExtClient} methods.
	 * <br><br>
	 * @param hostName the host name
	 * @param port the port the server is using
	 * @param user the user name
	 * @param password the user password
	 * @return an open {@link ObjectContainer ObjectContainer}
	 * @see ObjectServer#grantAccess
	 * @throws Db4oException
	 */

	ObjectContainer openClient(String hostName, int port, String user, String password) throws Db4oException;

	/**
	 * opens an {@link ObjectContainer ObjectContainer}
	 * client and connects it to the specified named server and port.
	 * <br><br>
	 * The server needs to
	 * {@link ObjectServer#grantAccess allow access} for the specified user and password.
	 * <br><br>
	 * A client {@link ObjectContainer ObjectContainer} can be cast to 
	 * {@link ExtClient ExtClient} to use extended
	 * {@link ExtObjectContainer ExtObjectContainer} 
	 * and {@link ExtClient ExtClient} methods.
	 * <br><br>
	 * @param config a custom {@link Configuration Configuration} instance to be obtained via {@link Db4o#newConfiguration()}
	 * @param hostName the host name
	 * @param port the port the server is using
	 * @param user the user name
	 * @param password the user password
	 * @return an open {@link ObjectContainer ObjectContainer}
	 * @see ObjectServer#grantAccess
	 * @throws Db4oException
	 */
	ObjectContainer openClient(Configuration config, String hostName, int port, String user, String password) throws Db4oException;

	/**
	 * Operates just like {@link Db4o#openFile(Configuration, String)}, but uses
	 * a newly created, vanilla {@link Configuration Configuration} context.
	 * 
	 * opens an {@link ObjectContainer ObjectContainer}
	 * on the specified database file for local use.
	 * <br><br>A database file can only be opened once, subsequent attempts to open
	 * another {@link ObjectContainer ObjectContainer} against the same file will result in
	 * a {@link DatabaseFileLockedException DatabaseFileLockedException}.<br><br>
	 * Database files can only be accessed for readwrite access from one process 
	 * (one Java VM) at one time. All versions except for db4o mobile edition use an
	 * internal mechanism to lock the database file for other processes. 
	 * <br><br>
	 * @param databaseFileName an absolute or relative path to the database file
	 * @return an open {@link ObjectContainer ObjectContainer}
	 * @see Configuration#readOnly
	 * @see Configuration#encrypt
	 * @see Configuration#password
	 * @throws Db4oException
	 */
	ObjectContainer openFile(String databaseFileName) throws Db4oException;

	/**
	 * opens an {@link ObjectContainer ObjectContainer}
	 * on the specified database file for local use.
	 * <br><br>A database file can only be opened once, subsequent attempts to open
	 * another {@link ObjectContainer ObjectContainer} against the same file will result in
	 * a {@link DatabaseFileLockedException DatabaseFileLockedException}.<br><br>
	 * Database files can only be accessed for readwrite access from one process 
	 * (one Java VM) at one time. All versions except for db4o mobile edition use an
	 * internal mechanism to lock the database file for other processes. 
	 * <br><br>
	 * @param config a custom {@link Configuration Configuration} instance to be obtained via {@link Db4o#newConfiguration()}
	 * @param databaseFileName an absolute or relative path to the database file
	 * @return an open {@link ObjectContainer ObjectContainer}
	 * @see Configuration#readOnly
	 * @see Configuration#encrypt
	 * @see Configuration#password
	 * @throws Db4oException
	 */
	ObjectContainer openFile(Configuration config, String databaseFileName) throws Db4oException;

	/**
	 * Operates just like {@link Db4o#openServer(Configuration, String, int)}, but uses
	 * a newly created, vanilla {@link Configuration Configuration} context.
	 * 
	 * opens an {@link ObjectServer ObjectServer}
	 * on the specified database file and port.
	 * <br><br>
	 * If the server does not need to listen on a port because it will only be used
	 * in embedded mode with {@link ObjectServer#openClient}, specify '0' as the
	 * port number.
	 * @param databaseFileName an absolute or relative path to the database file
	 * @param port the port to be used, or 0, if the server should not open a port,
	 * because it will only be used with {@link ObjectServer#openClient()}
	 * @return an {@link ObjectServer ObjectServer} listening
	 * on the specified port.
	 * @see Configuration#readOnly
	 * @see Configuration#encrypt
	 * @see Configuration#password
	 * @throws Db4oException
	 */
	ObjectServer openServer(String databaseFileName, int port) throws Db4oException;

	/**
	 * opens an {@link ObjectServer ObjectServer}
	 * on the specified database file and port.
	 * <br><br>
	 * If the server does not need to listen on a port because it will only be used
	 * in embedded mode with {@link ObjectServer#openClient}, specify '0' as the
	 * port number.
	 * @param config a custom {@link Configuration Configuration} instance to be obtained via {@link Db4o#newConfiguration()}
	 * @param databaseFileName an absolute or relative path to the database file
	 * @param port the port to be used, or 0, if the server should not open a port,
	 * because it will only be used with {@link ObjectServer#openClient()}
	 * @return an {@link ObjectServer ObjectServer} listening
	 * on the specified port.
	 * @see Configuration#readOnly
	 * @see Configuration#encrypt
	 * @see Configuration#password
	 * @throws Db4oException
	 */
	ObjectServer openServer(Configuration config, String databaseFileName, int port) throws Db4oException;
}
