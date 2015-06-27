package fr.loicdelorme.followUpYourGarden.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import fr.loicdelorme.followUpYourGarden.core.helpers.CasesHelper;
import fr.loicdelorme.followUpYourGarden.core.helpers.LegendItemsHelper;
import fr.loicdelorme.followUpYourGarden.core.helpers.TextComputerHelper;
import fr.loicdelorme.followUpYourGarden.core.models.Case;
import fr.loicdelorme.followUpYourGarden.core.models.CaseWithGroupOfPlants;
import fr.loicdelorme.followUpYourGarden.core.models.ContentSelectorType;
import fr.loicdelorme.followUpYourGarden.core.models.GroupOfPlants;
import fr.loicdelorme.followUpYourGarden.core.models.LegendItem;
import fr.loicdelorme.followUpYourGarden.core.models.TaskToBeCarryOut;
import fr.loicdelorme.followUpYourGarden.core.models.TypeOfPlants;
import fr.loicdelorme.followUpYourGarden.core.models.TypeOfTasks;
import fr.loicdelorme.followUpYourGarden.core.services.FollowUpYourGardenServices;

/**
 * This class allow you to control the global representation.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class GlobalRepresentationController extends Controller
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
	 * The groups of plants.
	 */
	@FXML
	private ListView<GroupOfPlants> groupsOfPlants;

	/**
	 * The apply filter button.
	 */
	@FXML
	private Button applyFilter;

	/**
	 * The cancel filter button.
	 */
	@FXML
	private Button cancelFilter;

	/**
	 * The global representation.
	 */
	@FXML
	private GridPane globalRepresentation;

	/**
	 * The legend.
	 */
	@FXML
	private ListView<LegendItem> legend;

	/**
	 * The groups of plants summary button.
	 */
	@FXML
	private Button groupsOfPlantsSummary;

	/**
	 * The task to be carry out schedule button.
	 */
	@FXML
	private Button tasksToBeCarryOutSchedule;

	/**
	 * The follow up your garden services.
	 */
	private FollowUpYourGardenServices followUpYourGardenServices;

	/**
	 * The width.
	 */
	private int width;

	/**
	 * The height.
	 */
	private int height;

	/**
	 * The list of all groups of plants.
	 */
	private List<GroupOfPlants> allGroupsOfPlants;

	/**
	 * The list of all legend items.
	 */
	private List<LegendItem> allLegendItems;

	/**
	 * The home.
	 */
	private GroupOfPlants home;

	/**
	 * Initialize data.
	 * 
	 * @param width
	 *            The width.
	 * @param height
	 *            The height.
	 * @param followUpYourGardenServices
	 *            The follow up your garden services.
	 * @param stage
	 *            The stage.
	 * @param bundle
	 *            The bundle.
	 */
	public void initializeData(int width, int height, FollowUpYourGardenServices followUpYourGardenServices, Stage stage, ResourceBundle bundle)
	{
		this.width = width;
		this.height = height;
		this.followUpYourGardenServices = followUpYourGardenServices;
		this.stage = stage;
		this.bundle = bundle;

		this.actions.setText(this.bundle.getString("globalRepresentationMenuActions"));
		this.quit.setText(this.bundle.getString("globalRepresentationMenuItemQuit"));
		this.typeOfPlants.setText(this.bundle.getString("globalRepresentationMenuTypeOfPlants"));
		this.addTypeOfPlants.setText(this.bundle.getString("globalRepresentationMenuItemAddTypeOfPlants"));
		this.updateTypeOfPlants.setText(this.bundle.getString("globalRepresentationMenuItemUpdateTypeOfPlants"));
		this.removeTypeOfPlants.setText(this.bundle.getString("globalRepresentationMenuItemRemoveTypeOfPlants"));
		this.typeOfTasks.setText(this.bundle.getString("globalRepresentationMenuTypeOfTasks"));
		this.addTypeOfTasks.setText(this.bundle.getString("globalRepresentationMenuItemAddTypeOfTasks"));
		this.updateTypeOfTasks.setText(this.bundle.getString("globalRepresentationMenuItemUpdateTypeOfTasks"));
		this.removeTypeOfTasks.setText(this.bundle.getString("globalRepresentationMenuItemRemoveTypeOfTasks"));
		this.help.setText(this.bundle.getString("globalRepresentationMenuHelp"));
		this.documentation.setText(this.bundle.getString("globalRepresentationMenuItemDocumentation"));
		this.about.setText(this.bundle.getString("globalRepresentationMenuItemAbout"));

		this.tasksToBeCarryOutSchedule.setText(this.bundle.getString("globalRepresentationTaskToBeCarryOutScheduleButton"));
		this.groupsOfPlantsSummary.setText(this.bundle.getString("globalRepresentationGroupsOfPlantsSummaryButton"));
		this.applyFilter.setText(this.bundle.getString("globalRepresentationApplyFilterButton"));
		this.cancelFilter.setText(this.bundle.getString("globalRepresentationCancelFilterButton"));

		this.cancelFilter.setDisable(true);

		try
		{
			this.allGroupsOfPlants = this.followUpYourGardenServices.getGroupOfPlantsServices().getGroupsOfPlants();
		}
		catch (ClassNotFoundException | IOException | SQLException e)
		{
			this.processException(e);
		}

		this.allLegendItems = LegendItemsHelper.generateLegend(this.allGroupsOfPlants);
		this.groupsOfPlants.getItems().addAll(this.allGroupsOfPlants);

		updateGlobalRepresentation(CasesHelper.generateGlobalRepresentationCases(this.width, this.height, this.allGroupsOfPlants), this.allLegendItems);

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
		controller.initializeData(null, this.followUpYourGardenServices.getTypeOfPlantsServices(), stage, this.bundle);

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
			typesOfPlants = this.followUpYourGardenServices.getTypeOfPlantsServices().getTypesOfPlants();
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
			controller_.initializeData(typeOfPlantsToUpdate, this.followUpYourGardenServices.getTypeOfPlantsServices(), stage_, this.bundle);

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
			typesOfPlants = this.followUpYourGardenServices.getTypeOfPlantsServices().getTypesOfPlants();
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
				this.followUpYourGardenServices.getTypeOfPlantsServices().removeTypeOfPlants(typeOfPlantsToRemove);
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
		controller.initializeData(null, this.followUpYourGardenServices.getTypeOfTasksServices(), stage, this.bundle);

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
			typesOfTasks = this.followUpYourGardenServices.getTypeOfTasksServices().getTypesOfTasks();
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
			controller_.initializeData(typeOfTasksToUpdate, this.followUpYourGardenServices.getTypeOfTasksServices(), stage_, this.bundle);

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
			typesOfTasks = this.followUpYourGardenServices.getTypeOfTasksServices().getTypesOfTasks();
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
				this.followUpYourGardenServices.getTypeOfTasksServices().removeTypeOfTasks(typeOfTasksToRemove);
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
	 * The on click apply filter action.
	 */
	public void onApplyFilterAction()
	{
		GroupOfPlants selectedItem = this.groupsOfPlants.getSelectionModel().getSelectedItem();
		if (selectedItem != null)
		{
			if (this.home == null)
			{
				initializeHomeAttribute();
			}

			List<GroupOfPlants> groupsOfPlants = new ArrayList<GroupOfPlants>();
			groupsOfPlants.add(this.home);
			groupsOfPlants.add(selectedItem);

			updateGlobalRepresentation(CasesHelper.generateGlobalRepresentationCases(this.width, this.height, groupsOfPlants), LegendItemsHelper.generateLegend(groupsOfPlants));

			this.cancelFilter.setDisable(false);
		}
		else
		{
			this.displayError(this.bundle.getString("invalidSelectionTitle"), this.bundle.getString("invalidGroupOfPlantsSelectionContent"));
		}
	}

	/**
	 * The on click cancel filter action.
	 */
	public void onCancelFilterAction()
	{
		this.groupsOfPlants.getSelectionModel().clearSelection();

		updateGlobalRepresentation(CasesHelper.generateGlobalRepresentationCases(this.width, this.height, this.allGroupsOfPlants), this.allLegendItems);

		this.cancelFilter.setDisable(true);
	}

	/**
	 * The on click task to be carry out schedule action.
	 * 
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void onTasksToBeCarryOutScheduleAction() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fr/loicdelorme/followUpYourGarden/views/TasksToBeCarryOutSchedule.fxml"));

		this.stage.setScene(new Scene(loader.load()));

		TasksToBeCarryOutScheduleController controller = loader.getController();
		controller.initializeData(this.width, this.height, this.followUpYourGardenServices, this.stage, this.bundle);

		this.stage.show();
	}

	/**
	 * The on click groups of plants summary action.
	 * 
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void onGroupsOfPlantsSummaryAction() throws IOException
	{
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fr/loicdelorme/followUpYourGarden/views/GroupsOfPlantsSummary.fxml"));

		this.stage.setScene(new Scene(loader.load()));

		GroupOfPlantsSummaryController controller = loader.getController();
		controller.initializeData(this.width, this.height, this.followUpYourGardenServices, this.stage, this.bundle);

		this.stage.show();
	}

	/**
	 * Update the global representation.
	 * 
	 * @param cases
	 *            The list of cases.
	 * @param legendItems
	 *            The legend items.
	 */
	private void updateGlobalRepresentation(List<Case> cases, List<LegendItem> legendItems)
	{
		this.legend.getItems().clear();
		this.legend.getItems().addAll(legendItems);

		Node grid = this.globalRepresentation.getChildren().get(0);
		this.globalRepresentation.getChildren().clear();
		this.globalRepresentation.getChildren().add(0, grid);

		int index = 0;
		for (int x = 0; x < this.width; x++)
		{
			for (int y = 0; y < this.height; y++)
			{
				Case case_ = cases.get(index);
				case_.setBackground(new Background(new BackgroundFill(case_.getIconColor(), CornerRadii.EMPTY, Insets.EMPTY)));
				case_.setPrefSize(25, 25);

				if (!case_.getIsDisabled())
				{
					case_.setOnAction(e ->
					{
						CaseWithGroupOfPlants caseWithGroupOfPlants = (CaseWithGroupOfPlants) case_;
						List<TaskToBeCarryOut> tasksToBeCarryOut = null;
						try
						{
							tasksToBeCarryOut = this.followUpYourGardenServices.getTaskToBeCarryOutServices().getTasksToBeCarryOut(caseWithGroupOfPlants.getGroupOfPlants().getId());
						}
						catch (ClassNotFoundException | IOException | SQLException e1)
						{
							this.processException(e1);
						}

						this.displayInformation(this.bundle.getString("globalRepresentationComplementaryInformationTitle"), this.bundle.getString("globalRepresentationComplementaryInformationList"), TextComputerHelper.computeTasksToBeCarryOutText(tasksToBeCarryOut));
					});
				}

				this.globalRepresentation.add(case_, y, x);
				index++;
			}
		}
	}

	/**
	 * Initialize the home attribute.
	 */
	private void initializeHomeAttribute()
	{
		for (GroupOfPlants currentGroupOfPlants : this.allGroupsOfPlants)
		{
			if (currentGroupOfPlants.getWording().equals("maison"))
			{
				this.home = currentGroupOfPlants;
				break;
			}
		}
	}
}
