package xbt.exp19;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xbt on 2017/9/7.
 */

public class Fruit implements Parcelable {
    private String name;

    private int imageId;

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(imageId);
    }

    public static final Parcelable.Creator<Fruit> CREATOR = new Parcelable.Creator<Fruit>(){
        @Override
        public Fruit createFromParcel(Parcel parcel) {
            Fruit fruit = new Fruit();
            fruit.name = parcel.readString();
            fruit.imageId = parcel.readInt();
            return fruit;
        }

        @Override
        public Fruit[] newArray(int i) {
            return new Fruit[i];
        }
    };
}