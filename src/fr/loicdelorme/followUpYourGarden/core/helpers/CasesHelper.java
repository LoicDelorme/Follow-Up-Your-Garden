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
	 * @return A list of cases.
	 */
	public static List<Case> generateGlobalRepresentationCases(int width, int height, List<GroupOfPlants> groupsOfPlants)
	{
		List<Position> occupiedPositions = new ArrayList<Position>();
		Map<Integer, GroupOfPlants> groupsOfPlantsMap = new HashMap<Integer, GroupOfPlants>();
		for (GroupOfPlants currentGroupOfPlants : groupsOfPlants)
		{
			occupiedPositions.addAll(currentGroupOfPlants.getPositions());
			groupsOfPlantsMap.put(currentGroupOfPlants.getId(), currentGroupOfPlants);
		}

		List<Case> cases = new ArrayList<Case>();

		GroupOfPlants groupOfPlants;
		for (Position currentOccupiedPosition : occupiedPositions)
		{
			groupOfPlants = groupsOfPlantsMap.get(currentOccupiedPosition.getGroupOfPlantsId());
			if (groupOfPlants.getWording().equals("maison"))
			{
				cases.add(new Case(currentOccupiedPosition, groupOfPlants.getIconColor(), false, true));
			}
			else
			{
				cases.add(new CaseWithGroupOfPlants(currentOccupiedPosition, groupOfPlants.getIconColor(), true, false, groupOfPlants));
			}
		}

		List<Position> availablePositions = PositionsHelper.getAvailablePositions(width, height, occupiedPositions);
		for (Position currentAvailablePosition : availablePositions)
		{
			cases.add(new Case(currentAvailablePosition, Color.WHITE, false, true));
		}

		cases.sort(null);

		return cases;
	}

	/**
	 * Generate cases for adding a group of plants.
	 * 
	 * @param width
	 *            The width.
	 * @param height
	 *            The height.
	 * @param groupsOfPlants
	 *            The list of groups of plants.
	 * @return A list of cases.
	 */
	public static List<Case> generateCasesForAddingGroupOfPlants(int width, int height, List<GroupOfPlants> groupsOfPlants)
	{
		List<Position> occupiedPositions = new ArrayList<Position>();
		Map<Integer, GroupOfPlants> groupsOfPlantsMap = new HashMap<Integer, GroupOfPlants>();
		for (GroupOfPlants currentGroupOfPlants : groupsOfPlants)
		{
			occupiedPositions.addAll(currentGroupOfPlants.getPositions());
			groupsOfPlantsMap.put(currentGroupOfPlants.getId(), currentGroupOfPlants);
		}

		List<Case> cases = new ArrayList<Case>();

		for (Position currentOccupiedPosition : occupiedPositions)
		{
			cases.add(new Case(currentOccupiedPosition, Color.BLACK, false, true));
		}

		List<Position> availablePositions = PositionsHelper.getAvailablePositions(width, height, occupiedPositions);
		for (Position currentAvailablePosition : availablePositions)
		{
			cases.add(new Case(currentAvailablePosition, Color.WHITE, false, false));
		}

		cases.sort(null);

		return cases;
	}

	/**
	 * Generate cases for updating a group of plants.
	 * 
	 * @param width
	 *            The width.
	 * @param height
	 *            The height.
	 * @param groupsOfPlants
	 *            The list of groups of plants.
	 * @param groupOfPlantsToUdpdate
	 *            The group of plants to update.
	 * @return A list of cases.
	 */
	public static List<Case> generateCasesForUpdatingGroupOfPlants(int width, int height, List<GroupOfPlants> groupsOfPlants, GroupOfPlants groupOfPlantsToUdpdate)
	{
		List<Position> occupiedPositions = new ArrayList<Position>();
		List<Position> occupiedPositionsByTheGroupOfPlantsToUdpdate = new ArrayList<Position>();
		List<Position> allOccupiedPositions = new ArrayList<Position>();
		Map<Integer, GroupOfPlants> groupsOfPlantsMap = new HashMap<Integer, GroupOfPlants>();
		for (GroupOfPlants currentGroupOfPlants : groupsOfPlants)
		{
			if (currentGroupOfPlants.equals(groupOfPlantsToUdpdate))
			{
				occupiedPositionsByTheGroupOfPlantsToUdpdate.addAll(currentGroupOfPlants.getPositions());
				allOccupiedPositions.addAll(currentGroupOfPlants.getPositions());
				groupsOfPlantsMap.put(currentGroupOfPlants.getId(), currentGroupOfPlants);
				continue;
			}

			occupiedPositions.addAll(currentGroupOfPlants.getPositions());
			allOccupiedPositions.addAll(currentGroupOfPlants.getPositions());
			groupsOfPlantsMap.put(currentGroupOfPlants.getId(), currentGroupOfPlants);
		}

		List<Case> cases = new ArrayList<Case>();

		for (Position currentOccupiedPosition : occupiedPositions)
		{
			cases.add(new Case(currentOccupiedPosition, Color.BLACK, false, true));
		}

		GroupOfPlants groupOfPlants = groupsOfPlantsMap.get(occupiedPositionsByTheGroupOfPlantsToUdpdate.get(0).getGroupOfPlantsId());
		for (Position currentOccupiedPositionByTheGroupOfPlantsToUdpdate : occupiedPositionsByTheGroupOfPlantsToUdpdate)
		{
			cases.add(new Case(currentOccupiedPositionByTheGroupOfPlantsToUdpdate, groupOfPlants.getIconColor(), true, false));
		}

		List<Position> availablePositions = PositionsHelper.getAvailablePositions(width, height, allOccupiedPositions);
		for (Position currentAvailablePosition : availablePositions)
		{
			cases.add(new Case(currentAvailablePosition, Color.WHITE, false, false));
		}

		cases.sort(null);

		return cases;
	}
}
