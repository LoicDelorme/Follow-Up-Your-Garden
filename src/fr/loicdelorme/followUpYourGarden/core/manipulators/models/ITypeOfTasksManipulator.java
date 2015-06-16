package fr.loicdelorme.followUpYourGarden.core.manipulators.models;

import java.sql.SQLException;
import java.util.List;

import fr.loicdelorme.followUpYourGarden.core.models.TypeOfTasks;

/**
 * This interface allow you to handle type of tasks.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public interface ITypeOfTasksManipulator extends IManipulator
{
	/**
	 * Get all types of tasks.
	 * 
	 * @return A list of types of tasks.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public List<TypeOfTasks> getTypesOfTasks() throws SQLException;

	/**
	 * Get a type of tasks.
	 * 
	 * @param typeOfTasksId
	 *            A type of tasks id.
	 * @return A type of tasks.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public TypeOfTasks getTypeOfTasks(int typeOfTasksId) throws SQLException;

	/**
	 * Add a type of tasks.
	 * 
	 * @param typeOfTasks
	 *            A type of tasks.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public void addTypeOfTasks(TypeOfTasks typeOfTasks) throws SQLException;

	/**
	 * Update a type of tasks.
	 * 
	 * @param typeOfTasks
	 *            A type of tasks.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public void updateTypeOfTasks(TypeOfTasks typeOfTasks) throws SQLException;

	/**
	 * Remove a type of tasks.
	 * 
	 * @param typeOfTasks
	 *            A type of tasks.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public void removeTypeOfTasks(TypeOfTasks typeOfTasks) throws SQLException;
}
