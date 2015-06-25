package fr.loicdelorme.followUpYourGarden.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import fr.loicdelorme.followUpYourGarden.core.helpers.CarriedOutTasksHelper;
import fr.loicdelorme.followUpYourGarden.core.helpers.CasesHelper;
import fr.loicdelorme.followUpYourGarden.core.helpers.TextComputerHelper;
import fr.loicdelorme.followUpYourGarden.core.models.CarriedOutTask;
import fr.loicdelorme.followUpYourGarden.core.models.ContentSelectorType;
import fr.loicdelorme.followUpYourGarden.core.models.GroupOfPlants;
import fr.loicdelorme.followUpYourGarden.core.models.TypeOfPlants;
import fr.loicdelorme.followUpYourGarden.core.models.TypeOfTasks;
import fr.loicdelorme.followUpYourGarden.core.services.CarriedOutTaskServices;
import fr.loicdelorme.followUpYourGarden.core.services.GroupOfPlantsServices;
import fr.loicdelorme.followUpYourGarden.core.services.TaskToBeCarryOutServices;
import fr.loicdelorme.followUpYourGarden.core.services.TypeOfPlantsServices;
import fr.loicdelorme.followUpYourGarden.core.services.TypeOfTasksServices;

/**
 * This class allow you to control the group of plants summary.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class GroupOfPlantsSummaryController extends Controller
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
	 * The add task to be carry out button.
	 */
	@FXML
	private Button addTaskToBeCarryOut;

	/**
	 * The add group of plants button.
	 */
	@FXML
	private Button addGroupOfPlants;

	/**
	 * The update group of plants button.
	 */
	@FXML
	private Button updateGroupOfPlants;

	/**
	 * The remove group of plants button.
	 */
	@FXML
	private Button removeGroupOfPlants;

	/**
	 * The group of plants historic button.
	 */
	@FXML
	private Button groupOfPlantsHistoric;

	/**
	 * The task to be carry out schedule button.
	 */
	@FXML
	private Button taskToBeCarryOutSchedule;

	/**
	 * The global representation button.
	 */
	@FXML
	private Button globalRepresentation;

	/**
	 * The groups of plants.
	 */
	@FXML
	private TreeView<Object> groupsOfPlants;

	/**
	 * The root.
	 */
	private TreeItem<Object> root;

	/**
	 * The group of plants services.
	 */
	private GroupOfPlantsServices groupOfPlantsServices;

	/**
	 * The task to be carry out services.
	 */
	private TaskToBeCarryOutServices taskToBeCarryOutServices;

	/**
	 * The carried out task services.
	 */
	private CarriedOutTaskServices carriedOutTaskServices;

	/**
	 * The type of plants services.
	 */
	private TypeOfPlantsServices typeOfPlantsServices;

	/**
	 * The type of tasks services.
	 */
	private TypeOfTasksServices typeOfTasksServices;

	/**
	 * The width.
	 */
	private int width;

	/**
	 * The height.
	 */
	private int height;

	/**
	 * Initialize data.
	 * 
	 * @param width
	 *            The width.
	 * @param height
	 *            The height.
	 * @param groupOfPlantsServices
	 *            The group of plants services.
	 * 
	 * @param taskToBeCarryOutServices
	 *            The task to be carry out services.
	 * @param carriedOutTaskServices
	 *            The carried out task services.
	 * @param typeOfPlantsServices
	 *            The type of plants services.
	 * @param typeOfTasksServices
	 *            The type of tasks services.
	 * @param stage
	 *            The stage.
	 * @param bundle
	 *            The bundle.
	 */
	public void initializeData(int width, int height, GroupOfPlantsServices groupOfPlantsServices, TaskToBeCarryOutServices taskToBeCarryOutServices, CarriedOutTaskServices carriedOutTaskServices, TypeOfPlantsServices typeOfPlantsServices, TypeOfTasksServices typeOfTasksServices, Stage stage, ResourceBundle bundle)
	{
		this.width = width;
		this.height = height;
		this.groupOfPlantsServices = groupOfPlantsServices;
		this.taskToBeCarryOutServices = taskToBeCarryOutServices;
		this.carriedOutTaskServices = carriedOutTaskServices;
		this.typeOfPlantsServices = typeOfPlantsServices;
		this.typeOfTasksServices = typeOfTasksServices;
		this.stage = stage;
		this.bundle = bundle;

		this.actions.setText(this.bundle.getString("groupOfPlantsSummaryMenuActions"));
		this.quit.setText(this.bundle.getString("groupOfPlantsSummaryMenuItemQuit"));
		this.typeOfPlants.setText(this.bundle.getString("groupOfPlantsSummaryMenuTypeOfPlants"));
		this.addTypeOfPlants.setText(this.bundle.getString("groupOfPlantsSummaryMenuItemAddTypeOfPlants"));
		this.updateTypeOfPlants.setText(this.bundle.getString("groupOfPlantsSummaryMenuItemUpdateTypeOfPlants"));
		this.removeTypeOfPlants.setText(this.bundle.getString("groupOfPlantsSummaryMenuItemRemoveTypeOfPlants"));
		this.typeOfTasks.setText(this.bundle.getString("groupOfPlantsSummaryMenuTypeOfTasks"));
		this.addTypeOfTasks.setText(this.bundle.getString("groupOfPlantsSummaryMenuItemAddTypeOfTasks"));
		this.updateTypeOfTasks.setText(this.bundle.getString("groupOfPlantsSummaryMenuItemUpdateTypeOfTasks"));
		this.removeTypeOfTasks.setText(this.bundle.getString("groupOfPlantsSummaryMenuItemRemoveTypeOfTasks"));
		this.help.setText(this.bundle.getString("groupOfPlantsSummaryMenuHelp"));
		this.documentation.setText(this.bundle.getString("groupOfPlantsSummaryMenuItemDocumentation"));
		this.about.setText(this.bundle.getString("groupOfPlantsSummaryMenuItemAbout"));

		this.addTaskToBeCarryOut.setText(this.bundle.getString("groupOfPlantsSummaryAddTaskToBeCarryOutButton"));
		this.addGroupOfPlants.setText(this.bundle.getString("groupOfPlantsSummaryAddGroupOfPlantsButton"));
		this.updateGroupOfPlants.setText(this.bundle.getString("groupOfPlantsSummaryUpdateGroupOfPlantsButton"));
		this.removeGroupOfPlants.setText(this.bundle.getString("groupOfPlantsSummaryRemoveGroupOfPlantsButton"));
		this.groupOfPlantsHistoric.setText(this.bundle.getString("groupOfPlantsSummaryGroupOfPlantsHistoricButton"));
		this.taskToBeCarryOutSchedule.setText(this.bundle.getString("groupOfPlantsSummaryTaskToBeCarryOutScheduleButton"));
		this.globalRepresentation.setText(this.bundle.getString("groupOfPlantsSummaryGlobalRepresentationButton"));

		this.addTaskToBeCarryOut.setGraphic(new ImageView("fr/loicdelorme/followUpYourGarden/views/images/summary/add.png"));
		this.addGroupOfPlants.setGraphic(new ImageView("fr/loicdelorme/followUpYourGarden/views/images/summary/add.png"));
		this.updateGroupOfPlants.setGraphic(new ImageView("fr/loicdelorme/followUpYourGarden/views/images/summary/update.png"));
		this.removeGroupOfPlants.setGraphic(new ImageView("fr/loicdelorme/followUpYourGarden/views/images/summary/delete.png"));
		this.groupOfPlantsHistoric.setGraphic(new ImageView("fr/loicdelorme/followUpYourGarden/views/images/summary/historic.png"));

		this.root = new TreeItem<Object>();

		updateGroupsOfPlants();
		this.groupsOfPlants.setRoot(this.root);
		this.groupsOfPlants.setShowRoot(false);
		this.groupsOfPlants.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

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
	 * The on click add task to be carry out action.
	 * 
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void onAddTaskToBeCarryOutAction() throws IOException
	{
		TreeItem<Object> selectedItem = this.groupsOfPlants.getSelectionModel().getSelectedItem();
		boolean isCorrectInstance = (selectedItem != null ? selectedItem.getValue() instanceof GroupOfPlants : false);
		if (selectedItem != null && isCorrectInstance)
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

			GroupOfPlants selectedGroupOfPlants = (GroupOfPlants) selectedItem.getValue();

			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fr/loicdelorme/followUpYourGarden/views/TaskToBeCarryOutAdditionForm.fxml"));

			Stage stage = new Stage();
			stage.setScene(new Scene(loader.load()));

			TaskToBeCarryOutAdditionFormController controller = loader.getController();
			controller.initializeData(selectedGroupOfPlants, typesOfTasks, this.taskToBeCarryOutServices, stage, this.bundle);

			stage.showAndWait();

			updateGroupsOfPlants();
		}
		else
		{
			this.displayError(this.bundle.getString("invalidSelectionTitle"), this.bundle.getString("invalidGroupOfPlantsSelectionContent"));
		}
	}

	/**
	 * The on click add group of plants action.
	 * 
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void onAddGroupOfPlantsAction() throws IOException
	{
		List<TypeOfPlants> typesOfPlants = null;
		List<GroupOfPlants> allGroupsOfPlants = null;
		try
		{
			typesOfPlants = this.typeOfPlantsServices.getTypesOfPlants();
			allGroupsOfPlants = this.groupOfPlantsServices.getGroupsOfPlants();
		}
		catch (ClassNotFoundException | SQLException e)
		{
			this.processException(e);
		}

		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fr/loicdelorme/followUpYourGarden/views/GroupsOfPlantsForm.fxml"));

		Stage stage = new Stage();
		stage.setScene(new Scene(loader.load()));

		GroupOfPlantsFormController controller = loader.getController();
		controller.initializeData(null, typesOfPlants, CasesHelper.generateCasesForAddingGroupOfPlants(this.width, this.height, allGroupsOfPlants), this.width, this.height, this.groupOfPlantsServices, stage, this.bundle);

		stage.showAndWait();
	}

	/**
	 * The on click update group of plants action.
	 * 
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void onUpdateGroupOfPlantsAction() throws IOException
	{
		TreeItem<Object> selectedItem = this.groupsOfPlants.getSelectionModel().getSelectedItem();
		boolean isCorrectInstance = (selectedItem != null ? selectedItem.getValue() instanceof GroupOfPlants : false);
		if (selectedItem != null && isCorrectInstance)
		{
			GroupOfPlants selectedGroupOfPlants = (GroupOfPlants) selectedItem.getValue();

			List<GroupOfPlants> allGroupsOfPlants = null;
			try
			{
				allGroupsOfPlants = this.groupOfPlantsServices.getGroupsOfPlants();
			}
			catch (ClassNotFoundException | SQLException | IOException e)
			{
				this.processException(e);
			}

			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fr/loicdelorme/followUpYourGarden/views/GroupsOfPlantsForm.fxml"));

			Stage stage = new Stage();
			stage.setScene(new Scene(loader.load()));

			GroupOfPlantsFormController controller = loader.getController();
			controller.initializeData(selectedGroupOfPlants, selectedGroupOfPlants.getTypesOfPlants(), CasesHelper.generateCasesForUpdatingGroupOfPlants(this.width, this.height, allGroupsOfPlants, selectedGroupOfPlants), this.width, this.height, this.groupOfPlantsServices, stage, this.bundle);

			stage.showAndWait();

			updateGroupsOfPlants();
		}
		else
		{
			this.displayError(this.bundle.getString("invalidSelectionTitle"), this.bundle.getString("invalidGroupOfPlantsSelectionContent"));
		}
	}

	/**
	 * The on click remove group of plants action.
	 */
	public void onRemoveGroupOfPlantsAction()
	{
		TreeItem<Object> selectedItem = this.groupsOfPlants.getSelectionModel().getSelectedItem();
		boolean isCorrectInstance = (selectedItem != null ? selectedItem.getValue() instanceof GroupOfPlants : false);
		if (selectedItem != null && isCorrectInstance)
		{
			GroupOfPlants selectedGroupOfPlants = (GroupOfPlants) selectedItem.getValue();

			if (this.displayConfirmationDialog(this.bundle.getString("groupOfPlantsRemovalConfirmation"), this.bundle.getString("groupOfPlantsRemovalQuestion")))
			{
				try
				{
					this.groupOfPlantsServices.removeGroupOfPlants(selectedGroupOfPlants);
					this.displayInformation(this.bundle.getString("taskToBeCarryOutRemovalConfirmation"), this.bundle.getString("taskToBeCarryOutRemovalSuccess"));
					updateGroupsOfPlants();
				}
				catch (ClassNotFoundException | SQLException | IOException e)
				{
					this.processException(e);
				}
			}
		}
		else
		{
			this.displayError(this.bundle.getString("invalidSelectionTitle"), this.bundle.getString("invalidGroupOfPlantsSelectionContent"));
		}
	}

	/**
	 * The on click group of plants historic action.
	 */
	public void onGroupOfPlantsHistoricAction()
	{
		TreeItem<Object> selectedItem = this.groupsOfPlants.getSelectionModel().getSelectedItem();
		boolean isCorrectInstance = (selectedItem != null ? selectedItem.getValue() instanceof GroupOfPlants : false);
		if (selectedItem != null && isCorrectInstance)
		{
			GroupOfPlants selectedGroupOfPlants = (GroupOfPlants) selectedItem.getValue();

			List<CarriedOutTask> carriedOutTasks = null;
			try
			{
				carriedOutTasks = this.carriedOutTaskServices.getCarriedOutTasks(selectedGroupOfPlants.getId());
			}
			catch (ClassNotFoundException | IOException | SQLException e)
			{
				this.processException(e);
			}

			this.displayInformation(this.bundle.getString("groupOfPlantsSummaryComplementaryInformationTitle"), this.bundle.getString("groupOfPlantsSummaryComplementaryInformationList"), TextComputerHelper.computeCarriedOutTasksText(CarriedOutTasksHelper.getLastCarriedOutTasks(carriedOutTasks)));
		}
		else
		{
			this.displayError(this.bundle.getString("invalidSelectionTitle"), this.bundle.getString("invalidGroupOfPlantsSelectionContent"));
		}
	}

	/**
	 * The on click task to be carry out schedule action.
	 * 
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void onTaskToBeCarryOutScheduleAction() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fr/loicdelorme/followUpYourGarden/views/TasksToBeCarryOutSchedule.fxml"));

		this.stage.setScene(new Scene(loader.load()));

		TasksToBeCarryOutScheduleController controller = loader.getController();
		controller.initializeData(this.taskToBeCarryOutServices, this.typeOfPlantsServices, this.typeOfTasksServices, this.stage, this.bundle);

		this.stage.show();
	}

	/**
	 * The on click global representation action.
	 * 
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void onGlobalRepresentationAction() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fr/loicdelorme/followUpYourGarden/views/GlobalRepresentation.fxml"));

		this.stage.setScene(new Scene(loader.load()));

		GlobalRepresentationController controller = loader.getController();
		controller.initializeData(this.stage, this.bundle);

		this.stage.show();
	}

	/**
	 * Update the groups of plants.
	 */
	private void updateGroupsOfPlants()
	{
		List<GroupOfPlants> groupsOfPlants = null;
		try
		{
			groupsOfPlants = this.groupOfPlantsServices.getGroupsOfPlants();
		}
		catch (ClassNotFoundException | IOException | SQLException e)
		{
			this.processException(e);
		}

		this.root.getChildren().clear();

		TreeItem<Object> groupOfPlantsTreeItem;
		for (GroupOfPlants currentGroupOfPlants : groupsOfPlants)
		{
			groupOfPlantsTreeItem = new TreeItem<Object>(currentGroupOfPlants);

			for (TypeOfPlants currentTypeOfPlants : currentGroupOfPlants.getTypesOfPlants())
			{
				groupOfPlantsTreeItem.getChildren().add(new TreeItem<Object>(currentTypeOfPlants));
			}

			groupOfPlantsTreeItem.setExpanded(true);

			this.root.getChildren().add(groupOfPlantsTreeItem);
		}
	}
}