package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

import fr.loicdelorme.followUpYourGarden.core.language.MyResourceBundle;

/**
 * This exception is thrown if the deadlineDate attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingTaskToBeCarryOutDeadlineDateException extends Exception
{
	/**
	 * This exception is built when the deadline date attribute is missing.
	 */
	public MissingTaskToBeCarryOutDeadlineDateException()
	{
		super(MyResourceBundle.getBundle().getString("missingTaskToBeCarryOutDeadlineDateException"));
	}
}
