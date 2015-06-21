package fr.loicdelorme.followUpYourGarden.core.models;

/**
 * This class allow you to create a type of plants.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class TypeOfPlants implements Comparable<TypeOfPlants>
{
	/**
	 * The id is unknown.
	 */
	public static final int UNKNOWN_TYPE_OF_PLANTS_ID = -1;

	/**
	 * The id.
	 */
	private final int id;

	/**
	 * The wording.
	 */
	private final String wording;

	/**
	 * Create a type of plants.
	 * 
	 * @param id
	 *            An id.
	 * @param wording
	 *            A wording.
	 */
	public TypeOfPlants(int id, String wording)
	{
		this.id = id;
		this.wording = wording;
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

	@Override
	public String toString()
	{
		StringBuilder representation = new StringBuilder();

		representation.append("{id : ").append(this.id).append(", wording : ").append(this.wording).append("}");

		return representation.toString();
	}

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

		if (!(object instanceof TypeOfPlants))
		{
			return false;
		}

		TypeOfPlants typeOfPlants = (TypeOfPlants) object;

		if (this.id != typeOfPlants.id)
		{
			return false;
		}

		if (!this.wording.equals(typeOfPlants.wording))
		{
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		return (this.id + this.wording.hashCode());
	}

	@Override
	public int compareTo(TypeOfPlants typeOfPlants)
	{
		return (this.id - typeOfPlants.id);
	}
}
