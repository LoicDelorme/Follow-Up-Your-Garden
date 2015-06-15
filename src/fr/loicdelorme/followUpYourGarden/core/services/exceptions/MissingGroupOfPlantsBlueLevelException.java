package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the blueLevel attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingGroupOfPlantsBlueLevelException extends Exception
{
	/**
	 * This exception is built when the blue level attribute is missing.
	 */
	public MissingGroupOfPlantsBlueLevelException()
	{
		super("The blue level is missing!");
	}
}
