package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

import fr.loicdelorme.followUpYourGarden.core.language.MyResourceBundle;

/**
 * This exception is thrown if the currentProgression attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingTaskToBeCarryOutCurrentProgressionException extends Exception
{
	/**
	 * This exception is built when the current progression attribute is missing.
	 */
	public MissingTaskToBeCarryOutCurrentProgressionException()
	{
		super(MyResourceBundle.getBundle().getString("missingTaskToBeCarryOutCurrentProgressionException"));
	}
}
