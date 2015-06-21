package fr.loicdelorme.followUpYourGarden.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import fr.loicdelorme.followUpYourGarden.core.helpers.DialogsHelper;
import fr.loicdelorme.followUpYourGarden.core.helpers.FileWriterHelper;
import fr.loicdelorme.followUpYourGarden.core.models.TypeOfTasks;
import fr.loicdelorme.followUpYourGarden.core.services.TypeOfTasksServices;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidTypeOfTasksDescriptionException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidTypeOfTasksIdException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidTypeOfTasksWordingException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTypeOfTasksDescriptionException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTypeOfTasksIdException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTypeOfTasksWordingException;

/**
 * This class allow you to control the type of tasks form.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class TypeOfTasksFormController
{
	/**
	 * The title.
	 */
	@FXML
	private Label title;

	/**
	 * The wording.
	 */
	@FXML
	private TextField wording;

	/**
	 * The description.
	 */
	@FXML
	private TextArea description;

	/**
	 * The valid button.
	 */
	@FXML
	private Button valid;

	/**
	 * The cancel button.
	 */
	@FXML
	private Button cancel;

	/**
	 * If it's update form.
	 */
	private boolean isUpdateForm;

	/**
	 * The type of tasks.
	 */
	private TypeOfTasks typeOfTasks;

	/**
	 * The type of tasks services.
	 */
	private TypeOfTasksServices typeOfTasksServices;

	/**
	 * The stage.
	 */
	private Stage stage;

	/**
	 * Create a type of tasks form controller.
	 * 
	 * @param title
	 *            The title.
	 * @param typeOfTasks
	 *            The type of tasks.
	 * @param typeOfTasksServices
	 *            The type of tasks services.
	 * @param stage
	 *            The stage.
	 */
	public TypeOfTasksFormController(String title, TypeOfTasks typeOfTasks, TypeOfTasksServices typeOfTasksServices, Stage stage)
	{
		this.title.setText(title);

		if (typeOfTasks != null)
		{
			this.wording.setText(typeOfTasks.getWording());
			this.description.setText(typeOfTasks.getDescription());
			this.isUpdateForm = true;
		}
		else
		{
			this.isUpdateForm = false;
		}

		this.typeOfTasks = typeOfTasks;
		this.typeOfTasksServices = typeOfTasksServices;
		this.stage = stage;
	}

	/**
	 * The on click valid action.
	 */
	public void onValidAction()
	{
		try
		{
			if (this.isUpdateForm)
			{
				this.typeOfTasksServices.updateTypeOfTasks(this.wording.getText(), this.description.getText(), this.typeOfTasks);

				Alert alert = DialogsHelper.generateInformationDialog(IContentController.OPERATION_SUCCESS_TITLE, "The type of tasks was updated!");
				alert.showAndWait();

				this.stage.close();
			}
			else
			{
				this.typeOfTasksServices.addTypeOfTasks(this.wording.getText(), this.description.getText());

				Alert alert = DialogsHelper.generateInformationDialog(IContentController.OPERATION_SUCCESS_TITLE, "The type of tasks was added!");
				alert.showAndWait();

				this.stage.close();
			}
		}
		catch (MissingTypeOfTasksIdException | MissingTypeOfTasksWordingException | MissingTypeOfTasksDescriptionException | InvalidTypeOfTasksIdException | InvalidTypeOfTasksWordingException | InvalidTypeOfTasksDescriptionException e)
		{
			Alert alert = DialogsHelper.generateErrorDialog(IContentController.FORM_DATA_EXCEPTION_TITLE, IContentController.FORM_DATA_EXCEPTION_HEADER, e.getMessage());
			alert.showAndWait();
		}
		catch (ClassNotFoundException e)
		{
			FileWriterHelper.writeContent(IContentController.ERROR_FILE_PATH, UUID.randomUUID().toString(), IContentController.ERROR_FILE_EXTENSION, e.getMessage());

			Alert alert = DialogsHelper.generateErrorDialog(IContentController.CLASS_NOT_FOUND_EXCEPTION_TITLE, IContentController.CLASS_NOT_FOUND_EXCEPTION_HEADER, e.getMessage());
			alert.showAndWait();
		}
		catch (SQLException e)
		{
			FileWriterHelper.writeContent(IContentController.ERROR_FILE_PATH, UUID.randomUUID().toString(), IContentController.ERROR_FILE_EXTENSION, e.getMessage());

			Alert alert = DialogsHelper.generateErrorDialog(IContentController.SQL_EXCEPTION_TITLE, IContentController.SQL_EXCEPTION_HEADER, e.getMessage());
			alert.showAndWait();
		}
		catch (IOException e)
		{
			FileWriterHelper.writeContent(IContentController.ERROR_FILE_PATH, UUID.randomUUID().toString(), IContentController.ERROR_FILE_EXTENSION, e.getMessage());

			Alert alert = DialogsHelper.generateErrorDialog(IContentController.IO_EXCEPTION_TITLE, IContentController.IO_EXCEPTION_HEADER, e.getMessage());
			alert.showAndWait();
		}
	}

	/**
	 * The on click cancel action.
	 */
	public void onCancelAction()
	{
		this.stage.close();
	}
}
