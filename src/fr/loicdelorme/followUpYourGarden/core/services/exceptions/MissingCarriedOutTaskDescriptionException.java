package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the description attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingCarriedOutTaskDescriptionException extends Exception
{
	/**
	 * This exception is built when the description attribute is missing.
	 */
	public MissingCarriedOutTaskDescriptionException()
	{
		super("The description is missing!");
	}
}
