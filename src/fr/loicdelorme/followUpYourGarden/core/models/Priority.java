package fr.loicdelorme.followUpYourGarden.core.models;

/**
 * This class allow you to handle several priorities.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public enum Priority
{
	/**
	 * The priority is high.
	 */
	HIGH(1, "high", "./data/images/priorities/high.png"),

	/**
	 * The priority is medium.
	 */
	MEDIUM(2, "medium", "./data/images/priorities/medium.png"),

	/**
	 * The priority is low.
	 */
	LOW(3, "low", "./data/images/priorities/low.png");

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
		StringBuilder representation = new StringBuilder();

		representation.append("{id : ").append(this.id).append(", wording : ").append(this.wording).append(", path : ").append(this.path).append("}");

		return representation.toString();
	}
}
