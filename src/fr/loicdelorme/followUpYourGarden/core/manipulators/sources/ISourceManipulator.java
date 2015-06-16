package fr.loicdelorme.followUpYourGarden.core.manipulators.sources;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * This interface allow you to handle a data source.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public interface ISourceManipulator
{
	/**
	 * Open a connection with a data source.
	 * 
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public void openConnection() throws SQLException;

	/**
	 * Test if the connection is established.
	 * 
	 * @return True if the connection is established, else False.
	 */
	public boolean isConnected();

	/**
	 * Get the data source connection.
	 * 
	 * @return A connection.
	 */
	public Connection getConnection();

	/**
	 * Close a connection with a data source.
	 * 
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public void closeConnection() throws SQLException;
}
