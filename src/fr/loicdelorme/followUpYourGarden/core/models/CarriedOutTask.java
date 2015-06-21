package fr.loicdelorme.followUpYourGarden.core.models;

import java.time.LocalDate;

/**
 * This class allow you to create a carried out task.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class CarriedOutTask implements Comparable<CarriedOutTask>
{
	/**
	 * The id is unknown.
	 */
	public static final int UNKNOWN_CARRIED_OUT_TASK_ID = -1;

	/**
	 * The id.
	 */
	private final int id;

	/**
	 * The group of plants.
	 */
	private final GroupOfPlants groupOfPlants;

	/**
	 * The type of tasks.
	 */
	private final TypeOfTasks typeOfTasks;

	/**
	 * The carried out task date.
	 */
	private final LocalDate carriedOutDate;

	/**
	 * The deadline date fixed by the user.
	 */
	private final LocalDate deadlineDate;

	/**
	 * The description.
	 */
	private final String description;

	/**
	 * Create a carried out task.
	 * 
	 * @param id
	 *            An id.
	 * @param groupOfPlants
	 *            A wording.
	 * @param typeOfTasks
	 *            A type of tasks.
	 * @param carriedOutDate
	 *            A carried out task date.
	 * @param deadlineDate
	 *            A deadline fixed by the user.
	 * @param description
	 *            A description.
	 */
	public CarriedOutTask(int id, GroupOfPlants groupOfPlants, TypeOfTasks typeOfTasks, LocalDate carriedOutDate, LocalDate deadlineDate, String description)
	{
		this.id = id;
		this.groupOfPlants = groupOfPlants;
		this.typeOfTasks = typeOfTasks;
		this.carriedOutDate = carriedOutDate;
		this.deadlineDate = deadlineDate;
		this.description = description;
	}

	/**
	 * Get the id.
	 * 
	 * @return The id.
	 */
	public int getId()
	{
		return this.id;
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
	 * Get the carried out task date.
	 * 
	 * @return The carried out task date.
	 */
	public LocalDate getCarriedOutDate()
	{
		return this.carriedOutDate;
	}

	/**
	 * Get the deadline date fixed by the user.
	 * 
	 * @return The deadline date fixed by the user.
	 */
	public LocalDate getDeadlineDate()
	{
		return this.deadlineDate;
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder representation = new StringBuilder();

		representation.append("{id : ").append(this.id).append(", groupOfPlants : ").append(this.groupOfPlants).append(", typeOfTasks : ").append(this.typeOfTasks).append(", carriedOutDate : ").append(this.carriedOutDate).append(", deadlineDate : ").append(this.deadlineDate).append(", description : ").append(this.description).append("}");

		return representation.toString();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}

		if (object == null)
		{
			return false;
		}

		if (!(object instanceof CarriedOutTask))
		{
			return false;
		}

		CarriedOutTask carriedOutTask = (CarriedOutTask) object;

		if (this.id != carriedOutTask.id)
		{
			return false;
		}

		if (!this.groupOfPlants.equals(carriedOutTask.groupOfPlants))
		{
			return false;
		}

		if (!this.typeOfTasks.equals(carriedOutTask.typeOfTasks))
		{
			return false;
		}

		if (!this.carriedOutDate.equals(carriedOutTask.carriedOutDate))
		{
			return false;
		}

		if (!this.deadlineDate.equals(carriedOutTask.deadlineDate))
		{
			return false;
		}

		if (!this.description.equals(carriedOutTask.description))
		{
			return false;
		}

		return true;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		int resultat = 0;

		resultat += this.id;
		resultat += this.groupOfPlants.hashCode();
		resultat += this.typeOfTasks.hashCode();
		resultat += this.carriedOutDate.hashCode();
		resultat += this.deadlineDate.hashCode();
		resultat += this.description.hashCode();

		return resultat;
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(CarriedOutTask carriedOutTask)
	{
		return (this.id - carriedOutTask.id);
	}
}
