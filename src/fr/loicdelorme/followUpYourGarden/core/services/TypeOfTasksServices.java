package fr.loicdelorme.followUpYourGarden.core.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import fr.loicdelorme.followUpYourGarden.core.database.MyDatabase;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITypeOfTasksManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.sources.ISourceManipulator;
import fr.loicdelorme.followUpYourGarden.core.models.TypeOfTasks;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidTypeOfTasksDescriptionException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidTypeOfTasksIdException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidTypeOfTasksWordingException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTypeOfTasksDescriptionException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTypeOfTasksIdException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTypeOfTasksWordingException;

/**
 * This class allow you to handle types of tasks.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class TypeOfTasksServices
{
	/**
	 * The minimal id.
	 */
	private static final int MINIMAL_ID = 1;

	/**
	 * A type of tasks manipulator.
	 */
	private ITypeOfTasksManipulator typeOfTasksManipulator;

	/**
	 * Create a type of tasks services.
	 * 
	 * @param typeOfTasksManipulator
	 *            A type of tasks manipulator.
	 */
	public TypeOfTasksServices(ITypeOfTasksManipulator typeOfTasksManipulator)
	{
		this.typeOfTasksManipulator = typeOfTasksManipulator;
	}

	/**
	 * Get all types of tasks.
	 * 
	 * @return A list of types of tasks.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 * @throws ClassNotFoundException
	 *             If the class is not found.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public List<TypeOfTasks> getTypesOfTasks() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException
	{
		ISourceManipulator sourceManipulator = MyDatabase.getInstance();
		sourceManipulator.openConnection();

		this.typeOfTasksManipulator.setConnection(sourceManipulator.getConnection());
		List<TypeOfTasks> typesOfTasks = this.typeOfTasksManipulator.getTypesOfTasks();

		sourceManipulator.closeConnection();

		return typesOfTasks;
	}

	/**
	 * Add a type of tasks.
	 * 
	 * @param wording
	 *            The wording.
	 * @param description
	 *            The description.
	 * @throws MissingTypeOfTasksIdException
	 *             The id is missing.
	 * @throws MissingTypeOfTasksWordingException
	 *             The wording is missing.
	 * @throws MissingTypeOfTasksDescriptionException
	 *             The description is missing.
	 * @throws InvalidTypeOfTasksIdException
	 *             The id is negative.
	 * @throws InvalidTypeOfTasksWordingException
	 *             The wording is empty.
	 * @throws InvalidTypeOfTasksDescriptionException
	 *             The description is empty.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 * @throws ClassNotFoundException
	 *             If the class is not found.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void addTypeOfTasks(String wording, String description) throws MissingTypeOfTasksIdException, MissingTypeOfTasksWordingException, MissingTypeOfTasksDescriptionException, InvalidTypeOfTasksIdException, InvalidTypeOfTasksWordingException, InvalidTypeOfTasksDescriptionException, SQLException, ClassNotFoundException, FileNotFoundException, IOException
	{
		checkTypeOfTasksParameters(TypeOfTasks.UNKNOWN_TYPE_OF_TASKS_ID, wording, description);

		ISourceManipulator sourceManipulator = MyDatabase.getInstance();
		sourceManipulator.openConnection();

		this.typeOfTasksManipulator.setConnection(sourceManipulator.getConnection());
		this.typeOfTasksManipulator.addTypeOfTasks(new TypeOfTasks(TypeOfTasks.UNKNOWN_TYPE_OF_TASKS_ID, wording, description));

		sourceManipulator.closeConnection();
	}

	/**
	 * Update a type of tasks.
	 * 
	 * @param wording
	 *            The wording.
	 * @param description
	 *            The description.
	 * @param oldTypeOfTasks
	 *            The old type of tasks.
	 * @throws MissingTypeOfTasksIdException
	 *             The id is missing.
	 * @throws MissingTypeOfTasksWordingException
	 *             The wording is missing.
	 * @throws MissingTypeOfTasksDescriptionException
	 *             The description is missing.
	 * @throws InvalidTypeOfTasksIdException
	 *             The id is negative.
	 * @throws InvalidTypeOfTasksWordingException
	 *             The wording is empty.
	 * @throws InvalidTypeOfTasksDescriptionException
	 *             The description is empty.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 * @throws ClassNotFoundException
	 *             If the class is not found.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void updateTypeOfTasks(String wording, String description, TypeOfTasks oldTypeOfTasks) throws MissingTypeOfTasksIdException, MissingTypeOfTasksWordingException, MissingTypeOfTasksDescriptionException, InvalidTypeOfTasksIdException, InvalidTypeOfTasksWordingException, InvalidTypeOfTasksDescriptionException, SQLException, ClassNotFoundException, FileNotFoundException, IOException
	{
		checkTypeOfTasksParameters(oldTypeOfTasks.getId(), wording, description);

		TypeOfTasks newTypeOfTasks = new TypeOfTasks(oldTypeOfTasks.getId(), wording, description);

		if (!oldTypeOfTasks.equals(newTypeOfTasks))
		{
			ISourceManipulator sourceManipulator = MyDatabase.getInstance();
			sourceManipulator.openConnection();

			this.typeOfTasksManipulator.setConnection(sourceManipulator.getConnection());
			this.typeOfTasksManipulator.updateTypeOfTasks(newTypeOfTasks);

			sourceManipulator.closeConnection();
		}
	}

	/**
	 * Remove a type of tasks.
	 * 
	 * @param typeOfTasksToRemove
	 *            The type of tasks to remove.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 * @throws ClassNotFoundException
	 *             If the class is not found.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void removeTypeOfTasks(TypeOfTasks typeOfTasksToRemove) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException
	{
		ISourceManipulator sourceManipulator = MyDatabase.getInstance();
		sourceManipulator.openConnection();

		this.typeOfTasksManipulator.setConnection(sourceManipulator.getConnection());
		this.typeOfTasksManipulator.removeTypeOfTasks(typeOfTasksToRemove);

		sourceManipulator.closeConnection();
	}

	/**
	 * Check type of tasks parameters.
	 * 
	 * @param id
	 *            The id.
	 * @param wording
	 *            The wording.
	 * @param description
	 *            The description.
	 * @throws MissingTypeOfTasksIdException
	 *             The id is missing.
	 * @throws MissingTypeOfTasksWordingException
	 *             The wording is missing.
	 * @throws MissingTypeOfTasksDescriptionException
	 *             The description is missing.
	 * @throws InvalidTypeOfTasksIdException
	 *             The id is negative.
	 * @throws InvalidTypeOfTasksWordingException
	 *             The wording is empty.
	 * @throws InvalidTypeOfTasksDescriptionException
	 *             The description is empty.
	 */
	private void checkTypeOfTasksParameters(Integer id, String wording, String description) throws MissingTypeOfTasksIdException, MissingTypeOfTasksWordingException, MissingTypeOfTasksDescriptionException, InvalidTypeOfTasksIdException, InvalidTypeOfTasksWordingException, InvalidTypeOfTasksDescriptionException
	{
		checkValuesAreSets(id, wording, description);
		checkValuesAreCorrects(id, wording, description);
	}

	/**
	 * Check values are sets.
	 * 
	 * @param id
	 *            The id.
	 * @param wording
	 *            The wording.
	 * @param description
	 *            The description.
	 * @throws MissingTypeOfTasksIdException
	 *             The id is missing.
	 * @throws MissingTypeOfTasksWordingException
	 *             The wording is missing.
	 * @throws MissingTypeOfTasksDescriptionException
	 *             The description is missing.
	 */
	private void checkValuesAreSets(Integer id, String wording, String description) throws MissingTypeOfTasksIdException, MissingTypeOfTasksWordingException, MissingTypeOfTasksDescriptionException
	{
		if (id == null)
		{
			throw new MissingTypeOfTasksIdException();
		}

		if (wording == null)
		{
			throw new MissingTypeOfTasksWordingException();
		}

		if (description == null)
		{
			throw new MissingTypeOfTasksDescriptionException();
		}
	}

	/**
	 * Check values are corrects.
	 * 
	 * @param id
	 *            The id.
	 * @param wording
	 *            The wording.
	 * @param description
	 *            The description.
	 * @throws InvalidTypeOfTasksIdException
	 *             The id is negative.
	 * @throws InvalidTypeOfTasksWordingException
	 *             The wording is empty.
	 * @throws InvalidTypeOfTasksDescriptionException
	 *             The description is empty.
	 */
	private void checkValuesAreCorrects(Integer id, String wording, String description) throws InvalidTypeOfTasksIdException, InvalidTypeOfTasksWordingException, InvalidTypeOfTasksDescriptionException
	{
		if ((id < MINIMAL_ID) && (id != TypeOfTasks.UNKNOWN_TYPE_OF_TASKS_ID))
		{
			throw new InvalidTypeOfTasksIdException(id);
		}

		if ("".equals(wording))
		{
			throw new InvalidTypeOfTasksWordingException();
		}

		if ("".equals(description))
		{
			throw new InvalidTypeOfTasksDescriptionException();
		}
	}
}