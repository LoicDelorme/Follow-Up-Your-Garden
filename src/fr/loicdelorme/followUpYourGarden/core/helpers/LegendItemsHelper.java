package fr.loicdelorme.followUpYourGarden.core.helpers;

import java.util.ArrayList;
import java.util.List;

import fr.loicdelorme.followUpYourGarden.core.models.GroupOfPlants;
import fr.loicdelorme.followUpYourGarden.core.models.LegendItem;

/**
 * This class allow you to handle legend items.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class LegendItemsHelper
{
	/**
	 * Generate the legend.
	 * 
	 * @param groupsOfPlants
	 *            The list of groups of plants.
	 * 
	 * @return A list of legend items.
	 */
	public static List<LegendItem> generateLegend(List<GroupOfPlants> groupsOfPlants)
	{
		List<LegendItem> legendItems = new ArrayList<LegendItem>();

		for (GroupOfPlants currentGroupOfPlants : groupsOfPlants)
		{
			legendItems.add(new LegendItem(currentGroupOfPlants.getWording(), currentGroupOfPlants.getIconColor()));
		}

		return legendItems;
	}
}
