package hackpuc.vedor.objects;

import com.parse.ParseObject;

/**
 * Created by JGabrielFreitas on 13/12/15.
 */
public class FeedItem {

    private String title;
    private String link;

    public FeedItem(ParseObject parseObject) {

        this.title = (String) parseObject.get("title");
        this.link  = (String) parseObject.get("linkUrl");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


}
