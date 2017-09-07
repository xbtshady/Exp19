package xbt.exp19;

import java.io.Serializable;

/**
 * Created by xbt on 2017/9/3.
 */

public class OverWatch implements Serializable {

    private String name;

    private int imageID;

    public OverWatch(String name, int imageID) {
        this.imageID = imageID;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getImageID() {
        return imageID;
    }
}
