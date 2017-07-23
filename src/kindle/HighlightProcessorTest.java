package kindle;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HighlightProcessorTest {

    @Test
    public void testGetPageNumber() {
        String input = "- Your Highlight on page 373 | location 5579-5580 | Added on Friday, 14 April 2017 09:26:18";
        int result = HighlightProcessor.getPageNumber(input);
        assertEquals(373, result);
    }

    @Test
    public void testGetAuthorName() {
        String input;
        String result;

        input = "Daughters of the Dragon (Andrews, William)";
        result = HighlightProcessor.getAuthorName(input);
        assertEquals("Andrews, William", result);

        input = "Wild Swans: Three Daughters of China (Chang, Jung)";
        result = HighlightProcessor.getAuthorName(input);
        assertEquals("Chang, Jung", result);

        input = "Not Your Usual Treatments: how medicine got better (Not Your Usual... Book 4) (Macinnis, Peter)";
        result = HighlightProcessor.getAuthorName(input);
        assertEquals("Macinnis, Peter", result);

        input = "Not Your Usual Treatments: how medicine got better";
        result = HighlightProcessor.getAuthorName(input);
        assertEquals("Unknown, Author", result);

        input = null;
        result = HighlightProcessor.getAuthorName(input);
        assertEquals(null, result);

        input = "";
        result = HighlightProcessor.getAuthorName(input);
        assertEquals(null, result);
    }

    @Test
    public void testGetBookName() {
        String input;
        String result;

        input = "Daughters of the Dragon (Andrews, William)";
        result = HighlightProcessor.getBookName(input);
        assertEquals("Daughters of the Dragon", result);

        input = "Wild Swans: Three Daughters of China (Chang, Jung)";
        result = HighlightProcessor.getBookName(input);
        assertEquals("Wild Swans: Three Daughters of China", result);

        input = "Not Your Usual Treatments: how medicine got better (Not Your Usual... Book 4) (Macinnis, Peter)";
        result = HighlightProcessor.getBookName(input);
        assertEquals("Not Your Usual Treatments: how medicine got better (Not Your Usual... Book 4)", result);

        input = "Not Your Usual Treatments: how medicine got better";
        result = HighlightProcessor.getBookName(input);
        assertEquals("Not Your Usual Treatments: how medicine got better", result);

        input = null;
        result = HighlightProcessor.getBookName(input);
        assertEquals("Unknown Book", result);

        input = "";
        result = HighlightProcessor.getBookName(input);
        assertEquals("Unknown Book", result);
    }

    @Test
    public void testGetCapturedDate() {
        String input;
        String result;

        input = "- Your Highlight on page 373 | location 5579-5580 | Added on Friday, 14 April 2017 09:26:18";
        result = HighlightProcessor.getCapturedDate(input);
        assertEquals("Friday, 14 April 2017 09:26:18", result);

        input = null;
        result = HighlightProcessor.getCapturedDate(input);
        assertEquals("Unknown Date", result);
    }
}
