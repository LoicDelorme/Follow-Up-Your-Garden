package fr.loicdelorme.followUpYourGarden.core.models;

import java.time.LocalDate;

/**
 * This class allow you to create a task to be carry out.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class TaskToBeCarryOut implements Comparable<TaskToBeCarryOut>
{
	/**
	 * The group of plants.
	 */
	private final GroupOfPlants groupOfPlants;

	/**
	 * The type of tasks.
	 */
	private final TypeOfTasks typeOfTasks;

	/**
	 * The deadline date.
	 */
	private final LocalDate deadlineDate;

	/**
	 * The priority.
	 */
	private final Priority priority;

	/**
	 * The description.
	 */
	private final String description;

	/**
	 * The current progression.
	 */
	private final int currentProgression;

	/**
	 * The anticipated duration.
	 */
	private final double anticipatedDuration;

	/**
	 * If it's recurrent.
	 */
	private final boolean isRecurrent;

	/**
	 * The periodicity.
	 */
	private final int periodicity;

	/**
	 * Create a task to be carry out.
	 * 
	 * @param groupOfPlants
	 *            A group of plants.
	 * @param typeOfTasks
	 *            A type of tasks.
	 * @param deadline
	 *            A deadline date.
	 * @param priority
	 *            A priority.
	 * @param description
	 *            A description.
	 * @param currentProgression
	 *            A current progression.
	 * @param anticipatedDuration
	 *            A anticipated duration.
	 * @param isRecurrent
	 *            If it's recurrent.
	 * @param periodicity
	 *            A periodicity.
	 */
	public TaskToBeCarryOut(GroupOfPlants groupOfPlants, TypeOfTasks typeOfTasks, LocalDate deadline, Priority priority, String description, int currentProgression, double anticipatedDuration, boolean isRecurrent, int periodicity)
	{
		this.groupOfPlants = groupOfPlants;
		this.typeOfTasks = typeOfTasks;
		this.deadlineDate = deadline;
		this.priority = priority;
		this.description = description;
		this.currentProgression = currentProgression;
		this.anticipatedDuration = anticipatedDuration;
		this.isRecurrent = isRecurrent;
		this.periodicity = periodicity;
	}

	/**
	 * Get the group of plants.
	 * 
	 * @return The group of plants.
	 */
	public GroupOfPlants getGroupOfPlants()
	{
		return this.groupOfPlants;
	}

	/**
	 * Get the type of tasks.
	 * 
	 * @return The type of tasks.
	 */
	public TypeOfTasks getTypeOfTasks()
	{
		return this.typeOfTasks;
	}

	/**
	 * Get the deadline date.
	 * 
	 * @return The deadline date.
	 */
	public LocalDate getDeadlineDate()
	{
		return this.deadlineDate;
	}

	/**
	 * Get the priority.
	 * 
	 * @return The priority.
	 */
	public Priority getPriority()
	{
		return this.priority;
	}

	/**
	 * Get the description.
	 * 
	 * @return The description.
	 */
	public String getDescription()
	{
		return this.description;
	}

	/**
	 * Get the current progression.
	 * 
	 * @return The current progression.
	 */
	public int getCurrentProgression()
	{
		return this.currentProgression;
	}

	/**
	 * Get the anticipated duration.
	 * 
	 * @return The anticipated duration.
	 */
	public double getAnticipatedDuration()
	{
		return this.anticipatedDuration;
	}

	/**
	 * Get if it's recurrent.
	 * 
	 * @return If it's recurrent.
	 */
	public boolean getIsRecurrent()
	{
		return this.isRecurrent;
	}

	/**
	 * Get the periodicity.
	 * 
	 * @return The periodicity.
	 */
	public int getPeriodicity()
	{
		return this.periodicity;
	}

	@Override
	public String toString()
	{
		StringBuilder representation = new StringBuilder();

		representation.append("{groupOfPlants : ").append(this.groupOfPlants).append(", typeOfTasks : ").append(this.typeOfTasks).append(", deadline : ").append(this.deadlineDate).append(", priority : ").append(this.priority).append(", description : ").append(this.description).append(", currentProgression : ").append(this.currentProgression).append(", anticipatedDuration : ").append(this.anticipatedDuration).append(", isRecurrent : ").append(this.isRecurrent).append(", periodicity : ").append(this.periodicity).append("}");

		return representation.toString();
	}

	@Override
	public boolean equals(Object object)
	{
		if (this == object)
			return true;

		if (object == null)
			return false;

		if (!(object instanceof TaskToBeCarryOut))
			return false;

		TaskToBeCarryOut taskToBeCarryOut = (TaskToBeCarryOut) object;

		if (!this.groupOfPlants.equals(taskToBeCarryOut.groupOfPlants))
			return false;

		if (!this.typeOfTasks.equals(taskToBeCarryOut.typeOfTasks))
			return false;

		if (!this.deadlineDate.equals(taskToBeCarryOut.deadlineDate))
			return false;

		if (this.priority != taskToBeCarryOut.priority)
			return false;

		if (!this.description.equals(taskToBeCarryOut.description))
			return false;

		if (this.currentProgression != taskToBeCarryOut.currentProgression)
			return false;

		if (this.anticipatedDuration != taskToBeCarryOut.anticipatedDuration)
			return false;

		if (this.isRecurrent != taskToBeCarryOut.isRecurrent)
			return false;

		if (this.periodicity != taskToBeCarryOut.periodicity)
			return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		int resultat = 0;

		resultat += this.groupOfPlants.hashCode();
		resultat += this.typeOfTasks.hashCode();
		resultat += this.deadlineDate.hashCode();
		resultat += this.priority.hashCode();
		resultat += this.description.hashCode();
		resultat += this.currentProgression;
		resultat += this.anticipatedDuration;
		resultat += (this.isRecurrent ? 1 : 0);
		resultat += this.periodicity;

		return resultat;
	}

	@Override
	public int compareTo(TaskToBeCarryOut taskToBeCarryOut)
	{
		return ((this.groupOfPlants.getId() - taskToBeCarryOut.groupOfPlants.getId()) + (this.typeOfTasks.getId() - taskToBeCarryOut.typeOfTasks.getId()));
	}
}
