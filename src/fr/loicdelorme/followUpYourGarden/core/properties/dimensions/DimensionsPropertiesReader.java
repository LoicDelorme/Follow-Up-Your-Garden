package fr.loicdelorme.followUpYourGarden.core.properties.dimensions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import fr.loicdelorme.followUpYourGarden.core.properties.IDimensionsReader;
import fr.loicdelorme.followUpYourGarden.core.properties.IPropertiesConstants;

/**
 * This class allow you to read dimensions configuration data into a properties file.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class DimensionsPropertiesReader implements IDimensionsReader
{
	/**
	 * The properties linked to the file.
	 */
	private Properties properties;

	/**
	 * The width.
	 */
	private int width;

	/**
	 * The height.
	 */
	private int height;

	/**
	 * Create a dimensions properties reader.
	 * 
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public DimensionsPropertiesReader() throws FileNotFoundException, IOException
	{
		this.properties = new Properties();
		openPropertiesFile(IPropertiesConstants.DIMENSIONS_CONFIGURATION_FILE);
	}

	/**
	 * Open the configuration file.
	 * 
	 * @param dimensionsConfigurationFile
	 *            The file name.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	private void openPropertiesFile(String dimensionsConfigurationFile) throws FileNotFoundException, IOException
	{
		this.properties.load(new FileInputStream(dimensionsConfigurationFile));
		readData();
	}

	/**
	 * Read all data.
	 */
	private void readData()
	{
		this.width = Integer.parseInt(this.properties.getProperty("width"));
		this.height = Integer.parseInt(this.properties.getProperty("height"));
	}

	/**
	 * @see fr.loicdelorme.followUpYourGarden.core.properties.IDimensionsReader#getWidth()
	 */
	@Override
	public int getWidth()
	{
		return this.width;
	}

	/**
	 * @see fr.loicdelorme.followUpYourGarden.core.properties.IDimensionsReader#getHeight()
	 */
	@Override
	public int getHeight()
	{
		return this.height;
	}
}
