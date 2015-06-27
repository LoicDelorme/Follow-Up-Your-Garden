package fr.loicdelorme.followUpYourGarden;

import java.util.ResourceBundle;
import java.util.UUID;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import fr.loicdelorme.followUpYourGarden.controllers.GroupsOfPlantsSummaryController;
import fr.loicdelorme.followUpYourGarden.core.helpers.DialogsHelper;
import fr.loicdelorme.followUpYourGarden.core.helpers.FileWriterHelper;
import fr.loicdelorme.followUpYourGarden.core.language.MyResourceBundle;
import fr.loicdelorme.followUpYourGarden.core.properties.IDimensionsReader;
import fr.loicdelorme.followUpYourGarden.core.properties.dimensions.DimensionsPropertiesReader;
import fr.loicdelorme.followUpYourGarden.core.services.FollowUpYourGardenServices;

/**
 * This class allow you to launch the application.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class Launcher extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			IDimensionsReader dimensionsReader = new DimensionsPropertiesReader();

			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fr/loicdelorme/followUpYourGarden/views/GroupsOfPlantsSummary.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(loader.load()));

			GroupsOfPlantsSummaryController controller = loader.getController();
			controller.initializeData(dimensionsReader.getWidth(), dimensionsReader.getHeight(), new FollowUpYourGardenServices(), stage, MyResourceBundle.getBundle());

			stage.showAndWait();
		}
		catch (Exception e)
		{
			// Nothing.
		}
	}

	/**
	 * The entry of the application.
	 * 
	 * @param args
	 *            Some arguments.
	 */
	public static void main(String[] args)
	{
		ResourceBundle bundle = MyResourceBundle.getBundle();

		try
		{
			launch(args);
		}
		catch (Exception e)
		{
			FileWriterHelper.writeContent(bundle.getString("errorFilePath"), UUID.randomUUID().toString(), bundle.getString("errorFileExtension"), e.getMessage());
			Alert alert = DialogsHelper.generateErrorDialog(bundle.getString("unknownErrorTitle"), bundle.getString("unknownErrorHeader"), bundle.getString("unknownErrorContent"));
			alert.showAndWait();

			System.exit(1);
		}
	}
}
