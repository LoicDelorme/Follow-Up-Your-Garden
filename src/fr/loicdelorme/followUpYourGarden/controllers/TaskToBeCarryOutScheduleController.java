package fr.loicdelorme.followUpYourGarden.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import fr.loicdelorme.followUpYourGarden.core.models.ContentSelectorType;
import fr.loicdelorme.followUpYourGarden.core.models.TaskToBeCarryOut;
import fr.loicdelorme.followUpYourGarden.core.models.TypeOfPlants;
import fr.loicdelorme.followUpYourGarden.core.models.TypeOfTasks;
import fr.loicdelorme.followUpYourGarden.core.services.TaskToBeCarryOutServices;
import fr.loicdelorme.followUpYourGarden.core.services.TypeOfPlantsServices;
import fr.loicdelorme.followUpYourGarden.core.services.TypeOfTasksServices;

/**
 * This class allow you to control the task to be carry out schedule.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class TaskToBeCarryOutScheduleController extends Controller
{
	/**
	 * The actions menu.
	 */
	@FXML
	private Menu actions;

	/**
	 * The quit menu item.
	 */
	@FXML
	private MenuItem quit;

	/**
	 * The type of plants menu.
	 */
	@FXML
	private Menu typeOfPlants;

	/**
	 * The add type of plants menu item.
	 */
	@FXML
	private MenuItem addTypeOfPlants;

	/**
	 * The update type of plants menu item.
	 */
	@FXML
	private MenuItem updateTypeOfPlants;

	/**
	 * The remove type of plants menu item.
	 */
	@FXML
	private MenuItem removeTypeOfPlants;

	/**
	 * The type of tasks menu.
	 */
	@FXML
	private Menu typeOfTasks;

	/**
	 * The add type of tasks menu item.
	 */
	@FXML
	private MenuItem addTypeOfTasks;

	/**
	 * The update type of tasks menu item.
	 */
	@FXML
	private MenuItem updateTypeOfTasks;

	/**
	 * The remove type of tasks menu item.
	 */
	@FXML
	private MenuItem removeTypeOfTasks;

	/**
	 * The help menu.
	 */
	@FXML
	private Menu help;

	/**
	 * The documentation menu item.
	 */
	@FXML
	private MenuItem documentation;

	/**
	 * The about menu item.
	 */
	@FXML
	private MenuItem about;

	/**
	 * The validate task to be carry out button.
	 */
	@FXML
	private Button validateTaskToBeCarryOut;

	/**
	 * The update task to be carry out button.
	 */
	@FXML
	private Button updateTaskToBeCarryOut;

	/**
	 * The remove task to be carry out button.
	 */
	@FXML
	private Button removeTaskToBeCarryOut;

	/**
	 * The schedule.
	 */
	@FXML
	private TableView<TaskToBeCarryOut> schedule;

	/**
	 * The group of plants column.
	 */
	@FXML
	private TableColumn<TaskToBeCarryOut, String> groupOfPlantsColumn;

	/**
	 * The type of tasks column.
	 */
	@FXML
	private TableColumn<TaskToBeCarryOut, String> typeOfTasksColumn;

	/**
	 * The deadline date column.
	 */
	@FXML
	private TableColumn<TaskToBeCarryOut, LocalDate> deadlineDateColumn;

	/**
	 * The priority column.
	 */
	@FXML
	private TableColumn<TaskToBeCarryOut, String> priorityColumn;

	/**
	 * The current progression column.
	 */
	@FXML
	private TableColumn<TaskToBeCarryOut, Integer> currentProgressionColumn;

	/**
	 * The description column.
	 */
	@FXML
	private TableColumn<TaskToBeCarryOut, String> descriptionColumn;

	/**
	 * The list of tasks to be carry out.
	 */
	private List<TaskToBeCarryOut> tasksToBeCarryOut;

	/**
	 * The task to be carry out services.
	 */
	private TaskToBeCarryOutServices taskToBeCarryOutServices;

	/**
	 * The type of plants services.
	 */
	private TypeOfPlantsServices typeOfPlantsServices;

	/**
	 * The type of tasks services.
	 */
	private TypeOfTasksServices typeOfTasksServices;

	/**
	 * Initialize data.
	 * 
	 * @param taskToBeCarryOutServices
	 *            The task to be carry out services.
	 * @param typeOfPlantsServices
	 *            The type of plants services.
	 * @param typeOfTasksServices
	 *            The type of tasks services.
	 * @param stage
	 *            The stage.
	 * @param bundle
	 *            The bundle.
	 */
	public void initializeData(TaskToBeCarryOutServices taskToBeCarryOutServices, TypeOfPlantsServices typeOfPlantsServices, TypeOfTasksServices typeOfTasksServices, Stage stage, ResourceBundle bundle)
	{
		this.taskToBeCarryOutServices = taskToBeCarryOutServices;
		this.typeOfPlantsServices = typeOfPlantsServices;
		this.typeOfTasksServices = typeOfTasksServices;
		this.stage = stage;
		this.bundle = bundle;

		this.actions.setText(this.bundle.getString("taskToBeCarryOutScheduleMenuActions"));
		this.quit.setText(this.bundle.getString("taskToBeCarryOutScheduleMenuItemQuit"));
		this.typeOfPlants.setText(this.bundle.getString("taskToBeCarryOutScheduleMenuTypeOfPlants"));
		this.addTypeOfPlants.setText(this.bundle.getString("taskToBeCarryOutScheduleMenuItemAddTypeOfPlants"));
		this.updateTypeOfPlants.setText(this.bundle.getString("taskToBeCarryOutScheduleMenuItemUpdateTypeOfPlants"));
		this.removeTypeOfPlants.setText(this.bundle.getString("taskToBeCarryOutScheduleMenuItemRemoveTypeOfPlants"));
		this.typeOfTasks.setText(this.bundle.getString("taskToBeCarryOutScheduleMenuTypeOfTasks"));
		this.addTypeOfTasks.setText(this.bundle.getString("taskToBeCarryOutScheduleMenuItemAddTypeOfTasks"));
		this.updateTypeOfTasks.setText(this.bundle.getString("taskToBeCarryOutScheduleMenuItemUpdateTypeOfTasks"));
		this.removeTypeOfTasks.setText(this.bundle.getString("taskToBeCarryOutScheduleMenuItemRemoveTypeOfTasks"));
		this.help.setText(this.bundle.getString("taskToBeCarryOutScheduleMenuHelp"));
		this.documentation.setText(this.bundle.getString("taskToBeCarryOutScheduleMenuItemDocumentation"));
		this.about.setText(this.bundle.getString("taskToBeCarryOutScheduleMenuItemAbout"));

		this.validateTaskToBeCarryOut.setText(this.bundle.getString("taskToBeCarryOutScheduleValidateButton"));
		this.updateTaskToBeCarryOut.setText(this.bundle.getString("taskToBeCarryOutScheduleUpdateButton"));
		this.removeTaskToBeCarryOut.setText(this.bundle.getString("taskToBeCarryOutScheduleRemoveButton"));

		this.validateTaskToBeCarryOut.setGraphic(new ImageView("fr/loicdelorme/followUpYourGarden/views/images/schedule/validate.png"));
		this.updateTaskToBeCarryOut.setGraphic(new ImageView("fr/loicdelorme/followUpYourGarden/views/images/schedule/update.png"));
		this.removeTaskToBeCarryOut.setGraphic(new ImageView("fr/loicdelorme/followUpYourGarden/views/images/schedule/delete.png"));

		this.groupOfPlantsColumn.setText(this.bundle.getString("taskToBeCarryOutScheduleGroupOfPlantsColumn"));
		this.typeOfTasksColumn.setText(this.bundle.getString("taskToBeCarryOutScheduleTypeOfTasksColumn"));
		this.deadlineDateColumn.setText(this.bundle.getString("taskToBeCarryOutScheduleDeadlineDateColumn"));
		this.priorityColumn.setText(this.bundle.getString("taskToBeCarryOutSchedulePriorityColumn"));
		this.currentProgressionColumn.setText(this.bundle.getString("taskToBeCarryOutScheduleCurrentProgressionColumn"));
		this.descriptionColumn.setText(this.bundle.getString("taskToBeCarryOutScheduleDescriptionColumn"));

		this.groupOfPlantsColumn.setResizable(false);
		this.typeOfTasksColumn.setResizable(false);
		this.deadlineDateColumn.setResizable(false);
		this.priorityColumn.setResizable(false);
		this.currentProgressionColumn.setResizable(false);
		this.descriptionColumn.setResizable(false);

		this.groupOfPlantsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGroupOfPlants().getWording()));
		this.typeOfTasksColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTypeOfTasks().getWording()));
		this.deadlineDateColumn.setCellValueFactory(new PropertyValueFactory<TaskToBeCarryOut, LocalDate>("deadlineDate"));
		// this.priorityColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<ImageView>(new ImageView(cellData.getValue().getPriority().getPath()))); // TODO A FINIR.
		this.priorityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPriority().getPath()));
		this.currentProgressionColumn.setCellValueFactory(new PropertyValueFactory<TaskToBeCarryOut, Integer>("currentProgression"));
		this.descriptionColumn.setCellValueFactory(new PropertyValueFactory<TaskToBeCarryOut, String>("description"));

		this.schedule.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		updateSchedule();

		this.stage.setResizable(false);
	}

	/**
	 * The on click quit action.
	 */
	public void onQuitAction()
	{
		this.stage.close();
	}

	/**
	 * The on click add type of plants action.
	 * 
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void onAddTypeOfPlantsAction() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fr/loicdelorme/followUpYourGarden/views/TypeOfPlantsForm.fxml"));

		Stage stage = new Stage();
		stage.setScene(new Scene(loader.load()));

		TypeOfPlantsFormController controller = loader.getController();
		controller.initializeData(null, this.typeOfPlantsServices, stage, this.bundle);

		stage.showAndWait();
	}

	/**
	 * The on click update type of plants action.
	 * 
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void onUpdateTypeOfPlantsAction() throws IOException
	{
		List<TypeOfPlants> typesOfPlants = null;
		try
		{
			typesOfPlants = this.typeOfPlantsServices.getTypesOfPlants();
		}
		catch (ClassNotFoundException | SQLException | IOException e)
		{
			this.processException(e);
		}

		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fr/loicdelorme/followUpYourGarden/views/ContentSelector.fxml"));

		Stage stage = new Stage();
		stage.setScene(new Scene(loader.load()));

		ContentSelectorController<TypeOfPlants> controller = loader.getController();
		controller.initializeData(typesOfPlants, ContentSelectorType.UPDATE_TYPE_OF_PLANTS, stage, this.bundle);

		stage.showAndWait();

		TypeOfPlants typeOfPlantsToUpdate = controller.getSelectedValue();

		if (typeOfPlantsToUpdate != null)
		{
			FXMLLoader loader_ = new FXMLLoader(this.getClass().getResource("/fr/loicdelorme/followUpYourGarden/views/TypeOfPlantsForm.fxml"));

			Stage stage_ = new Stage();
			stage_.setScene(new Scene(loader_.load()));

			TypeOfPlantsFormController controller_ = loader_.getController();
			controller_.initializeData(typeOfPlantsToUpdate, this.typeOfPlantsServices, stage_, this.bundle);

			stage_.showAndWait();
		}
	}

	/**
	 * The on click remove type of plants action.
	 * 
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void onRemoveTypeOfPlantsAction() throws IOException
	{
		List<TypeOfPlants> typesOfPlants = null;

		try
		{
			typesOfPlants = this.typeOfPlantsServices.getTypesOfPlants();
		}
		catch (ClassNotFoundException | SQLException | IOException e1)
		{
			this.processException(e1);
		}

		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fr/loicdelorme/followUpYourGarden/views/ContentSelector.fxml"));

		Stage stage = new Stage();
		stage.setScene(new Scene(loader.load()));

		ContentSelectorController<TypeOfPlants> controller = loader.getController();
		controller.initializeData(typesOfPlants, ContentSelectorType.REMOVE_TYPE_OF_PLANTS, stage, this.bundle);

		stage.showAndWait();

		TypeOfPlants typeOfPlantsToRemove = controller.getSelectedValue();

		if (typeOfPlantsToRemove != null)
		{
			try
			{
				this.typeOfPlantsServices.removeTypeOfPlants(typeOfPlantsToRemove);
				this.displayInformation(this.bundle.getString("operationSuccess"), this.bundle.getString("typeOfPlantsRemovalSuccess"));
			}
			catch (ClassNotFoundException | SQLException | IOException e2)
			{
				this.processException(e2);
			}
		}
	}

	/**
	 * The on click add type of tasks action.
	 * 
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void onAddTypeOfTasksAction() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fr/loicdelorme/followUpYourGarden/views/TypeOfTasksForm.fxml"));

		Stage stage = new Stage();
		stage.setScene(new Scene(loader.load()));

		TypeOfTasksFormController controller = loader.getController();
		controller.initializeData(null, this.typeOfTasksServices, stage, this.bundle);

		stage.showAndWait();
	}

	/**
	 * The on click update type of tasks action.
	 * 
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void onUpdateTypeOfTasksAction() throws IOException
	{
		List<TypeOfTasks> typesOfTasks = null;

		try
		{
			typesOfTasks = this.typeOfTasksServices.getTypesOfTasks();
		}
		catch (ClassNotFoundException | SQLException | IOException e)
		{
			this.processException(e);
		}

		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fr/loicdelorme/followUpYourGarden/views/ContentSelector.fxml"));

		Stage stage = new Stage();
		stage.setScene(new Scene(loader.load()));

		ContentSelectorController<TypeOfTasks> controller = loader.getController();
		controller.initializeData(typesOfTasks, ContentSelectorType.UPDATE_TYPE_OF_TASKS, stage, this.bundle);

		stage.showAndWait();

		TypeOfTasks typeOfTasksToUpdate = controller.getSelectedValue();

		if (typeOfTasksToUpdate != null)
		{
			FXMLLoader loader_ = new FXMLLoader(this.getClass().getResource("/fr/loicdelorme/followUpYourGarden/views/TypeOfTasksForm.fxml"));

			Stage stage_ = new Stage();
			stage_.setScene(new Scene(loader_.load()));

			TypeOfTasksFormController controller_ = loader_.getController();
			controller_.initializeData(typeOfTasksToUpdate, this.typeOfTasksServices, stage_, this.bundle);

			stage_.showAndWait();
		}
	}

	/**
	 * The on click remove type of tasks action.
	 * 
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void onRemoveTypeOfTasksAction() throws IOException
	{
		List<TypeOfTasks> typesOfTasks = null;

		try
		{
			typesOfTasks = this.typeOfTasksServices.getTypesOfTasks();
		}
		catch (ClassNotFoundException | SQLException | IOException e)
		{
			this.processException(e);
		}

		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fr/loicdelorme/followUpYourGarden/views/ContentSelector.fxml"));

		Stage stage = new Stage();
		stage.setScene(new Scene(loader.load()));

		ContentSelectorController<TypeOfTasks> controller = loader.getController();
		controller.initializeData(typesOfTasks, ContentSelectorType.REMOVE_TYPE_OF_TASKS, stage, this.bundle);

		stage.showAndWait();

		TypeOfTasks typeOfTasksToRemove = controller.getSelectedValue();

		if (typeOfTasksToRemove != null)
		{
			try
			{
				this.typeOfTasksServices.removeTypeOfTasks(typeOfTasksToRemove);
				this.displayInformation(this.bundle.getString("operationSuccess"), this.bundle.getString("typeOfTasksRemovalSuccess"));
			}
			catch (ClassNotFoundException | SQLException | IOException e)
			{
				this.processException(e);
			}
		}
	}

	/**
	 * The on click documentation action.
	 */
	public void onDocumentationAction()
	{
		this.displayInformation(this.bundle.getString("documentationTitle"), this.bundle.getString("documentationContent"));
	}

	/**
	 * The on click about action.
	 */
	public void onAboutAction()
	{
		this.displayInformation(this.bundle.getString("aboutTitle"), this.bundle.getString("aboutHeader"), this.bundle.getString("aboutContent"));
	}

	/**
	 * The on click validate task to be carry out action.
	 */
	public void onValidateTaskToBeCarryOutAction()
	{
		TaskToBeCarryOut selectedTaskToBeCarryOut = this.schedule.getSelectionModel().getSelectedItem();

		try
		{
			this.taskToBeCarryOutServices.validateTaskToBeCarryOut(selectedTaskToBeCarryOut);
			this.displayInformation(this.bundle.getString("operationSuccess"), this.bundle.getString("taskToBeCarryOutValidateSuccess"));
		}
		catch (ClassNotFoundException | SQLException | IOException e)
		{
			this.processException(e);
		}

		updateSchedule();
	}

	/**
	 * The on click update task to be carry out action.
	 * 
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void onUpdateTaskToBeCarryOutAction() throws IOException
	{
		TaskToBeCarryOut selectedTaskToBeCarryOut = this.schedule.getSelectionModel().getSelectedItem();

		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fr/loicdelorme/followUpYourGarden/views/TaskToBeCarryOutUpdatingForm.fxml"));

		Stage stage = new Stage();
		stage.setScene(new Scene(loader.load()));

		TaskToBeCarryOutUpdatingFormController controller = loader.getController();
		controller.initializeData(selectedTaskToBeCarryOut, this.taskToBeCarryOutServices, stage, this.bundle);

		stage.showAndWait();

		updateSchedule();
	}

	/**
	 * The on click remove task to be carry out action.
	 */
	public void onRemoveTaskToBeCarryOutAction()
	{
		if (this.displayConfirmationDialog(this.bundle.getString("taskToBeCarryOutRemovalConfirmation"), this.bundle.getString("taskToBeCarryOutRemovalQuestion")))
		{
			TaskToBeCarryOut selectedTaskToBeCarryOut = this.schedule.getSelectionModel().getSelectedItem();

			try
			{
				this.taskToBeCarryOutServices.removeTaskToBeCarryOut(selectedTaskToBeCarryOut);
			}
			catch (ClassNotFoundException | SQLException | IOException e)
			{
				this.processException(e);
			}

			updateSchedule();
		}
	}

	/**
	 * Update the schedule.
	 */
	private void updateSchedule()
	{
		try
		{
			this.tasksToBeCarryOut = this.taskToBeCarryOutServices.getTasksToBeCarryOut();
		}
		catch (ClassNotFoundException | IOException | SQLException e)
		{
			this.processException(e);
		}

		this.schedule.getItems().clear();
		this.schedule.setItems(FXCollections.observableArrayList(this.tasksToBeCarryOut));
	}
}
