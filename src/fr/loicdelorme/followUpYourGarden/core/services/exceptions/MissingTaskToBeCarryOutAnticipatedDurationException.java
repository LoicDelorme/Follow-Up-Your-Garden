package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

import fr.loicdelorme.followUpYourGarden.core.language.MyResourceBundle;

/**
 * This exception is thrown if the anticipatedDuration attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingTaskToBeCarryOutAnticipatedDurationException extends Exception
{
	/**
	 * This exception is built when the anticipated duration attribute is missing.
	 */
	public MissingTaskToBeCarryOutAnticipatedDurationException()
	{
		super(MyResourceBundle.getBundle().getString("missingTaskToBeCarryOutAnticipatedDurationException"));
	}
}
