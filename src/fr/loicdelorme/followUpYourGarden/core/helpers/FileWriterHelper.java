package fr.loicdelorme.followUpYourGarden.core.helpers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class allow you to handle files.
 * 
 * @author DELORME Loïc
 * @version 1.0.0
 */
public class FileWriterHelper
{
	/**
	 * Write content into a file.
	 * 
	 * @param path
	 *            The path.
	 * @param fileName
	 *            The file name.
	 * @param extension
	 *            The extension.
	 * @param content
	 *            The content.
	 */
	public static void writeContent(String path, String fileName, String extension, String content)
	{
		FileWriter fileWriter;
		try
		{
			fileWriter = new FileWriter(new StringBuilder().append(path).append(File.separator).append(fileName).append(extension).toString());
			fileWriter.write(content);
			fileWriter.close();
		}
		catch (IOException e)
		{
			// Nothing.
		}
	}
}
