package fr.loicdelorme.followUpYourGarden.core.properties.language;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import fr.loicdelorme.followUpYourGarden.core.properties.ILanguageReader;
import fr.loicdelorme.followUpYourGarden.core.properties.IPropertiesConstants;

/**
 * This class allow you to read language configuration data into a properties file.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class LanguagePropertiesReader implements ILanguageReader
{
	/**
	 * The properties linked to the file.
	 */
	private Properties properties;

	/**
	 * The language.
	 */
	private String language;

	/**
	 * Create a language properties reader.
	 * 
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public LanguagePropertiesReader() throws FileNotFoundException, IOException
	{
		this.properties = new Properties();
		openPropertiesFile(IPropertiesConstants.LANGUAGE_CONFIGURATION_FILE);
	}

	/**
	 * Open the configuration file.
	 * 
	 * @param languageConfigurationFile
	 *            The file name.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	private void openPropertiesFile(String languageConfigurationFile) throws FileNotFoundException, IOException
	{
		this.properties.load(new FileInputStream(languageConfigurationFile));
		readData();
	}

	/**
	 * Read all data.
	 */
	private void readData()
	{
		this.language = this.properties.getProperty("lang");
	}

	/**
	 * @see fr.loicdelorme.followUpYourGarden.core.properties.ILanguageReader#getLanguage()
	 */
	@Override
	public String getLanguage()
	{
		return this.language;
	}
}
