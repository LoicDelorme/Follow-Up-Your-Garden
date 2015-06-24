package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

import fr.loicdelorme.followUpYourGarden.core.language.MyResourceBundle;

/**
 * This exception is thrown if the typesOfPlants attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingGroupOfPlantsTypesOfPlantsException extends Exception
{
	/**
	 * This exception is built when the types of plants attribute is missing.
	 */
	public MissingGroupOfPlantsTypesOfPlantsException()
	{
		super(MyResourceBundle.getBundle().getString("missingGroupOfPlantsTypesOfPlantsException"));
	}
}
