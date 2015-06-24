package fr.loicdelorme.followUpYourGarden.core.models;

import javafx.scene.paint.Color;

/**
 * This class allow you to create a case with a group of plants.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class CaseWithGroupOfPlants extends Case
{
	/**
	 * The group of plants.
	 */
	private final GroupOfPlants groupOfPlants;

	/**
	 * Create a case with a group of plants.
	 * 
	 * @param position
	 *            The position.
	 * @param iconColor
	 *            The icon color.
	 * @param isActivated
	 *            If it's activated.
	 * @param isDisabled
	 *            If it's disabled.
	 * @param groupOfPlants
	 *            The group of plants.
	 */
	public CaseWithGroupOfPlants(Position position, Color iconColor, boolean isActivated, boolean isDisabled, GroupOfPlants groupOfPlants)
	{
		super(position, iconColor, isActivated, isDisabled);

		this.groupOfPlants = groupOfPlants;
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
	 * @see javafx.scene.control.Labeled#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder representation = new StringBuilder();

		representation.append("{case : {").append(super.toString()).append("}, groupOfPlants : ").append(this.groupOfPlants).append("}");

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

		if (!super.equals(object))
		{
			return false;
		}

		if (!(object instanceof CaseWithGroupOfPlants))
		{
			return false;
		}

		CaseWithGroupOfPlants caseWithGroupOfPlants = (CaseWithGroupOfPlants) object;

		if (!this.groupOfPlants.equals(caseWithGroupOfPlants.groupOfPlants))
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
		return (super.hashCode() + this.groupOfPlants.hashCode());
	}
}
