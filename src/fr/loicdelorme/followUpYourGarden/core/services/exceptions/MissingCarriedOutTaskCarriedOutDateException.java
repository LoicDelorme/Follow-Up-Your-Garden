package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

import fr.loicdelorme.followUpYourGarden.core.language.MyResourceBundle;

/**
 * This exception is thrown if the carriedOutDate attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingCarriedOutTaskCarriedOutDateException extends Exception
{
	/**
	 * This exception is built when the carried out date attribute is missing.
	 */
	public MissingCarriedOutTaskCarriedOutDateException()
	{
		super(MyResourceBundle.getBundle().getString("missingCarriedOutTaskCarriedOutDateException"));
	}
}
