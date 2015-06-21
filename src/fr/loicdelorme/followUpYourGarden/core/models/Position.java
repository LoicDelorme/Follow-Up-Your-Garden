package fr.loicdelorme.followUpYourGarden.core.models;

/**
 * This class allow you to create a two dimensional position.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class Position implements Comparable<Position>
{
	/**
	 * The X coordinate.
	 */
	private final int x;

	/**
	 * The Y coordinate.
	 */
	private final int y;

	/**
	 * The group of plants id.
	 */
	private final int groupOfPlantsId;

	/**
	 * Create a two dimensional position.
	 * 
	 * @param x
	 *            A X coordinate.
	 * @param y
	 *            A Y coordinate.
	 * @param groupOfPlantsId
	 *            A group of plants id.
	 */
	public Position(int x, int y, int groupOfPlantsId)
	{
		this.x = x;
		this.y = y;
		this.groupOfPlantsId = groupOfPlantsId;
	}

	/**
	 * Test if two positions have same coordinates.
	 * 
	 * @param x
	 *            The X coordinate.
	 * @param y
	 *            The Y coordinate.
	 * 
	 * @return True if they have the same coordinates, else False.
	 */
	public boolean areCoordinatesEquals(int x, int y)
	{
		return ((this.x == x) && (this.y == y));
	}

	/**
	 * Get the X coordinate.
	 * 
	 * @return The X coordinate.
	 */
	public int getX()
	{
		return this.x;
	}

	/**
	 * Get the Y coordinate.
	 * 
	 * @return The Y coordinate.
	 */
	public int getY()
	{
		return this.y;
	}

	/**
	 * Get the group of plants id.
	 * 
	 * @return The group of plants id.
	 */
	public int getGroupOfPlantsId()
	{
		return this.groupOfPlantsId;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder representation = new StringBuilder();

		representation.append("{X : ").append(this.x).append(", Y : ").append(this.y).append(", groupOfPlantsId : ").append(this.groupOfPlantsId).append("}");

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

		if (!(object instanceof Position))
		{
			return false;
		}

		Position position = (Position) object;

		if (this.x != position.x)
		{
			return false;
		}

		if (this.y != position.y)
		{
			return false;
		}

		if (this.groupOfPlantsId != position.groupOfPlantsId)
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
		return (this.x + this.y + this.groupOfPlantsId);
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Position position)
	{
		int result = this.x - position.x;

		if (result != 0)
		{
			return result;
		}

		result = this.y - position.y;

		return result;
	}
}
