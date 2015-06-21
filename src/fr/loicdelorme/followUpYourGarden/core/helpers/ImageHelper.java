package fr.loicdelorme.followUpYourGarden.core.helpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * This class allow you to handle images.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class ImageHelper
{
	/**
	 * Copy the file to a new destination.
	 * 
	 * @param path
	 *            The path.
	 * @param destination
	 *            The new destination.
	 * @return The new path.
	 * @throws IOException
	 *             If the file can not be copied.
	 */
	public static String copy(String path, String destination) throws IOException
	{
		File file = new File(path);
		String newPath = new StringBuilder().append(destination).append(file.getName()).toString();

		Files.copy(file.toPath(), new File(newPath).toPath());

		return newPath;
	}

	/**
	 * Delete the file.
	 * 
	 * @param path
	 *            The path.
	 * 
	 * @return True if the file was deleted, else False.
	 */
	public static boolean delete(String path)
	{
		return new File(path).delete();
	}
}
