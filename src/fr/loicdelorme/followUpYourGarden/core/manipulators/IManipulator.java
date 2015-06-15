package fr.loicdelorme.followUpYourGarden.core.manipulators;

import java.sql.Connection;

/**
 * This interface allow you to set connection.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public interface IManipulator
{
	/**
	 * Set the connection.
	 * 
	 * @param connection
	 *            A connection.
	 */
	public void setConnection(Connection connection);
}
