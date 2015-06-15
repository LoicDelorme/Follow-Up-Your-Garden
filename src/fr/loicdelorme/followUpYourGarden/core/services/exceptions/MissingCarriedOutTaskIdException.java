package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the id attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingCarriedOutTaskIdException extends Exception
{
	/**
	 * This exception is built when the id attribute is missing.
	 */
	public MissingCarriedOutTaskIdException()
	{
		super("The id is missing!");
	}
}
