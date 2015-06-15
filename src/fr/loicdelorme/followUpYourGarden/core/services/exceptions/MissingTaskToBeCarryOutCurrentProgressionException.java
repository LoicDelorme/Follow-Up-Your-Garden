package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the currentProgression attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingTaskToBeCarryOutCurrentProgressionException extends Exception
{
	/**
	 * This exception is built when the current progression attribute is missing.
	 */
	public MissingTaskToBeCarryOutCurrentProgressionException()
	{
		super("The current progression is missing!");
	}
}
