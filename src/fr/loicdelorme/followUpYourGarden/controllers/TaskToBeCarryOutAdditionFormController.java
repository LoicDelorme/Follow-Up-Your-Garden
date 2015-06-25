package fr.loicdelorme.followUpYourGarden.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import fr.loicdelorme.followUpYourGarden.core.models.GroupOfPlants;
import fr.loicdelorme.followUpYourGarden.core.models.Priority;
import fr.loicdelorme.followUpYourGarden.core.models.TypeOfTasks;
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
 * This class allow you to control the task to be carry out addition form.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class TaskToBeCarryOutAdditionFormController extends Controller
{
	/**
	 * The title.
	 */
	@FXML
	private Label title;

	/**
	 * The type of tasks label.
	 */
	@FXML
	private Label typesOfTasksLabel;

	/**
	 * The types of tasks.
	 */
	@FXML
	private ChoiceBox<TypeOfTasks> typesOfTasks;

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
	 * The group of plants.
	 */
	private GroupOfPlants groupOfPlants;

	/**
	 * The task to be carry out services.
	 */
	private TaskToBeCarryOutServices taskToBeCarryOutServices;

	/**
	 * Initialize data.
	 * 
	 * @param groupOfPlants
	 *            The group of plants.
	 * @param typesOfTasks
	 *            The types of tasks.
	 * @param taskToBeCarryOutServices
	 *            The task to be carry out services.
	 * @param stage
	 *            The stage.
	 * @param bundle
	 *            The bundle.
	 */
	public void initializeData(GroupOfPlants groupOfPlants, List<TypeOfTasks> typesOfTasks, TaskToBeCarryOutServices taskToBeCarryOutServices, Stage stage, ResourceBundle bundle)
	{
		this.groupOfPlants = groupOfPlants;
		this.taskToBeCarryOutServices = taskToBeCarryOutServices;
		this.stage = stage;
		this.bundle = bundle;

		this.title.setText(this.bundle.getString("taskToBeCarryOutAdditionFormTitle"));
		this.typesOfTasksLabel.setText(this.bundle.getString("taskToBeCarryOutTypeOfTasksLabel"));
		this.deadlineDateLabel.setText(this.bundle.getString("taskToBeCarryOutDeadlineDateLabel"));
		this.anticipatedDurationLabel.setText(this.bundle.getString("taskToBeCarryOutAnticipatedDurationLabel"));
		this.anticipatedDuration.setPromptText(this.bundle.getString("taskToBeCarryOutAnticipatedDurationPromptText"));
		this.anticipatedDurationLabelBis.setText(this.bundle.getString("taskToBeCarryOutAnticipatedDurationLabelBis"));
		this.priorityLabel.setText(this.bundle.getString("taskToBeCarryOutPriorityLabel"));
		this.isRecurrentLabel.setText(this.bundle.getString("taskToBeCarryOutIsRecurrentLabel"));
		this.periodicityLabel.setText(this.bundle.getString("taskToBeCarryOutPeriodicityLabel"));
		this.periodicity.setPromptText(this.bundle.getString("taskToBeCarryOutPeriodicityPromptText"));
		this.periodicityLabelBis.setText(this.bundle.getString("taskToBeCarryOutPeriodicityLabelBis"));
		this.valid.setText(this.bundle.getString("taskToBeCarryOutValidButton"));
		this.cancel.setText(this.bundle.getString("taskToBeCarryOutCancelButton"));

		this.typesOfTasks.getItems().addAll(typesOfTasks);
		this.typesOfTasks.setValue(typesOfTasks.get(0));

		this.priority.getItems().addAll(Priority.values());
		this.priority.setValue(Priority.values()[0]);

		this.stage.setResizable(false);
	}

	/**
	 * The on click valid action.
	 */
	public void onValidAction()
	{
		try
		{
			this.taskToBeCarryOutServices.addTaskToBeCarryOut(this.groupOfPlants, this.typesOfTasks.getValue(), this.deadlineDate.getValue(), this.priority.getValue(), Double.parseDouble(this.anticipatedDuration.getText()), this.isRecurrent.isSelected(), Integer.parseInt(this.periodicity.getText()));
			this.displayInformation(this.bundle.getString("operationSuccess"), this.bundle.getString("taskToBeCarryOutAdditionSuccess"));
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
