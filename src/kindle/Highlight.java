package kindle;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author yang.xia
 *
 */
public class Highlight {
    @JSONField(name = "bookname")
    public String bookName;

    @JSONField(name = "author")
    public String authorName;

    @JSONField(name = "pagenum")
    public int pageNumber;

    @JSONField(name = "content")
    public String highlightContent;

    @JSONField(name = "date")
    public String highlightDate;

    public Highlight() {
        this.pageNumber = 0;
    }
}
