package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the typeOfTasks attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingCarriedOutTaskTypeOfTasksException extends Exception
{
	/**
	 * This exception is built when the type of tasks attribute is missing.
	 */
	public MissingCarriedOutTaskTypeOfTasksException()
	{
		super("The type of tasks is missing!");
	}
}
