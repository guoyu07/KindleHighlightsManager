package main;

import java.io.BufferedReader;
import java.util.ArrayList;

import kindle.Highlight;
import kindle.HighlightProcessor;
import utility.FileProcessor;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // read the text file
        ArrayList<String> fileContentCollection = null;
        BufferedReader bufferedReader = FileProcessor.fileInitializer("Clippings.txt");

        // convert the raw content to an array list
        if (bufferedReader != null) {
            fileContentCollection = new ArrayList<String>();
            fileContentCollection = FileProcessor.fileContentScanner(bufferedReader);
        }

        if (!fileContentCollection.isEmpty()) {
            HighlightProcessor highlightProcessor = new HighlightProcessor();
            ArrayList<Highlight> hightlight = highlightProcessor.highlightsProcesser(fileContentCollection);
        }
    }

}
