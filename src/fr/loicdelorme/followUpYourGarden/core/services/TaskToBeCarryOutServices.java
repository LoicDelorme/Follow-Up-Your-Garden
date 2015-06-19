package fr.loicdelorme.followUpYourGarden.core.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import fr.loicdelorme.followUpYourGarden.core.database.MyDatabase;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.IGroupOfPlantsManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.IPositionManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITaskToBeCarryOutManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITypeOfPlantsManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITypeOfTasksManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.sources.ISourceManipulator;
import fr.loicdelorme.followUpYourGarden.core.models.GroupOfPlants;
import fr.loicdelorme.followUpYourGarden.core.models.Priority;
import fr.loicdelorme.followUpYourGarden.core.models.TaskToBeCarryOut;
import fr.loicdelorme.followUpYourGarden.core.models.TypeOfTasks;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidTaskToBeCarryOutAnticipatedDurationException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidTaskToBeCarryOutCurrentProgressionException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTaskToBeCarryOutAnticipatedDurationException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTaskToBeCarryOutCurrentProgressionException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTaskToBeCarryOutDeadlineDateException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTaskToBeCarryOutDescriptionException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTaskToBeCarryOutGroupOfPlantsException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTaskToBeCarryOutIsRecurrentException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTaskToBeCarryOutPeriodicityException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTaskToBeCarryOutPriorityException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTaskToBeCarryOutTypeOfTasksException;

/**
 * This class allow you to handle tasks to be carry out.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class TaskToBeCarryOutServices
{
	/**
	 * The minimal current progression value.
	 */
	private static final int MINIMAL_CURRENT_PROGRESSION_VALUE = 0;

	/**
	 * The maximal current progression value.
	 */
	private static final int MAXIMAL_CURRENT_PROGRESSION_VALUE = 100;

	/**
	 * The minimal anticipated duration value.
	 */
	private static final int MINIMAL_ANTICIPATED_DURATION_VALUE = 0;

	/**
	 * A task to be carry out manipulator.
	 */
	private ITaskToBeCarryOutManipulator taskToBeCarryOutManipulator;

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
	 * Create a task to be carry out services.
	 * 
	 * @param taskToBeCarryOutManipulator
	 *            A task to be carry out manipulator.
	 * @param groupOfPlantsManipulator
	 *            A group of plants manipulator.
	 * @param typeOfTasksManipulator
	 *            A type of tasks manipulator.
	 * @param positionManipulator
	 *            A position manipulator.
	 * @param typeOfPlantsManipulator
	 *            A type of plants manipulator.
	 */
	public TaskToBeCarryOutServices(ITaskToBeCarryOutManipulator taskToBeCarryOutManipulator, IGroupOfPlantsManipulator groupOfPlantsManipulator, ITypeOfTasksManipulator typeOfTasksManipulator, IPositionManipulator positionManipulator, ITypeOfPlantsManipulator typeOfPlantsManipulator)
	{
		this.taskToBeCarryOutManipulator = taskToBeCarryOutManipulator;
		this.groupOfPlantsManipulator = groupOfPlantsManipulator;
		this.typeOfTasksManipulator = typeOfTasksManipulator;
		this.positionManipulator = positionManipulator;
		this.typeOfPlantsManipulator = typeOfPlantsManipulator;
	}

	/**
	 * Get all tasks to be carry out.
	 * 
	 * @return A list of tasks to be carry out.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 * @throws ClassNotFoundException
	 *             If the class is not found.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public List<TaskToBeCarryOut> getTasksToBeCarryOut() throws ClassNotFoundException, FileNotFoundException, IOException, SQLException
	{
		ISourceManipulator sourceManipulator = MyDatabase.getInstance();
		sourceManipulator.openConnection();

		this.taskToBeCarryOutManipulator.setConnection(sourceManipulator.getConnection());
		List<TaskToBeCarryOut> tasksToBeCarryOut = this.taskToBeCarryOutManipulator.getTasksToBeCarryOut(this.groupOfPlantsManipulator, this.typeOfTasksManipulator, this.positionManipulator, this.typeOfPlantsManipulator);

		sourceManipulator.closeConnection();

		return tasksToBeCarryOut;
	}

	/**
	 * Add a task to be carry out.
	 * 
	 * @param groupOfPlants
	 *            The group of plants.
	 * @param typeOfTasks
	 *            The type of tasks.
	 * @param deadlineDate
	 *            The deadline date.
	 * @param priority
	 *            The priority.
	 * @param description
	 *            The description.
	 * @param currentProgression
	 *            The current progression.
	 * @param anticipatedDuration
	 *            The anticipated duration.
	 * @param isRecurrent
	 *            If it's recurrent.
	 * @param periodicity
	 *            The periodicity.
	 * @throws InvalidTaskToBeCarryOutCurrentProgressionException
	 *             The current progression is not between 0 and 100.
	 * @throws InvalidTaskToBeCarryOutAnticipatedDurationException
	 *             The anticipated duration is negative or null.
	 * @throws MissingTaskToBeCarryOutGroupOfPlantsException
	 *             The group of plants is missing.
	 * @throws MissingTaskToBeCarryOutTypeOfTasksException
	 *             The type of tasks is missing.
	 * @throws MissingTaskToBeCarryOutDeadlineDateException
	 *             The deadline date is missing.
	 * @throws MissingTaskToBeCarryOutPriorityException
	 *             The priority is missing.
	 * @throws MissingTaskToBeCarryOutDescriptionException
	 *             The description is missing.
	 * @throws MissingTaskToBeCarryOutCurrentProgressionException
	 *             The current progression is missing.
	 * @throws MissingTaskToBeCarryOutAnticipatedDurationException
	 *             The anticipated duration is missing.
	 * @throws MissingTaskToBeCarryOutIsRecurrentException
	 *             If it's recurrent is missing.
	 * @throws MissingTaskToBeCarryOutPeriodicityException
	 *             The periodicity is missing.
	 * @throws ClassNotFoundException
	 *             If the class is not found.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public void addTaskToBeCarryOut(GroupOfPlants groupOfPlants, TypeOfTasks typeOfTasks, LocalDate deadlineDate, Priority priority, String description, Integer currentProgression, Double anticipatedDuration, Boolean isRecurrent, Integer periodicity) throws MissingTaskToBeCarryOutGroupOfPlantsException, MissingTaskToBeCarryOutTypeOfTasksException, MissingTaskToBeCarryOutDeadlineDateException, MissingTaskToBeCarryOutPriorityException, MissingTaskToBeCarryOutDescriptionException, MissingTaskToBeCarryOutCurrentProgressionException, MissingTaskToBeCarryOutAnticipatedDurationException, MissingTaskToBeCarryOutIsRecurrentException, MissingTaskToBeCarryOutPeriodicityException, InvalidTaskToBeCarryOutCurrentProgressionException, InvalidTaskToBeCarryOutAnticipatedDurationException, ClassNotFoundException, FileNotFoundException, IOException, SQLException
	{
		checkTaskToBeCarryOutParameters(groupOfPlants, typeOfTasks, deadlineDate, priority, description, currentProgression, anticipatedDuration, isRecurrent, periodicity);

		ISourceManipulator sourceManipulator = MyDatabase.getInstance();
		sourceManipulator.openConnection();

		this.taskToBeCarryOutManipulator.setConnection(sourceManipulator.getConnection());
		this.taskToBeCarryOutManipulator.addTaskToBeCarryOut(new TaskToBeCarryOut(groupOfPlants, typeOfTasks, deadlineDate, priority, description, currentProgression, anticipatedDuration, isRecurrent, periodicity));

		sourceManipulator.closeConnection();
	}

	/**
	 * Update a task to be carry out.
	 * 
	 * @param deadlineDate
	 *            The deadline date.
	 * @param priority
	 *            The priority.
	 * @param description
	 *            The description.
	 * @param currentProgression
	 *            The current progression.
	 * @param anticipatedDuration
	 *            The anticipated duration.
	 * @param isRecurrent
	 *            If it's recurrent.
	 * @param periodicity
	 *            The periodicity.
	 * @param oldTaskToBeCarryOut
	 *            The old task to be carry out.
	 * @throws InvalidTaskToBeCarryOutCurrentProgressionException
	 *             The current progression is not between 0 and 100.
	 * @throws InvalidTaskToBeCarryOutAnticipatedDurationException
	 *             The anticipated duration is negative or null.
	 * @throws MissingTaskToBeCarryOutGroupOfPlantsException
	 *             The group of plants is missing.
	 * @throws MissingTaskToBeCarryOutTypeOfTasksException
	 *             The type of tasks is missing.
	 * @throws MissingTaskToBeCarryOutDeadlineDateException
	 *             The deadline date is missing.
	 * @throws MissingTaskToBeCarryOutPriorityException
	 *             The priority is missing.
	 * @throws MissingTaskToBeCarryOutDescriptionException
	 *             The description is missing.
	 * @throws MissingTaskToBeCarryOutCurrentProgressionException
	 *             The current progression is missing.
	 * @throws MissingTaskToBeCarryOutAnticipatedDurationException
	 *             The anticipated duration is missing.
	 * @throws MissingTaskToBeCarryOutIsRecurrentException
	 *             If it's recurrent is missing.
	 * @throws MissingTaskToBeCarryOutPeriodicityException
	 *             The periodicity is missing.
	 * @throws ClassNotFoundException
	 *             If the class is not found.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public void updateTaskToBeCarryOut(LocalDate deadlineDate, Priority priority, String description, Integer currentProgression, Double anticipatedDuration, Boolean isRecurrent, Integer periodicity, TaskToBeCarryOut oldTaskToBeCarryOut) throws MissingTaskToBeCarryOutGroupOfPlantsException, MissingTaskToBeCarryOutTypeOfTasksException, MissingTaskToBeCarryOutDeadlineDateException, MissingTaskToBeCarryOutPriorityException, MissingTaskToBeCarryOutDescriptionException, MissingTaskToBeCarryOutCurrentProgressionException, MissingTaskToBeCarryOutAnticipatedDurationException, MissingTaskToBeCarryOutIsRecurrentException, MissingTaskToBeCarryOutPeriodicityException, InvalidTaskToBeCarryOutCurrentProgressionException, InvalidTaskToBeCarryOutAnticipatedDurationException, ClassNotFoundException, FileNotFoundException, IOException, SQLException
	{
		checkTaskToBeCarryOutParameters(oldTaskToBeCarryOut.getGroupOfPlants(), oldTaskToBeCarryOut.getTypeOfTasks(), deadlineDate, priority, description, currentProgression, anticipatedDuration, isRecurrent, periodicity);

		TaskToBeCarryOut newTaskToBeCarryOut = new TaskToBeCarryOut(oldTaskToBeCarryOut.getGroupOfPlants(), oldTaskToBeCarryOut.getTypeOfTasks(), deadlineDate, priority, description, currentProgression, anticipatedDuration, isRecurrent, periodicity);

		if (!oldTaskToBeCarryOut.equals(newTaskToBeCarryOut))
		{
			ISourceManipulator sourceManipulator = MyDatabase.getInstance();
			sourceManipulator.openConnection();

			this.taskToBeCarryOutManipulator.setConnection(sourceManipulator.getConnection());
			this.taskToBeCarryOutManipulator.updateTaskToBeCarryOut(newTaskToBeCarryOut);

			sourceManipulator.closeConnection();
		}
	}

	/**
	 * Remove a task to be carry out.
	 * 
	 * @param taskToBeCarryOutToRemove
	 *            The task to be carry out to remove.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 * @throws ClassNotFoundException
	 *             If the class is not found.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void removeTaskToBeCarryOut(TaskToBeCarryOut taskToBeCarryOutToRemove) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException
	{
		ISourceManipulator sourceManipulator = MyDatabase.getInstance();
		sourceManipulator.openConnection();

		this.taskToBeCarryOutManipulator.setConnection(sourceManipulator.getConnection());
		this.taskToBeCarryOutManipulator.removeTaskToBeCarryOut(taskToBeCarryOutToRemove);

		sourceManipulator.closeConnection();
	}

	/**
	 * Check task to be carry out parameters.
	 * 
	 * @param groupOfPlants
	 *            The group of plants.
	 * @param typeOfTasks
	 *            The type of tasks.
	 * @param deadlineDate
	 *            The deadline date.
	 * @param priority
	 *            The priority.
	 * @param description
	 *            The description.
	 * @param currentProgression
	 *            The current progression.
	 * @param anticipatedDuration
	 *            The anticipated duration.
	 * @param isRecurrent
	 *            If it's recurrent.
	 * @param periodicity
	 *            The periodicity.
	 * @throws MissingTaskToBeCarryOutGroupOfPlantsException
	 *             The group of plants is missing.
	 * @throws MissingTaskToBeCarryOutTypeOfTasksException
	 *             The type of tasks is missing.
	 * @throws MissingTaskToBeCarryOutDeadlineDateException
	 *             The deadline date is missing.
	 * @throws MissingTaskToBeCarryOutPriorityException
	 *             The priority is missing.
	 * @throws MissingTaskToBeCarryOutDescriptionException
	 *             The description is missing.
	 * @throws MissingTaskToBeCarryOutCurrentProgressionException
	 *             The current progression is missing.
	 * @throws MissingTaskToBeCarryOutAnticipatedDurationException
	 *             The anticipated duration is missing.
	 * @throws MissingTaskToBeCarryOutIsRecurrentException
	 *             If it's recurrent is missing.
	 * @throws MissingTaskToBeCarryOutPeriodicityException
	 *             The periodicity is missing.
	 * @throws InvalidTaskToBeCarryOutCurrentProgressionException
	 *             The current progression is not between 0 and 100.
	 * @throws InvalidTaskToBeCarryOutAnticipatedDurationException
	 *             The anticipated duration is negative or null.
	 */
	private void checkTaskToBeCarryOutParameters(GroupOfPlants groupOfPlants, TypeOfTasks typeOfTasks, LocalDate deadlineDate, Priority priority, String description, Integer currentProgression, Double anticipatedDuration, Boolean isRecurrent, Integer periodicity) throws MissingTaskToBeCarryOutGroupOfPlantsException, MissingTaskToBeCarryOutTypeOfTasksException, MissingTaskToBeCarryOutDeadlineDateException, MissingTaskToBeCarryOutPriorityException, MissingTaskToBeCarryOutDescriptionException, MissingTaskToBeCarryOutCurrentProgressionException, MissingTaskToBeCarryOutAnticipatedDurationException, MissingTaskToBeCarryOutIsRecurrentException, MissingTaskToBeCarryOutPeriodicityException, InvalidTaskToBeCarryOutCurrentProgressionException, InvalidTaskToBeCarryOutAnticipatedDurationException
	{
		checkValuesAreSets(groupOfPlants, typeOfTasks, deadlineDate, priority, description, currentProgression, anticipatedDuration, isRecurrent, periodicity);
		checkValuesAreCorrects(currentProgression, anticipatedDuration);
	}

	/**
	 * Check values are sets.
	 * 
	 * @param groupOfPlants
	 *            The group of plants.
	 * @param typeOfTasks
	 *            The type of tasks.
	 * @param deadlineDate
	 *            The deadline date.
	 * @param priority
	 *            The priority.
	 * @param description
	 *            The description.
	 * @param currentProgression
	 *            The current progression.
	 * @param anticipatedDuration
	 *            The anticipated duration.
	 * @param isRecurrent
	 *            If it's recurrent.
	 * @param periodicity
	 *            The periodicity.
	 * @throws MissingTaskToBeCarryOutGroupOfPlantsException
	 *             The group of plants is missing.
	 * @throws MissingTaskToBeCarryOutTypeOfTasksException
	 *             The type of tasks is missing.
	 * @throws MissingTaskToBeCarryOutDeadlineDateException
	 *             The deadline date is missing.
	 * @throws MissingTaskToBeCarryOutPriorityException
	 *             The priority is missing.
	 * @throws MissingTaskToBeCarryOutDescriptionException
	 *             The description is missing.
	 * @throws MissingTaskToBeCarryOutCurrentProgressionException
	 *             The current progression is missing.
	 * @throws MissingTaskToBeCarryOutAnticipatedDurationException
	 *             The anticipated duration is missing.
	 * @throws MissingTaskToBeCarryOutIsRecurrentException
	 *             If it's recurrent is missing.
	 * @throws MissingTaskToBeCarryOutPeriodicityException
	 *             The periodicity is missing.
	 */
	private void checkValuesAreSets(GroupOfPlants groupOfPlants, TypeOfTasks typeOfTasks, LocalDate deadlineDate, Priority priority, String description, Integer currentProgression, Double anticipatedDuration, Boolean isRecurrent, Integer periodicity) throws MissingTaskToBeCarryOutGroupOfPlantsException, MissingTaskToBeCarryOutTypeOfTasksException, MissingTaskToBeCarryOutDeadlineDateException, MissingTaskToBeCarryOutPriorityException, MissingTaskToBeCarryOutDescriptionException, MissingTaskToBeCarryOutCurrentProgressionException, MissingTaskToBeCarryOutAnticipatedDurationException, MissingTaskToBeCarryOutIsRecurrentException, MissingTaskToBeCarryOutPeriodicityException
	{
		if (groupOfPlants == null)
		{
			throw new MissingTaskToBeCarryOutGroupOfPlantsException();
		}

		if (typeOfTasks == null)
		{
			throw new MissingTaskToBeCarryOutTypeOfTasksException();
		}

		if (deadlineDate == null)
		{
			throw new MissingTaskToBeCarryOutDeadlineDateException();
		}

		if (priority == null)
		{
			throw new MissingTaskToBeCarryOutPriorityException();
		}

		if (description == null)
		{
			throw new MissingTaskToBeCarryOutDescriptionException();
		}

		if (currentProgression == null)
		{
			throw new MissingTaskToBeCarryOutCurrentProgressionException();
		}

		if (anticipatedDuration == null)
		{
			throw new MissingTaskToBeCarryOutAnticipatedDurationException();
		}

		if (isRecurrent == null)
		{
			throw new MissingTaskToBeCarryOutIsRecurrentException();
		}

		if (periodicity == null)
		{
			throw new MissingTaskToBeCarryOutPeriodicityException();
		}
	}

	/**
	 * Check values are corrects.
	 * 
	 * @param currentProgression
	 *            The current progression.
	 * @param anticipatedDuration
	 *            The anticipated duration.
	 * @throws InvalidTaskToBeCarryOutCurrentProgressionException
	 *             The current progression is not between 0 and 100.
	 * @throws InvalidTaskToBeCarryOutAnticipatedDurationException
	 *             The anticipated duration is negative or null.
	 */
	private void checkValuesAreCorrects(Integer currentProgression, Double anticipatedDuration) throws InvalidTaskToBeCarryOutCurrentProgressionException, InvalidTaskToBeCarryOutAnticipatedDurationException
	{
		if ((currentProgression < MINIMAL_CURRENT_PROGRESSION_VALUE) || (currentProgression > MAXIMAL_CURRENT_PROGRESSION_VALUE))
		{
			throw new InvalidTaskToBeCarryOutCurrentProgressionException(currentProgression);
		}

		if (anticipatedDuration <= MINIMAL_ANTICIPATED_DURATION_VALUE)
		{
			throw new InvalidTaskToBeCarryOutAnticipatedDurationException(anticipatedDuration);
		}
	}
}