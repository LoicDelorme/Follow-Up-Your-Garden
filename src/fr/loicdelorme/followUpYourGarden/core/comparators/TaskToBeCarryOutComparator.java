package fr.loicdelorme.followUpYourGarden.core.comparators;

import java.util.Comparator;

import fr.loicdelorme.followUpYourGarden.core.models.TaskToBeCarryOut;

/**
 * This class allow you to compare tasks to be carry out.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class TaskToBeCarryOutComparator implements Comparator<TaskToBeCarryOut>
{
	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(TaskToBeCarryOut firstTaskToBeCarryOut, TaskToBeCarryOut secondTaskToBeCarryOut)
	{
		return compareTaskToBeCarryOutOnPriorityAttribute(firstTaskToBeCarryOut, secondTaskToBeCarryOut);
	}

	/**
	 * Compare task to be carry out on priority attribute.
	 * 
	 * @param firstTaskToBeCarryOut
	 *            A task to be carry out.
	 * @param secondTaskToBeCarryOut
	 *            A task to be carry out.
	 * @return The result of comparison.
	 */
	private int compareTaskToBeCarryOutOnPriorityAttribute(TaskToBeCarryOut firstTaskToBeCarryOut, TaskToBeCarryOut secondTaskToBeCarryOut)
	{
		if (firstTaskToBeCarryOut.getPriority().getId() == secondTaskToBeCarryOut.getPriority().getId())
		{
			return compareTaskToBeCarryOutOnCurrentProgressionAttribute(firstTaskToBeCarryOut, secondTaskToBeCarryOut);
		}

		if (firstTaskToBeCarryOut.getPriority().getId() > secondTaskToBeCarryOut.getPriority().getId())
		{
			return 1;
		}

		return -1;
	}

	/**
	 * Compare task to be carry out on current progression attribute.
	 * 
	 * @param firstTaskToBeCarryOut
	 *            A task to be carry out.
	 * @param secondTaskToBeCarryOut
	 *            A task to be carry out.
	 * @return The result of comparison.
	 */
	private int compareTaskToBeCarryOutOnCurrentProgressionAttribute(TaskToBeCarryOut firstTaskToBeCarryOut, TaskToBeCarryOut secondTaskToBeCarryOut)
	{
		if (firstTaskToBeCarryOut.getCurrentProgression() == secondTaskToBeCarryOut.getCurrentProgression())
		{
			return compareTaskToBeCarryOutOnAnticipatedDurationAttribute(firstTaskToBeCarryOut, secondTaskToBeCarryOut);
		}

		if (firstTaskToBeCarryOut.getCurrentProgression() > secondTaskToBeCarryOut.getCurrentProgression())
		{
			return -1;
		}

		return 1;
	}

	/**
	 * Compare task to be carry out on anticipated duration attribute.
	 * 
	 * @param firstTaskToBeCarryOut
	 *            A task to be carry out.
	 * @param secondTaskToBeCarryOut
	 *            A task to be carry out.
	 * @return The result of comparison.
	 */
	private int compareTaskToBeCarryOutOnAnticipatedDurationAttribute(TaskToBeCarryOut firstTaskToBeCarryOut, TaskToBeCarryOut secondTaskToBeCarryOut)
	{
		if (firstTaskToBeCarryOut.getAnticipatedDuration() == secondTaskToBeCarryOut.getAnticipatedDuration())
		{
			return compareTaskToBeCarryOutOnPeriodicityAttribute(firstTaskToBeCarryOut, secondTaskToBeCarryOut);
		}

		if (firstTaskToBeCarryOut.getAnticipatedDuration() > secondTaskToBeCarryOut.getAnticipatedDuration())
		{
			return 1;
		}

		return -1;
	}

	/**
	 * Compare task to be carry out on periodicity attribute.
	 * 
	 * @param firstTaskToBeCarryOut
	 *            A task to be carry out.
	 * @param secondTaskToBeCarryOut
	 *            A task to be carry out.
	 * @return The result of comparison.
	 */
	private int compareTaskToBeCarryOutOnPeriodicityAttribute(TaskToBeCarryOut firstTaskToBeCarryOut, TaskToBeCarryOut secondTaskToBeCarryOut)
	{
		if (firstTaskToBeCarryOut.getPeriodicity() == secondTaskToBeCarryOut.getPeriodicity())
		{
			return 0;
		}

		if (firstTaskToBeCarryOut.getPeriodicity() > secondTaskToBeCarryOut.getPeriodicity())
		{
			return -1;
		}

		return 1;
	}
}
