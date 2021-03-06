package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

import fr.loicdelorme.followUpYourGarden.core.language.MyResourceBundle;

/**
 * This exception is thrown if the description value is empty.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class InvalidTypeOfTasksDescriptionException extends Exception
{
	/**
	 * This exception is built when the description is not valid.
	 */
	public InvalidTypeOfTasksDescriptionException()
	{
		super(MyResourceBundle.getBundle().getString("invalidTypeOfTasksDescriptionException"));
	}
}
