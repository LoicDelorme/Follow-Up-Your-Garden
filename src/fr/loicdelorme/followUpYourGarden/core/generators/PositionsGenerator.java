package fr.loicdelorme.followUpYourGarden.core.generators;

import java.util.ArrayList;
import java.util.List;

import fr.loicdelorme.followUpYourGarden.core.models.GroupOfPlants;
import fr.loicdelorme.followUpYourGarden.core.models.Position;

/**
 * This class allow you to generate positions.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class PositionsGenerator
{
	/**
	 * The width.
	 */
	private int width;

	/**
	 * The height.
	 */
	private int height;

	/**
	 * Create a positions generator.
	 * 
	 * @param width
	 *            The width.
	 * @param height
	 *            The height.
	 */
	public PositionsGenerator(int width, int height)
	{
		this.width = width;
		this.height = height;
	}

	/**
	 * Get all possible positions.
	 * 
	 * @return A list of positions.
	 */
	public List<Position> getAllPossiblePositions()
	{
		List<Position> positions = new ArrayList<Position>();

		for (int x = 1; x <= this.width; x++)
		{
			for (int y = 1; y <= this.height; y++)
			{
				positions.add(new Position(x, y, GroupOfPlants.UNKNOWN_GROUP_OF_PLANTS_ID));
			}
		}

		return positions;
	}
}
