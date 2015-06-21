package fr.loicdelorme.followUpYourGarden.core.helpers;

import java.util.ArrayList;
import java.util.List;

import fr.loicdelorme.followUpYourGarden.core.models.GroupOfPlants;
import fr.loicdelorme.followUpYourGarden.core.models.Position;

/**
 * This class allow you to handle positions.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class PositionsHelper
{
	/**
	 * Get all possible positions.
	 * 
	 * @param width
	 *            The width.
	 * @param height
	 *            The height.
	 * 
	 * @return A list of positions.
	 */
	public static List<Position> generateAllPossiblePositions(int width, int height)
	{
		List<Position> positions = new ArrayList<Position>();

		for (int x = 1; x <= width; x++)
		{
			for (int y = 1; y <= height; y++)
			{
				positions.add(new Position(x, y, GroupOfPlants.UNKNOWN_GROUP_OF_PLANTS_ID));
			}
		}

		return positions;
	}
}
