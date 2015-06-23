package fr.loicdelorme.followUpYourGarden.controllers;

import fr.loicdelorme.followUpYourGarden.core.manipulators.models.ICarriedOutTaskManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.IGroupOfPlantsManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.IPositionManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITaskToBeCarryOutManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITypeOfPlantsManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITypeOfTasksManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.database.CarriedOutTaskDatabaseManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.database.GroupOfPlantsDatabaseManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.database.PositionDatabaseManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.database.TaskToBeCarryOutDatabaseManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.database.TypeOfPlantsDatabaseManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.database.TypeOfTasksDatabaseManipulator;
import fr.loicdelorme.followUpYourGarden.core.services.CarriedOutTaskServices;
import fr.loicdelorme.followUpYourGarden.core.services.GroupOfPlantsServices;
import fr.loicdelorme.followUpYourGarden.core.services.PositionServices;
import fr.loicdelorme.followUpYourGarden.core.services.TaskToBeCarryOutServices;
import fr.loicdelorme.followUpYourGarden.core.services.TypeOfPlantsServices;
import fr.loicdelorme.followUpYourGarden.core.services.TypeOfTasksServices;

/**
 * This class allow you to handle services.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class FollowUpYourGardenServices
{
	/**
	 * The carried out task manipulator.
	 */
	private static final ICarriedOutTaskManipulator CARRIED_OUT_TASK_MANIPULATOR = new CarriedOutTaskDatabaseManipulator();

	/**
	 * The group of plants manipulator.
	 */
	private static final IGroupOfPlantsManipulator GROUP_OF_PLANTS_MANIPULATOR = new GroupOfPlantsDatabaseManipulator();

	/**
	 * The position manipulator.
	 */
	private static final IPositionManipulator POSITION_MANIPULATOR = new PositionDatabaseManipulator();

	/**
	 * The task to be carry out manipulator.
	 */
	private static final ITaskToBeCarryOutManipulator TASK_TO_BE_CARRY_OUT_MANIPULATOR = new TaskToBeCarryOutDatabaseManipulator();

	/**
	 * The type of plants manipulator.
	 */
	private static final ITypeOfPlantsManipulator TYPE_OF_PLANTS_MANIPULATOR = new TypeOfPlantsDatabaseManipulator();

	/**
	 * The type of tasks manipulator.
	 */
	private static final ITypeOfTasksManipulator TYPE_OF_TASKS_MANIPULATOR = new TypeOfTasksDatabaseManipulator();

	/**
	 * Get the carried out task services.
	 * 
	 * @return The carried out task services.
	 */
	public static CarriedOutTaskServices getCarriedOutTaskServices()
	{
		return new CarriedOutTaskServices(CARRIED_OUT_TASK_MANIPULATOR, GROUP_OF_PLANTS_MANIPULATOR, TYPE_OF_TASKS_MANIPULATOR, POSITION_MANIPULATOR, TYPE_OF_PLANTS_MANIPULATOR);
	}

	/**
	 * Get the group of plants services.
	 * 
	 * @return The group of plants services.
	 */
	public static GroupOfPlantsServices getGroupOfPlantsServices()
	{
		return new GroupOfPlantsServices(GROUP_OF_PLANTS_MANIPULATOR, POSITION_MANIPULATOR, TYPE_OF_PLANTS_MANIPULATOR);
	}

	/**
	 * Get the position services.
	 * 
	 * @return The position services.
	 */
	public static PositionServices getPositionServices()
	{
		return new PositionServices(POSITION_MANIPULATOR);
	}

	/**
	 * Get the task to be carry out services.
	 * 
	 * @return The task to be carry out services.
	 */
	public static TaskToBeCarryOutServices getTaskToBeCarryOutServices()
	{
		return new TaskToBeCarryOutServices(TASK_TO_BE_CARRY_OUT_MANIPULATOR, GROUP_OF_PLANTS_MANIPULATOR, TYPE_OF_TASKS_MANIPULATOR, POSITION_MANIPULATOR, TYPE_OF_PLANTS_MANIPULATOR);
	}

	/**
	 * Get the type of plants services.
	 * 
	 * @return The type of plants services.
	 */
	public static TypeOfPlantsServices getTypeOfPlantsServices()
	{
		return new TypeOfPlantsServices(TYPE_OF_PLANTS_MANIPULATOR);
	}

	/**
	 * Get the type of tasks services.
	 * 
	 * @return The type of tasks services.
	 */
	public static TypeOfTasksServices getTypeOfTasksServices()
	{
		return new TypeOfTasksServices(TYPE_OF_TASKS_MANIPULATOR);
	}
}
