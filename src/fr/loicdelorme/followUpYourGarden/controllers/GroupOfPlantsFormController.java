package fr.loicdelorme.followUpYourGarden.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import fr.loicdelorme.followUpYourGarden.core.models.Case;
import fr.loicdelorme.followUpYourGarden.core.models.GroupOfPlants;
import fr.loicdelorme.followUpYourGarden.core.models.Position;
import fr.loicdelorme.followUpYourGarden.core.models.TypeOfPlants;
import fr.loicdelorme.followUpYourGarden.core.services.GroupOfPlantsServices;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidGroupOfPlantsBlueLevelException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidGroupOfPlantsGreenLevelException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidGroupOfPlantsIdException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidGroupOfPlantsPathException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidGroupOfPlantsPositionsException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidGroupOfPlantsRedLevelException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidGroupOfPlantsTypesOfPlantsException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidGroupOfPlantsWordingException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingGroupOfPlantsIconColorException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingGroupOfPlantsIdException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingGroupOfPlantsPathException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingGroupOfPlantsPlantingDateException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingGroupOfPlantsPositionsException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingGroupOfPlantsTypesOfPlantsException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingGroupOfPlantsWordingException;

/**
 * This class allow you to control the group of plants form.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class GroupOfPlantsFormController extends Controller
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
	 * The planting date label.
	 */
	@FXML
	private Label plantingDateLabel;

	/**
	 * The planting date.
	 */
	@FXML
	private DatePicker plantingDate;

	/**
	 * The image label.
	 */
	@FXML
	private Label imageLabel;

	/**
	 * The image.
	 */
	@FXML
	private Button image;

	/**
	 * The image path.
	 */
	@FXML
	private Label imagePath;

	/**
	 * The icon color label.
	 */
	@FXML
	private Label iconColorLabel;

	/**
	 * The icon color.
	 */
	@FXML
	private ColorPicker iconColor;

	/**
	 * The types of plants label.
	 */
	@FXML
	private Label typesOfPlantsLabel;

	/**
	 * The types of plants.
	 */
	@FXML
	private ListView<TypeOfPlants> typesOfPlants;

	/**
	 * The positions.
	 */
	@FXML
	private GridPane positions;

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
	 * The group of plants.
	 */
	private GroupOfPlants groupOfPlants;

	/**
	 * The group of plants services.
	 */
	private GroupOfPlantsServices groupOfPlantsServices;

	/**
	 * The final positions.
	 */
	private List<Position> finalPositions;

	/**
	 * The icon color to set.
	 */
	private Color iconColorToSet;

	/**
	 * Initialize data.
	 * 
	 * @param groupOfPlants
	 *            The group of plants.
	 * @param typesOfPlants
	 *            The list of types of plants.
	 * @param cases
	 *            The list of cases.
	 * @param width
	 *            The width.
	 * @param height
	 *            The height.
	 * @param groupOfPlantsServices
	 *            The group of plants services.
	 * @param stage
	 *            The stage.
	 * @param bundle
	 *            The bundle.
	 */
	public void initializeData(GroupOfPlants groupOfPlants, List<TypeOfPlants> typesOfPlants, List<Case> cases, int width, int height, GroupOfPlantsServices groupOfPlantsServices, Stage stage, ResourceBundle bundle)
	{
		this.groupOfPlants = groupOfPlants;
		this.groupOfPlantsServices = groupOfPlantsServices;
		this.finalPositions = new ArrayList<Position>();
		this.stage = stage;
		this.bundle = bundle;

		if (this.groupOfPlants == null)
		{
			this.title.setText(this.bundle.getString("groupOfPlantsAdditionFormTitle"));
			this.wording.setPromptText(this.bundle.getString("groupOfPlantsWordingPromptText"));
			this.typesOfPlantsLabel.setText(this.bundle.getString("groupOfPlantsTypesOfPlantsAdditionLabel"));
			this.iconColorToSet = Color.GREEN;
			this.isUpdateForm = false;
		}
		else
		{
			this.title.setText(this.bundle.getString("groupOfPlantsModificationFormTitle"));
			this.wording.setText(this.groupOfPlants.getWording());
			this.plantingDate.setValue(this.groupOfPlants.getPlantingDate());
			this.imagePath.setText(this.groupOfPlants.getPath());
			this.iconColor.setValue(this.groupOfPlants.getIconColor());
			this.typesOfPlantsLabel.setText(this.bundle.getString("groupOfPlantsTypesOfPlantsModificationLabel"));
			this.finalPositions.addAll(this.groupOfPlants.getPositions());
			this.iconColorToSet = this.groupOfPlants.getIconColor();
			this.isUpdateForm = true;
		}

		this.wordingLabel.setText(this.bundle.getString("groupOfPlantsWordingLabel"));
		this.plantingDateLabel.setText(this.bundle.getString("groupOfPlantsPlantingDateLabel"));
		this.imageLabel.setText(this.bundle.getString("groupOfPlantsImageLabel"));
		this.image.setText(this.bundle.getString("groupOfPlantsImage"));
		this.iconColorLabel.setText(this.bundle.getString("groupOfPlantsIconColorLabel"));
		this.valid.setText(this.bundle.getString("groupOfPlantsValidButton"));
		this.cancel.setText(this.bundle.getString("groupOfPlantsCancelButton"));

		this.typesOfPlants.getItems().clear();
		this.typesOfPlants.getItems().addAll(typesOfPlants);
		this.typesOfPlants.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		int index = 0;
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				Case case_ = cases.get(index);
				case_.setBackground(new Background(new BackgroundFill(case_.getIconColor(), CornerRadii.EMPTY, Insets.EMPTY)));
				case_.setPrefSize(25, 25);

				if (!case_.getIsDisabled())
				{
					case_.setOnAction(e ->
					{
						if (case_.getIsActivated())
						{
							this.finalPositions.remove(case_.getPosition());
							case_.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
						}
						else
						{
							this.finalPositions.add(case_.getPosition());
							case_.setBackground(new Background(new BackgroundFill(this.iconColorToSet, CornerRadii.EMPTY, Insets.EMPTY)));
						}

						case_.setIsActivated(!case_.getIsActivated());
					});
				}

				this.positions.add(case_, y, x);
				index++;
			}
		}

		this.stage.setResizable(false);
	}

	/**
	 * The on click image action.
	 */
	public void onImageAction()
	{
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extensionsFilter = new FileChooser.ExtensionFilter(this.bundle.getString("groupOfPlantsExtensions"), "*.jpg", "*.jpeg", "*.png", "*.gif", "*.bmp");

		fileChooser.getExtensionFilters().add(extensionsFilter);

		File selectedFile = fileChooser.showOpenDialog(this.stage);
		if (selectedFile != null)
		{
			this.imagePath.setText(selectedFile.getAbsolutePath());
		}
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
				List<TypeOfPlants> finalTypesOfPlants = new ArrayList<TypeOfPlants>();
				finalTypesOfPlants.addAll(this.groupOfPlants.getTypesOfPlants());
				finalTypesOfPlants.removeAll(this.typesOfPlants.getSelectionModel().getSelectedItems());

				this.groupOfPlantsServices.updateGroupOfPlants(this.wording.getText().trim(), this.plantingDate.getValue(), this.imagePath.getText().trim(), this.iconColor.getValue(), finalTypesOfPlants, this.finalPositions, this.groupOfPlants);
				this.displayInformation(this.bundle.getString("operationSuccess"), this.bundle.getString("groupOfPlantsModificationSuccess"));
				this.stage.close();
			}
			else
			{
				this.groupOfPlantsServices.addGroupOfPlants(this.wording.getText().trim(), this.plantingDate.getValue(), this.imagePath.getText().trim(), this.iconColor.getValue(), new ArrayList<TypeOfPlants>(this.typesOfPlants.getSelectionModel().getSelectedItems()), this.finalPositions);
				this.displayInformation(this.bundle.getString("operationSuccess"), this.bundle.getString("groupOfPlantsAdditionSuccess"));
				this.stage.close();
			}
		}
		catch (MissingGroupOfPlantsIdException | MissingGroupOfPlantsWordingException | MissingGroupOfPlantsPlantingDateException | MissingGroupOfPlantsPathException | MissingGroupOfPlantsIconColorException | MissingGroupOfPlantsTypesOfPlantsException | MissingGroupOfPlantsPositionsException | InvalidGroupOfPlantsIdException | InvalidGroupOfPlantsWordingException | InvalidGroupOfPlantsPathException | InvalidGroupOfPlantsRedLevelException | InvalidGroupOfPlantsGreenLevelException | InvalidGroupOfPlantsBlueLevelException | InvalidGroupOfPlantsTypesOfPlantsException | InvalidGroupOfPlantsPositionsException e)
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
