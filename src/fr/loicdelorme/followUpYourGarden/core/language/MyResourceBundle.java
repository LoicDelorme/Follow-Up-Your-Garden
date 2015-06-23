package fr.loicdelorme.followUpYourGarden.core.language;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import fr.loicdelorme.followUpYourGarden.core.properties.ILanguageReader;
import fr.loicdelorme.followUpYourGarden.core.properties.language.LanguagePropertiesReader;

/**
 * This class allow you to handle your resource bundle.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class MyResourceBundle
{
	/**
	 * A resource bundle.
	 */
	private static ResourceBundle resourceBundle;

	/**
	 * Get an instance of my resource bundle.
	 * 
	 * @return An instance of my resource bundle.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public static ResourceBundle getBundle() throws FileNotFoundException, IOException
	{
		if (resourceBundle == null)
		{
			ILanguageReader languageReader = new LanguagePropertiesReader();
			String[] language = languageReader.getLanguage().split("_");

			resourceBundle = ResourceBundle.getBundle("./data/bundles/followUpYourGarden", new Locale(language[0], language[1]));
		}

		return resourceBundle;
	}
}
