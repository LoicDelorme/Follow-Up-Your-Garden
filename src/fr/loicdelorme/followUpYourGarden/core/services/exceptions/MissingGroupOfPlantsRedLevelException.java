package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the redLevel attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingGroupOfPlantsRedLevelException extends Exception
{
	/**
	 * This exception is built when the red level attribute is missing.
	 */
	public MissingGroupOfPlantsRedLevelException()
	{
		super("The red level is missing!");
	}
}
