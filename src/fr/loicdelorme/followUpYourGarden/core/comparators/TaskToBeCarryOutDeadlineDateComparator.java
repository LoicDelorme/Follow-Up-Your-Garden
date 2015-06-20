package fr.loicdelorme.followUpYourGarden.core.comparators;

import java.util.Comparator;

import fr.loicdelorme.followUpYourGarden.core.models.TaskToBeCarryOut;

/**
 * This class allow you to compare deadline date attribute.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class TaskToBeCarryOutDeadlineDateComparator implements Comparator<TaskToBeCarryOut>
{
	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(TaskToBeCarryOut firstTaskToBeCarryOut, TaskToBeCarryOut secondTaskToBeCarryOut)
	{
		if (firstTaskToBeCarryOut.getDeadlineDate().isBefore(secondTaskToBeCarryOut.getDeadlineDate()))
		{
			return -1;
		}

		if (firstTaskToBeCarryOut.getDeadlineDate().isAfter(secondTaskToBeCarryOut.getDeadlineDate()))
		{
			return 1;
		}

		return 0;
	}
}
