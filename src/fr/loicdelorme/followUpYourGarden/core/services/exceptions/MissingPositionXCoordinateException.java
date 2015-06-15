package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the X coordinate attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingPositionXCoordinateException extends Exception
{
	/**
	 * This exception is built when the X coordinate attribute is missing.
	 */
	public MissingPositionXCoordinateException()
	{
		super("The X coordinate is missing!");
	}
}
