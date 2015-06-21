package fr.loicdelorme.followUpYourGarden.core.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;
import fr.loicdelorme.followUpYourGarden.core.models.Case;
import fr.loicdelorme.followUpYourGarden.core.models.CaseWithGroupOfPlants;
import fr.loicdelorme.followUpYourGarden.core.models.GroupOfPlants;
import fr.loicdelorme.followUpYourGarden.core.models.Position;

/**
 * This class allow you to handle cases.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class CasesHelper
{
	/**
	 * Generate global representation cases.
	 * 
	 * @param width
	 *            The width.
	 * @param height
	 *            The height.
	 * @param groupsOfPlants
	 *            The list of groups of plants.
	 * @param occupiedPositions
	 *            The list of occupied positions.
	 * @return A list of cases.
	 */
	public static List<Case> generateGlobalRepresentationCases(int width, int height, List<GroupOfPlants> groupsOfPlants, List<Position> occupiedPositions)
	{
		Map<Integer, GroupOfPlants> groupsOfPlantsMap = new HashMap<Integer, GroupOfPlants>();
		for (GroupOfPlants currentGroupOfPlants : groupsOfPlants)
		{
			groupsOfPlantsMap.put(currentGroupOfPlants.getId(), currentGroupOfPlants);
		}

		List<Case> tempCases = new ArrayList<Case>();
		GroupOfPlants groupOfPlants;
		for (Position currentOccupiedPosition : occupiedPositions)
		{
			groupOfPlants = groupsOfPlantsMap.get(currentOccupiedPosition.getGroupOfPlantsId());
			tempCases.add(new CaseWithGroupOfPlants(currentOccupiedPosition, groupOfPlants.getIconColor(), groupOfPlants));
		}

		tempCases.sort(null);

		List<Case> cases = new ArrayList<Case>();
		cases.addAll(tempCases);

		int index = 0;
		Case currentCase = cases.get(index);

		for (int x = 1; x <= width; x++)
		{
			for (int y = 1; y <= height; y++)
			{
				if (!currentCase.getPosition().areCoordinatesEquals(x, y))
				{
					cases.add(new Case(new Position(x, y, GroupOfPlants.UNKNOWN_GROUP_OF_PLANTS_ID), Color.WHITE));
				}
				else
				{
					index++;
					currentCase = cases.get(index);
				}
			}
		}

		cases.sort(null);

		return cases;
	}
}
