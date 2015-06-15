package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the groupOfPlantsId attribute is missing.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class MissingPositionGroupOfPlantsIdException extends Exception
{
	/**
	 * This exception is built when the group of plants id attribute is missing.
	 */
	public MissingPositionGroupOfPlantsIdException()
	{
		super("The group of plants id is missing!");
	}
}
