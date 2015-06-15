package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the path is empty.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class InvalidGroupOfPlantsPathException extends Exception
{
	/**
	 * This exception is built when the path is not valid.
	 */
	public InvalidGroupOfPlantsPathException()
	{
		super("The path is empty!");
	}
}
