package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

import fr.loicdelorme.followUpYourGarden.core.language.MyResourceBundle;

/**
 * This exception is thrown if the wording attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingTypeOfTasksWordingException extends Exception
{
	/**
	 * This exception is built when the wording attribute is missing.
	 */
	public MissingTypeOfTasksWordingException()
	{
		super(MyResourceBundle.getBundle().getString("missingTypeOfTasksWordingException"));
	}
}
