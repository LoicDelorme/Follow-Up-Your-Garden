package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the isRecurrent attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingTaskToBeCarryOutIsRecurrentException extends Exception
{
	/**
	 * This exception is built when the recurrent attribute is missing.
	 */
	public MissingTaskToBeCarryOutIsRecurrentException()
	{
		super("The recurrent value is missing!");
	}
}
