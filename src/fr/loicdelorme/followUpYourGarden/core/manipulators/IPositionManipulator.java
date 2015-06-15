package fr.loicdelorme.followUpYourGarden.core.manipulators;

import java.sql.SQLException;
import java.util.List;

import fr.loicdelorme.followUpYourGarden.core.models.Position;

/**
 * This interface allow you to handle position.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public interface IPositionManipulator extends IManipulator
{
	/**
	 * Get all positions.
	 * 
	 * @return A list of positions.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public List<Position> getPositions() throws SQLException;

	/**
	 * Get a position.
	 * 
	 * @param x
	 *            The X coordinate.
	 * @param y
	 *            The Y coordinate.
	 * @return A position.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public Position getPosition(int x, int y) throws SQLException;

	/**
	 * Get all positions for a specific group of plants.
	 * 
	 * @param groupOfPlantsId
	 *            A group of plants id.
	 * @return A list of positions for the specific group of plants.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public List<Position> getPositions(int groupOfPlantsId) throws SQLException;

	/**
	 * Add a position.
	 * 
	 * @param position
	 *            A position.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public void addPosition(Position position) throws SQLException;

	/**
	 * Remove a position.
	 * 
	 * @param position
	 *            A position.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public void removePosition(Position position) throws SQLException;

	/**
	 * Remove positions for a specific group of plants.
	 * 
	 * @param idGroupOfPlants
	 *            An id of the group of plants.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public void removePositions(int idGroupOfPlants) throws SQLException;
}
