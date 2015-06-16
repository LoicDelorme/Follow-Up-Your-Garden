package fr.loicdelorme.followUpYourGarden.core.manipulators;

import java.sql.SQLException;
import java.util.List;

import fr.loicdelorme.followUpYourGarden.core.models.TaskToBeCarryOut;

/**
 * This interface allow you to handle task to be carry out.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public interface ITaskToBeCarryOutManipulator extends IManipulator
{
	/**
	 * Get all tasks to be carry out.
	 * 
	 * @param groupOfPlantsManipulator
	 *            A group of plants manipulator.
	 * @param typeOfTasksManipulator
	 *            A type of tasks manipulator.
	 * @param positionManipulator
	 *            A position manipulator.
	 * @param typeOfPlantsManipulator
	 *            A type of plants manipulator.
	 * @return A list of tasks to be carry out.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public List<TaskToBeCarryOut> getTasksToBeCarryOut(IGroupOfPlantsManipulator groupOfPlantsManipulator, ITypeOfTasksManipulator typeOfTasksManipulator, IPositionManipulator positionManipulator, ITypeOfPlantsManipulator typeOfPlantsManipulator) throws SQLException;

	/**
	 * Get a task to be carry out.
	 * 
	 * @param groupOfPlantsId
	 *            A group of plants id.
	 * @param typeOfTasksId
	 *            A type of tasks id.
	 * @param groupOfPlantsManipulator
	 *            A group of plants manipulator.
	 * @param typeOfTasksManipulator
	 *            A type of tasks manipulator.
	 * @param positionManipulator
	 *            A position manipulator.
	 * @param typeOfPlantsManipulator
	 *            A type of plants manipulator.
	 * @return A task to be carry out.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public TaskToBeCarryOut getTaskToBeCarryOut(int groupOfPlantsId, int typeOfTasksId, IGroupOfPlantsManipulator groupOfPlantsManipulator, ITypeOfTasksManipulator typeOfTasksManipulator, IPositionManipulator positionManipulator, ITypeOfPlantsManipulator typeOfPlantsManipulator) throws SQLException;

	/**
	 * Get all tasks to be carry out for a specific group of plants.
	 * 
	 * @param idGroupOfPlants
	 *            A group of plants id.
	 * @param groupOfPlantsManipulator
	 *            A group of plants manipulator.
	 * @param typeOfTasksManipulator
	 *            A type of tasks manipulator.
	 * @param positionManipulator
	 *            A position manipulator.
	 * @param typeOfPlantsManipulator
	 *            A type of plants manipulator.
	 * @return A list of tasks to be carry out for the specific group of plants.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public List<TaskToBeCarryOut> getTasksToBeCarryOut(int idGroupOfPlants, IGroupOfPlantsManipulator groupOfPlantsManipulator, ITypeOfTasksManipulator typeOfTasksManipulator, IPositionManipulator positionManipulator, ITypeOfPlantsManipulator typeOfPlantsManipulator) throws SQLException;

	/**
	 * Add a task to be carry out.
	 * 
	 * @param taskToBeCarryOut
	 *            A task to be carry out.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public void addTaskToBeCarryOut(TaskToBeCarryOut taskToBeCarryOut) throws SQLException;

	/**
	 * Update a task to be carry out.
	 * 
	 * @param taskToBeCarryOut
	 *            A task to be carry out.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public void updateTaskToBeCarryOut(TaskToBeCarryOut taskToBeCarryOut) throws SQLException;

	/**
	 * Remove a task to be carry out.
	 * 
	 * @param taskToBeCarryOut
	 *            A task to be carry out.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public void removeTaskToBeCarryOut(TaskToBeCarryOut taskToBeCarryOut) throws SQLException;
}
