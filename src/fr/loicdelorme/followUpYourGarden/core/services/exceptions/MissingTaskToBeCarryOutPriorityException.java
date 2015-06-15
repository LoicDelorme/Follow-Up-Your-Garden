package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the priority attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingTaskToBeCarryOutPriorityException extends Exception
{
	/**
	 * This exception is built when the priority attribute is missing.
	 */
	public MissingTaskToBeCarryOutPriorityException()
	{
		super("The priority is missing!");
	}
}
