package kindle;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author yang.xia
 *
 */
public class Highlight {
    @JSONField(name = "book-name")
    public String bookName;

    @JSONField(name = "author")
    public String authorName;

    @JSONField(name = "page-number")
    public int pageNumber;

    @JSONField(name = "content")
    public String highlightContent;

    public Highlight() {
        this.pageNumber = 0;
    }
}
