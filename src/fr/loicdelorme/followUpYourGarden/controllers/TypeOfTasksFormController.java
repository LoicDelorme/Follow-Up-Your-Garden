package fr.loicdelorme.followUpYourGarden.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
public class TypeOfTasksFormController extends Controller
{
	/**
	 * The title.
	 */
	@FXML
	private Label title;

	/**
	 * The wording label.
	 */
	@FXML
	private Label wordingLabel;

	/**
	 * The wording.
	 */
	@FXML
	private TextField wording;

	/**
	 * The description label.
	 */
	@FXML
	private Label descriptionLabel;

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
	 * Initialize data.
	 * 
	 * @param typeOfTasks
	 *            The type of tasks.
	 * @param typeOfTasksServices
	 *            The type of tasks services.
	 * @param stage
	 *            The stage.
	 * @param bundle
	 *            The bundle.
	 */
	public void initializeData(TypeOfTasks typeOfTasks, TypeOfTasksServices typeOfTasksServices, Stage stage, ResourceBundle bundle)
	{
		this.typeOfTasks = typeOfTasks;
		this.typeOfTasksServices = typeOfTasksServices;
		this.stage = stage;
		this.bundle = bundle;

		if (this.typeOfTasks == null)
		{
			this.title.setText(this.bundle.getString("typeOfTasksAdditionFormTitle"));
			this.wording.setPromptText(this.bundle.getString("typeOfTasksWordingPromptText"));
			this.description.setPromptText(this.bundle.getString("typeOfTasksDescriptionPromptText"));
			this.isUpdateForm = false;
		}
		else
		{
			this.title.setText(this.bundle.getString("typeOfTasksModificationFormTitle"));
			this.wording.setText(this.typeOfTasks.getWording());
			this.description.setText(this.typeOfTasks.getDescription());
			this.isUpdateForm = true;
		}

		this.wordingLabel.setText(this.bundle.getString("typeOfTasksWordingLabel"));
		this.descriptionLabel.setText(this.bundle.getString("typeOfTasksDescriptionLabel"));
		this.valid.setText(this.bundle.getString("typeOfTasksValidButton"));
		this.cancel.setText(this.bundle.getString("typeOfTasksCancelButton"));

		this.stage.setResizable(false);
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
				this.typeOfTasksServices.updateTypeOfTasks(this.wording.getText().trim(), this.description.getText().trim(), this.typeOfTasks);
				this.displayInformation(this.bundle.getString("operationSuccess"), this.bundle.getString("typeOfTasksModificationSuccess"));
				this.stage.close();
			}
			else
			{
				this.typeOfTasksServices.addTypeOfTasks(this.wording.getText().trim(), this.description.getText().trim());
				this.displayInformation(this.bundle.getString("operationSuccess"), this.bundle.getString("typeOfTasksAdditionSuccess"));
				this.stage.close();
			}
		}
		catch (MissingTypeOfTasksIdException | MissingTypeOfTasksWordingException | MissingTypeOfTasksDescriptionException | InvalidTypeOfTasksIdException | InvalidTypeOfTasksWordingException | InvalidTypeOfTasksDescriptionException e)
		{
			this.displayError(this.bundle.getString("invalidFormTitle"), this.bundle.getString("invalidFormHeader"), e.getMessage());
		}
		catch (ClassNotFoundException | SQLException | IOException e)
		{
			this.processException(e);
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
