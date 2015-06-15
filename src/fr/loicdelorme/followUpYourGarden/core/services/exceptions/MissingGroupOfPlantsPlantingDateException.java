package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the plantingDate attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingGroupOfPlantsPlantingDateException extends Exception
{
	/**
	 * This exception is built when the planting date attribute is missing.
	 */
	public MissingGroupOfPlantsPlantingDateException()
	{
		super("The planting date is missing!");
	}
}
