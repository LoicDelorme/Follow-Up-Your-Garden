package fr.loicdelorme.followUpYourGarden.core.models;

import fr.loicdelorme.followUpYourGarden.core.language.MyResourceBundle;

/**
 * This enumeration allow you to handle several priorities.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public enum Priority
{
	/**
	 * The priority is high.
	 */
	HIGH(1, MyResourceBundle.getBundle().getString("highPriority"), "fr/loicdelorme/followUpYourGarden/views/images/priorities/high.png"),

	/**
	 * The priority is medium.
	 */
	MEDIUM(2, MyResourceBundle.getBundle().getString("mediumPriority"), "fr/loicdelorme/followUpYourGarden/views/images/priorities/medium.png"),

	/**
	 * The priority is low.
	 */
	LOW(3, MyResourceBundle.getBundle().getString("lowPriority"), "fr/loicdelorme/followUpYourGarden/views/images/priorities/low.png");

	/**
	 * The id.
	 */
	private final int id;

	/**
	 * The wording.
	 */
	private final String wording;

	/**
	 * The image path.
	 */
	private final String path;

	/**
	 * Private constructor.
	 * 
	 * @param id
	 *            An id.
	 * @param wording
	 *            A wording.
	 * @param path
	 *            An image path.
	 */
	private Priority(int id, String wording, String path)
	{
		this.id = id;
		this.wording = wording;
		this.path = path;
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
	 * Get the image path.
	 * 
	 * @return The image path.
	 */
	public String getPath()
	{
		return this.path;
	}

	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString()
	{
		return this.wording;
	}
}
