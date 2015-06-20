package fr.loicdelorme.followUpYourGarden.core.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import fr.loicdelorme.followUpYourGarden.core.database.MyDatabase;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.ICarriedOutTaskManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.IGroupOfPlantsManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.IPositionManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITypeOfPlantsManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITypeOfTasksManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.sources.ISourceManipulator;
import fr.loicdelorme.followUpYourGarden.core.models.CarriedOutTask;
import fr.loicdelorme.followUpYourGarden.core.models.GroupOfPlants;
import fr.loicdelorme.followUpYourGarden.core.models.TypeOfTasks;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidCarriedOutTaskIdException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingCarriedOutTaskCarriedOutDateException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingCarriedOutTaskDeadlineDateException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingCarriedOutTaskDescriptionException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingCarriedOutTaskGroupOfPlantsException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingCarriedOutTaskIdException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingCarriedOutTaskTypeOfTasksException;

/**
 * This class allow you to handle tasks to be carry out.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class CarriedOutTaskServices
{
	/**
	 * The minimal id.
	 */
	private static final int MINIMAL_ID = 1;

	/**
	 * A carried out task manipulator.
	 */
	private ICarriedOutTaskManipulator carriedOutTaskManipulator;

	/**
	 * A group of plants manipulator.
	 */
	private IGroupOfPlantsManipulator groupOfPlantsManipulator;

	/**
	 * A type of tasks manipulator.
	 */
	private ITypeOfTasksManipulator typeOfTasksManipulator;

	/**
	 * A position manipulator.
	 */
	private IPositionManipulator positionManipulator;

	/**
	 * A type of plants manipulator.
	 */
	private ITypeOfPlantsManipulator typeOfPlantsManipulator;

	/**
	 * Create a carried out task services.
	 * 
	 * @param carriedOutTaskManipulator
	 *            A carried out task manipulator.
	 * @param groupOfPlantsManipulator
	 *            A group of plants manipulator.
	 * @param typeOfTasksManipulator
	 *            A type of tasks manipulator.
	 * @param positionManipulator
	 *            A position manipulator.
	 * @param typeOfPlantsManipulator
	 *            A type of plants manipulator.
	 */
	public CarriedOutTaskServices(ICarriedOutTaskManipulator carriedOutTaskManipulator, IGroupOfPlantsManipulator groupOfPlantsManipulator, ITypeOfTasksManipulator typeOfTasksManipulator, IPositionManipulator positionManipulator, ITypeOfPlantsManipulator typeOfPlantsManipulator)
	{
		this.carriedOutTaskManipulator = carriedOutTaskManipulator;
		this.groupOfPlantsManipulator = groupOfPlantsManipulator;
		this.typeOfTasksManipulator = typeOfTasksManipulator;
		this.positionManipulator = positionManipulator;
		this.typeOfPlantsManipulator = typeOfPlantsManipulator;
	}

	/**
	 * Get all carried out tasks.
	 * 
	 * @return A list of carried out tasks.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 * @throws ClassNotFoundException
	 *             If the class is not found.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public List<CarriedOutTask> getCarriedOutTasks() throws ClassNotFoundException, FileNotFoundException, IOException, SQLException
	{
		ISourceManipulator sourceManipulator = MyDatabase.getInstance();
		sourceManipulator.openConnection();

		this.carriedOutTaskManipulator.setConnection(sourceManipulator.getConnection());
		List<CarriedOutTask> carriedOutTasks = this.carriedOutTaskManipulator.getCarriedOutTasks(this.groupOfPlantsManipulator, this.typeOfTasksManipulator, this.positionManipulator, this.typeOfPlantsManipulator);

		sourceManipulator.closeConnection();

		return carriedOutTasks;
	}

	/**
	 * Get all carried out tasks for a specific group of plants.
	 * 
	 * @param groupOfPlantsId
	 *            The group of plants id.
	 * @return A list of carried out tasks.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 * @throws ClassNotFoundException
	 *             If the class is not found.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public List<CarriedOutTask> getCarriedOutTasks(int groupOfPlantsId) throws ClassNotFoundException, FileNotFoundException, IOException, SQLException
	{
		ISourceManipulator sourceManipulator = MyDatabase.getInstance();
		sourceManipulator.openConnection();

		this.carriedOutTaskManipulator.setConnection(sourceManipulator.getConnection());
		List<CarriedOutTask> carriedOutTasks = this.carriedOutTaskManipulator.getCarriedOutTasks(groupOfPlantsId, this.groupOfPlantsManipulator, this.typeOfTasksManipulator, this.positionManipulator, this.typeOfPlantsManipulator);

		sourceManipulator.closeConnection();

		return carriedOutTasks;
	}

	/**
	 * Add a carried out task.
	 * 
	 * @param groupOfPlants
	 *            The group of plants.
	 * @param typeOfTasks
	 *            The type of tasks.
	 * @param carriedOutDate
	 *            The carried out date.
	 * @param deadlineDate
	 *            The deadline date.
	 * @param description
	 *            The description.
	 * @throws MissingCarriedOutTaskIdException
	 *             The id is missing.
	 * @throws MissingCarriedOutTaskGroupOfPlantsException
	 *             The group of plants is missing.
	 * @throws MissingCarriedOutTaskTypeOfTasksException
	 *             The type of tasks is missing.
	 * @throws MissingCarriedOutTaskCarriedOutDateException
	 *             The carried out date is missing.
	 * @throws MissingCarriedOutTaskDeadlineDateException
	 *             The deadline date is missing.
	 * @throws MissingCarriedOutTaskDescriptionException
	 *             The description is missing.
	 * @throws InvalidCarriedOutTaskIdException
	 *             The id is negative.
	 * @throws ClassNotFoundException
	 *             If the class is not found.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public void addCarriedOutTask(GroupOfPlants groupOfPlants, TypeOfTasks typeOfTasks, LocalDate carriedOutDate, LocalDate deadlineDate, String description) throws MissingCarriedOutTaskIdException, MissingCarriedOutTaskGroupOfPlantsException, MissingCarriedOutTaskTypeOfTasksException, MissingCarriedOutTaskCarriedOutDateException, MissingCarriedOutTaskDeadlineDateException, MissingCarriedOutTaskDescriptionException, InvalidCarriedOutTaskIdException, ClassNotFoundException, FileNotFoundException, IOException, SQLException
	{
		checkCarriedOutTaskParameters(CarriedOutTask.UNKNOWN_CARRIED_OUT_TASK_ID, groupOfPlants, typeOfTasks, carriedOutDate, deadlineDate, description);

		ISourceManipulator sourceManipulator = MyDatabase.getInstance();
		sourceManipulator.openConnection();

		this.carriedOutTaskManipulator.setConnection(sourceManipulator.getConnection());
		this.carriedOutTaskManipulator.addCarriedOutTask(new CarriedOutTask(CarriedOutTask.UNKNOWN_CARRIED_OUT_TASK_ID, groupOfPlants, typeOfTasks, carriedOutDate, deadlineDate, description));

		sourceManipulator.closeConnection();
	}

	/**
	 * Remove a carried out task.
	 * 
	 * @param carriedOutTaskToRemove
	 *            The carried out task to remove.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 * @throws ClassNotFoundException
	 *             If the class is not found.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void removeCarriedOutTask(CarriedOutTask carriedOutTaskToRemove) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException
	{
		ISourceManipulator sourceManipulator = MyDatabase.getInstance();
		sourceManipulator.openConnection();

		this.carriedOutTaskManipulator.setConnection(sourceManipulator.getConnection());
		this.carriedOutTaskManipulator.removeCarriedOutTask(carriedOutTaskToRemove);

		sourceManipulator.closeConnection();
	}

	/**
	 * Remove a carried out task for a specific group of plants.
	 * 
	 * @param groupOfPlantsId
	 *            The group of plants id.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 * @throws ClassNotFoundException
	 *             If the class is not found.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void removeCarriedOutTasks(int groupOfPlantsId) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException
	{
		ISourceManipulator sourceManipulator = MyDatabase.getInstance();
		sourceManipulator.openConnection();

		this.carriedOutTaskManipulator.setConnection(sourceManipulator.getConnection());
		this.carriedOutTaskManipulator.removeCarriedOutTasks(groupOfPlantsId);

		sourceManipulator.closeConnection();
	}

	/**
	 * Check carried out task parameters.
	 * 
	 * @param id
	 *            The id.
	 * @param groupOfPlants
	 *            The group of plants.
	 * @param typeOfTasks
	 *            The type of tasks.
	 * @param carriedOutDate
	 *            The carried out date.
	 * @param deadlineDate
	 *            The deadline date.
	 * @param description
	 *            The description.
	 * @throws MissingCarriedOutTaskIdException
	 *             The id is missing.
	 * @throws MissingCarriedOutTaskGroupOfPlantsException
	 *             The group of plants is missing.
	 * @throws MissingCarriedOutTaskTypeOfTasksException
	 *             The type of tasks is missing.
	 * @throws MissingCarriedOutTaskCarriedOutDateException
	 *             The carried out date is missing.
	 * @throws MissingCarriedOutTaskDeadlineDateException
	 *             The deadline date is missing.
	 * @throws MissingCarriedOutTaskDescriptionException
	 *             The description is missing.
	 * @throws InvalidCarriedOutTaskIdException
	 *             The id is negative.
	 */
	private void checkCarriedOutTaskParameters(Integer id, GroupOfPlants groupOfPlants, TypeOfTasks typeOfTasks, LocalDate carriedOutDate, LocalDate deadlineDate, String description) throws MissingCarriedOutTaskIdException, MissingCarriedOutTaskGroupOfPlantsException, MissingCarriedOutTaskTypeOfTasksException, MissingCarriedOutTaskCarriedOutDateException, MissingCarriedOutTaskDeadlineDateException, MissingCarriedOutTaskDescriptionException, InvalidCarriedOutTaskIdException
	{
		checkValuesAreSets(id, groupOfPlants, typeOfTasks, carriedOutDate, deadlineDate, description);
		checkValuesAreCorrects(id);
	}

	/**
	 * Check values are sets.
	 * 
	 * @param id
	 *            The id.
	 * @param groupOfPlants
	 *            The group of plants.
	 * @param typeOfTasks
	 *            The type of tasks.
	 * @param carriedOutDate
	 *            The carried out date.
	 * @param deadlineDate
	 *            The deadline date.
	 * @param description
	 *            The description.
	 * @throws MissingCarriedOutTaskIdException
	 *             The id is missing.
	 * @throws MissingCarriedOutTaskGroupOfPlantsException
	 *             The group of plants is missing.
	 * @throws MissingCarriedOutTaskTypeOfTasksException
	 *             The type of tasks is missing.
	 * @throws MissingCarriedOutTaskCarriedOutDateException
	 *             The carried out date is missing.
	 * @throws MissingCarriedOutTaskDeadlineDateException
	 *             The deadline date is missing.
	 * @throws MissingCarriedOutTaskDescriptionException
	 *             The description is missing.
	 */
	private void checkValuesAreSets(Integer id, GroupOfPlants groupOfPlants, TypeOfTasks typeOfTasks, LocalDate carriedOutDate, LocalDate deadlineDate, String description) throws MissingCarriedOutTaskIdException, MissingCarriedOutTaskGroupOfPlantsException, MissingCarriedOutTaskTypeOfTasksException, MissingCarriedOutTaskCarriedOutDateException, MissingCarriedOutTaskDeadlineDateException, MissingCarriedOutTaskDescriptionException
	{
		if (id == null)
		{
			throw new MissingCarriedOutTaskIdException();
		}

		if (groupOfPlants == null)
		{
			throw new MissingCarriedOutTaskGroupOfPlantsException();
		}

		if (typeOfTasks == null)
		{
			throw new MissingCarriedOutTaskTypeOfTasksException();
		}

		if (carriedOutDate == null)
		{
			throw new MissingCarriedOutTaskCarriedOutDateException();
		}

		if (deadlineDate == null)
		{
			throw new MissingCarriedOutTaskDeadlineDateException();
		}

		if (description == null)
		{
			throw new MissingCarriedOutTaskDescriptionException();
		}
	}

	/**
	 * Check values are corrects.
	 * 
	 * @param id
	 *            The id.
	 * @throws InvalidCarriedOutTaskIdException
	 *             The id is negative.
	 */
	private void checkValuesAreCorrects(Integer id) throws InvalidCarriedOutTaskIdException
	{
		if ((id < MINIMAL_ID) && (id != CarriedOutTask.UNKNOWN_CARRIED_OUT_TASK_ID))
		{
			throw new InvalidCarriedOutTaskIdException(id);
		}
	}
}