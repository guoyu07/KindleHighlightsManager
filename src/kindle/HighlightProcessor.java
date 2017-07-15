package kindle;

import java.util.ArrayList;

/**
 * @author yang.xia
 *
 */
public class HighlightProcessor {
    public static final String HILIGHT_PAGE_SIGNAL = "- Your Highlight on page ";
    public static final String HILIGHT_LOC_SIGNAL = " | location ";
    public static final String HILIGHT_DATETIME_SIGNAL = " Added on ";
    public static final String HILIGHT_SEPARATOR = "==========";

    /**
     * @param p_highlights
     */
    public ArrayList<Highlight> highlightsProcesser(ArrayList<String> p_highlights) {
        // 0 - author; 1 - page info; 2 - content
        int state = 0;

        ArrayList<Highlight> highlightCollection = new ArrayList<Highlight>();
        ArrayList<String> processedHighlights = p_highlights;
        Highlight singleHighlight = null;

        if (!processedHighlights.isEmpty()) {
            for (String line : processedHighlights) {
                // disregard the separator
                if (!line.equals(HILIGHT_SEPARATOR)) {
                    if (state == 0 && !line.isEmpty()) {
                        singleHighlight = new Highlight();
                        singleHighlight.bookName = line;
                        state++;
                    } else if (state == 1 && !line.isEmpty()) {
                        singleHighlight.pageNumber = HighlightProcessor.getPageNumber(line);
                        state++;
                    } else if (state == 2 && !line.isEmpty()) {
                        singleHighlight.highlightContent = line;
                        state = 0;
                        highlightCollection.add(singleHighlight);
                        singleHighlight = null;
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

        if (p_currentLine.contains(HILIGHT_PAGE_SIGNAL)) {
            String[] splitString = p_currentLine.split("\\|");

            if (splitString != null && splitString.length == 3) {
                rawPageNumber = splitString[0];
            } else if (splitString.length == 2) {
                if (!splitString[0].contains(HILIGHT_PAGE_SIGNAL)) {
                    System.out.println("=== Warning: No Page number section found ===");
                }
            }

            if (rawPageNumber != null && rawPageNumber.contains(HILIGHT_PAGE_SIGNAL)) {
                pageNumber = rawPageNumber.substring(
                        rawPageNumber.indexOf(HILIGHT_PAGE_SIGNAL) + HILIGHT_PAGE_SIGNAL.length(),
                        rawPageNumber.length());
            }
        }

        return Integer.parseInt(pageNumber.trim());
    }

    /**
     * Extract the date and time of the clipping
     * 
     * @param p_currentLine
     * @return
     */
    public static String getCapturedDate(String p_currentLine) {
        String dateTime = null;
        // TODO: implement this
        return dateTime;
    }
}