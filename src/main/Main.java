package main;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kindle.Highlight;
import kindle.HighlightProcessor;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {}

    public static String execute(BufferedReader bufferedReader) {
        String result = null;

        // read the text file
        ArrayList<String> fileContentCollection = null;

        // convert the raw content to an array list
        if (bufferedReader != null) {
            fileContentCollection = new ArrayList<String>();
            fileContentCollection = utility.FileProcessor.fileContentScanner(bufferedReader);
        }

        if (!fileContentCollection.isEmpty()) {
            HighlightProcessor highlightProcessor = new HighlightProcessor();
            Map<String, List<Highlight>> fancyHighlight = highlightProcessor.highlightsProcesser(fileContentCollection);
            result = com.alibaba.fastjson.JSON.toJSONString(fancyHighlight);

        }

        return result;
    }

}
