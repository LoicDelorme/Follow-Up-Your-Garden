package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

import fr.loicdelorme.followUpYourGarden.core.language.MyResourceBundle;

/**
 * This exception is thrown if the description attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingTaskToBeCarryOutDescriptionException extends Exception
{
	/**
	 * This exception is built when the description attribute is missing.
	 */
	public MissingTaskToBeCarryOutDescriptionException()
	{
		super(MyResourceBundle.getBundle().getString("missingTaskToBeCarryOutDescriptionException"));
	}
}
