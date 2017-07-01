import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileReaderWriter {

	public static boolean createNewFile(String filePath)
	{
		boolean isCreate = false;
		String filePathTurn = filePath.replaceAll("\\\\", "/");
		int index = filePathTurn.lastIndexOf("/");
		String dir = filePathTurn.substring(0, index);
		File fileDir = new File(dir);
		isCreate = fileDir.mkdirs();
		File file = new File(filePathTurn);
		try
		{
			isCreate = file.createNewFile();
		}
		catch (IOException exception)
		{
			exception.printStackTrace();
		}
		return isCreate;
	}

	public static boolean writeIntoFile(String content, String filePath, boolean isAppend)
	{
		boolean isWrite = false;
		int index = filePath.lastIndexOf("/");
		String dir = filePath.substring(0, index);
		File fileDir = new File(dir);
		fileDir.mkdirs();
		File file = null;
		try
		{
			file = new File(filePath);
			file.createNewFile();
		}
		catch (IOException exception)
		{
			exception.printStackTrace();
		}
		
		FileWriter fileWriter = null;
		try
		{
			fileWriter = new FileWriter(file, isAppend);
			fileWriter.write(content);
			fileWriter.flush();
		}
		catch (IOException exception)
		{
			isWrite = false;
			exception.printStackTrace();
		}
		finally
		{
			try
			{
				if (fileWriter != null)
				{
					fileWriter.close();
				}
			}
			catch (IOException exception)
			{
				exception.printStackTrace();
			}
		}
		return isWrite;
	}
}
