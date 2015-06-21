package fr.loicdelorme.followUpYourGarden.core.helpers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import fr.loicdelorme.followUpYourGarden.core.comparators.CarriedOutTaskCarriedOutDateComparator;
import fr.loicdelorme.followUpYourGarden.core.models.CarriedOutTask;
import fr.loicdelorme.followUpYourGarden.core.models.TypeOfTasks;

/**
 * This class allow you to handle carried out tasks.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class CarriedOutTasksHelper
{
	/**
	 * Get last carried out tasks for each type of tasks.
	 * 
	 * @param carriedOutTasks
	 *            The carried out tasks.
	 * 
	 * @return A list of carried out tasks.
	 */
	public static List<CarriedOutTask> getLastCarriedOutTasks(List<CarriedOutTask> carriedOutTasks)
	{
		return getLastCarriedOutTasks(orderByTypeOfTasks(carriedOutTasks));
	}

	/**
	 * Split the list into different collections using a type of tasks for key.
	 * 
	 * @param carriedOutTasks
	 *            The carried out tasks.
	 * 
	 * @return A sorted map of carried out tasks.
	 */
	private static SortedMap<TypeOfTasks, List<CarriedOutTask>> orderByTypeOfTasks(List<CarriedOutTask> carriedOutTasks)
	{
		SortedMap<TypeOfTasks, List<CarriedOutTask>> carriedOutTasks_ = new TreeMap<TypeOfTasks, List<CarriedOutTask>>();

		TypeOfTasks typeOfTasks;
		for (CarriedOutTask currentCarriedOutTask : carriedOutTasks)
		{
			typeOfTasks = currentCarriedOutTask.getTypeOfTasks();

			List<CarriedOutTask> carriedOutTasksTemp = carriedOutTasks_.get(typeOfTasks);
			if (carriedOutTasksTemp == null)
			{
				carriedOutTasksTemp = new ArrayList<CarriedOutTask>();
				carriedOutTasks_.put(typeOfTasks, carriedOutTasksTemp);
			}

			carriedOutTasksTemp.add(currentCarriedOutTask);
		}

		return carriedOutTasks_;
	}

	/**
	 * Get the last carried out task for each type of tasks.
	 * 
	 * @param carriedOutTasks
	 *            The carried out tasks.
	 * @return A list of carried out tasks.
	 */
	private static List<CarriedOutTask> getLastCarriedOutTasks(SortedMap<TypeOfTasks, List<CarriedOutTask>> carriedOutTasks)
	{
		List<CarriedOutTask> carriedOutTasks_ = new ArrayList<CarriedOutTask>();

		Iterator<Entry<TypeOfTasks, List<CarriedOutTask>>> iterator = carriedOutTasks.entrySet().iterator();
		while (iterator.hasNext())
		{
			SortedMap.Entry<TypeOfTasks, List<CarriedOutTask>> data = iterator.next();
			data.getValue().sort(new CarriedOutTaskCarriedOutDateComparator());

			carriedOutTasks_.add(data.getValue().get(0));
		}

		return carriedOutTasks_;
	}
}
