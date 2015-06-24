package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

import fr.loicdelorme.followUpYourGarden.core.language.MyResourceBundle;

/**
 * This exception is thrown if the path attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingGroupOfPlantsPathException extends Exception
{
	/**
	 * This exception is built when the path attribute is missing.
	 */
	public MissingGroupOfPlantsPathException()
	{
		super(MyResourceBundle.getBundle().getString("missingGroupOfPlantsPathException"));
	}
}
