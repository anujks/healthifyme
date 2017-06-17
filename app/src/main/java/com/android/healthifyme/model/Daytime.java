package com.android.healthifyme.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anuj on 15/06/17.
 */

public class Daytime implements Parcelable {
    @SerializedName("afternoon")
    @Expose
    public List<SlotDetails> afternoon;

    @SerializedName("evening")
    @Expose
    public List<SlotDetails> evening;

    @SerializedName("morning")
    @Expose
    public List<SlotDetails> morning;

    public List<SlotDetails> getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(List<SlotDetails> afternoon) {
        this.afternoon = afternoon;
    }

    public List<SlotDetails> getEvening() {
        return evening;
    }

    public void setEvening(List<SlotDetails> evening) {
        this.evening = evening;
    }

    public List<SlotDetails> getMorning() {
        return morning;
    }

    public void setMorning(List<SlotDetails> morning) {
        this.morning = morning;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.afternoon);
        dest.writeTypedList(this.evening);
        dest.writeTypedList(this.morning);
    }

    public Daytime() {

    }

    protected Daytime(Parcel in) {
        this.afternoon = in.createTypedArrayList(SlotDetails.CREATOR);
        this.evening = in.createTypedArrayList(SlotDetails.CREATOR);
        this.morning = in.createTypedArrayList(SlotDetails.CREATOR);
    }

    public static final Creator<Daytime> CREATOR = new Creator<Daytime>() {
        @Override
        public Daytime createFromParcel(Parcel source) {
            return new Daytime(source);
        }

        @Override
        public Daytime[] newArray(int size) {
            return new Daytime[size];
        }
    };
}
