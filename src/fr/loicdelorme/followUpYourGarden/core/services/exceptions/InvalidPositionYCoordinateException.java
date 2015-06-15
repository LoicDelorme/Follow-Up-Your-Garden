package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the Y coordinate is negative.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class InvalidPositionYCoordinateException extends Exception
{
	/**
	 * This exception is built when the Y coordinate is not valid.
	 * 
	 * @param yCoordinateValue
	 *            The invalid Y coordinate.
	 */
	public InvalidPositionYCoordinateException(int yCoordinateValue)
	{
		super(new StringBuilder().append("The Y coordinate (").append(yCoordinateValue).append(") is not valid!").toString());
	}
}
