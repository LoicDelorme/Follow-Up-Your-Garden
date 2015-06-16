package fr.loicdelorme.followUpYourGarden.core.manipulators.models.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.loicdelorme.followUpYourGarden.core.manipulators.models.IGroupOfPlantsManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.IPositionManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITaskToBeCarryOutManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITypeOfPlantsManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITypeOfTasksManipulator;
import fr.loicdelorme.followUpYourGarden.core.models.GroupOfPlants;
import fr.loicdelorme.followUpYourGarden.core.models.Priority;
import fr.loicdelorme.followUpYourGarden.core.models.TaskToBeCarryOut;

/**
 * This class allow you to handle tasks to be carry out using a database.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class TaskToBeCarryOutDatabaseManipulator implements ITaskToBeCarryOutManipulator
{
	/**
	 * The database columns names.
	 */
	public static final String[] COLUMNS_NAMES = new String[] { "id_group_of_plants", "id_type_of_tasks", "carry_out_before_date", "is_recurrent", "anticipated_duration", "priority", "description", "current_progression", "periodicity" };

	/**
	 * Get all tasks to be carry out with a database query.
	 */
	public static final String GET_TASKS_TO_BE_CARRY_OUT_QUERY = "SELECT id_group_of_plants, id_type_of_tasks, carry_out_before_date, is_recurrent, anticipated_duration, priority, description, current_progression, periodicity FROM TaskToBeCarryOut;";

	/**
	 * Get a task to be carry out with a database query.
	 */
	public static final String GET_TASK_TO_BE_CARRY_OUT_QUERY = "SELECT id_group_of_plants, id_type_of_tasks, carry_out_before_date, is_recurrent, anticipated_duration, priority, description, current_progression, periodicity FROM TaskToBeCarryOut WHERE id_group_of_plants = ? AND id_type_of_tasks = ?;";

	/**
	 * Get all tasks to be carry out for a specific group of plants with a database query.
	 */
	public static final String GET_TASKS_TO_BE_CARRY_OUT_FOR_A_SPECIFIC_GROUP_OF_PLANTS_QUERY = "SELECT id_group_of_plants, id_type_of_tasks, carry_out_before_date, is_recurrent, anticipated_duration, priority, description, current_progression, periodicity FROM TaskToBeCarryOut WHERE id_group_of_plants = ?;";

	/**
	 * Add a task to be carry out with a database query.
	 */
	public static final String ADD_TASK_TO_BE_CARRY_OUT_QUERY = "INSERT INTO TaskToBeCarryOut (id_group_of_plants, id_type_of_tasks, carry_out_before_date, is_recurrent, anticipated_duration, priority, description, current_progression, periodicity) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

	/**
	 * Update a task to be carry out with a database query.
	 */
	public static final String UPDATE_TASK_TO_BE_CARRY_OUT_QUERY = "UPDATE TaskToBeCarryOut SET carry_out_before_date = ?, is_recurrent = ?, anticipated_duration = ?, priority = ?, description = ?, current_progression = ?, periodicity = ? WHERE id_group_of_plants = ? AND id_type_of_tasks = ?;";

	/**
	 * Remove a task to be carry out with a database query.
	 */
	public static final String REMOVE_TASK_TO_BE_CARRY_OUT_QUERY = "DELETE FROM TaskToBeCarryOut WHERE id_group_of_plants = ? AND id_type_of_tasks = ?;";

	/**
	 * A connection.
	 */
	private Connection connection;

	/**
	 * @see fr.loicdelorme.followUpYourGarden.core.manipulators.models.IManipulator#setConnection(java.sql.Connection)
	 */
	@Override
	public void setConnection(Connection connection)
	{
		this.connection = connection;
	}

	/**
	 * @see fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITaskToBeCarryOutManipulator#getTasksToBeCarryOut(fr.loicdelorme.followUpYourGarden.core.manipulators.models.IGroupOfPlantsManipulator, fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITypeOfTasksManipulator, fr.loicdelorme.followUpYourGarden.core.manipulators.models.IPositionManipulator, fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITypeOfPlantsManipulator)
	 */
	@Override
	public List<TaskToBeCarryOut> getTasksToBeCarryOut(IGroupOfPlantsManipulator groupOfPlantsManipulator, ITypeOfTasksManipulator typeOfTasksManipulator, IPositionManipulator positionManipulator, ITypeOfPlantsManipulator typeOfPlantsManipulator) throws SQLException
	{
		List<TaskToBeCarryOut> tasksToBeCarryOut = new ArrayList<TaskToBeCarryOut>();

		Statement statement = this.connection.createStatement();

		ResultSet resultSet = statement.executeQuery(GET_TASKS_TO_BE_CARRY_OUT_QUERY);

		groupOfPlantsManipulator.setConnection(this.connection);
		typeOfTasksManipulator.setConnection(this.connection);

		while (resultSet.next())
		{
			tasksToBeCarryOut.add(new TaskToBeCarryOut(groupOfPlantsManipulator.getGroupOfPlants(resultSet.getInt(COLUMNS_NAMES[0]), positionManipulator, typeOfPlantsManipulator), typeOfTasksManipulator.getTypeOfTasks(resultSet.getInt(COLUMNS_NAMES[1])), resultSet.getDate(COLUMNS_NAMES[2]).toLocalDate(), getCorrespondantPriority(resultSet.getInt(COLUMNS_NAMES[5])), resultSet.getString(COLUMNS_NAMES[6]), resultSet.getInt(COLUMNS_NAMES[7]), resultSet.getDouble(COLUMNS_NAMES[4]), resultSet.getBoolean(COLUMNS_NAMES[3]), resultSet.getInt(COLUMNS_NAMES[8])));
		}

		tasksToBeCarryOut.sort(null);

		return tasksToBeCarryOut;
	}

	/**
	 * @see fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITaskToBeCarryOutManipulator#getTaskToBeCarryOut(int, int, fr.loicdelorme.followUpYourGarden.core.manipulators.models.IGroupOfPlantsManipulator, fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITypeOfTasksManipulator, fr.loicdelorme.followUpYourGarden.core.manipulators.models.IPositionManipulator, fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITypeOfPlantsManipulator)
	 */
	@Override
	public TaskToBeCarryOut getTaskToBeCarryOut(int groupOfPlantsId, int typeOfTasksId, IGroupOfPlantsManipulator groupOfPlantsManipulator, ITypeOfTasksManipulator typeOfTasksManipulator, IPositionManipulator positionManipulator, ITypeOfPlantsManipulator typeOfPlantsManipulator) throws SQLException
	{
		TaskToBeCarryOut taskToBeCarryOut = null;

		PreparedStatement statement = this.connection.prepareStatement(GET_TASK_TO_BE_CARRY_OUT_QUERY);
		statement.setInt(1, groupOfPlantsId);
		statement.setInt(2, typeOfTasksId);

		ResultSet resultSet = statement.executeQuery();
		resultSet.absolute(1);

		groupOfPlantsManipulator.setConnection(this.connection);
		typeOfTasksManipulator.setConnection(this.connection);

		taskToBeCarryOut = new TaskToBeCarryOut(groupOfPlantsManipulator.getGroupOfPlants(groupOfPlantsId, positionManipulator, typeOfPlantsManipulator), typeOfTasksManipulator.getTypeOfTasks(groupOfPlantsId), resultSet.getDate(COLUMNS_NAMES[2]).toLocalDate(), getCorrespondantPriority(resultSet.getInt(COLUMNS_NAMES[5])), resultSet.getString(COLUMNS_NAMES[6]), resultSet.getInt(COLUMNS_NAMES[7]), resultSet.getDouble(COLUMNS_NAMES[4]), resultSet.getBoolean(COLUMNS_NAMES[3]), resultSet.getInt(COLUMNS_NAMES[8]));

		return taskToBeCarryOut;
	}

	/**
	 * @see fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITaskToBeCarryOutManipulator#getTasksToBeCarryOut(int, fr.loicdelorme.followUpYourGarden.core.manipulators.models.IGroupOfPlantsManipulator, fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITypeOfTasksManipulator, fr.loicdelorme.followUpYourGarden.core.manipulators.models.IPositionManipulator, fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITypeOfPlantsManipulator)
	 */
	@Override
	public List<TaskToBeCarryOut> getTasksToBeCarryOut(int groupOfPlantsId, IGroupOfPlantsManipulator groupOfPlantsManipulator, ITypeOfTasksManipulator typeOfTasksManipulator, IPositionManipulator positionManipulator, ITypeOfPlantsManipulator typeOfPlantsManipulator) throws SQLException
	{
		List<TaskToBeCarryOut> tasksToBeCarryOut = new ArrayList<TaskToBeCarryOut>();

		PreparedStatement statement = this.connection.prepareStatement(GET_TASKS_TO_BE_CARRY_OUT_FOR_A_SPECIFIC_GROUP_OF_PLANTS_QUERY);
		statement.setInt(1, groupOfPlantsId);

		ResultSet resultSet = statement.executeQuery();

		groupOfPlantsManipulator.setConnection(this.connection);
		typeOfTasksManipulator.setConnection(this.connection);

		GroupOfPlants groupOfPlants = groupOfPlantsManipulator.getGroupOfPlants(groupOfPlantsId, positionManipulator, typeOfPlantsManipulator);

		while (resultSet.next())
		{
			tasksToBeCarryOut.add(new TaskToBeCarryOut(groupOfPlants, typeOfTasksManipulator.getTypeOfTasks(resultSet.getInt(COLUMNS_NAMES[1])), resultSet.getDate(COLUMNS_NAMES[2]).toLocalDate(), getCorrespondantPriority(resultSet.getInt(COLUMNS_NAMES[5])), resultSet.getString(COLUMNS_NAMES[6]), resultSet.getInt(COLUMNS_NAMES[7]), resultSet.getDouble(COLUMNS_NAMES[4]), resultSet.getBoolean(COLUMNS_NAMES[3]), resultSet.getInt(COLUMNS_NAMES[8])));
		}

		tasksToBeCarryOut.sort(null);

		return tasksToBeCarryOut;
	}

	/**
	 * @see fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITaskToBeCarryOutManipulator#addTaskToBeCarryOut(fr.loicdelorme.followUpYourGarden.core.models.TaskToBeCarryOut)
	 */
	@Override
	public void addTaskToBeCarryOut(TaskToBeCarryOut taskToBeCarryOut) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(ADD_TASK_TO_BE_CARRY_OUT_QUERY);
		statement.setInt(1, taskToBeCarryOut.getGroupOfPlants().getId());
		statement.setInt(2, taskToBeCarryOut.getTypeOfTasks().getId());
		statement.setString(3, taskToBeCarryOut.getDeadlineDate().toString());
		statement.setBoolean(4, taskToBeCarryOut.getIsRecurrent());
		statement.setDouble(5, taskToBeCarryOut.getAnticipatedDuration());
		statement.setInt(6, taskToBeCarryOut.getPriority().getId());
		statement.setString(7, taskToBeCarryOut.getDescription());
		statement.setInt(8, taskToBeCarryOut.getCurrentProgression());
		statement.setInt(9, taskToBeCarryOut.getPeriodicity());

		statement.executeUpdate();
	}

	/**
	 * @see fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITaskToBeCarryOutManipulator#updateTaskToBeCarryOut(fr.loicdelorme.followUpYourGarden.core.models.TaskToBeCarryOut)
	 */
	@Override
	public void updateTaskToBeCarryOut(TaskToBeCarryOut taskToBeCarryOut) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(UPDATE_TASK_TO_BE_CARRY_OUT_QUERY);
		statement.setString(1, taskToBeCarryOut.getDeadlineDate().toString());
		statement.setBoolean(2, taskToBeCarryOut.getIsRecurrent());
		statement.setDouble(3, taskToBeCarryOut.getAnticipatedDuration());
		statement.setInt(4, taskToBeCarryOut.getPriority().getId());
		statement.setString(5, taskToBeCarryOut.getDescription());
		statement.setInt(6, taskToBeCarryOut.getCurrentProgression());
		statement.setInt(7, taskToBeCarryOut.getPeriodicity());
		statement.setInt(8, taskToBeCarryOut.getGroupOfPlants().getId());
		statement.setInt(9, taskToBeCarryOut.getTypeOfTasks().getId());

		statement.executeUpdate();
	}

	/**
	 * @see fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITaskToBeCarryOutManipulator#removeTaskToBeCarryOut(fr.loicdelorme.followUpYourGarden.core.models.TaskToBeCarryOut)
	 */
	@Override
	public void removeTaskToBeCarryOut(TaskToBeCarryOut taskToBeCarryOut) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(REMOVE_TASK_TO_BE_CARRY_OUT_QUERY);
		statement.setInt(1, taskToBeCarryOut.getGroupOfPlants().getId());
		statement.setInt(2, taskToBeCarryOut.getTypeOfTasks().getId());

		statement.executeUpdate();
	}

	/**
	 * Get the correspondant priority.
	 * 
	 * @param idValue
	 *            The id value.
	 * @return A priority.
	 */
	private Priority getCorrespondantPriority(int idValue)
	{
		switch (idValue)
		{
			case 1:
				return Priority.HIGH;
			case 2:
				return Priority.MEDIUM;
			case 3:
				return Priority.LOW;
			default:
				return null;
		}
	}
}
