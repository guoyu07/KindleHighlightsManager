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

}
