package kindle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yang.xia
 *
 */
public class HighlightProcessor {

    class HighlightSignal {
        public static final String HILIGHT_PAGE_SIGNAL = "- Your Highlight on page ";
        public static final String HILIGHT_LOC_SIGNAL = " | location ";
        public static final String HILIGHT_DATETIME_SIGNAL = " Added on ";
        public static final String HILIGHT_SEPARATOR = "==========";
    }

    /**
     * @param p_highlights
     */
    public Map<String, List<Highlight>> highlightsProcesser(ArrayList<String> p_highlights) {
        // 0 - author; 1 - page info; 2 - content
        int state = 0;

        Set<String> tempContentHolder = new HashSet<String>();
        Map<String, List<Highlight>> highlightCollection = new HashMap<String, List<Highlight>>();
        ArrayList<String> processedHighlights = p_highlights;
        List<Highlight> tempHighlightArray = new ArrayList<Highlight>();
        Highlight singleHighlight = null;

        if (!processedHighlights.isEmpty()) {
            for (String line : processedHighlights) {
                // disregard the separator
                if (!HighlightSignal.HILIGHT_SEPARATOR.equals(line)) {
                    if (state == 0 && !line.isEmpty()) {
                        singleHighlight = new Highlight();
                        singleHighlight.bookName = HighlightProcessor.getBookName(line);
                        singleHighlight.authorName = HighlightProcessor.getAuthorName(line);
                        state++;
                    } else if (state == 1 && !line.isEmpty()) {
                        singleHighlight.pageNumber = HighlightProcessor.getPageNumber(line);
                        singleHighlight.highlightDate = HighlightProcessor.getCapturedDate(line);
                        state++;
                    } else if (state == 2 && !line.isEmpty()) {
                        singleHighlight.highlightContent = line;
                        state = 0;
                    }

                    if (state == 0) {
                        if (!tempContentHolder.contains(line)) {
                            // if we don't have this book yet, then create and store the new list
                            if (!highlightCollection.containsKey(singleHighlight.bookName)) {
                                tempHighlightArray = new ArrayList<Highlight>();
                            } else {
                                tempHighlightArray = highlightCollection.get(singleHighlight.bookName);
                            }

                            tempHighlightArray.add(singleHighlight);
                            highlightCollection.put(singleHighlight.bookName, tempHighlightArray);
                            tempContentHolder.add(line);
                        }
                    }
                }
            }
        }

        return highlightCollection;
    }

    /**
     * @param p_currentLine
     * @return
     */
    public static int getPageNumber(String p_currentLine) {
        String pageNumber = null;
        String rawPageNumber = null;
        int parsedResult = 0;

        if (p_currentLine.contains(HighlightSignal.HILIGHT_PAGE_SIGNAL)) {
            String[] splitString = p_currentLine.split("\\|");

            if (splitString != null && splitString.length == 3) {
                rawPageNumber = splitString[0];
            } else if (splitString.length == 2) {
                if (!splitString[0].contains(HighlightSignal.HILIGHT_PAGE_SIGNAL)) {
                    System.out.println("=== Warning: No Page number section found ===");
                }
            }

            if (rawPageNumber != null && rawPageNumber.contains(HighlightSignal.HILIGHT_PAGE_SIGNAL)) {
                pageNumber = rawPageNumber.substring(rawPageNumber.indexOf(HighlightSignal.HILIGHT_PAGE_SIGNAL)
                        + HighlightSignal.HILIGHT_PAGE_SIGNAL.length(), rawPageNumber.length());

                try {
                    parsedResult = Integer.parseInt(pageNumber.trim());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        return parsedResult;
    }

    /**
     * Extract the date and time of the clipping
     * 
     * @param p_currentLine
     * @return dateTime
     */
    public static String getCapturedDate(String p_currentLine) {
        String dateTime = null;
        String rawCapturedDate = null;

        if (p_currentLine != null && !p_currentLine.isEmpty()
                && p_currentLine.contains(HighlightSignal.HILIGHT_DATETIME_SIGNAL)) {
            String[] splitString = p_currentLine.split("\\|");

            if (splitString != null && splitString.length == 3) {
                rawCapturedDate = splitString[2];
            }

            if (rawCapturedDate != null && rawCapturedDate.contains("day")) {
                dateTime = rawCapturedDate.substring(rawCapturedDate.indexOf(HighlightSignal.HILIGHT_DATETIME_SIGNAL)
                        + HighlightSignal.HILIGHT_DATETIME_SIGNAL.length(), rawCapturedDate.length());
            }
        } else {
            dateTime = "Unknown Date";
        }

        return dateTime;
    }

    /**
     * Extract the book name of the clipping
     * 
     * @param p_currentLine
     * @return
     */
    public static String getBookName(String p_currentLine) {
        String bookName = null;

        if (p_currentLine != null && !p_currentLine.isEmpty()) {
            try {
                bookName = p_currentLine.substring(0, p_currentLine.lastIndexOf("(") - 1);
            } catch (StringIndexOutOfBoundsException ex) {
                bookName = p_currentLine;
            }
        } else {
            bookName = "Unknown Book";
        }

        return bookName.trim();
    }

    /**
     * Extract the author name of the clipping
     * 
     * @param p_currentLine
     * @return
     */
    public static String getAuthorName(String p_currentLine) {
        String authorSection = null;
        String authorName = null;

        if (p_currentLine != null && !p_currentLine.isEmpty()) {
            try {
                authorSection = p_currentLine.substring(p_currentLine.lastIndexOf("("));
            } catch (StringIndexOutOfBoundsException ex) {
                authorSection = null;
            }

            if (authorSection != null) {
                authorName = authorSection.substring(1, authorSection.length() - 1);
            } else {
                authorName = "Unknown, Author";
            }
        }

        if (authorName != null && !authorName.isEmpty()) {
            authorName = authorName.trim();
        }

        return authorName;
    }
}