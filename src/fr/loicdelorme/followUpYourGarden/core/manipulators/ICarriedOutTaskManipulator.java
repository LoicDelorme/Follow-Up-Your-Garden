package fr.loicdelorme.followUpYourGarden.core.manipulators;

import java.sql.SQLException;
import java.util.List;

import fr.loicdelorme.followUpYourGarden.core.models.CarriedOutTask;

/**
 * This interface allow you to handle carried out task.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public interface ICarriedOutTaskManipulator extends IManipulator
{
	/**
	 * Get all carried out tasks.
	 * 
	 * @param groupOfPlantsManipulator
	 *            A group of plants manipulator.
	 * @param typeOfTasksManipulator
	 *            A type of tasks manipulator.
	 * @param positionManipulator A position manipulator.
	 * @param typeOfPlantsManipulator A type of plants manipulator.
	 * 
	 * @return A list of carried out tasks.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public List<CarriedOutTask> getCarriedOutTasks(IGroupOfPlantsManipulator groupOfPlantsManipulator, ITypeOfTasksManipulator typeOfTasksManipulator, IPositionManipulator positionManipulator, ITypeOfPlantsManipulator typeOfPlantsManipulator) throws SQLException;

	/**
	 * Get a carried out task.
	 * 
	 * @param carriedOutTaskId
	 *            A carried out task id.
	 * @param groupOfPlantsManipulator
	 *            A group of plants manipulator.
	 * @param typeOfTasksManipulator
	 *            A type of tasks manipulator.
	 * @param positionManipulator A position manipulator.
	 * @param typeOfPlantsManipulator A type of plants manipulator.
	 * @return A carried out task.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public CarriedOutTask getCarriedOutTask(int carriedOutTaskId, IGroupOfPlantsManipulator groupOfPlantsManipulator, ITypeOfTasksManipulator typeOfTasksManipulator, IPositionManipulator positionManipulator, ITypeOfPlantsManipulator typeOfPlantsManipulator) throws SQLException;

	/**
	 * Get all carried out tasks for a specific group of plants.
	 * 
	 * @param groupOfPlantsId
	 *            A group of plants id.
	 * @param groupOfPlantsManipulator
	 *            A group of plants manipulator.
	 * @param typeOfTasksManipulator
	 *            A type of tasks manipulator.
	 * @param positionManipulator
	 *            A position manipulator.
	 * @param typeOfPlantsManipulator
	 *            A type of plants manipulator.
	 * @return A list of carried out tasks for the specific group of plants.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public List<CarriedOutTask> getCarriedOutTasks(int groupOfPlantsId, IGroupOfPlantsManipulator groupOfPlantsManipulator, ITypeOfTasksManipulator typeOfTasksManipulator, IPositionManipulator positionManipulator, ITypeOfPlantsManipulator typeOfPlantsManipulator) throws SQLException;

	/**
	 * Add a carried out task.
	 * 
	 * @param carriedOutTask
	 *            A carried out task.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public void addCarriedOutTask(CarriedOutTask carriedOutTask) throws SQLException;

	/**
	 * Remove a carried out task.
	 * 
	 * @param carriedOutTask
	 *            A carried out task.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public void removeCarriedOutTask(CarriedOutTask carriedOutTask) throws SQLException;

	/**
	 * Remove a carried out task for a specific group of plants.
	 * 
	 * @param groupOfPlantsId
	 *            A group of plants id.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public void removeCarriedOutTasks(int groupOfPlantsId) throws SQLException;
}
