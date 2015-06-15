package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the X coordinate is negative.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class InvalidPositionXCoordinateException extends Exception
{
	/**
	 * This exception is built when the X coordinate is not valid.
	 * 
	 * @param xCoordinateValue
	 *            The invalid X coordinate.
	 */
	public InvalidPositionXCoordinateException(int xCoordinateValue)
	{
		super(new StringBuilder().append("The X coordinate (").append(xCoordinateValue).append(") is not valid!").toString());
	}
}
