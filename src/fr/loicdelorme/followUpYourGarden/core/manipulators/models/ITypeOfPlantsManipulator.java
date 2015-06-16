package fr.loicdelorme.followUpYourGarden.core.manipulators.models;

import java.sql.SQLException;
import java.util.List;

import fr.loicdelorme.followUpYourGarden.core.models.TypeOfPlants;

/**
 * This interface allow you to handle type of plants.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public interface ITypeOfPlantsManipulator extends IManipulator
{
	/**
	 * Get all types of plants.
	 * 
	 * @return A list of types of plants.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public List<TypeOfPlants> getTypesOfPlants() throws SQLException;

	/**
	 * Get a type of plants.
	 * 
	 * @param typeOfPlantsId
	 *            A type of plants id.
	 * @return A type of plants.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public TypeOfPlants getTypeOfPlants(int typeOfPlantsId) throws SQLException;

	/**
	 * Get all types of plants for a specific group of plants.
	 * 
	 * @param groupOfPlantsId
	 *            A group of plants id.
	 * @return A list of types of plants for the specific group of plants.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public List<TypeOfPlants> getTypesOfPlants(int groupOfPlantsId) throws SQLException;

	/**
	 * Add a type of plants.
	 * 
	 * @param typeOfPlants
	 *            A type of plants.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public void addTypeOfPlants(TypeOfPlants typeOfPlants) throws SQLException;

	/**
	 * Update a type of plants.
	 * 
	 * @param typeOfPlants
	 *            A type of plants.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public void updateTypeOfPlants(TypeOfPlants typeOfPlants) throws SQLException;

	/**
	 * Remove a type of plants.
	 * 
	 * @param typeOfPlants
	 *            A type of plants.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public void removeTypeOfPlants(TypeOfPlants typeOfPlants) throws SQLException;
}
