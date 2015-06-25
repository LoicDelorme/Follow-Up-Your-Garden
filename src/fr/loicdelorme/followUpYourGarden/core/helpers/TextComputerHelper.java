package fr.loicdelorme.followUpYourGarden.core.helpers;

import java.util.List;

import fr.loicdelorme.followUpYourGarden.core.language.MyResourceBundle;
import fr.loicdelorme.followUpYourGarden.core.models.CarriedOutTask;

/**
 * This class allow you to handle texts.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class TextComputerHelper
{
	/**
	 * Compute carried out tasks text.
	 * 
	 * @param carriedOutTasks
	 *            The list of carried out tasks.
	 * @return The computed text.
	 */
	public static String computeCarriedOutTasksText(List<CarriedOutTask> carriedOutTasks)
	{
		if (carriedOutTasks.size() == 0)
		{
			return MyResourceBundle.getBundle().getString("groupOfPlantsSummaryComplementaryInformationNoHistoric");
		}

		StringBuilder result = new StringBuilder();

		for (CarriedOutTask currentCarriedOutTask : carriedOutTasks)
		{
			result.append(currentCarriedOutTask.getTypeOfTasks().getWording());
			result.append(" : ");
			result.append(currentCarriedOutTask.getCarriedOutDate());
			result.append("\n");
		}

		return result.toString();
	}
}
