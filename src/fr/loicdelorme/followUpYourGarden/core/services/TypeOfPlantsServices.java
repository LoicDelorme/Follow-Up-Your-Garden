package fr.loicdelorme.followUpYourGarden.core.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import fr.loicdelorme.followUpYourGarden.core.database.MyDatabase;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITypeOfPlantsManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.sources.ISourceManipulator;
import fr.loicdelorme.followUpYourGarden.core.models.TypeOfPlants;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidTypeOfPlantsIdException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.InvalidTypeOfPlantsWordingException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTypeOfPlantsIdException;
import fr.loicdelorme.followUpYourGarden.core.services.exceptions.MissingTypeOfPlantsWordingException;

/**
 * This class allow you to handle types of plants.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class TypeOfPlantsServices
{
	/**
	 * The minimal id.
	 */
	private static final int MINIMAL_ID = 1;

	/**
	 * A type of plants manipulator.
	 */
	private ITypeOfPlantsManipulator typeOfPlantsManipulator;

	/**
	 * Create a type of plants services.
	 * 
	 * @param typeOfPlantsManipulator
	 *            A type of plants manipulator.
	 */
	public TypeOfPlantsServices(ITypeOfPlantsManipulator typeOfPlantsManipulator)
	{
		this.typeOfPlantsManipulator = typeOfPlantsManipulator;
	}

	/**
	 * Get all types of plants.
	 * 
	 * @return A list of types of plants.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 * @throws ClassNotFoundException
	 *             If the class is not found.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public List<TypeOfPlants> getTypesOfPlants() throws ClassNotFoundException, FileNotFoundException, IOException, SQLException
	{
		ISourceManipulator sourceManipulator = MyDatabase.getInstance();
		sourceManipulator.openConnection();

		this.typeOfPlantsManipulator.setConnection(sourceManipulator.getConnection());
		List<TypeOfPlants> typesOfPlants = this.typeOfPlantsManipulator.getTypesOfPlants();

		sourceManipulator.closeConnection();

		return typesOfPlants;
	}

	/**
	 * Add a type of plants.
	 * 
	 * @param wording
	 *            The wording.
	 * @throws InvalidTypeOfPlantsIdException
	 *             The id is negative.
	 * @throws InvalidTypeOfPlantsWordingException
	 *             The wording is empty.
	 * @throws MissingTypeOfPlantsIdException
	 *             The id is missing.
	 * @throws MissingTypeOfPlantsWordingException
	 *             The wording is missing.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 * @throws ClassNotFoundException
	 *             If the class is not found.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void addTypeOfPlants(String wording) throws MissingTypeOfPlantsIdException, MissingTypeOfPlantsWordingException, InvalidTypeOfPlantsIdException, InvalidTypeOfPlantsWordingException, SQLException, ClassNotFoundException, FileNotFoundException, IOException
	{
		checkTypeOfPlantsParameters(TypeOfPlants.UNKNOWN_TYPE_OF_PLANTS_ID, wording);

		ISourceManipulator sourceManipulator = MyDatabase.getInstance();
		sourceManipulator.openConnection();

		this.typeOfPlantsManipulator.setConnection(sourceManipulator.getConnection());
		this.typeOfPlantsManipulator.addTypeOfPlants(new TypeOfPlants(TypeOfPlants.UNKNOWN_TYPE_OF_PLANTS_ID, wording));

		sourceManipulator.closeConnection();
	}

	/**
	 * Update a type of plants.
	 * 
	 * @param wording
	 *            The wording.
	 * @param oldTypeOfPlants
	 *            The old type of plants.
	 * @throws InvalidTypeOfPlantsIdException
	 *             The id is negative.
	 * @throws InvalidTypeOfPlantsWordingException
	 *             The wording is empty.
	 * @throws MissingTypeOfPlantsIdException
	 *             The id is missing.
	 * @throws MissingTypeOfPlantsWordingException
	 *             The wording is missing.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 * @throws ClassNotFoundException
	 *             If the class is not found.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void updateTypeOfPlants(String wording, TypeOfPlants oldTypeOfPlants) throws MissingTypeOfPlantsIdException, MissingTypeOfPlantsWordingException, InvalidTypeOfPlantsIdException, InvalidTypeOfPlantsWordingException, SQLException, ClassNotFoundException, FileNotFoundException, IOException
	{
		checkTypeOfPlantsParameters(oldTypeOfPlants.getId(), wording);

		TypeOfPlants newTypeOfPlants = new TypeOfPlants(oldTypeOfPlants.getId(), wording);

		if (!oldTypeOfPlants.equals(newTypeOfPlants))
		{
			ISourceManipulator sourceManipulator = MyDatabase.getInstance();
			sourceManipulator.openConnection();

			this.typeOfPlantsManipulator.setConnection(sourceManipulator.getConnection());
			this.typeOfPlantsManipulator.updateTypeOfPlants(newTypeOfPlants);

			sourceManipulator.closeConnection();
		}
	}

	/**
	 * Remove a type of plants.
	 * 
	 * @param typeOfPlantsToRemove
	 *            The type of plants to remove.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 * @throws ClassNotFoundException
	 *             If the class is not found.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public void removeTypeOfPlants(TypeOfPlants typeOfPlantsToRemove) throws SQLException, ClassNotFoundException, FileNotFoundException, IOException
	{
		ISourceManipulator sourceManipulator = MyDatabase.getInstance();
		sourceManipulator.openConnection();

		this.typeOfPlantsManipulator.setConnection(sourceManipulator.getConnection());
		this.typeOfPlantsManipulator.removeTypeOfPlants(typeOfPlantsToRemove);

		sourceManipulator.closeConnection();
	}

	/**
	 * Check type of plants parameters.
	 * 
	 * @param id
	 *            The id.
	 * @param wording
	 *            The wording.
	 * @throws MissingTypeOfPlantsIdException
	 *             The id is missing.
	 * @throws MissingTypeOfPlantsWordingException
	 *             The wording is missing.
	 * @throws InvalidTypeOfPlantsIdException
	 *             The id is negative.
	 * @throws InvalidTypeOfPlantsWordingException
	 *             The wording is empty.
	 */
	private void checkTypeOfPlantsParameters(Integer id, String wording) throws MissingTypeOfPlantsIdException, MissingTypeOfPlantsWordingException, InvalidTypeOfPlantsIdException, InvalidTypeOfPlantsWordingException
	{
		checkValuesAreSets(id, wording);
		checkValuesAreCorrects(id, wording);
	}

	/**
	 * Check values are sets.
	 * 
	 * @param id
	 *            The id.
	 * @param wording
	 *            The wording.
	 * @throws MissingTypeOfPlantsIdException
	 *             The id is missing.
	 * @throws MissingTypeOfPlantsWordingException
	 *             The wording is missing.
	 */
	private void checkValuesAreSets(Integer id, String wording) throws MissingTypeOfPlantsIdException, MissingTypeOfPlantsWordingException
	{
		if (id == null)
		{
			throw new MissingTypeOfPlantsIdException();
		}

		if (wording == null)
		{
			throw new MissingTypeOfPlantsWordingException();
		}
	}

	/**
	 * Check values are corrects.
	 * 
	 * @param id
	 *            The id.
	 * @param wording
	 *            The wording.
	 * @throws InvalidTypeOfPlantsIdException
	 *             The id is negative.
	 * @throws InvalidTypeOfPlantsWordingException
	 *             The wording is empty.
	 */
	private void checkValuesAreCorrects(Integer id, String wording) throws InvalidTypeOfPlantsIdException, InvalidTypeOfPlantsWordingException
	{
		if ((id < MINIMAL_ID) && (id != TypeOfPlants.UNKNOWN_TYPE_OF_PLANTS_ID))
		{
			throw new InvalidTypeOfPlantsIdException(id);
		}

		if ("".equals(wording))
		{
			throw new InvalidTypeOfPlantsWordingException();
		}
	}
}