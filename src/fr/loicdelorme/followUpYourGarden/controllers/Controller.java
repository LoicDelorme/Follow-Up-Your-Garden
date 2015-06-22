package fr.loicdelorme.followUpYourGarden.controllers;

import javafx.scene.control.Alert;
import fr.loicdelorme.followUpYourGarden.core.helpers.DialogsHelper;

/**
 * This class allow you to display information.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class Controller
{
	/**
	 * Display an information.
	 * 
	 * @param title
	 *            The title.
	 * @param header
	 *            The header.
	 * @param content
	 *            The content.
	 */
	protected void displayInformation(String title, String header, String content)
	{
		Alert alert = DialogsHelper.generateInformationDialog(title, header, content);
		alert.showAndWait();
	}

	/**
	 * Display an error.
	 * 
	 * @param title
	 *            The title.
	 * @param header
	 *            The header.
	 * @param content
	 *            The content.
	 */
	protected void displayError(String title, String header, String content)
	{
		Alert alert = DialogsHelper.generateErrorDialog(title, header, content);
		alert.showAndWait();
	}
}
