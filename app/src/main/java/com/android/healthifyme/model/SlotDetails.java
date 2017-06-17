package com.android.healthifyme.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by anuj on 15/06/17.
 */

public class SlotDetails implements Parcelable {

    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("is_booked")
    @Expose
    private Boolean isBooked;
    @SerializedName("is_expired")
    @Expose
    private Boolean isExpired;
    @SerializedName("slot_id")
    @Expose
    private Integer slotId;
    @SerializedName("start_time")
    @Expose
    private String startTime;

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Boolean getBooked() {
        return isBooked;
    }

    public void setBooked(Boolean booked) {
        isBooked = booked;
    }

    public Boolean getExpired() {
        return isExpired;
    }

    public void setExpired(Boolean expired) {
        isExpired = expired;
    }

    public Integer getSlotId() {
        return slotId;
    }

    public void setSlotId(Integer slotId) {
        this.slotId = slotId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.endTime);
        dest.writeValue(this.isBooked);
        dest.writeValue(this.isExpired);
        dest.writeValue(this.slotId);
        dest.writeString(this.startTime);
    }

    public SlotDetails() {
    }

    protected SlotDetails(Parcel in) {
        this.endTime = in.readString();
        this.isBooked = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.isExpired = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.slotId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.startTime = in.readString();
    }

    public static final Parcelable.Creator<SlotDetails> CREATOR = new Parcelable.Creator<SlotDetails>() {
        @Override
        public SlotDetails createFromParcel(Parcel source) {
            return new SlotDetails(source);
        }

        @Override
        public SlotDetails[] newArray(int size) {
            return new SlotDetails[size];
        }
    };
}
