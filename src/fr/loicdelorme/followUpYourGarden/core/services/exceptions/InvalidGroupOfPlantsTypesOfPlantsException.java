package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the set of types of plants is empty.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class InvalidGroupOfPlantsTypesOfPlantsException extends Exception
{
	/**
	 * This exception is built when the set of types of plants is not valid.
	 */
	public InvalidGroupOfPlantsTypesOfPlantsException()
	{
		super("The set of types of plants is empty!");
	}
}
