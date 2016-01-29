package years.im.ultimaterecyclerview;

import java.util.Date;

/**
 * Created by alvinzeng on 1/29/16.
 */
public class ContentMock {
    public String title;
    public String content;
    public Date time = new Date();

    public ContentMock(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
