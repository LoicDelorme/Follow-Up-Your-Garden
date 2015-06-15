package fr.loicdelorme.followUpYourGarden.core.services.exceptions;

/**
 * This exception is thrown if the groupOfPlantsId is negative.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class InvalidPositionGroupOfPlantsIdException extends Exception
{
	/**
	 * This exception is built when the group of plants id is not valid.
	 * 
	 * @param groupOfPlantsIdValue
	 *            The invalid group of plants id.
	 */
	public InvalidPositionGroupOfPlantsIdException(int groupOfPlantsIdValue)
	{
		super(new StringBuilder().append("The group of plants id (").append(groupOfPlantsIdValue).append(") is not valid!").toString());
	}
}
