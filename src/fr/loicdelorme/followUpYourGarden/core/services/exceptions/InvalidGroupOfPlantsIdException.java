package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

import fr.loicdelorme.followUpYourGarden.core.language.MyResourceBundle;

/**
 * This exception is thrown if the id is negative.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class InvalidGroupOfPlantsIdException extends Exception
{
	/**
	 * This exception is built when the id is not valid.
	 * 
	 * @param idValue
	 *            The invalid id.
	 */
	public InvalidGroupOfPlantsIdException(int idValue)
	{
		super(String.format(MyResourceBundle.getBundle().getString("invalidGroupOfPlantsIdException"), idValue));
	}
}
