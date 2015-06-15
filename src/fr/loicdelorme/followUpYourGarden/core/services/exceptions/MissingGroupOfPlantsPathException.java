package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the path attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingGroupOfPlantsPathException extends Exception
{
	/**
	 * This exception is built when the path attribute is missing.
	 */
	public MissingGroupOfPlantsPathException()
	{
		super("The path is missing!");
	}
}
