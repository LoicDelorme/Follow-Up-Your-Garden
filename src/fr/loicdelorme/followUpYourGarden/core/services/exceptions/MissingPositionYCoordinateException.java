package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the Y coordinate attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingPositionYCoordinateException extends Exception
{
	/**
	 * This exception is built when the Y coordinate attribute is missing.
	 */
	public MissingPositionYCoordinateException()
	{
		super("The Y coordinate is missing!");
	}
}
