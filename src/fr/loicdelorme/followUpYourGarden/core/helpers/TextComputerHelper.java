package fr.loicdelorme.followUpYourGarden.core.helpers;

import java.util.List;

import fr.loicdelorme.followUpYourGarden.core.language.MyResourceBundle;
import fr.loicdelorme.followUpYourGarden.core.models.CarriedOutTask;
import fr.loicdelorme.followUpYourGarden.core.models.TaskToBeCarryOut;

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
		if (carriedOutTasks.isEmpty())
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

	/**
	 * Compute tasks to be carry out text.
	 * 
	 * @param tasksToBeCarryOut
	 *            The list of tasks to be carry out.
	 * @return The computed text.
	 */
	public static String computeTasksToBeCarryOutText(List<TaskToBeCarryOut> tasksToBeCarryOut)
	{
		if (tasksToBeCarryOut.isEmpty())
		{
			return MyResourceBundle.getBundle().getString("globalRepresentationComplementaryInformationNoTaskToBeCarryOut");
		}

		StringBuilder result = new StringBuilder();

		for (TaskToBeCarryOut currentTaskToBeCarryOut : tasksToBeCarryOut)
		{
			result.append(currentTaskToBeCarryOut.getTypeOfTasks().getWording());
			result.append(" : ");
			result.append(currentTaskToBeCarryOut.getDeadlineDate());
			result.append(" (");
			result.append(currentTaskToBeCarryOut.getCurrentProgression());
			result.append(" %)");
			result.append("\n");
		}

		return result.toString();
	}
}
