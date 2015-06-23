package fr.loicdelorme.followUpYourGarden.controllers;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import fr.loicdelorme.followUpYourGarden.core.models.GroupOfPlants;
import fr.loicdelorme.followUpYourGarden.core.models.TypeOfPlants;
import fr.loicdelorme.followUpYourGarden.core.services.GroupOfPlantsServices;
import fr.loicdelorme.followUpYourGarden.core.services.TypeOfPlantsServices;
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
	 * The type of plants services.
	 */
	private TypeOfPlantsServices typeOfPlantsServices;

	/**
	 * Initialize data.
	 * 
	 * @param groupOfPlants
	 *            The group of plants.
	 * @param groupOfPlantsServices
	 *            The group of plants services.
	 * @param typeOfPlantsServices
	 *            The type of plants services.
	 * @param stage
	 *            The stage.
	 * @param bundle
	 *            The bundle.
	 */
	public void initializeData(GroupOfPlants groupOfPlants, GroupOfPlantsServices groupOfPlantsServices, TypeOfPlantsServices typeOfPlantsServices, Stage stage, ResourceBundle bundle)
	{
		this.groupOfPlants = groupOfPlants;
		this.groupOfPlantsServices = groupOfPlantsServices;
		this.typeOfPlantsServices = typeOfPlantsServices;
		this.stage = stage;
		this.bundle = bundle;

		if (this.groupOfPlants == null)
		{
			this.title.setText(this.bundle.getString("groupOfPlantsAdditionFormTitle"));
			this.wording.setPromptText(this.bundle.getString("groupOfPlantsWordingPromptText"));
			this.typesOfPlantsLabel.setText(this.bundle.getString("groupOfPlantsTypesOfPlantsAdditionLabel"));
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
			this.isUpdateForm = true;
		}

		this.wordingLabel.setText(this.bundle.getString("groupOfPlantsWordingLabel"));
		this.plantingDateLabel.setText(this.bundle.getString("groupOfPlantsPlantingDateLabel"));
		this.imageLabel.setText(this.bundle.getString("groupOfPlantsImageLabel"));
		this.image.setText(this.bundle.getString("groupOfPlantsImage"));
		this.iconColorLabel.setText(this.bundle.getString("groupOfPlantsIconColorLabel"));
		this.valid.setText(this.bundle.getString("groupOfPlantsValidButton"));
		this.cancel.setText(this.bundle.getString("groupOfPlantsCancelButton"));

		initializeTypesOfPlants();
		this.typesOfPlants.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		this.stage.setResizable(false);
	}

	/**
	 * Initialize types of plants.
	 */
	private void initializeTypesOfPlants()
	{
		List<TypeOfPlants> typesOfPlants = null;

		if (this.groupOfPlants == null)
		{
			try
			{
				typesOfPlants = this.typeOfPlantsServices.getTypesOfPlants();
			}
			catch (ClassNotFoundException | IOException | SQLException e)
			{
				this.processException(e);
			}
		}
		else
		{
			typesOfPlants = this.groupOfPlants.getTypesOfPlants();
		}

		this.typesOfPlants.getItems().clear();
		this.typesOfPlants.getItems().addAll(typesOfPlants);
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
				this.groupOfPlantsServices.updateGroupOfPlants(this.wording.getText().trim(), this.plantingDate.getValue(), this.imagePath.getText().trim(), this.iconColor.getValue(), this.typesOfPlants.getItems(), null, this.groupOfPlants);
				this.displayInformation(this.bundle.getString("operationSuccess"), null, this.bundle.getString("groupOfPlantsModificationSuccess"));
				this.stage.close();
			}
			else
			{
				this.groupOfPlantsServices.addGroupOfPlants(this.wording.getText().trim(), this.plantingDate.getValue(), this.imagePath.getText().trim(), this.iconColor.getValue(), this.typesOfPlants.getItems(), null);
				this.displayInformation(this.bundle.getString("operationSuccess"), null, this.bundle.getString("groupOfPlantsAdditionSuccess"));
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
