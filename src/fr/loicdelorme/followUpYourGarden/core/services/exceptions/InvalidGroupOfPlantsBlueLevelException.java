package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

import fr.loicdelorme.followUpYourGarden.core.language.MyResourceBundle;

/**
 * This exception is thrown if the blue level is not between 0 and 255.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class InvalidGroupOfPlantsBlueLevelException extends Exception
{
	/**
	 * This exception is built when the blue level is not valid.
	 * 
	 * @param blueLevelValue
	 *            The invalid blue level.
	 */
	public InvalidGroupOfPlantsBlueLevelException(int blueLevelValue)
	{
		super(String.format(MyResourceBundle.getBundle().getString("invalidGroupOfPlantsBlueLevelException"), blueLevelValue));
	}
}
