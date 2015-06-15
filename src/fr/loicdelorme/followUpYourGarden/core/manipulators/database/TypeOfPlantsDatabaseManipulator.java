package fr.loicdelorme.followUpYourGarden.core.manipulators.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.loicdelorme.followUpYourGarden.core.manipulators.ITypeOfPlantsManipulator;
import fr.loicdelorme.followUpYourGarden.core.models.TypeOfPlants;

/**
 * This class allow you to handle type of plants using a database.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class TypeOfPlantsDatabaseManipulator implements ITypeOfPlantsManipulator
{
	/**
	 * The database columns names.
	 */
	public static final String[] COLUMNS_NAMES = new String[] { "id_type_of_plants", "wording" };

	/**
	 * Get all types of plants with a database query.
	 */
	public static final String GET_TYPES_OF_PLANTS_QUERY = "SELECT id_type_of_plants, wording FROM TypeOfPlants;";

	/**
	 * Get a type of plants with a database query.
	 */
	public static final String GET_TYPE_OF_PLANTS_QUERY = "SELECT id_type_of_plants, wording FROM TypeOfPlants WHERE id_type_of_plants = ?;";

	/**
	 * Get all types of plants for a specific group of plants with a database query.
	 */
	public static final String GET_TYPES_OF_PLANTS_FOR_A_SPECIFIC_GROUP_OF_PLANTS_QUERY = "SELECT id_group_of_plants, id_type_of_plants FROM IsComposedOf WHERE id_group_of_plants = ?";

	/**
	 * Add a type of plants with a database query.
	 */
	public static final String ADD_TYPE_OF_PLANTS_QUERY = "INSERT INTO TypeOfPlants (wording) VALUES (?);";

	/**
	 * Update a type of plants with a database query.
	 */
	public static final String UPDATE_TYPE_OF_PLANTS_QUERY = "UPDATE TypeOfPlants SET wording = ? WHERE id_type_of_plants = ?;";

	/**
	 * Remove a type of plants with a database query.
	 */
	public static final String REMOVE_TYPE_OF_PLANTS_QUERY = "DELETE FROM TypeOfPlants WHERE id_type_of_plants = ?;";

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
	public List<TypeOfPlants> getTypesOfPlants() throws SQLException
	{
		List<TypeOfPlants> typesOfPlants = new ArrayList<TypeOfPlants>();

		Statement statement = this.connection.createStatement();

		ResultSet resultSet = statement.executeQuery(GET_TYPES_OF_PLANTS_QUERY);

		while (resultSet.next())
		{
			typesOfPlants.add(new TypeOfPlants(resultSet.getInt(COLUMNS_NAMES[0]), resultSet.getString(COLUMNS_NAMES[1])));
		}

		typesOfPlants.sort(null);

		return typesOfPlants;
	}

	@Override
	public TypeOfPlants getTypeOfPlants(int typeOfPlantsId) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(GET_TYPE_OF_PLANTS_QUERY);
		statement.setInt(1, typeOfPlantsId);

		ResultSet resultSet = statement.executeQuery();
		resultSet.absolute(1);

		TypeOfPlants typeOfPlants = new TypeOfPlants(resultSet.getInt(COLUMNS_NAMES[0]), resultSet.getString(COLUMNS_NAMES[1]));

		return typeOfPlants;
	}

	@Override
	public List<TypeOfPlants> getTypesOfPlants(int groupOfPlantsId) throws SQLException
	{
		List<TypeOfPlants> typesOfPlants = new ArrayList<TypeOfPlants>();

		PreparedStatement statement = this.connection.prepareStatement(GET_TYPES_OF_PLANTS_FOR_A_SPECIFIC_GROUP_OF_PLANTS_QUERY);
		statement.setInt(1, groupOfPlantsId);

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next())
		{
			typesOfPlants.add(getTypeOfPlants(resultSet.getInt(COLUMNS_NAMES[0])));
		}

		typesOfPlants.sort(null);

		return typesOfPlants;
	}

	@Override
	public void addTypeOfPlants(TypeOfPlants typeOfPlants) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(ADD_TYPE_OF_PLANTS_QUERY);
		statement.setString(1, typeOfPlants.getWording());

		statement.executeUpdate();
	}

	@Override
	public void updateTypeOfPlants(TypeOfPlants typeOfPlants) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(UPDATE_TYPE_OF_PLANTS_QUERY);
		statement.setString(1, typeOfPlants.getWording());
		statement.setInt(2, typeOfPlants.getId());

		statement.executeUpdate();
	}

	@Override
	public void removeTypeOfPlants(TypeOfPlants typeOfPlants) throws SQLException
	{
		PreparedStatement statement = this.connection.prepareStatement(REMOVE_TYPE_OF_PLANTS_QUERY);
		statement.setInt(1, typeOfPlants.getId());

		statement.executeUpdate();
	}
}
