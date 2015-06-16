package fr.loicdelorme.followUpYourGarden.core.properties.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import fr.loicdelorme.followUpYourGarden.core.properties.PropertiesConstants;

/**
 * This class allow you to read database configuration data into a properties file.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class DatabaseConfigurationPropertiesReader implements DatabaseConfigurationReader
{
	/**
	 * The properties linked to the file.
	 */
	private Properties properties;

	/**
	 * The driver.
	 */
	private String driver;

	/**
	 * The database URL.
	 */
	private String url;

	/**
	 * The username.
	 */
	private String username;

	/**
	 * The password.
	 */
	private String password;

	/**
	 * Create a database configuration properties reader.
	 * 
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public DatabaseConfigurationPropertiesReader() throws FileNotFoundException, IOException
	{
		this.properties = new Properties();
		openPropertiesFile(PropertiesConstants.DATABASE_CONFIGURATION_FILE);
	}

	/**
	 * Open the configuration file.
	 * 
	 * @param databaseConfigurationFile
	 *            The file name.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	private void openPropertiesFile(String databaseConfigurationFile) throws FileNotFoundException, IOException
	{
		this.properties.load(new FileInputStream(databaseConfigurationFile));
		readData();
	}

	/**
	 * Read all data.
	 */
	private void readData()
	{
		this.driver = this.properties.getProperty("driver");
		this.url = this.properties.getProperty("url");
		this.username = this.properties.getProperty("username");
		this.password = this.properties.getProperty("password");
	}

	@Override
	public String getDriver()
	{
		return this.driver;
	}

	@Override
	public String getUrl()
	{
		return this.url;
	}

	@Override
	public String getUsername()
	{
		return this.username;
	}

	@Override
	public String getPassword()
	{
		return this.password;
	}
}
