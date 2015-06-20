package fr.loicdelorme.followUpYourGarden.core.comparators;

import java.util.Comparator;

import fr.loicdelorme.followUpYourGarden.core.models.CarriedOutTask;

/**
 * This class allow you to compare carried out date attribute.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class CarriedOutTaskCarriedOutDateComparator implements Comparator<CarriedOutTask>
{
	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(CarriedOutTask firstCarriedOutTask, CarriedOutTask secondCarriedOutTask)
	{
		if (firstCarriedOutTask.getCarriedOutDate().isBefore(secondCarriedOutTask.getCarriedOutDate()))
		{
			return 1;
		}

		if (firstCarriedOutTask.getCarriedOutDate().isAfter(secondCarriedOutTask.getCarriedOutDate()))
		{
			return -1;
		}

		return 0;
	}
}
