package fr.loicdelorme.followUpYourGarden.core.manipulators.models.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITypeOfTasksManipulator;
import fr.loicdelorme.followUpYourGarden.core.models.TypeOfTasks;

/**
 * This class allow you to handle type of tasks using a database.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class TypeOfTasksDatabaseManipulator implements ITypeOfTasksManipulator
{
	/**
	 * The database columns names.
	 */
	public static final String[] COLUMNS_NAMES = new String[] { "id_type_of_tasks", "wording", "description" };

	/**
	 * Get all types of tasks with a database query.
	 */
	public static final String GET_TYPES_OF_TASKS_QUERY = "SELECT id_type_of_tasks, wording, description FROM TypeOfTasks;";

	/**
	 * Get a type of tasks with a database query.
	 */
	public static final String GET_TYPE_OF_TASKS_QUERY = "SELECT id_type_of_tasks, wording, description FROM TypeOfTasks WHERE id_type_of_tasks = ?;";

	/**
	 * Add a type of tasks with a database query.
	 */
	public static final String ADD_TYPE_OF_TASKS_QUERY = "INSERT INTO TypeOfTasks (wording, description) VALUES (?, ?);";

	/**
	 * Update a type of tasks with a database query.
	 */
	public static final String UPDATE_TYPE_OF_TASKS_QUERY = "UPDATE TypeOfTasks SET wording = ?, description = ? WHERE id_type_of_tasks = ?;";

	/**
	 * Remove a type of tasks with a database query.
	 */
	public static final String REMOVE_TYPE_OF_TASKS_QUERY = "DELETE FROM TypeOfTasks WHERE id_type_of_tasks = ?;";

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
	public List<TypeOfTasks> getTypesOfTasks() throws SQLException
	{
		List<TypeOfTasks> typesOfTasks = new ArrayList<TypeOfTasks>();

		Statement statement = this.connection.createStatement();

		ResultSet resultSet = statement.executeQuery(GET_TYPES_OF_TASKS_QUERY);

		while (resultSet.next())
		{
			typesOfTasks.add(new TypeOfTasks(resultSet.getInt(COLUMNS_NAMES[0]), resultSet.getString(COLUMNS_NAMES[1]), resultSet.getString(COLUMNS_NAMES[2])));
		}

		typesOfTasks.sort(null);

		return typesOfTasks;
	}

	@Override
	public TypeOfTasks getTypeOfTasks(int typeOfTasksId) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(GET_TYPE_OF_TASKS_QUERY);
		statement.setInt(1, typeOfTasksId);

		ResultSet resultSet = statement.executeQuery();
		resultSet.absolute(1);

		TypeOfTasks typeOfTasks = new TypeOfTasks(resultSet.getInt(COLUMNS_NAMES[0]), resultSet.getString(COLUMNS_NAMES[1]), resultSet.getString(COLUMNS_NAMES[2]));

		return typeOfTasks;
	}

	@Override
	public void addTypeOfTasks(TypeOfTasks typeOfTasks) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(ADD_TYPE_OF_TASKS_QUERY);
		statement.setString(1, typeOfTasks.getWording());
		statement.setString(2, typeOfTasks.getDescription());

		statement.executeUpdate();
	}

	@Override
	public void updateTypeOfTasks(TypeOfTasks typeOfTasks) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(UPDATE_TYPE_OF_TASKS_QUERY);
		statement.setString(1, typeOfTasks.getWording());
		statement.setString(2, typeOfTasks.getDescription());
		statement.setInt(3, typeOfTasks.getId());

		statement.executeUpdate();
	}

	@Override
	public void removeTypeOfTasks(TypeOfTasks typeOfTasks) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(REMOVE_TYPE_OF_TASKS_QUERY);
		statement.setInt(1, typeOfTasks.getId());

		statement.executeUpdate();
	}
}
