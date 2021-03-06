package fr.loicdelorme.followUpYourGarden.core.models;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * This class allow you to create a legend item.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class LegendItem extends HBox
{
	/**
	 * The wording.
	 */
	private final String wording;

	/**
	 * The icon color.
	 */
	private final Color iconColor;

	/**
	 * Create a legend item.
	 * 
	 * @param wording
	 *            The wording.
	 * @param iconColor
	 *            The icon color.
	 */
	public LegendItem(String wording, Color iconColor)
	{
		this.wording = wording;
		this.iconColor = iconColor;

		Button button = new Button();
		button.setBackground(new Background(new BackgroundFill(this.iconColor, CornerRadii.EMPTY, Insets.EMPTY)));

		Label label = new Label(this.wording);

		this.setSpacing(5);
		this.getChildren().addAll(button, label);
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
	 * Get the icon color.
	 * 
	 * @return The icon color.
	 */
	public Color getIconColor()
	{
		return this.iconColor;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return this.wording;
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

		if (!(object instanceof LegendItem))
		{
			return false;
		}

		LegendItem legendItem = (LegendItem) object;

		if (!this.wording.equals(legendItem.wording))
		{
			return false;
		}

		if (!this.iconColor.equals(legendItem.iconColor))
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
		return (this.wording.hashCode() + this.iconColor.hashCode());
	}
}
