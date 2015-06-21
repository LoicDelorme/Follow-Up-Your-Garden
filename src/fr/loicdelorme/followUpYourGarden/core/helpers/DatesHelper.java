package fr.loicdelorme.followUpYourGarden.core.helpers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * This class allow you to handle dates.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class DatesHelper
{
	/**
	 * Convert LocalDate into Date.
	 * 
	 * @param localDate
	 *            The LocalDate to convert.
	 * @return A Date.
	 */
	public static Date convertLocalDateIntoDate(LocalDate localDate)
	{
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * Convert Date into LocalDate.
	 * 
	 * @param date
	 *            The Date to convert.
	 * @return A LocalDate.
	 */
	public static LocalDate convertDateIntoLocalDate(Date date)
	{
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
}
