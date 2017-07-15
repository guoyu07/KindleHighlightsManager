package utility;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author yang.xia
 *
 */
public class FileProcessor {

    /**
     * @param p_fileName
     *            The file name with its path
     * @return bufferedReader
     */
    public static BufferedReader fileInitializer(String p_fileName) {
        FileInputStream fileInputStream = null;

        if (p_fileName != null && !p_fileName.isEmpty()) {
            try {
                fileInputStream = new FileInputStream(p_fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            // TODO: file name is empty
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        return bufferedReader;
    }

    /**
     * @param p_bufferedReader
     * @return contentCollection
     */
    public static ArrayList<String> fileContentScanner(BufferedReader p_bufferedReader) {
        String strLine;
        ArrayList<String> contentCollection = new ArrayList<String>();

        try {
            while ((strLine = p_bufferedReader.readLine()) != null) {
                contentCollection.add(strLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentCollection;
    }

}
