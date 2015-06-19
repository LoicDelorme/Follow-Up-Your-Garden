package fr.loicdelorme.followUpYourGarden.core.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javafx.scene.paint.Color;
import fr.loicdelorme.followUpYourGarden.core.database.MyDatabase;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.IGroupOfPlantsManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.IPositionManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITypeOfPlantsManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.sources.ISourceManipulator;
import fr.loicdelorme.followUpYourGarden.core.models.GroupOfPlants;
import fr.loicdelorme.followUpYourGarden.core.models.Position;
import fr.loicdelorme.followUpYourGarden.core.models.TypeOfPlants;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidGroupOfPlantsBlueLevelException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidGroupOfPlantsGreenLevelException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidGroupOfPlantsIdException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidGroupOfPlantsPathException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidGroupOfPlantsPositionsException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidGroupOfPlantsRedLevelException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidGroupOfPlantsTypesOfPlantsException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidGroupOfPlantsWordingException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingGroupOfPlantsIconColorException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingGroupOfPlantsIdException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingGroupOfPlantsPathException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingGroupOfPlantsPlantingDateException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingGroupOfPlantsPositionsException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingGroupOfPlantsTypesOfPlantsException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingGroupOfPlantsWordingException;

/**
 * This class allow you to handle groups of plants.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class GroupOfPlantsServices
{
	/**
	 * The minimal id.
	 */
	private static final int MINIMAL_ID = 1;

	/**
	 * The minimal level value.
	 */
	private static final int MINIMAL_LEVEL_VALUE = 0;

	/**
	 * The maximal level value.
	 */
	private static final int MAXIMAL_LEVEL_VALUE = 255;

	/**
	 * A group of plants manipulator.
	 */
	private IGroupOfPlantsManipulator groupOfPlantsManipulator;

	/**
	 * A position manipulator.
	 */
	private IPositionManipulator positionManipulator;

	/**
	 * A type of plants manipulator.
	 */
	private ITypeOfPlantsManipulator typeOfPlantsManipulator;

	/**
	 * Create a group of plants services.
	 * 
	 * @param groupOfPlantsManipulator
	 *            A group of plants manipulator.
	 * @param positionManipulator
	 *            A position manipulator.
	 * @param typeOfPlantsManipulator
	 *            A type of plants manipulator.
	 */
	public GroupOfPlantsServices(IGroupOfPlantsManipulator groupOfPlantsManipulator, IPositionManipulator positionManipulator, ITypeOfPlantsManipulator typeOfPlantsManipulator)
	{
		this.groupOfPlantsManipulator = groupOfPlantsManipulator;
		this.positionManipulator = positionManipulator;
		this.typeOfPlantsManipulator = typeOfPlantsManipulator;
	}

	/**
	 * Get all groups of plants.
	 * 
	 * @return A list of groups of plants.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 * @throws ClassNotFoundException
	 *             If the class is not found.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public List<GroupOfPlants> getGroupsOfPlants() throws ClassNotFoundException, FileNotFoundException, IOException, SQLException
	{
		ISourceManipulator sourceManipulator = MyDatabase.getInstance();
		sourceManipulator.openConnection();

		this.groupOfPlantsManipulator.setConnection(sourceManipulator.getConnection());
		List<GroupOfPlants> groupsOfPlants = this.groupOfPlantsManipulator.getGroupsOfPlants(this.positionManipulator, this.typeOfPlantsManipulator);

		sourceManipulator.closeConnection();

		return groupsOfPlants;
	}

	/**
	 * Add a group of plants.
	 * 
	 * @param wording
	 *            The wording.
	 * @param plantingDate
	 *            The planting date.
	 * @param path
	 *            The path.
	 * @param iconColor
	 *            The icon color.
	 * @param typesOfPlants
	 *            The types of plants.
	 * @param positions
	 *            The positions.
	 * @throws MissingGroupOfPlantsIdException
	 *             The id is missing.
	 * @throws MissingGroupOfPlantsWordingException
	 *             The wording is missing.
	 * @throws MissingGroupOfPlantsPlantingDateException
	 *             The planting date is missing.
	 * @throws MissingGroupOfPlantsPathException
	 *             The path is missing.
	 * @throws MissingGroupOfPlantsIconColorException
	 *             The icon color is missing.
	 * @throws MissingGroupOfPlantsTypesOfPlantsException
	 *             The types of plants is missing.
	 * @throws MissingGroupOfPlantsPositionsException
	 *             The positions is missing.
	 * @throws InvalidGroupOfPlantsIdException
	 *             The id is negative.
	 * @throws InvalidGroupOfPlantsWordingException
	 *             The wording is empty.
	 * @throws InvalidGroupOfPlantsPathException
	 *             The path is empty.
	 * @throws InvalidGroupOfPlantsRedLevelException
	 *             The red level value is not between 0 and 255.
	 * @throws InvalidGroupOfPlantsGreenLevelException
	 *             The green level value is not between 0 and 255.
	 * @throws InvalidGroupOfPlantsBlueLevelException
	 *             The blue level value is not between 0 and 255.
	 * @throws InvalidGroupOfPlantsTypesOfPlantsException
	 *             The list of types of plants is empty.
	 * @throws InvalidGroupOfPlantsPositionsException
	 *             The list of positions is empty.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 * @throws ClassNotFoundException
	 *             If the class is not found.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void addGroupOfPlants(String wording, LocalDate plantingDate, String path, Color iconColor, List<TypeOfPlants> typesOfPlants, List<Position> positions) throws MissingGroupOfPlantsIdException, MissingGroupOfPlantsWordingException, MissingGroupOfPlantsPlantingDateException, MissingGroupOfPlantsPathException, MissingGroupOfPlantsIconColorException, MissingGroupOfPlantsTypesOfPlantsException, MissingGroupOfPlantsPositionsException, InvalidGroupOfPlantsIdException, InvalidGroupOfPlantsWordingException, InvalidGroupOfPlantsPathException, InvalidGroupOfPlantsRedLevelException, InvalidGroupOfPlantsGreenLevelException, InvalidGroupOfPlantsBlueLevelException, InvalidGroupOfPlantsTypesOfPlantsException, InvalidGroupOfPlantsPositionsException, SQLException, ClassNotFoundException, FileNotFoundException, IOException
	{
		checkGroupOfPlantsParameters(GroupOfPlants.UNKNOWN_GROUP_OF_PLANTS_ID, wording, plantingDate, path, iconColor, typesOfPlants, positions);

		ISourceManipulator sourceManipulator = MyDatabase.getInstance();
		sourceManipulator.openConnection();

		this.groupOfPlantsManipulator.setConnection(sourceManipulator.getConnection());
		this.groupOfPlantsManipulator.addGroupOfPlants(new GroupOfPlants(GroupOfPlants.UNKNOWN_GROUP_OF_PLANTS_ID, wording, plantingDate, path, iconColor, typesOfPlants, positions), this.positionManipulator);

		sourceManipulator.closeConnection();
	}

	/**
	 * Update a group of plants.
	 * 
	 * @param wording
	 *            The wording.
	 * @param plantingDate
	 *            The planting date.
	 * @param path
	 *            The path.
	 * @param iconColor
	 *            The icon color.
	 * @param typesOfPlants
	 *            The types of plants.
	 * @param positions
	 *            The positions.
	 * @param oldGroupOfPlants
	 *            The old group of plants.
	 * @throws MissingGroupOfPlantsIdException
	 *             The id is missing.
	 * @throws MissingGroupOfPlantsWordingException
	 *             The wording is missing.
	 * @throws MissingGroupOfPlantsPlantingDateException
	 *             The planting date is missing.
	 * @throws MissingGroupOfPlantsPathException
	 *             The path is missing.
	 * @throws MissingGroupOfPlantsIconColorException
	 *             The icon color is missing.
	 * @throws MissingGroupOfPlantsTypesOfPlantsException
	 *             The types of plants is missing.
	 * @throws MissingGroupOfPlantsPositionsException
	 *             The positions is missing.
	 * @throws InvalidGroupOfPlantsIdException
	 *             The id is negative.
	 * @throws InvalidGroupOfPlantsWordingException
	 *             The wording is empty.
	 * @throws InvalidGroupOfPlantsPathException
	 *             The path is empty.
	 * @throws InvalidGroupOfPlantsRedLevelException
	 *             The red level value is not between 0 and 255.
	 * @throws InvalidGroupOfPlantsGreenLevelException
	 *             The green level value is not between 0 and 255.
	 * @throws InvalidGroupOfPlantsBlueLevelException
	 *             The blue level value is not between 0 and 255.
	 * @throws InvalidGroupOfPlantsTypesOfPlantsException
	 *             The list of types of plants is empty.
	 * @throws InvalidGroupOfPlantsPositionsException
	 *             The list of positions is empty.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 * @throws ClassNotFoundException
	 *             If the class is not found.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void updateGroupOfPlants(String wording, LocalDate plantingDate, String path, Color iconColor, List<TypeOfPlants> typesOfPlants, List<Position> positions, GroupOfPlants oldGroupOfPlants) throws MissingGroupOfPlantsIdException, MissingGroupOfPlantsWordingException, MissingGroupOfPlantsPlantingDateException, MissingGroupOfPlantsPathException, MissingGroupOfPlantsIconColorException, MissingGroupOfPlantsTypesOfPlantsException, MissingGroupOfPlantsPositionsException, InvalidGroupOfPlantsIdException, InvalidGroupOfPlantsWordingException, InvalidGroupOfPlantsPathException, InvalidGroupOfPlantsRedLevelException, InvalidGroupOfPlantsGreenLevelException, InvalidGroupOfPlantsBlueLevelException, InvalidGroupOfPlantsTypesOfPlantsException, InvalidGroupOfPlantsPositionsException, SQLException, ClassNotFoundException, FileNotFoundException, IOException
	{
		checkGroupOfPlantsParameters(oldGroupOfPlants.getId(), wording, plantingDate, path, iconColor, typesOfPlants, positions);

		GroupOfPlants newGroupOfPlants = new GroupOfPlants(oldGroupOfPlants.getId(), wording, plantingDate, path, iconColor, typesOfPlants, positions);

		if (!oldGroupOfPlants.equals(newGroupOfPlants))
		{
			ISourceManipulator sourceManipulator = MyDatabase.getInstance();
			sourceManipulator.openConnection();

			this.groupOfPlantsManipulator.setConnection(sourceManipulator.getConnection());
			this.groupOfPlantsManipulator.updateGroupOfPlants(newGroupOfPlants, this.positionManipulator);

			sourceManipulator.closeConnection();
		}
	}

	/**
	 * Remove a group of plants.
	 * 
	 * @param groupOfPlantsToRemove
	 *            The group of plants to remove.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 * @throws ClassNotFoundException
	 *             If the class is not found.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void removeGroupOfPlants(GroupOfPlants groupOfPlantsToRemove) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException
	{
		ISourceManipulator sourceManipulator = MyDatabase.getInstance();
		sourceManipulator.openConnection();

		this.groupOfPlantsManipulator.setConnection(sourceManipulator.getConnection());
		this.groupOfPlantsManipulator.removeGroupOfPlants(groupOfPlantsToRemove);

		sourceManipulator.closeConnection();
	}

	/**
	 * Check group of plants parameters.
	 * 
	 * @param id
	 *            The id.
	 * @param wording
	 *            The wording.
	 * @param plantingDate
	 *            The planting date.
	 * @param path
	 *            The path.
	 * @param iconColor
	 *            The icon color.
	 * @param typesOfPlants
	 *            The types of plants.
	 * @param positions
	 *            The positions.
	 * @throws MissingGroupOfPlantsIdException
	 *             The id is missing.
	 * @throws MissingGroupOfPlantsWordingException
	 *             The wording is missing.
	 * @throws MissingGroupOfPlantsPlantingDateException
	 *             The planting date is missing.
	 * @throws MissingGroupOfPlantsPathException
	 *             The path is missing.
	 * @throws MissingGroupOfPlantsIconColorException
	 *             The icon color is missing.
	 * @throws MissingGroupOfPlantsTypesOfPlantsException
	 *             The types of plants is missing.
	 * @throws MissingGroupOfPlantsPositionsException
	 *             The positions is missing.
	 * @throws InvalidGroupOfPlantsIdException
	 *             The id is negative.
	 * @throws InvalidGroupOfPlantsWordingException
	 *             The wording is empty.
	 * @throws InvalidGroupOfPlantsPathException
	 *             The path is empty.
	 * @throws InvalidGroupOfPlantsRedLevelException
	 *             The red level value is not between 0 and 255.
	 * @throws InvalidGroupOfPlantsGreenLevelException
	 *             The green level value is not between 0 and 255.
	 * @throws InvalidGroupOfPlantsBlueLevelException
	 *             The blue level value is not between 0 and 255.
	 * @throws InvalidGroupOfPlantsTypesOfPlantsException
	 *             The list of types of plants is empty.
	 * @throws InvalidGroupOfPlantsPositionsException
	 *             The list of positions is empty.
	 */
	private void checkGroupOfPlantsParameters(Integer id, String wording, LocalDate plantingDate, String path, Color iconColor, List<TypeOfPlants> typesOfPlants, List<Position> positions) throws MissingGroupOfPlantsIdException, MissingGroupOfPlantsWordingException, MissingGroupOfPlantsPlantingDateException, MissingGroupOfPlantsPathException, MissingGroupOfPlantsIconColorException, MissingGroupOfPlantsTypesOfPlantsException, MissingGroupOfPlantsPositionsException, InvalidGroupOfPlantsIdException, InvalidGroupOfPlantsWordingException, InvalidGroupOfPlantsPathException, InvalidGroupOfPlantsRedLevelException, InvalidGroupOfPlantsGreenLevelException, InvalidGroupOfPlantsBlueLevelException, InvalidGroupOfPlantsTypesOfPlantsException, InvalidGroupOfPlantsPositionsException
	{
		checkValuesAreSets(id, wording, plantingDate, path, iconColor, typesOfPlants, positions);
		checkValuesAreCorrects(id, wording, plantingDate, path, iconColor, typesOfPlants, positions);
	}

	/**
	 * Check values are sets.
	 * 
	 * @param id
	 *            The id.
	 * @param wording
	 *            The wording.
	 * @param plantingDate
	 *            The planting date.
	 * @param path
	 *            The path.
	 * @param iconColor
	 *            The icon color.
	 * @param typesOfPlants
	 *            The types of plants.
	 * @param positions
	 *            The positions.
	 * @throws MissingGroupOfPlantsIdException
	 *             The id is missing.
	 * @throws MissingGroupOfPlantsWordingException
	 *             The wording is missing.
	 * @throws MissingGroupOfPlantsPlantingDateException
	 *             The planting date is missing.
	 * @throws MissingGroupOfPlantsPathException
	 *             The path is missing.
	 * @throws MissingGroupOfPlantsIconColorException
	 *             The icon color is missing.
	 * @throws MissingGroupOfPlantsTypesOfPlantsException
	 *             The types of plants is missing.
	 * @throws MissingGroupOfPlantsPositionsException
	 *             The positions is missing.
	 */
	private void checkValuesAreSets(Integer id, String wording, LocalDate plantingDate, String path, Color iconColor, List<TypeOfPlants> typesOfPlants, List<Position> positions) throws MissingGroupOfPlantsIdException, MissingGroupOfPlantsWordingException, MissingGroupOfPlantsPlantingDateException, MissingGroupOfPlantsPathException, MissingGroupOfPlantsIconColorException, MissingGroupOfPlantsTypesOfPlantsException, MissingGroupOfPlantsPositionsException
	{
		if (id == null)
		{
			throw new MissingGroupOfPlantsIdException();
		}

		if (wording == null)
		{
			throw new MissingGroupOfPlantsWordingException();
		}

		if (plantingDate == null)
		{
			throw new MissingGroupOfPlantsPlantingDateException();
		}

		if (path == null)
		{
			throw new MissingGroupOfPlantsPathException();
		}

		if (iconColor == null)
		{
			throw new MissingGroupOfPlantsIconColorException();
		}

		if (typesOfPlants == null)
		{
			throw new MissingGroupOfPlantsTypesOfPlantsException();
		}

		if (positions == null)
		{
			throw new MissingGroupOfPlantsPositionsException();
		}
	}

	/**
	 * Check values are corrects.
	 * 
	 * @param id
	 *            The id.
	 * @param wording
	 *            The wording.
	 * @param plantingDate
	 *            The planting date.
	 * @param path
	 *            The path.
	 * @param iconColor
	 *            The icon color.
	 * @param typesOfPlants
	 *            The types of plants.
	 * @param positions
	 *            The positions.
	 * @throws InvalidGroupOfPlantsIdException
	 *             The id is negative.
	 * @throws InvalidGroupOfPlantsWordingException
	 *             The wording is empty.
	 * @throws InvalidGroupOfPlantsPathException
	 *             The path is empty.
	 * @throws InvalidGroupOfPlantsRedLevelException
	 *             The red level value is not between 0 and 255.
	 * @throws InvalidGroupOfPlantsGreenLevelException
	 *             The green level value is not between 0 and 255.
	 * @throws InvalidGroupOfPlantsBlueLevelException
	 *             The blue level value is not between 0 and 255.
	 * @throws InvalidGroupOfPlantsTypesOfPlantsException
	 *             The list of types of plants is empty.
	 * @throws InvalidGroupOfPlantsPositionsException
	 *             The list of positions is empty.
	 */
	private void checkValuesAreCorrects(Integer id, String wording, LocalDate plantingDate, String path, Color iconColor, List<TypeOfPlants> typesOfPlants, List<Position> positions) throws InvalidGroupOfPlantsIdException, InvalidGroupOfPlantsWordingException, InvalidGroupOfPlantsPathException, InvalidGroupOfPlantsRedLevelException, InvalidGroupOfPlantsGreenLevelException, InvalidGroupOfPlantsBlueLevelException, InvalidGroupOfPlantsTypesOfPlantsException, InvalidGroupOfPlantsPositionsException
	{
		int redLevel = (int) (iconColor.getRed() * 255);
		int greenLevel = (int) (iconColor.getGreen() * 255);
		int blueLevel = (int) (iconColor.getBlue() * 255);

		if ((id < MINIMAL_ID) && (id != GroupOfPlants.UNKNOWN_GROUP_OF_PLANTS_ID))
		{
			throw new InvalidGroupOfPlantsIdException(id);
		}

		if ("".equals(wording))
		{
			throw new InvalidGroupOfPlantsWordingException();
		}

		if ("".equals(path))
		{
			throw new InvalidGroupOfPlantsPathException();
		}

		if ((redLevel < MINIMAL_LEVEL_VALUE) || (redLevel > MAXIMAL_LEVEL_VALUE))
		{
			throw new InvalidGroupOfPlantsRedLevelException(redLevel);
		}

		if ((greenLevel < MINIMAL_LEVEL_VALUE) || (greenLevel > MAXIMAL_LEVEL_VALUE))
		{
			throw new InvalidGroupOfPlantsGreenLevelException(greenLevel);
		}

		if ((blueLevel < MINIMAL_LEVEL_VALUE) || (blueLevel > MAXIMAL_LEVEL_VALUE))
		{
			throw new InvalidGroupOfPlantsBlueLevelException(blueLevel);
		}

		if (typesOfPlants.isEmpty())
		{
			throw new InvalidGroupOfPlantsTypesOfPlantsException();
		}

		if (positions.isEmpty())
		{
			throw new InvalidGroupOfPlantsPositionsException();
		}
	}
}