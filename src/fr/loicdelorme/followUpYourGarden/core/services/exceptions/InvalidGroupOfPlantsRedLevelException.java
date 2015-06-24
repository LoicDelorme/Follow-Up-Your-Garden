package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

import fr.loicdelorme.followUpYourGarden.core.language.MyResourceBundle;

/**
 * This exception is thrown if the red level is not between 0 and 255.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class InvalidGroupOfPlantsRedLevelException extends Exception
{
	/**
	 * This exception is built when the red level is not valid.
	 * 
	 * @param redLevelValue
	 *            The invalid red level.
	 */
	public InvalidGroupOfPlantsRedLevelException(int redLevelValue)
	{
		super(String.format(MyResourceBundle.getBundle().getString("invalidGroupOfPlantsRedLevelException"), redLevelValue));
	}
}
