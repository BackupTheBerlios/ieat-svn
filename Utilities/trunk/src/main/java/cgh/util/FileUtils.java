package cgh.util;

import java.io.*;

public class FileUtils
{
    /**
     * @param filePath the name of the file to open. Not sure if it can accept
     *        URLs or just filenames. Path handling could be better, and buffer
     *        sizes are hardcoded
     */
    public static String readFileAsString(String filePath)
        throws java.io.IOException
    {
        StringBuilder fileData = new StringBuilder(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead = 0;
        while ((numRead = reader.read(buf)) != -1)
        {
            fileData.append(buf, 0, numRead);
        }
        reader.close();
        return fileData.toString();
    }

}
