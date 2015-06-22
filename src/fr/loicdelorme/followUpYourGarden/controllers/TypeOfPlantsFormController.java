package fr.loicdelorme.followUpYourGarden.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
public class TypeOfPlantsFormController extends Controller
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
	 * The bundle.
	 */
	private ResourceBundle bundle;

	/**
	 * Create a type of plants form controller.
	 * 
	 * @param typeOfPlants
	 *            The type of plants.
	 * @param typeOfPlantsServices
	 *            The type of plants services.
	 * @param stage
	 *            The stage.
	 * @param bundle
	 *            The bundle.
	 */
	public TypeOfPlantsFormController(TypeOfPlants typeOfPlants, TypeOfPlantsServices typeOfPlantsServices, Stage stage, ResourceBundle bundle)
	{
		this.typeOfPlants = typeOfPlants;
		this.typeOfPlantsServices = typeOfPlantsServices;
		this.stage = stage;
		this.bundle = bundle;

		if (this.typeOfPlants == null)
		{
			this.title.setText(this.bundle.getString("typeOfPlantsAdditionFormTitle"));
			this.wording.setPromptText(this.bundle.getString("typeOfPlantsWordingPromptText"));
			this.isUpdateForm = false;
		}
		else
		{
			this.title.setText(this.bundle.getString("typeOfPlantsModificationFormTitle"));
			this.wording.setText(this.typeOfPlants.getWording());
			this.isUpdateForm = true;
		}

		this.wordingLabel.setText(this.bundle.getString("typeOfPlantsWordingLabel"));
		this.valid.setText(this.bundle.getString("typeOfPlantsValidButton"));
		this.cancel.setText(this.bundle.getString("typeOfPlantsCancelButton"));

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
				this.typeOfPlantsServices.updateTypeOfPlants(this.wording.getText(), this.typeOfPlants);
				this.displayInformation(this.bundle.getString("operationSuccess"), null, this.bundle.getString("typeOfPlantsModificationSuccess"));
				this.stage.close();
			}
			else
			{
				this.typeOfPlantsServices.addTypeOfPlants(this.wording.getText());
				this.displayInformation(this.bundle.getString("operationSuccess"), null, this.bundle.getString("typeOfPlantsAdditionSuccess"));
				this.stage.close();
			}
		}
		catch (MissingTypeOfPlantsIdException | MissingTypeOfPlantsWordingException | InvalidTypeOfPlantsIdException | InvalidTypeOfPlantsWordingException e)
		{
			this.displayError(this.bundle.getString("invalidFormTitle"), this.bundle.getString("invalidFormHeader"), e.getMessage());
		}
		catch (ClassNotFoundException e)
		{
			this.saveError(this.bundle.getString("errorFilePath"), this.bundle.getString("errorFileExtension"), e);
			this.displayError(this.bundle.getString("driverErrorTitle"), this.bundle.getString("driverErrorHeader"), e.getMessage());
		}
		catch (SQLException e)
		{
			this.saveError(this.bundle.getString("errorFilePath"), this.bundle.getString("errorFileExtension"), e);
			this.displayError(this.bundle.getString("sqlErrorTitle"), this.bundle.getString("sqlErrorHeader"), e.getMessage());
		}
		catch (IOException e)
		{
			this.saveError(this.bundle.getString("errorFilePath"), this.bundle.getString("errorFileExtension"), e);
			this.displayError(this.bundle.getString("ioErrorTitle"), this.bundle.getString("ioErrorHeader"), e.getMessage());
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
