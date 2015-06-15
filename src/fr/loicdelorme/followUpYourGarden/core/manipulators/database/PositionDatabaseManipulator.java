package fr.loicdelorme.followUpYourGarden.core.manipulators.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.loicdelorme.followUpYourGarden.core.manipulators.IPositionManipulator;
import fr.loicdelorme.followUpYourGarden.core.models.Position;

/**
 * This class allow you to handle position using a database.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class PositionDatabaseManipulator implements IPositionManipulator
{
	/**
	 * The database columns names.
	 */
	public static final String[] COLUMNS_NAMES = new String[] { "x", "y", "id_group_of_plants" };

	/**
	 * Get all positions with a database query.
	 */
	public static final String GET_POSITIONS_QUERY = "SELECT x, y, id_group_of_plants FROM Position;";

	/**
	 * Get a position with a database query.
	 */
	public static final String GET_POSITION_QUERY = "SELECT x, y, id_group_of_plants FROM Position WHERE x = ? AND y = ?;";

	/**
	 * Get all positions for a specific group of plants with a database query.
	 */
	public static final String GET_POSITIONS_FOR_A_SPECIFIC_GROUP_OF_PLANTS_QUERY = "SELECT x, y, id_group_of_plants FROM Position WHERE id_group_of_plants = ?;";

	/**
	 * Add a position with a database query.
	 */
	public static final String ADD_POSITION_QUERY = "INSERT INTO Position (x, y, id_group_of_plants) VALUES (?, ?, ?);";

	/**
	 * Remove a position with a database query.
	 */
	public static final String REMOVE_POSITION_QUERY = "DELETE FROM Position WHERE x = ? AND y = ?;";

	/**
	 * Remove positions for a specific group of plants with a database query.
	 */
	public static final String REMOVE_POSITIONS_FOR_A_SPECIFIC_GROUP_OF_PLANTS_QUERY = "DELETE FROM Position WHERE id_group_of_plants = ?;";

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
	public List<Position> getPositions() throws SQLException
	{
		List<Position> positions = new ArrayList<Position>();

		Statement statement = this.connection.createStatement();

		ResultSet resultSet = statement.executeQuery(GET_POSITIONS_QUERY);

		while (resultSet.next())
		{
			positions.add(new Position(resultSet.getInt(COLUMNS_NAMES[0]), resultSet.getInt(COLUMNS_NAMES[1]), resultSet.getInt(COLUMNS_NAMES[2])));
		}

		positions.sort(null);

		return positions;
	}

	@Override
	public Position getPosition(int x, int y) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(GET_POSITION_QUERY);
		statement.setInt(1, x);
		statement.setInt(2, y);

		ResultSet resultSet = statement.executeQuery();
		resultSet.absolute(1);

		Position position = new Position(resultSet.getInt(COLUMNS_NAMES[0]), resultSet.getInt(COLUMNS_NAMES[1]), resultSet.getInt(COLUMNS_NAMES[2]));

		return position;
	}

	@Override
	public List<Position> getPositions(int groupOfPlantsId) throws SQLException
	{
		List<Position> positions = new ArrayList<Position>();

		PreparedStatement statement = this.connection.prepareStatement(GET_POSITIONS_FOR_A_SPECIFIC_GROUP_OF_PLANTS_QUERY);
		statement.setInt(1, groupOfPlantsId);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next())
		{
			positions.add(new Position(resultSet.getInt(COLUMNS_NAMES[0]), resultSet.getInt(COLUMNS_NAMES[1]), resultSet.getInt(COLUMNS_NAMES[2])));
		}

		positions.sort(null);

		return positions;
	}

	@Override
	public void addPosition(Position position) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(ADD_POSITION_QUERY);
		statement.setInt(1, position.getX());
		statement.setInt(2, position.getY());
		statement.setInt(3, position.getGroupOfPlantsId());

		statement.executeUpdate();
	}

	@Override
	public void removePosition(Position position) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(REMOVE_POSITION_QUERY);
		statement.setInt(1, position.getX());
		statement.setInt(2, position.getY());

		statement.executeUpdate();
	}

	@Override
	public void removePositions(int groupOfPlantsId) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(REMOVE_POSITIONS_FOR_A_SPECIFIC_GROUP_OF_PLANTS_QUERY);
		statement.setInt(1, groupOfPlantsId);

		statement.executeUpdate();
	}
}
