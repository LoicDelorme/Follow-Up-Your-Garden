package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

import fr.loicdelorme.followUpYourGarden.core.language.MyResourceBundle;

/**
 * This exception is thrown if the periodicity attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingTaskToBeCarryOutPeriodicityException extends Exception
{
	/**
	 * This exception is built when the periodicity attribute is missing.
	 */
	public MissingTaskToBeCarryOutPeriodicityException()
	{
		super(MyResourceBundle.getBundle().getString("missingTaskToBeCarryOutPeriodicityException"));
	}
}
