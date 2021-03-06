package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

import fr.loicdelorme.followUpYourGarden.core.language.MyResourceBundle;

/**
 * This exception is thrown if the set of positions is empty.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class InvalidGroupOfPlantsPositionsException extends Exception
{
	/**
	 * This exception is built when the set of positions is not valid.
	 */
	public InvalidGroupOfPlantsPositionsException()
	{
		super(MyResourceBundle.getBundle().getString("invalidGroupOfPlantsPositionsException"));
	}
}
