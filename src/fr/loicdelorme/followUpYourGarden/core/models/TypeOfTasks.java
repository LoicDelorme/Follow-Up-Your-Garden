package fr.loicdelorme.followUpYourGarden.core.models;

/**
 * This class allow you to create a type of tasks.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class TypeOfTasks implements Comparable<TypeOfTasks>
{
	/**
	 * The id is unknown.
	 */
	public static final int UNKNOWN_TYPE_OF_TASKS_ID = -1;

	/**
	 * The id.
	 */
	private final int id;

	/**
	 * The wording.
	 */
	private final String wording;

	/**
	 * The description.
	 */
	private final String description;

	/**
	 * Create a type of tasks.
	 * 
	 * @param id
	 *            An id.
	 * @param wording
	 *            A wording.
	 * @param description
	 *            A description.
	 */
	public TypeOfTasks(int id, String wording, String description)
	{
		this.id = id;
		this.wording = wording;
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
	 * Get the wording.
	 * 
	 * @return The wording.
	 */
	public String getWording()
	{
		return this.wording;
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
		return this.wording;
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

		if (!(object instanceof TypeOfTasks))
		{
			return false;
		}

		TypeOfTasks typeOfTasks = (TypeOfTasks) object;

		if (this.id != typeOfTasks.id)
		{
			return false;
		}

		if (!this.wording.equals(typeOfTasks.wording))
		{
			return false;
		}

		if (!this.description.equals(typeOfTasks.description))
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
		return (this.id + this.wording.hashCode() + this.description.hashCode());
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(TypeOfTasks typeOfTasks)
	{
		return (this.id - typeOfTasks.id);
	}
}
