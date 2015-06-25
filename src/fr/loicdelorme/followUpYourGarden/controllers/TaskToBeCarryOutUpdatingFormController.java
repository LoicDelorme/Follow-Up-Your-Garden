package fr.loicdelorme.followUpYourGarden.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import fr.loicdelorme.followUpYourGarden.core.models.Priority;
import fr.loicdelorme.followUpYourGarden.core.models.TaskToBeCarryOut;
import fr.loicdelorme.followUpYourGarden.core.services.TaskToBeCarryOutServices;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidTaskToBeCarryOutAnticipatedDurationException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidTaskToBeCarryOutCurrentProgressionException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTaskToBeCarryOutAnticipatedDurationException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTaskToBeCarryOutCurrentProgressionException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTaskToBeCarryOutDeadlineDateException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTaskToBeCarryOutDescriptionException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTaskToBeCarryOutGroupOfPlantsException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTaskToBeCarryOutIsRecurrentException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTaskToBeCarryOutPeriodicityException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTaskToBeCarryOutPriorityException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTaskToBeCarryOutTypeOfTasksException;

/**
 * This class allow you to control the task to be carry out updating form.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class TaskToBeCarryOutUpdatingFormController extends Controller
{
	/**
	 * The title.
	 */
	@FXML
	private Label title;

	/**
	 * The deadline date label.
	 */
	@FXML
	private Label deadlineDateLabel;

	/**
	 * The deadline date.
	 */
	@FXML
	private DatePicker deadlineDate;

	/**
	 * The anticipated duration label.
	 */
	@FXML
	private Label anticipatedDurationLabel;

	/**
	 * The anticipated duration.
	 */
	@FXML
	private TextField anticipatedDuration;

	/**
	 * The anticipated duration label bis.
	 */
	@FXML
	private Label anticipatedDurationLabelBis;

	/**
	 * The priority label.
	 */
	@FXML
	private Label priorityLabel;

	/**
	 * The priority.
	 */
	@FXML
	private ChoiceBox<Priority> priority;

	/**
	 * The is recurrent label.
	 */
	@FXML
	private Label isRecurrentLabel;

	/**
	 * The is recurrent.
	 */
	@FXML
	private CheckBox isRecurrent;

	/**
	 * The periodicity label.
	 */
	@FXML
	private Label periodicityLabel;

	/**
	 * The periodicity.
	 */
	@FXML
	private TextField periodicity;

	/**
	 * The periodicity label bis.
	 */
	@FXML
	private Label periodicityLabelBis;

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
	 * The current progression label.
	 */
	@FXML
	private Label currentProgressionLabel;

	/**
	 * The current progression.
	 */
	@FXML
	private Slider currentProgression;

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
	 * The task to be carry out.
	 */
	private TaskToBeCarryOut taskToBeCarryOut;

	/**
	 * The task to be carry out services.
	 */
	private TaskToBeCarryOutServices taskToBeCarryOutServices;

	/**
	 * Initialize data.
	 * 
	 * @param taskToBeCarryOut
	 *            The task to be carry out.
	 * @param taskToBeCarryOutServices
	 *            The task to be carry out services.
	 * @param stage
	 *            The stage.
	 * @param bundle
	 *            The bundle.
	 */
	public void initializeData(TaskToBeCarryOut taskToBeCarryOut, TaskToBeCarryOutServices taskToBeCarryOutServices, Stage stage, ResourceBundle bundle)
	{
		this.taskToBeCarryOut = taskToBeCarryOut;
		this.taskToBeCarryOutServices = taskToBeCarryOutServices;
		this.stage = stage;
		this.bundle = bundle;

		this.title.setText(this.bundle.getString("taskToBeCarryOutAdditionFormTitle"));
		this.deadlineDateLabel.setText(this.bundle.getString("taskToBeCarryOutDeadlineDateLabel"));
		this.anticipatedDurationLabel.setText(this.bundle.getString("taskToBeCarryOutAnticipatedDurationLabel"));
		this.anticipatedDuration.setPromptText(this.bundle.getString("taskToBeCarryOutAnticipatedDurationPromptText"));
		this.anticipatedDurationLabelBis.setText(this.bundle.getString("taskToBeCarryOutAnticipatedDurationLabelBis"));
		this.priorityLabel.setText(this.bundle.getString("taskToBeCarryOutPriorityLabel"));
		this.isRecurrentLabel.setText(this.bundle.getString("taskToBeCarryOutIsRecurrentLabel"));
		this.anticipatedDurationLabel.setText(this.bundle.getString("taskToBeCarryOutPeriodicityLabel"));
		this.anticipatedDuration.setPromptText(this.bundle.getString("taskToBeCarryOutPeriodicityPromptText"));
		this.anticipatedDurationLabelBis.setText(this.bundle.getString("taskToBeCarryOutPeriodicityLabelBis"));
		this.descriptionLabel.setText(this.bundle.getString("taskToBeCarryOutDescriptionLabel"));
		this.currentProgressionLabel.setText(this.bundle.getString("taskToBeCarryOutCurrentProgressionLabel"));
		this.valid.setText(this.bundle.getString("taskToBeCarryOutValidButton"));
		this.cancel.setText(this.bundle.getString("taskToBeCarryOutCancelButton"));

		this.stage.setResizable(false);
	}

	/**
	 * The on click valid action.
	 */
	public void onValidAction()
	{
		try
		{
			this.taskToBeCarryOutServices.updateTaskToBeCarryOut(this.deadlineDate.getValue(), this.priority.getValue(), this.description.getText(), (int) this.currentProgression.getValue(), Double.parseDouble(this.anticipatedDuration.getText()), this.isRecurrent.isSelected(), Integer.parseInt(this.periodicity.getText()), this.taskToBeCarryOut);
			this.displayInformation(this.bundle.getString("operationSuccess"), this.bundle.getString("taskToBeCarryOutModificationSuccess"));
			this.stage.close();
		}
		catch (MissingTaskToBeCarryOutGroupOfPlantsException | MissingTaskToBeCarryOutTypeOfTasksException | MissingTaskToBeCarryOutDeadlineDateException | MissingTaskToBeCarryOutPriorityException | MissingTaskToBeCarryOutDescriptionException | MissingTaskToBeCarryOutCurrentProgressionException | MissingTaskToBeCarryOutAnticipatedDurationException | MissingTaskToBeCarryOutIsRecurrentException | MissingTaskToBeCarryOutPeriodicityException | InvalidTaskToBeCarryOutCurrentProgressionException | InvalidTaskToBeCarryOutAnticipatedDurationException e)
		{
			this.displayError(this.bundle.getString("invalidFormTitle"), this.bundle.getString("invalidFormHeader"), e.getMessage());
		}
		catch (NumberFormatException e)
		{
			this.displayError(this.bundle.getString("invalidFormTitle"), this.bundle.getString("invalidFormHeader"), this.bundle.getString("invalidFormNumberValue"));
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
