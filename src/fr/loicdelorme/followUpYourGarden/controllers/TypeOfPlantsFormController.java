package fr.loicdelorme.followUpYourGarden.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import fr.loicdelorme.followUpYourGarden.core.helpers.DialogsHelper;
import fr.loicdelorme.followUpYourGarden.core.helpers.FileWriterHelper;
import fr.loicdelorme.followUpYourGarden.core.models.TypeOfPlants;
import fr.loicdelorme.followUpYourGarden.core.services.TypeOfPlantsServices;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidTypeOfPlantsIdException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidTypeOfPlantsWordingException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTypeOfPlantsIdException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTypeOfPlantsWordingException;

/**
 * This class allow you to control the type of plants form.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class TypeOfPlantsFormController
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
	 * The type of plants.
	 */
	private TypeOfPlants typeOfPlants;

	/**
	 * The type of plants services.
	 */
	private TypeOfPlantsServices typeOfPlantsServices;

	/**
	 * The stage.
	 */
	private Stage stage;

	/**
	 * Create a type of plants form controller.
	 * 
	 * @param title
	 *            The title.
	 * @param typeOfPlants
	 *            The type of plants.
	 * @param typeOfPlantsServices
	 *            The type of plants services.
	 * @param stage
	 *            The stage.
	 */
	public TypeOfPlantsFormController(String title, TypeOfPlants typeOfPlants, TypeOfPlantsServices typeOfPlantsServices, Stage stage)
	{
		this.title.setText(title);

		if (typeOfPlants != null)
		{
			this.wording.setText(typeOfPlants.getWording());
			this.isUpdateForm = true;
		}
		else
		{
			this.isUpdateForm = false;
		}

		this.typeOfPlants = typeOfPlants;
		this.typeOfPlantsServices = typeOfPlantsServices;
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
				this.typeOfPlantsServices.updateTypeOfPlants(this.wording.getText(), this.typeOfPlants);

				Alert alert = DialogsHelper.generateInformationDialog(IContentController.OPERATION_SUCCESS_TITLE, "The type of plants was updated!");
				alert.showAndWait();

				this.stage.close();
			}
			else
			{
				this.typeOfPlantsServices.addTypeOfPlants(this.wording.getText());

				Alert alert = DialogsHelper.generateInformationDialog(IContentController.OPERATION_SUCCESS_TITLE, "The type of plants was added!");
				alert.showAndWait();

				this.stage.close();
			}
		}
		catch (MissingTypeOfPlantsIdException | MissingTypeOfPlantsWordingException | InvalidTypeOfPlantsIdException | InvalidTypeOfPlantsWordingException e)
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
