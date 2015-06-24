package fr.loicdelorme.followUpYourGarden.core.models;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
 * This class allow you to create a case.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class Case extends Button implements Comparable<Case>
{
	/**
	 * The position.
	 */
	private final Position position;

	/**
	 * The icon color.
	 */
	private final Color iconColor;

	/**
	 * If it's activated.
	 */
	private boolean isActivated;

	/**
	 * If it's disabled.
	 */
	private boolean isDisabled;

	/**
	 * Create a case.
	 * 
	 * @param position
	 *            The position.
	 * @param iconColor
	 *            The icon color.
	 * @param isActivated
	 *            If it's activated.
	 * @param isDisabled
	 *            If it's disabled.
	 */
	public Case(Position position, Color iconColor, boolean isActivated, boolean isDisabled)
	{
		this.position = position;
		this.iconColor = iconColor;
		this.isActivated = isActivated;
		this.isDisabled = isDisabled;
	}

	/**
	 * Get the position.
	 * 
	 * @return The position.
	 */
	public Position getPosition()
	{
		return this.position;
	}

	/**
	 * Get the icon color.
	 * 
	 * @return The icon color.
	 */
	public Color getIconColor()
	{
		return this.iconColor;
	}

	/**
	 * Get if it's activated.
	 * 
	 * @return True if it's activated, else False.
	 */
	public boolean getIsActivated()
	{
		return this.isActivated;
	}

	/**
	 * Get if it's disabled.
	 * 
	 * @return True if it's disabled, else False.
	 */
	public boolean getIsDisabled()
	{
		return this.isDisabled;
	}

	/**
	 * Set if it's activated.
	 * 
	 * @param isActivated
	 *            If it's is activated.
	 */
	public void setIsActivated(boolean isActivated)
	{
		this.isActivated = isActivated;
	}

	/**
	 * @see javafx.scene.control.Labeled#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder representation = new StringBuilder();

		representation.append("{position : ").append(this.position).append(", iconColor : ").append(this.iconColor).append("}");

		return representation.toString();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}

		if (object == null)
		{
			return false;
		}

		if (!(object instanceof Case))
		{
			return false;
		}

		Case case_ = (Case) object;

		if (!this.position.equals(case_.position))
		{
			return false;
		}

		if (!this.iconColor.equals(case_.iconColor))
		{
			return false;
		}

		return true;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return (this.position.hashCode() + this.iconColor.hashCode());
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Case case_)
	{
		return this.position.compareTo(case_.position);
	}
}
