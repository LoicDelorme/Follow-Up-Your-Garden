package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

import fr.loicdelorme.followUpYourGarden.core.language.MyResourceBundle;

/**
 * This exception is thrown if the positions attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingGroupOfPlantsPositionsException extends Exception
{
	/**
	 * This exception is built when the positions attribute is missing.
	 */
	public MissingGroupOfPlantsPositionsException()
	{
		super(MyResourceBundle.getBundle().getString("missingGroupOfPlantsPositionsException"));
	}
}
