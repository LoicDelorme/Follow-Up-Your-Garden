package fr.loicdelorme.followUpYourGarden.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.scene.control.Alert;
import fr.loicdelorme.followUpYourGarden.core.helpers.DialogsHelper;
import fr.loicdelorme.followUpYourGarden.core.helpers.FileWriterHelper;

/**
 * This class allow you to display information.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class Controller
{
	/**
	 * The bundle.
	 */
	protected ResourceBundle bundle;

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

	/**
	 * Save the error.
	 * 
	 * @param path
	 *            The path.
	 * @param extension
	 *            The extension.
	 * @param exception
	 *            The exception.
	 */
	protected void saveError(String path, String extension, Exception exception)
	{
		FileWriterHelper.writeContent(path, UUID.randomUUID().toString(), extension, exception.getMessage());
	}

	/**
	 * Process the exception.
	 * 
	 * @param exception
	 *            The exception.
	 */
	protected void processException(Exception exception)
	{
		if (exception instanceof ClassNotFoundException)
		{
			this.saveError(this.bundle.getString("errorFilePath"), this.bundle.getString("errorFileExtension"), exception);
			this.displayError(this.bundle.getString("driverErrorTitle"), this.bundle.getString("driverErrorHeader"), exception.getMessage());
		}

		if (exception instanceof SQLException)
		{
			this.saveError(this.bundle.getString("errorFilePath"), this.bundle.getString("errorFileExtension"), exception);
			this.displayError(this.bundle.getString("sqlErrorTitle"), this.bundle.getString("sqlErrorHeader"), exception.getMessage());
		}

		if (exception instanceof IOException)
		{
			this.saveError(this.bundle.getString("errorFilePath"), this.bundle.getString("errorFileExtension"), exception);
			this.displayError(this.bundle.getString("ioErrorTitle"), this.bundle.getString("ioErrorHeader"), exception.getMessage());
		}
	}
}
