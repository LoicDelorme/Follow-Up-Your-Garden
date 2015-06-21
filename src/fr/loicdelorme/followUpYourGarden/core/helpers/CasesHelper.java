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

		List<Case> cases = new ArrayList<Case>();

		GroupOfPlants groupOfPlants;
		for (Position currentOccupiedPosition : occupiedPositions)
		{
			groupOfPlants = groupsOfPlantsMap.get(currentOccupiedPosition.getGroupOfPlantsId());
			cases.add(new CaseWithGroupOfPlants(currentOccupiedPosition, groupOfPlants.getIconColor(), groupOfPlants));
		}

		List<Position> availablePositions = PositionsHelper.getAvailablePositions(width, height, occupiedPositions);
		for (Position currentAvailablePosition : availablePositions)
		{
			cases.add(new Case(currentAvailablePosition, Color.WHITE));
		}

		cases.sort(null);

		return cases;
	}
}
