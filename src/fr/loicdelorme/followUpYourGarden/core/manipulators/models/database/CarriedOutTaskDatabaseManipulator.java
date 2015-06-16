package fr.loicdelorme.followUpYourGarden.core.manipulators.models.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.loicdelorme.followUpYourGarden.core.manipulators.models.ICarriedOutTaskManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.IGroupOfPlantsManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.IPositionManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITypeOfPlantsManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITypeOfTasksManipulator;
import fr.loicdelorme.followUpYourGarden.core.models.CarriedOutTask;
import fr.loicdelorme.followUpYourGarden.core.models.GroupOfPlants;

/**
 * This class allow you to handle carried out task using a database.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class CarriedOutTaskDatabaseManipulator implements ICarriedOutTaskManipulator
{
	/**
	 * The database columns names.
	 */
	public static final String[] COLUMNS_NAMES = new String[] { "id_history", "id_group_of_plants", "id_type_of_tasks", "carried_out_date", "deadline_date", "description" };

	/**
	 * Get all carried out tasks with a database query.
	 */
	public static final String GET_CARRIED_OUT_TASKS_QUERY = "SELECT id_history, id_group_of_plants, id_type_of_tasks, carried_out_date, deadline_date, description FROM HistoryOfCarriedOutTasks;";

	/**
	 * Get a carried out task with a database query.
	 */
	public static final String GET_CARRIED_OUT_TASK_QUERY = "SELECT id_history, id_group_of_plants, id_type_of_tasks, carried_out_date, deadline_date, description FROM HistoryOfCarriedOutTasks WHERE id_history = ?;";

	/**
	 * Get all carried out tasks for a specific group of plants with a database query.
	 */
	public static final String GET_CARRIED_OUT_TASKS_FOR_A_SPECIFIC_GROUP_OF_PLANTS_QUERY = "SELECT id_history, id_group_of_plants, id_type_of_tasks, carried_out_date, deadline_date, description FROM HistoryOfCarriedOutTasks WHERE id_group_of_plants = ?;";

	/**
	 * Add a carried out task with a database query.
	 */
	public static final String ADD_CARRIED_OUT_TASK_QUERY = "INSERT INTO HistoryOfCarriedOutTasks (id_group_of_plants, id_type_of_tasks, carried_out_date, deadline_date, description) VALUES (?, ?, ?, ?, ?);";

	/**
	 * Remove a carried out task with a database query.
	 */
	public static final String REMOVE_CARRIED_OUT_TASK_QUERY = "DELETE FROM HistoryOfCarriedOutTasks WHERE id_history = ?;";

	/**
	 * Remove carried out tasks for a specific group of plants with a database query.
	 */
	public static final String REMOVE_CARRIED_OUT_TASKS_FOR_A_SPECIFIC_GROUP_OF_PLANTS_QUERY = "DELETE FROM HistoryOfCarriedOutTasks WHERE id_group_of_plants = ?;";

	/**
	 * A connection.
	 */
	private Connection connection;

	@Override
	public void setConnection(Connection connection)
	{
		this.connection = connection;
	}

	@Override
	public List<CarriedOutTask> getCarriedOutTasks(IGroupOfPlantsManipulator groupOfPlantsManipulator, ITypeOfTasksManipulator typeOfTasksManipulator, IPositionManipulator positionManipulator, ITypeOfPlantsManipulator typeOfPlantsManipulator) throws SQLException
	{
		List<CarriedOutTask> carriedOutTasks = new ArrayList<CarriedOutTask>();

		Statement statement = this.connection.createStatement();

		ResultSet resultSet = statement.executeQuery(GET_CARRIED_OUT_TASKS_QUERY);

		groupOfPlantsManipulator.setConnection(this.connection);
		typeOfTasksManipulator.setConnection(this.connection);

		while (resultSet.next())
		{
			carriedOutTasks.add(new CarriedOutTask(resultSet.getInt(COLUMNS_NAMES[0]), groupOfPlantsManipulator.getGroupOfPlants(resultSet.getInt(COLUMNS_NAMES[1]), positionManipulator, typeOfPlantsManipulator), typeOfTasksManipulator.getTypeOfTasks(resultSet.getInt(COLUMNS_NAMES[2])), resultSet.getDate(COLUMNS_NAMES[3]).toLocalDate(), resultSet.getDate(COLUMNS_NAMES[4]).toLocalDate(), resultSet.getString(COLUMNS_NAMES[5])));
		}

		carriedOutTasks.sort(null);

		return carriedOutTasks;
	}

	@Override
	public CarriedOutTask getCarriedOutTask(int carriedOutTaskId, IGroupOfPlantsManipulator groupOfPlantsManipulator, ITypeOfTasksManipulator typeOfTasksManipulator, IPositionManipulator positionManipulator, ITypeOfPlantsManipulator typeOfPlantsManipulator) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(GET_CARRIED_OUT_TASK_QUERY);
		statement.setInt(1, carriedOutTaskId);

		ResultSet resultSet = statement.executeQuery();
		resultSet.absolute(1);

		groupOfPlantsManipulator.setConnection(this.connection);
		typeOfTasksManipulator.setConnection(this.connection);

		CarriedOutTask carriedOutTask = new CarriedOutTask(resultSet.getInt(COLUMNS_NAMES[0]), groupOfPlantsManipulator.getGroupOfPlants(resultSet.getInt(COLUMNS_NAMES[1]), positionManipulator, typeOfPlantsManipulator), typeOfTasksManipulator.getTypeOfTasks(resultSet.getInt(COLUMNS_NAMES[2])), resultSet.getDate(COLUMNS_NAMES[3]).toLocalDate(), resultSet.getDate(COLUMNS_NAMES[4]).toLocalDate(), resultSet.getString(COLUMNS_NAMES[5]));

		return carriedOutTask;
	}

	@Override
	public List<CarriedOutTask> getCarriedOutTasks(int groupOfPlantsId, IGroupOfPlantsManipulator groupOfPlantsManipulator, ITypeOfTasksManipulator typeOfTasksManipulator, IPositionManipulator positionManipulator, ITypeOfPlantsManipulator typeOfPlantsManipulator) throws SQLException
	{
		List<CarriedOutTask> carriedOutTasks = new ArrayList<CarriedOutTask>();

		PreparedStatement statement = this.connection.prepareStatement(GET_CARRIED_OUT_TASKS_FOR_A_SPECIFIC_GROUP_OF_PLANTS_QUERY);
		statement.setInt(1, groupOfPlantsId);

		ResultSet resultSet = statement.executeQuery();

		groupOfPlantsManipulator.setConnection(this.connection);
		typeOfTasksManipulator.setConnection(this.connection);

		GroupOfPlants groupOfPlants = groupOfPlantsManipulator.getGroupOfPlants(groupOfPlantsId, positionManipulator, typeOfPlantsManipulator);

		while (resultSet.next())
		{
			carriedOutTasks.add(new CarriedOutTask(resultSet.getInt(COLUMNS_NAMES[0]), groupOfPlants, typeOfTasksManipulator.getTypeOfTasks(resultSet.getInt(COLUMNS_NAMES[2])), resultSet.getDate(COLUMNS_NAMES[3]).toLocalDate(), resultSet.getDate(COLUMNS_NAMES[4]).toLocalDate(), resultSet.getString(COLUMNS_NAMES[5])));
		}

		carriedOutTasks.sort(null);

		return carriedOutTasks;
	}

	@Override
	public void addCarriedOutTask(CarriedOutTask carriedOutTask) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(ADD_CARRIED_OUT_TASK_QUERY);
		statement.setInt(1, carriedOutTask.getGroupOfPlants().getId());
		statement.setInt(2, carriedOutTask.getTypeOfTasks().getId());
		statement.setString(3, carriedOutTask.getCarriedOutDate().toString());
		statement.setString(4, carriedOutTask.getDeadlineDate().toString());
		statement.setString(5, carriedOutTask.getDescription());

		statement.executeUpdate();
	}

	@Override
	public void removeCarriedOutTask(CarriedOutTask carriedOutTask) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(REMOVE_CARRIED_OUT_TASK_QUERY);
		statement.setInt(1, carriedOutTask.getId());

		statement.executeUpdate();
	}

	@Override
	public void removeCarriedOutTasks(int groupOfPlantsId) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(REMOVE_CARRIED_OUT_TASKS_FOR_A_SPECIFIC_GROUP_OF_PLANTS_QUERY);
		statement.setInt(1, groupOfPlantsId);

		statement.executeUpdate();
	}
}
