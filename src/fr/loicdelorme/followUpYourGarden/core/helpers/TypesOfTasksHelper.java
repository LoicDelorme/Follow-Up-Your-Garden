package fr.loicdelorme.followUpYourGarden.core.helpers;

import java.util.ArrayList;
import java.util.List;

import fr.loicdelorme.followUpYourGarden.core.models.TaskToBeCarryOut;
import fr.loicdelorme.followUpYourGarden.core.models.TypeOfTasks;

/**
 * This class allow you to handle types of tasks.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class TypesOfTasksHelper
{
	/**
	 * Get all available types of tasks.
	 * 
	 * @param typesOfTasks
	 *            The type of tasks.
	 * @param tasksToBeCarryOut
	 *            The tasks to be carry out.
	 * 
	 * @return A list of available types of tasks.
	 */
	public static List<TypeOfTasks> getAvailableTypesOfTasks(List<TypeOfTasks> typesOfTasks, List<TaskToBeCarryOut> tasksToBeCarryOut)
	{
		List<TypeOfTasks> availableTypesOfTasks = new ArrayList<TypeOfTasks>();
		availableTypesOfTasks.addAll(typesOfTasks);
		availableTypesOfTasks.removeAll(getAlreadyProgrammedTypesOfTasks(tasksToBeCarryOut));
		availableTypesOfTasks.sort(null);

		return availableTypesOfTasks;
	}

	/**
	 * Get all already programmed types of tasks.
	 * 
	 * @param tasksToBeCarryOut
	 *            The tasks to be carry out.
	 * 
	 * @return A list of types of tasks.
	 */
	private static List<TypeOfTasks> getAlreadyProgrammedTypesOfTasks(List<TaskToBeCarryOut> tasksToBeCarryOut)
	{
		List<TypeOfTasks> typesOfTasks = new ArrayList<TypeOfTasks>();
		for (TaskToBeCarryOut currentTaskToBeCarryOut : tasksToBeCarryOut)
		{
			typesOfTasks.add(currentTaskToBeCarryOut.getTypeOfTasks());
		}

		return typesOfTasks;
	}
}
