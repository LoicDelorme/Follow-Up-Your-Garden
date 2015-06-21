package fr.loicdelorme.followUpYourGarden.core.database;

import java.io.FileNotFoundException;
import java.io.IOException;

import fr.loicdelorme.followUpYourGarden.core.manipulators.sources.ISourceManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.sources.database.DatabaseSourceManipulator;
import fr.loicdelorme.followUpYourGarden.core.properties.IDatabaseConfigurationReader;
import fr.loicdelorme.followUpYourGarden.core.properties.database.DatabaseConfigurationPropertiesReader;

/**
 * This class allow you to handle your database.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class MyDatabase
{
	/**
	 * A data source manipulator.
	 */
	private static ISourceManipulator sourceManipulator;

	/**
	 * A database configuration data reader.
	 */
	private static IDatabaseConfigurationReader databaseConfigurationReader;

	/**
	 * Get an instance of my database.
	 * 
	 * @return An instance of my database.
	 * @throws ClassNotFoundException
	 *             If the class is not found.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public static ISourceManipulator getInstance() throws ClassNotFoundException, FileNotFoundException, IOException
	{
		if (databaseConfigurationReader == null)
		{
			databaseConfigurationReader = new DatabaseConfigurationPropertiesReader();
		}

		if (sourceManipulator == null)
		{
			sourceManipulator = new DatabaseSourceManipulator(databaseConfigurationReader.getDriver(), databaseConfigurationReader.getUrl(), databaseConfigurationReader.getUsername(), databaseConfigurationReader.getPassword());
		}
		
		return sourceManipulator;
	}
}
