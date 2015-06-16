package fr.loicdelorme.followUpYourGarden.core.properties;

/**
 * This interface allow you to read dimensions data.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public interface IDimensionsReader
{
	/**
	 * Get the width.
	 * 
	 * @return The width.
	 */
	public int getWidth();

	/**
	 * Get the height.
	 * 
	 * @return The height.
	 */
	public int getHeight();
}
