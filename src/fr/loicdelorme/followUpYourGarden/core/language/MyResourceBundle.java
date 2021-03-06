package fr.loicdelorme.followUpYourGarden.core.language;

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
	 */
	public static ResourceBundle getBundle()
	{
		if (resourceBundle == null)
		{
			ILanguageReader languageReader = null;
			try
			{
				languageReader = new LanguagePropertiesReader();
			}
			catch (IOException e)
			{
				// Nothing.
			}

			String[] language = languageReader.getLanguage().split("_");

			resourceBundle = ResourceBundle.getBundle("fr/loicdelorme/followUpYourGarden/views/resources/followUpYourGarden", new Locale(language[0], language[1]));
		}

		return resourceBundle;
	}
}
