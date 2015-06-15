package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the deadlineDate attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingCarriedOutTaskDeadlineDateException extends Exception
{
	/**
	 * This exception is built when the deadline date attribute is missing.
	 */
	public MissingCarriedOutTaskDeadlineDateException()
	{
		super("The deadline date is missing!");
	}
}
