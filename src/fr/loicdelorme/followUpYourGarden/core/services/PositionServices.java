package fr.loicdelorme.followUpYourGarden.core.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import fr.loicdelorme.followUpYourGarden.core.database.MyDatabase;
import fr.loicdelorme.followUpYourGarden.core.manipulators.models.IPositionManipulator;
import fr.loicdelorme.followUpYourGarden.core.manipulators.sources.ISourceManipulator;
import fr.loicdelorme.followUpYourGarden.core.models.Position;

/**
 * This class allow you to handle positions.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class PositionServices
{
	/**
	 * A position manipulator.
	 */
	private IPositionManipulator positionManipulator;

	/**
	 * Create a position services.
	 * 
	 * @param positionManipulator
	 *            A position manipulator.
	 */
	public PositionServices(IPositionManipulator positionManipulator)
	{
		this.positionManipulator = positionManipulator;
	}

	/**
	 * Get all positions.
	 * 
	 * @return A list of positions.
	 * @throws SQLException
	 *             If an SQL exception is thrown.
	 * @throws ClassNotFoundException
	 *             If the class is not found.
	 * @throws FileNotFoundException
	 *             If the file is not found.
	 * @throws IOException
	 *             If the file can't be opened.
	 */
	public List<Position> getPositions() throws ClassNotFoundException, FileNotFoundException, IOException, SQLException
	{
		ISourceManipulator sourceManipulator = MyDatabase.getInstance();
		sourceManipulator.openConnection();

		this.positionManipulator.setConnection(sourceManipulator.getConnection());
		List<Position> positions = this.positionManipulator.getPositions();

		sourceManipulator.closeConnection();

		return positions;
	}
}