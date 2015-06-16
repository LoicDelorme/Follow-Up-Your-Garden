package fr.loicdelorme.followUpYourGarden.core.properties.database;

/**
 * This interface allow you to read database configuration data.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public interface DatabaseConfigurationReader
{
	/**
	 * Get the driver.
	 * 
	 * @return The driver.
	 */
	public String getDriver();

	/**
	 * Get the database URL.
	 * 
	 * @return The database URL.
	 */
	public String getUrl();

	/**
	 * Get the username.
	 * 
	 * @return The username.
	 */
	public String getUsername();

	/**
	 * Get the password.
	 * 
	 * @return The password.
	 */
	public String getPassword();
}
