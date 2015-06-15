package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the currentProgression value is negative or higher than one hundred.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class InvalidTaskToBeCarryOutCurrentProgressionException extends Exception
{
	/**
	 * This exception is built when the current progression is not valid.
	 * 
	 * @param currentProgressionValue
	 *            The invalid current progression.
	 */
	public InvalidTaskToBeCarryOutCurrentProgressionException(int currentProgressionValue)
	{
		super(new StringBuilder().append("The current progression (").append(currentProgressionValue).append(") is not valid!").toString());
	}
}
