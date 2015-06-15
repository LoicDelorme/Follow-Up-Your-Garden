package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the green level is not between 0 and 255.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class InvalidGroupOfPlantsGreenLevelException extends Exception
{
	/**
	 * This exception is built when the green level is not valid.
	 * 
	 * @param greenLevelValue
	 *            The invalid green level.
	 */
	public InvalidGroupOfPlantsGreenLevelException(int greenLevelValue)
	{
		super(new StringBuilder().append("The green level (").append(greenLevelValue).append(") is not valid!").toString());
	}
}
