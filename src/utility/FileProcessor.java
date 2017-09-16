package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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

    /**
     * @param p_fileName
     * @return fileExtension
     */
    public static String checkFileExtension(String p_fileName) {
        String fileExtension = null;

        if (p_fileName != null || p_fileName != "") {
            String fileName = p_fileName.substring(p_fileName.lastIndexOf("/"));
            fileExtension = fileName.substring(fileName.indexOf("."));
        }

        return fileExtension;
    }

    /**
     * @param p_request
     * @return bufferedReader
     */
    public static BufferedReader servletRequestToBufferedReader(HttpServletRequest p_request,
            ServletContext p_context) {
        BufferedReader bufferedReader = null;

        if (ServletFileUpload.isMultipartContent(p_request)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            File repository = (File) p_context.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);

            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> items = null;

            try {
                items = upload.parseRequest(p_request);
            } catch (FileUploadException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if (items != null) {
                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    if (!item.isFormField()) {
                        InputStream inputStream = null;
                        try {
                            inputStream = item.getInputStream();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        if (inputStream != null) {
                            InputStreamReader isr = new InputStreamReader(inputStream);
                            bufferedReader = new BufferedReader(isr);
                        }
                    }
                }
            }
        }
        return bufferedReader;
    }

}
