package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

import fr.loicdelorme.followUpYourGarden.core.language.MyResourceBundle;

/**
 * This exception is thrown if the path is empty.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class InvalidGroupOfPlantsPathException extends Exception
{
	/**
	 * This exception is built when the path is not valid.
	 */
	public InvalidGroupOfPlantsPathException()
	{
		super(MyResourceBundle.getBundle().getString("invalidGroupOfPlantsPathException"));
	}
}
