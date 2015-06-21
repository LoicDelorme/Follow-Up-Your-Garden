package fr.loicdelorme.followUpYourGarden.controllers;

/**
 * This interface allow you to handle constants.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public interface IContentController
{
	/**
	 * The form data exception title.
	 */
	public static final String FORM_DATA_EXCEPTION_TITLE = "Invalid form";

	/**
	 * The operation success title.
	 */
	public static final String OPERATION_SUCCESS_TITLE = "Operation success";

	/**
	 * The operation fail title.
	 */
	public static final String OPERATION_FAIL_TITLE = "Operation fail";

	/**
	 * The SQL exception title.
	 */
	public static final String SQL_EXCEPTION_TITLE = "SQL error";

	/**
	 * The class not found exception title.
	 */
	public static final String CLASS_NOT_FOUND_EXCEPTION_TITLE = "Driver error";

	/**
	 * The IO exception title.
	 */
	public static final String IO_EXCEPTION_TITLE = "IO error";

	/**
	 * The form data exception header.
	 */
	public static final String FORM_DATA_EXCEPTION_HEADER = "It seems that the form is invalid!";

	/**
	 * The SQL exception header.
	 */
	public static final String SQL_EXCEPTION_HEADER = "It seems that an SQL exception occured!";

	/**
	 * The class not found exception header.
	 */
	public static final String CLASS_NOT_FOUND_EXCEPTION_HEADER = "It seems that the JDBC driver was not found!";

	/**
	 * The IO exception header.
	 */
	public static final String IO_EXCEPTION_HEADER = "It seems that an IO exception occured!";

	/**
	 * The error file path.
	 */
	public static final String ERROR_FILE_PATH = "./data/errors";

	/**
	 * The error file extension.
	 */
	public static final String ERROR_FILE_EXTENSION = ".txt";

}
