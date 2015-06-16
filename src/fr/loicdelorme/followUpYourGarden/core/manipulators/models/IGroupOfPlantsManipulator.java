package fr.loicdelorme.followUpYourGarden.core.manipulators;

import java.sql.SQLException;
import java.util.List;

import fr.loicdelorme.followUpYourGarden.core.models.GroupOfPlants;

/**
 * This interface allow you to handle group of plants.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public interface IGroupOfPlantsManipulator extends IManipulator
{
	/**
	 * Get all groups of plants.
	 * 
	 * @param positionManipulator
	 *            A position manipulator.
	 * @param typeOfPlantsManipulator
	 *            A type of plantes manipulator.
	 * 
	 * @return A list of groups of plants.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public List<GroupOfPlants> getGroupsOfPlants(IPositionManipulator positionManipulator, ITypeOfPlantsManipulator typeOfPlantsManipulator) throws SQLException;

	/**
	 * Get a group of plants.
	 * 
	 * @param groupOfPlantsId
	 *            A group of plants id.
	 * @param positionManipulator
	 *            A position manipulator.
	 * @param typeOfPlantsManipulator
	 * @return A group of plants.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public GroupOfPlants getGroupOfPlants(int groupOfPlantsId, IPositionManipulator positionManipulator, ITypeOfPlantsManipulator typeOfPlantsManipulator) throws SQLException;

	/**
	 * Add a group of plants.
	 * 
	 * @param groupOfPlants
	 *            A group of plants.
	 * @param positionManipulator
	 *            A position manipulator.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public void addGroupOfPlants(GroupOfPlants groupOfPlants, IPositionManipulator positionManipulator) throws SQLException;

	/**
	 * Update a group of plants.
	 * 
	 * @param groupOfPlants
	 *            A group of plants.
	 * @param positionManipulator
	 *            A position manipulator.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public void updateGroupOfPlants(GroupOfPlants groupOfPlants, IPositionManipulator positionManipulator) throws SQLException;

	/**
	 * Remove a group of plants.
	 * 
	 * @param groupOfPlants
	 *            A group of plants.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public void removeGroupOfPlants(GroupOfPlants groupOfPlants) throws SQLException;
}
