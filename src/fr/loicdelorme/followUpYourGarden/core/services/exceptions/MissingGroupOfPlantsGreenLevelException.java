package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the greenLevel attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingGroupOfPlantsGreenLevelException extends Exception
{
	/**
	 * This exception is built when the green level attribute is missing.
	 */
	public MissingGroupOfPlantsGreenLevelException()
	{
		super("The green level is missing!");
	}
}
