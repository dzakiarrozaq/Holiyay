package android.example.utsmobapps;

import android.os.Parcel;
import android.os.Parcelable;

public class Wisata implements Parcelable {
    private String name;
    private String lok;
    private String link;
    private String high;
    private String detail;


    public Wisata(String name, String lok, String link, String detail, String high) {
        this.name = name;
        this.lok = lok;
        this.link = link;
        this.high = high;
        this.detail = detail;

    }

    protected Wisata(Parcel in) {
        name = in.readString();
        lok = in.readString();
        link = in.readString();
        high = in.readString();
        detail = in.readString();
    }

    public static final Creator<Wisata> CREATOR = new Creator<Wisata>() {
        @Override
        public Wisata createFromParcel(Parcel in) {
            return new Wisata(in);
        }

        @Override
        public Wisata[] newArray(int size) {
            return new Wisata[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getLocation() {
        return lok;
    }

    public String getPhoto() {
        return link;
    }
    public String getDescription() {
        return high;
    }

    public String getHighlights() {
        return detail;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(lok);
        dest.writeString(link);
        dest.writeString(high);
        dest.writeString(detail);
    }
}
