package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the id value is negative.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class InvalidTypeOfTasksIdException extends Exception
{
	/**
	 * This exception is built when the id is not valid.
	 * 
	 * @param idValue
	 *            The invalid id.
	 */
	public InvalidTypeOfTasksIdException(int idValue)
	{
		super(new StringBuilder().append("The id (").append(idValue).append(") is not valid!").toString());
	}
}
