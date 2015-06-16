package fr.loicdelorme.followUpYourGarden.core.manipulators.sources.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.loicdelorme.followUpYourGarden.core.manipulators.sources.ISourceManipulator;

/**
 * This class allow you to handle a connection with a database.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class DatabaseSourceManipulator implements ISourceManipulator
{
	/**
	 * The database URL.
	 */
	private final String url;

	/**
	 * The username.
	 */
	private final String username;

	/**
	 * The password.
	 */
	private final String password;

	/**
	 * The connection.
	 */
	private Connection connection;

	/**
	 * If you are connected.
	 */
	private boolean isConnected;

	/**
	 * Create a database data source manipulator.
	 * 
	 * @param driver
	 *            The driver name.
	 * @param url
	 *            The database URL.
	 * @param username
	 *            The username.
	 * @param password
	 *            The password.
	 * @throws ClassNotFoundException
	 *             If the driver is not found.
	 */
	public DatabaseSourceManipulator(String driver, String url, String username, String password) throws ClassNotFoundException
	{
		Class.forName(driver);

		this.url = url;
		this.username = username;
		this.password = password;
		this.connection = null;
		this.isConnected = false;
	}

	/**
	 * @see fr.loicdelorme.followUpYourGarden.core.manipulators.sources.ISourceManipulator#openConnection()
	 */
	@Override
	public void openConnection() throws SQLException
	{
		if (!this.isConnected)
		{
			this.connection = DriverManager.getConnection(this.url, this.username, this.password);
			this.isConnected = true;
		}
	}

	/**
	 * @see fr.loicdelorme.followUpYourGarden.core.manipulators.sources.ISourceManipulator#isConnected()
	 */
	@Override
	public boolean isConnected()
	{
		return this.isConnected;
	}

	/**
	 * @see fr.loicdelorme.followUpYourGarden.core.manipulators.sources.ISourceManipulator#getConnection()
	 */
	@Override
	public Connection getConnection()
	{
		return this.connection;
	}

	/**
	 * @see fr.loicdelorme.followUpYourGarden.core.manipulators.sources.ISourceManipulator#closeConnection()
	 */
	@Override
	public void closeConnection() throws SQLException
	{
		if (this.isConnected)
		{
			this.connection.close();
			this.connection = null;
			this.isConnected = false;
		}
	}
}
