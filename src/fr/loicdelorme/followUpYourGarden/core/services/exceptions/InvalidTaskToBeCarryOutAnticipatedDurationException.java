package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the anticipatedDuration value is negative.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class InvalidTaskToBeCarryOutAnticipatedDurationException extends Exception
{
	/**
	 * This exception is built when the anticipated duration is not valid.
	 * 
	 * @param anticipatedDurationValue
	 *            The invalid anticipated duration.
	 */
	public InvalidTaskToBeCarryOutAnticipatedDurationException(double anticipatedDurationValue)
	{
		super(new StringBuilder().append("The anticipated duration (").append(anticipatedDurationValue).append(") is not valid!").toString());
	}
}
