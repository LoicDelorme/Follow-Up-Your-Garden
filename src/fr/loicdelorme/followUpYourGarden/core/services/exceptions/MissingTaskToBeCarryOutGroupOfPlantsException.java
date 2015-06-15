package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the groupOfPlants attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingTaskToBeCarryOutGroupOfPlantsException extends Exception
{
	/**
	 * This exception is built when the group of plants attribute is missing.
	 */
	public MissingTaskToBeCarryOutGroupOfPlantsException()
	{
		super("The group of plants is missing!");
	}
}
