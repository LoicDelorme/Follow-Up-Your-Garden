package fr.loicdelorme.followUpYourGarden.core.services;

import java.sql.SQLException;

import fr.loicdelorme.followUpYourGarden.core.manipulators.models.ITypeOfPlantsManipulator;
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
	 */
	public void addTypeOfPlants(String wording) throws MissingTypeOfPlantsIdException, MissingTypeOfPlantsWordingException, InvalidTypeOfPlantsIdException, InvalidTypeOfPlantsWordingException, SQLException
	{
		checkTypeOfPlantsParameters(TypeOfPlants.UNKNOWN_TYPE_OF_PLANTS_ID, wording);
		this.typeOfPlantsManipulator.addTypeOfPlants(new TypeOfPlants(TypeOfPlants.UNKNOWN_TYPE_OF_PLANTS_ID, wording));
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
	 */
	public void updateTypeOfPlants(String wording, TypeOfPlants oldTypeOfPlants) throws MissingTypeOfPlantsIdException, MissingTypeOfPlantsWordingException, InvalidTypeOfPlantsIdException, InvalidTypeOfPlantsWordingException, SQLException
	{
		checkTypeOfPlantsParameters(oldTypeOfPlants.getId(), wording);

		TypeOfPlants newTypeOfPlants = new TypeOfPlants(oldTypeOfPlants.getId(), wording);

		if (!oldTypeOfPlants.equals(newTypeOfPlants))
		{
			this.typeOfPlantsManipulator.updateTypeOfPlants(newTypeOfPlants);
		}
	}

	/**
	 * Remove a type of plants.
	 * 
	 * @param typeOfPlantsToRemove
	 *            The type of plants to remove.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 */
	public void removeTypeOfPlants(TypeOfPlants typeOfPlantsToRemove) throws SQLException
	{
		this.typeOfPlantsManipulator.removeTypeOfPlants(typeOfPlantsToRemove);
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