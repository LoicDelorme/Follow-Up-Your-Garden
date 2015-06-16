package fr.loicdelorme.followUpYourGarden.core.manipulators.models.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.IGroupOfPlantsManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.IPositionManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITypeOfPlantsManipulator;
import fr.loicdelorme.followUpYourGarden.core.models.GroupOfPlants;
import fr.loicdelorme.followUpYourGarden.core.models.Position;
import fr.loicdelorme.followUpYourGarden.core.models.TypeOfPlants;

/**
 * This class allow you to handle group of plants using a database.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class GroupOfPlantsDatabaseManipulator implements IGroupOfPlantsManipulator
{
	/**
	 * The database columns names.
	 */
	public static final String[] COLUMNS_NAMES = new String[] { "id_group_of_plants", "wording", "planting_date", "path", "r_level", "g_level", "b_level" };

	/**
	 * Get all groups of plants with a database query.
	 */
	public static final String GET_GROUPS_OF_PLANTS_QUERY = "SELECT id_group_of_plants, wording, planting_date, path, r_level, g_level, b_level FROM GroupOfPlants;";

	/**
	 * Get a group of plants with a database query.
	 */
	public static final String GET_GROUP_OF_PLANTS_QUERY = "SELECT id_group_of_plants, wording, planting_date, path, r_level, g_level, b_level FROM GroupOfPlants WHERE id_group_of_plants = ?;";

	/**
	 * Add a group of plants with a database query.
	 */
	public static final String ADD_GROUP_OF_PLANTS_QUERY = "INSERT INTO GroupOfPlants (wording, planting_date, path, r_level, g_level, b_level) VALUES (?, ?, ?, ?, ?, ?);";

	/**
	 * Update a group of plants with a database query.
	 */
	public static final String UPDATE_GROUP_OF_PLANTS_QUERY = "UPDATE GroupOfPlants SET wording = ?, planting_date = ?, path = ?, r_level = ?, g_level = ?, b_level = ? WHERE id_group_of_plants = ?;";

	/**
	 * Remove a group of plants with a database query.
	 */
	public static final String REMOVE_GROUP_OF_PLANTS_QUERY = "DELETE FROM GroupOfPlants WHERE id_group_of_plants = ?;";

	/**
	 * Add types of plants' composition with a database query.
	 */
	public static final String ADD_TYPES_OF_PLANTS_COMPOSITION_QUERY = "INSERT INTO IsComposedOf (id_group_of_plants, id_type_of_plants) VALUES (?, ?);";

	/**
	 * Remove types of plants' composition with a database query.
	 */
	public static final String REMOVE_TYPES_OF_PLANTS_COMPOSITION_QUERY = "DELETE FROM IsComposedOf WHERE id_group_of_plants = ?;";

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
	public List<GroupOfPlants> getGroupsOfPlants(IPositionManipulator positionManipulator, ITypeOfPlantsManipulator typeOfPlantsManipulator) throws SQLException
	{
		List<GroupOfPlants> groupsOfPlants = new ArrayList<GroupOfPlants>();

		Statement statement = this.connection.createStatement();

		ResultSet resultSet = statement.executeQuery(GET_GROUPS_OF_PLANTS_QUERY);

		positionManipulator.setConnection(this.connection);
		typeOfPlantsManipulator.setConnection(this.connection);

		int id;

		while (resultSet.next())
		{
			id = resultSet.getInt(COLUMNS_NAMES[0]);
			groupsOfPlants.add(new GroupOfPlants(id, resultSet.getString(COLUMNS_NAMES[1]), resultSet.getDate(COLUMNS_NAMES[2]).toLocalDate(), resultSet.getString(COLUMNS_NAMES[3]), Color.rgb(resultSet.getInt(COLUMNS_NAMES[4]), resultSet.getInt(COLUMNS_NAMES[5]), resultSet.getInt(COLUMNS_NAMES[6])), typeOfPlantsManipulator.getTypesOfPlants(id), positionManipulator.getPositions(id)));
		}

		groupsOfPlants.sort(null);

		return groupsOfPlants;
	}

	@Override
	public GroupOfPlants getGroupOfPlants(int groupOfPlantsId, IPositionManipulator positionManipulator, ITypeOfPlantsManipulator typeOfPlantsManipulator) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(GET_GROUP_OF_PLANTS_QUERY);
		statement.setInt(1, groupOfPlantsId);

		ResultSet resultSet = statement.executeQuery();
		resultSet.absolute(1);

		positionManipulator.setConnection(this.connection);
		typeOfPlantsManipulator.setConnection(this.connection);

		GroupOfPlants groupOfPlants = new GroupOfPlants(resultSet.getInt(COLUMNS_NAMES[0]), resultSet.getString(COLUMNS_NAMES[1]), resultSet.getDate(COLUMNS_NAMES[2]).toLocalDate(), resultSet.getString(COLUMNS_NAMES[3]), Color.rgb(resultSet.getInt(COLUMNS_NAMES[4]), resultSet.getInt(COLUMNS_NAMES[5]), resultSet.getInt(COLUMNS_NAMES[6])), typeOfPlantsManipulator.getTypesOfPlants(groupOfPlantsId), positionManipulator.getPositions(groupOfPlantsId));

		return groupOfPlants;
	}

	@Override
	public void addGroupOfPlants(GroupOfPlants groupOfPlants, IPositionManipulator positionManipulator) throws SQLException
	{
		int generatedKey;

		PreparedStatement statement = this.connection.prepareStatement(ADD_GROUP_OF_PLANTS_QUERY, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, groupOfPlants.getWording());
		statement.setString(2, groupOfPlants.getPlantingDate().toString());
		statement.setString(3, groupOfPlants.getPath());
		statement.setInt(4, ((int) groupOfPlants.getIconColor().getRed() * 255));
		statement.setInt(5, ((int) groupOfPlants.getIconColor().getGreen() * 255));
		statement.setInt(6, ((int) groupOfPlants.getIconColor().getBlue() * 255));

		statement.executeUpdate();

		generatedKey = getGeneratedKey(statement.getGeneratedKeys());

		positionManipulator.setConnection(this.connection);

		for (Position currentPosition : groupOfPlants.getPositions())
		{
			positionManipulator.addPosition(new Position(currentPosition.getX(), currentPosition.getY(), generatedKey));
		}

		for (TypeOfPlants currentTypeOfPlants : groupOfPlants.getTypesOfPlants())
		{
			addGroupOfPlantsComposition(currentTypeOfPlants, generatedKey);
		}
	}

	@Override
	public void updateGroupOfPlants(GroupOfPlants groupOfPlants, IPositionManipulator positionManipulator) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(UPDATE_GROUP_OF_PLANTS_QUERY, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, groupOfPlants.getWording());
		statement.setString(2, groupOfPlants.getPlantingDate().toString());
		statement.setString(3, groupOfPlants.getPath());
		statement.setInt(4, ((int) groupOfPlants.getIconColor().getRed() * 255));
		statement.setInt(5, ((int) groupOfPlants.getIconColor().getGreen() * 255));
		statement.setInt(6, ((int) groupOfPlants.getIconColor().getBlue() * 255));
		statement.setInt(7, groupOfPlants.getId());

		statement.executeUpdate();

		positionManipulator.setConnection(this.connection);

		positionManipulator.removePositions(groupOfPlants.getId());

		for (Position currentPosition : groupOfPlants.getPositions())
		{
			positionManipulator.addPosition(new Position(currentPosition.getX(), currentPosition.getY(), groupOfPlants.getId()));
		}

		removeGroupOfPlantsComposition(groupOfPlants.getId());

		for (TypeOfPlants currentTypeOfPlants : groupOfPlants.getTypesOfPlants())
		{
			addGroupOfPlantsComposition(currentTypeOfPlants, groupOfPlants.getId());
		}
	}

	@Override
	public void removeGroupOfPlants(GroupOfPlants groupOfPlants) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(REMOVE_GROUP_OF_PLANTS_QUERY);
		statement.setInt(1, groupOfPlants.getId());

		statement.executeUpdate();
	}

	/**
	 * Get the generated key.
	 * 
	 * @param generatedKeys
	 *            A result set resulting from a group of plants insertion.
	 * @return The generated key.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	private int getGeneratedKey(ResultSet generatedKeys) throws SQLException
	{
		generatedKeys.next();
		int result = generatedKeys.getInt(1);

		return result;
	}

	/**
	 * Add the composition of a group of plants.
	 * 
	 * @param typeOfPlants
	 *            A type of plants.
	 * @param generatedKey
	 *            The id of the group of plants.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	private void addGroupOfPlantsComposition(TypeOfPlants typeOfPlants, int generatedKey) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(ADD_TYPES_OF_PLANTS_COMPOSITION_QUERY);
		statement.setInt(1, generatedKey);
		statement.setInt(2, typeOfPlants.getId());

		statement.executeUpdate();
	}

	/**
	 * Remove the composition of a group of plants.
	 * 
	 * @param groupOfPlantsId
	 *            The group of plants id.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	private void removeGroupOfPlantsComposition(int groupOfPlantsId) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(REMOVE_TYPES_OF_PLANTS_COMPOSITION_QUERY);
		statement.setInt(1, groupOfPlantsId);

		statement.executeUpdate();
	}
}
