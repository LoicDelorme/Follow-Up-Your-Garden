package fr.loicdelorme.followUpYourGarden.controllers;

import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import fr.loicdelorme.followUpYourGarden.core.models.ContentSelectorType;

/**
 * This class allow you to control the content selector.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 * @param <T>
 *            The type of content.
 */
public class ContentSelectorController<T> extends Controller
{
	/**
	 * The title.
	 */
	@FXML
	private Label title;

	/**
	 * The choice box.
	 */
	@FXML
	private ChoiceBox<T> choiceBox;

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
	 * The selected value.
	 */
	private T selectedValue;

	/**
	 * Initialize data.
	 * 
	 * @param data
	 *            The list of data.
	 * @param contentSelectorType
	 *            The content selector type.
	 * @param stage
	 *            The stage.
	 * @param bundle
	 *            The bundle.
	 */
	public void initializeData(List<T> data, ContentSelectorType contentSelectorType, Stage stage, ResourceBundle bundle)
	{
		this.stage = stage;
		this.bundle = bundle;

		initializeTitle(contentSelectorType);

		this.choiceBox.getItems().addAll(data);
		this.choiceBox.setValue(data.get(0));

		this.valid.setText(this.bundle.getString("contentSelectorValidButton"));
		this.cancel.setText(this.bundle.getString("contentSelectorCancelButton"));

		this.stage.setResizable(false);
	}

	/**
	 * Initialize the title.
	 * 
	 * @param contentSelectorType
	 *            The content selector type.
	 */
	private void initializeTitle(ContentSelectorType contentSelectorType)
	{
		switch (contentSelectorType)
		{
			case UPDATE_TYPE_OF_PLANTS:
				this.title.setText(this.bundle.getString("updateTypeOfPlantsTitle"));
				break;
			case UPDATE_TYPE_OF_TASKS:
				this.title.setText(this.bundle.getString("updateTypeOfTasksTitle"));
				break;
			case REMOVE_TYPE_OF_PLANTS:
				this.title.setText(this.bundle.getString("removeTypeOfPlantsTitle"));
				break;
			case REMOVE_TYPE_OF_TASKS:
				this.title.setText(this.bundle.getString("removeTypeOfTasksTitle"));
				break;
			default:
				break;
		}
	}

	/**
	 * Get the selected value.
	 * 
	 * @return The selected value or NULL if the user close the window.
	 */
	public T getSelectedValue()
	{
		return this.selectedValue;
	}

	/**
	 * The on click valid action.
	 */
	public void onValidAction()
	{
		this.selectedValue = this.choiceBox.getValue();
		this.stage.close();
	}

	/**
	 * The on click cancel action.
	 */
	public void onCancelAction()
	{
		this.selectedValue = null;
		this.stage.close();
	}
}
