package fr.loicdelorme.followUpYourGarden.core.models;

import java.time.LocalDate;
import java.util.List;

import javafx.scene.paint.Color;

/**
 * This class allow you to create a group of plants.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class GroupOfPlants implements Comparable<GroupOfPlants>
{
	/**
	 * The id is unknown.
	 */
	public static final int UNKNOWN_GROUP_OF_PLANTS_ID = -1;

	/**
	 * The id.
	 */
	private final int id;

	/**
	 * The wording.
	 */
	private final String wording;

	/**
	 * The planting date.
	 */
	private final LocalDate plantingDate;

	/**
	 * The image path.
	 */
	private final String path;

	/**
	 * The icon color.
	 */
	private final Color iconColor;

	/**
	 * The list of types of plants.
	 */
	private final List<TypeOfPlants> typesOfPlants;

	/**
	 * The list of positions.
	 */
	private final List<Position> positions;

	/**
	 * Create a group of plants.
	 * 
	 * @param id
	 *            An id.
	 * @param wording
	 *            A wording.
	 * @param plantingDate
	 *            A planting date.
	 * @param path
	 *            An image path.
	 * @param iconColor
	 *            An icon color.
	 * @param typesOfPlants
	 *            A list of types of plants.
	 * @param positions
	 *            A list of positions.
	 */
	public GroupOfPlants(int id, String wording, LocalDate plantingDate, String path, Color iconColor, List<TypeOfPlants> typesOfPlants, List<Position> positions)
	{
		this.id = id;
		this.wording = wording;
		this.plantingDate = plantingDate;
		this.path = path;
		this.iconColor = iconColor;
		this.typesOfPlants = typesOfPlants;
		this.positions = positions;

		this.typesOfPlants.sort(null);
		this.positions.sort(null);
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
	 * Get the planting date.
	 * 
	 * @return The planting date.
	 */
	public LocalDate getPlantingDate()
	{
		return this.plantingDate;
	}

	/**
	 * Get the image path.
	 * 
	 * @return The image path.
	 */
	public String getPath()
	{
		return this.path;
	}

	/**
	 * Get the icon color.
	 * 
	 * @return The icon color.
	 */
	public Color getIconColor()
	{
		return this.iconColor;
	}

	/**
	 * Get the list of types of plants.
	 * 
	 * @return The list of types of plants.
	 */
	public List<TypeOfPlants> getTypesOfPlants()
	{
		return this.typesOfPlants;
	}

	/**
	 * Get the list of positions.
	 * 
	 * @return The list of positions.
	 */
	public List<Position> getPositions()
	{
		return this.positions;
	}

	@Override
	public String toString()
	{
		StringBuilder representation = new StringBuilder();

		representation.append("{id : ").append(this.id).append(", wording : ").append(this.wording).append(", plantingDate : ").append(this.plantingDate).append(", path : ").append(this.path).append(", iconColor : ").append(this.iconColor).append(", typesOfPlants : ").append(this.typesOfPlants).append(", positions : ").append(this.positions).append("}");

		return representation.toString();
	}

	@Override
	public boolean equals(Object object)
	{
		if (this == object)
			return true;

		if (object == null)
			return false;

		if (!(object instanceof GroupOfPlants))
			return false;

		GroupOfPlants groupOfPlants = (GroupOfPlants) object;

		if (this.id != groupOfPlants.id)
			return false;

		if (!this.wording.equals(groupOfPlants.wording))
			return false;

		if (!this.plantingDate.equals(groupOfPlants.plantingDate))
			return false;

		if (!this.path.equals(groupOfPlants.path))
			return false;

		if (!this.iconColor.equals(groupOfPlants.iconColor))
			return false;

		if (this.typesOfPlants.size() != groupOfPlants.typesOfPlants.size())
			return false;

		if (this.positions.size() != groupOfPlants.positions.size())
			return false;

		if (!this.typesOfPlants.equals(groupOfPlants.typesOfPlants))
			return false;

		if (!this.positions.equals(groupOfPlants.positions))
			return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		int resultat = 0;

		resultat += this.id;
		resultat += this.wording.hashCode();
		resultat += this.plantingDate.hashCode();
		resultat += this.path.hashCode();
		resultat += this.iconColor.hashCode();
		resultat += this.typesOfPlants.hashCode();
		resultat += this.positions.hashCode();

		return resultat;
	}

	@Override
	public int compareTo(GroupOfPlants groupOfPlants)
	{
		return (this.id - groupOfPlants.id);
	}
}