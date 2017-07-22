package main;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import kindle.Highlight;
import kindle.HighlightProcessor;
import utility.FileProcessor;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // compatible with command line call
        BufferedReader bufferedReader = FileProcessor.fileInitializer("Clippings_.txt");
        String result = Main.execute(bufferedReader);
    }

    public static String execute(BufferedReader bufferedReader) {
        String result = null;

        // read the text file
        ArrayList<String> fileContentCollection = null;

        // convert the raw content to an array list
        if (bufferedReader != null) {
            fileContentCollection = new ArrayList<String>();
            fileContentCollection = FileProcessor.fileContentScanner(bufferedReader);
        }

        if (!fileContentCollection.isEmpty()) {
            HighlightProcessor highlightProcessor = new HighlightProcessor();
            List<Highlight> hightlight = new ArrayList<Highlight>();
            hightlight = highlightProcessor.highlightsProcesser(fileContentCollection);
            result = com.alibaba.fastjson.JSON.toJSONString(hightlight);
        }

        return result;
    }

}
