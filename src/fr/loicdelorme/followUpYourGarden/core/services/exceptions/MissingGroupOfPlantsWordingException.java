package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the wording attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingGroupOfPlantsWordingException extends Exception
{
	/**
	 * This exception is built when the wording attribute is missing.
	 */
	public MissingGroupOfPlantsWordingException()
	{
		super("The wording is missing!");
	}
}
