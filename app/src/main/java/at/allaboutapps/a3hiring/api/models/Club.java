package at.allaboutapps.a3hiring.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Club implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("images")
    @Expose
    private String images;
    public final static Parcelable.Creator<Club> CREATOR = new Creator<Club>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Club createFromParcel(Parcel in) {
            return new Club(in);
        }

        public Club[] newArray(int size) {
            return (new Club[size]);
        }

    }
            ;

    private Club(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.country = ((String) in.readValue((String.class.getClassLoader())));
        this.value = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.images = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Club() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(country);
        dest.writeValue(value);
        dest.writeValue(image);
        dest.writeValue(images);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return  name + '\n' +
                country + '\n' +
                "Value: " + value;
    }



}