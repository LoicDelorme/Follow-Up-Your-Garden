package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

import fr.loicdelorme.followUpYourGarden.core.language.MyResourceBundle;

/**
 * This exception is thrown if the wording value is empty.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class InvalidTypeOfPlantsWordingException extends Exception
{
	/**
	 * This exception is built when the wording is not valid.
	 */
	public InvalidTypeOfPlantsWordingException()
	{
		super(MyResourceBundle.getBundle().getString("invalidTypeOfPlantsWordingException"));
	}
}
