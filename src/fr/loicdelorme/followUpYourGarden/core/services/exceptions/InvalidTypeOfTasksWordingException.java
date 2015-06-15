package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the wording value is empty.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class InvalidTypeOfTasksWordingException extends Exception
{
	/**
	 * This exception is built when the wording is not valid.
	 */
	public InvalidTypeOfTasksWordingException()
	{
		super("The wording is empty!");
	}
}
