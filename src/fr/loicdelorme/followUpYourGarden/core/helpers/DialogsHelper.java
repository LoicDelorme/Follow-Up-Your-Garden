package fr.loicdelorme.followUpYourGarden.core.helpers;

import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * This class allow you to handle dialogs.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class DialogsHelper
{
	/**
	 * Generate an information dialog.
	 * 
	 * @param title
	 *            The title.
	 * @param header
	 *            The header.
	 * @param content
	 *            The content.
	 * @return An alert.
	 */
	public static Alert generateInformationDialog(String title, String header, String content)
	{
		return generateAlert(AlertType.INFORMATION, title, header, content);
	}

	/**
	 * Generate an information dialog.
	 * 
	 * @param title
	 *            The title.
	 * @param content
	 *            The content.
	 * @return An alert.
	 */
	public static Alert generateInformationDialog(String title, String content)
	{
		return generateInformationDialog(title, null, content);
	}

	/**
	 * Generate a warning dialog.
	 * 
	 * @param title
	 *            The title.
	 * @param header
	 *            The header.
	 * @param content
	 *            The content.
	 * @return An alert.
	 */
	public static Alert generateWarningDialog(String title, String header, String content)
	{
		return generateAlert(AlertType.WARNING, title, header, content);
	}

	/**
	 * Generate a warning dialog.
	 * 
	 * @param title
	 *            The title.
	 * @param content
	 *            The content.
	 * @return An alert.
	 */
	public static Alert generateWarningDialog(String title, String content)
	{
		return generateWarningDialog(title, null, content);
	}

	/**
	 * Generate an error dialog.
	 * 
	 * @param title
	 *            The title.
	 * @param header
	 *            The header.
	 * @param content
	 *            The content.
	 * @return An alert.
	 */
	public static Alert generateErrorDialog(String title, String header, String content)
	{
		return generateAlert(AlertType.ERROR, title, header, content);
	}

	/**
	 * Generate an error dialog.
	 * 
	 * @param title
	 *            The title.
	 * @param content
	 *            The content.
	 * @return An alert.
	 */
	public static Alert generateErrorDialog(String title, String content)
	{
		return generateErrorDialog(title, null, content);
	}

	/**
	 * Generate an exception dialog.
	 * 
	 * @param title
	 *            The title.
	 * @param header
	 *            The header.
	 * @param content
	 *            The content.
	 * @param stacktrace
	 *            The stacktrace.
	 * @return An alert.
	 */
	public static Alert generateExceptionDialog(String title, String header, String content, String stacktrace)
	{
		Alert alert = generateAlert(AlertType.ERROR, title, header, content);

		Label label = new Label("The exception stacktrace was:");

		TextArea exceptionStacktrace = new TextArea(stacktrace);
		exceptionStacktrace.setEditable(false);
		exceptionStacktrace.setWrapText(true);
		exceptionStacktrace.setMaxWidth(Double.MAX_VALUE);
		exceptionStacktrace.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(exceptionStacktrace, Priority.ALWAYS);
		GridPane.setHgrow(exceptionStacktrace, Priority.ALWAYS);

		GridPane pane = new GridPane();
		pane.setMaxWidth(Double.MAX_VALUE);
		pane.add(label, 0, 0);
		pane.add(exceptionStacktrace, 0, 1);

		alert.getDialogPane().setExpandableContent(pane);

		return alert;
	}

	/**
	 * Generate an exception dialog.
	 * 
	 * @param title
	 *            The title.
	 * @param content
	 *            The content.
	 * @param stacktrace
	 *            The stacktrace.
	 * @return An alert.
	 */
	public static Alert generateExceptionDialog(String title, String content, String stacktrace)
	{
		return generateExceptionDialog(title, null, content, stacktrace);
	}

	/**
	 * Generate a confirmation dialog.
	 * 
	 * @param title
	 *            The title.
	 * @param header
	 *            The header.
	 * @param content
	 *            The content.
	 * @return An alert.
	 */
	public static Alert generateConfirmationDialog(String title, String header, String content)
	{
		return generateAlert(AlertType.CONFIRMATION, title, header, content);
	}

	/**
	 * Generate a confirmation dialog.
	 * 
	 * @param title
	 *            The title.
	 * @param content
	 *            The content.
	 * @return An alert.
	 */
	public static Alert generateConfirmationDialog(String title, String content)
	{
		return generateConfirmationDialog(title, null, content);
	}

	/**
	 * Generate a confirmation dialog with custom actions.
	 * 
	 * @param title
	 *            The title.
	 * @param header
	 *            The header.
	 * @param content
	 *            The content.
	 * @param buttonsType
	 *            The list of buttons type.
	 * @return An alert.
	 */
	public static Alert generateConfirmationDialogWithCustomActions(String title, String header, String content, List<ButtonType> buttonsType)
	{
		Alert alert = generateConfirmationDialog(title, header, content);
		alert.getButtonTypes().addAll(buttonsType);

		return alert;
	}

	/**
	 * Generate a confirmation dialog with custom actions.
	 * 
	 * @param title
	 *            The title.
	 * @param content
	 *            The content.
	 * @param buttonsType
	 *            The list of buttons type.
	 * @return An alert.
	 */
	public static Alert generateConfirmationDialogWithCustomActions(String title, String content, List<ButtonType> buttonsType)
	{
		return generateConfirmationDialogWithCustomActions(title, null, content, buttonsType);
	}

	/**
	 * Generate an input dialog.
	 * 
	 * @param title
	 *            The title.
	 * @param header
	 *            The header.
	 * @param content
	 *            The content.
	 * @return An input dialog.
	 */
	public static TextInputDialog generateInputDialog(String title, String header, String content)
	{
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(title);
		dialog.setHeaderText(header);
		dialog.setContentText(content);

		return dialog;
	}

	/**
	 * Generate an input dialog.
	 * 
	 * @param title
	 *            The title.
	 * @param content
	 *            The content.
	 * @return An input dialog.
	 */
	public static TextInputDialog generateInputDialog(String title, String content)
	{
		return generateInputDialog(title, null, content);
	}

	/**
	 * Generate a choice dialog.
	 * 
	 * @param title
	 *            The title.
	 * @param header
	 *            The header.
	 * @param content
	 *            The content.
	 * @param choices
	 *            The list of choices.
	 * @return A choice dialog.
	 */
	public static ChoiceDialog<String> generateChoiceDialog(String title, String header, String content, List<String> choices)
	{
		ChoiceDialog<String> dialog = new ChoiceDialog<String>(choices.get(0), choices);
		dialog.setTitle(title);
		dialog.setHeaderText(header);
		dialog.setContentText(content);

		return dialog;
	}

	/**
	 * Generate a choice dialog.
	 * 
	 * @param title
	 *            The title.
	 * @param content
	 *            The content.
	 * @param choices
	 *            The list of choices.
	 * @return A choice dialog.
	 */
	public static ChoiceDialog<String> generateChoiceDialog(String title, String content, List<String> choices)
	{
		return generateChoiceDialog(title, null, content, choices);
	}

	/**
	 * Generate an alert dialog.
	 * 
	 * @param alertType
	 *            The alert type.
	 * 
	 * @param title
	 *            The title.
	 * @param header
	 *            The header.
	 * @param content
	 *            The content.
	 * @return An alert.
	 */
	private static Alert generateAlert(AlertType alertType, String title, String header, String content)
	{
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);

		return alert;
	}
}
