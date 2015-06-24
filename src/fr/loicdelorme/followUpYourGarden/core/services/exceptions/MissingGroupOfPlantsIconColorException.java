package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

import fr.loicdelorme.followUpYourGarden.core.language.MyResourceBundle;

/**
 * This exception is thrown if the iconColor attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingGroupOfPlantsIconColorException extends Exception
{
	/**
	 * This exception is built when the icon color attribute is missing.
	 */
	public MissingGroupOfPlantsIconColorException()
	{
		super(MyResourceBundle.getBundle().getString("missingGroupOfPlantsIconColorException"));
	}
}
