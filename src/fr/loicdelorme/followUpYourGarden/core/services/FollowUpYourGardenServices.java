package fr.loicdelorme.followUpYourGarden.core.services;

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
	private final ICarriedOutTaskManipulator carriedOutTaskManipulator = new CarriedOutTaskDatabaseManipulator();

	/**
	 * The group of plants manipulator.
	 */
	private final IGroupOfPlantsManipulator groupOfPlantsManipulator = new GroupOfPlantsDatabaseManipulator();

	/**
	 * The position manipulator.
	 */
	private final IPositionManipulator positionManipulator = new PositionDatabaseManipulator();

	/**
	 * The task to be carry out manipulator.
	 */
	private final ITaskToBeCarryOutManipulator taskToBeCarryOutManipulator = new TaskToBeCarryOutDatabaseManipulator();

	/**
	 * The type of plants manipulator.
	 */
	private final ITypeOfPlantsManipulator typeOfPlantsManipulator = new TypeOfPlantsDatabaseManipulator();

	/**
	 * The type of tasks manipulator.
	 */
	private final ITypeOfTasksManipulator typeOfTasksManipulator = new TypeOfTasksDatabaseManipulator();

	/**
	 * Get the carried out task services.
	 * 
	 * @return The carried out task services.
	 */
	public CarriedOutTaskServices getCarriedOutTaskServices()
	{
		return new CarriedOutTaskServices(this.carriedOutTaskManipulator, this.groupOfPlantsManipulator, this.typeOfTasksManipulator, this.positionManipulator, this.typeOfPlantsManipulator);
	}

	/**
	 * Get the group of plants services.
	 * 
	 * @return The group of plants services.
	 */
	public GroupOfPlantsServices getGroupOfPlantsServices()
	{
		return new GroupOfPlantsServices(this.groupOfPlantsManipulator, this.positionManipulator, this.typeOfPlantsManipulator);
	}

	/**
	 * Get the position services.
	 * 
	 * @return The position services.
	 */
	public PositionServices getPositionServices()
	{
		return new PositionServices(this.positionManipulator);
	}

	/**
	 * Get the task to be carry out services.
	 * 
	 * @return The task to be carry out services.
	 */
	public TaskToBeCarryOutServices getTaskToBeCarryOutServices()
	{
		return new TaskToBeCarryOutServices(this.taskToBeCarryOutManipulator, this.groupOfPlantsManipulator, this.typeOfTasksManipulator, this.positionManipulator, this.typeOfPlantsManipulator, this.carriedOutTaskManipulator);
	}

	/**
	 * Get the type of plants services.
	 * 
	 * @return The type of plants services.
	 */
	public TypeOfPlantsServices getTypeOfPlantsServices()
	{
		return new TypeOfPlantsServices(this.typeOfPlantsManipulator);
	}

	/**
	 * Get the type of tasks services.
	 * 
	 * @return The type of tasks services.
	 */
	public TypeOfTasksServices getTypeOfTasksServices()
	{
		return new TypeOfTasksServices(this.typeOfTasksManipulator);
	}
}
