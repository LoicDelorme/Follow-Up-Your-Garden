package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

import fr.loicdelorme.followUpYourGarden.core.language.MyResourceBundle;

/**
 * This exception is thrown if the groupOfPlants attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingTaskToBeCarryOutGroupOfPlantsException extends Exception
{
	/**
	 * This exception is built when the group of plants attribute is missing.
	 */
	public MissingTaskToBeCarryOutGroupOfPlantsException()
	{
		super(MyResourceBundle.getBundle().getString("missingTaskToBeCarryOutGroupOfPlantsException"));
	}
}
