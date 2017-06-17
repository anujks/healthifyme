package com.android.healthifyme.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.healthifyme.R;
import com.android.healthifyme.Utils.ExpandableItemIndicator;
import com.android.healthifyme.Utils.Utils;
import com.android.healthifyme.model.Daytime;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemConstants;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

/**
 * Created by anuj on 16/06/17.
 */

public class SlotDetailExpandableAdapter extends AbstractExpandableItemAdapter<SlotDetailExpandableAdapter.MyGroupViewHolder, SlotDetailExpandableAdapter.MyChildViewHolder> {

    // NOTE: Make accessible with short name
    private interface Expandable extends ExpandableItemConstants {

    }

    private Daytime mDaytime;

    public static abstract class MyBaseViewHolder extends AbstractExpandableItemViewHolder {
        public FrameLayout mContainer;
        public TextView mTitle;
        public AppCompatTextView mAvailableSlots;

        public MyBaseViewHolder(View v) {
            super(v);
            mContainer = (FrameLayout) v.findViewById(R.id.container);
            mTitle = (TextView) v.findViewById(android.R.id.text1);
            mAvailableSlots=(AppCompatTextView)v.findViewById(R.id.available_slots);
        }
    }

    public static class MyGroupViewHolder extends MyBaseViewHolder {

        public ExpandableItemIndicator mIndicator;

        public MyGroupViewHolder(View v) {
            super(v);
            mIndicator = (ExpandableItemIndicator) v.findViewById(R.id.indicator);
        }
    }

    public static class MyChildViewHolder extends MyBaseViewHolder {
        public MyChildViewHolder(View v) {
            super(v);
        }
    }

    public SlotDetailExpandableAdapter(Daytime daytime) {
        mDaytime = daytime;
        setHasStableIds(true);
    }

    @Override
    public int getGroupCount() {
        return 3;
    }

    @Override
    public int getChildCount(int groupPosition) {
        switch(groupPosition){
            case 0:return mDaytime.getMorning().size();
            case 1:return mDaytime.getAfternoon().size();
            case 2:return mDaytime.getEvening().size();
        }
        return 0;
    }

    @Override
    public long getGroupId(int groupPosition) {
        switch(groupPosition){
            case 0:return groupPosition;
            case 1:return groupPosition;
            case 2:return groupPosition;
        }
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        switch(groupPosition){
            case 0:return mDaytime.getMorning().get(childPosition).getSlotId();
            case 1:return mDaytime.getAfternoon().get(childPosition).getSlotId();
            case 2:return mDaytime.getEvening().get(childPosition).getSlotId();
        }
        return 0;
    }

    @Override
    public int getGroupItemViewType(int groupPosition) {
        return 0;
    }

    @Override
    public int getChildItemViewType(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public MyGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View v = inflater.inflate(R.layout.list_group_item, parent, false);
        return new MyGroupViewHolder(v);
    }

    @Override
    public MyChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View v = inflater.inflate(R.layout.list_child_item, parent, false);
        return new MyChildViewHolder(v);
    }

    @Override
    public void onBindGroupViewHolder(MyGroupViewHolder holder, int groupPosition, int viewType) {
        // child item
        String GroupTile="";
        String numberOfSlot="0";
        switch(groupPosition){
            case 0:GroupTile="Morning";
                numberOfSlot=mDaytime.getMorning().size()+"";
                break;
            case 1:GroupTile="Afternoon";
                numberOfSlot=mDaytime.getAfternoon().size()+"";
                break;
            case 2:GroupTile="Evening";
                numberOfSlot=mDaytime.getEvening().size()+"";
                break;
        }

        // set time of day
        holder.mTitle.setText(GroupTile);

        // set available slots
        holder.mAvailableSlots.setText(numberOfSlot+" Slots available");

        // mark as clickable
        holder.itemView.setClickable(true);

        // set background resource (target view ID: container)
        final int expandState = holder.getExpandStateFlags();

        if ((expandState & ExpandableItemConstants.STATE_FLAG_IS_UPDATED) != 0) {
            int bgResId;
            boolean isExpanded;
            boolean animateIndicator = ((expandState & Expandable.STATE_FLAG_HAS_EXPANDED_STATE_CHANGED) != 0);

            if ((expandState & Expandable.STATE_FLAG_IS_EXPANDED) != 0) {
                bgResId = R.color.bg_group_item_expanded_state;
                isExpanded = true;
            } else {
                bgResId = R.color.bg_group_item_normal_state;
                isExpanded = false;
            }

            holder.mContainer.setBackgroundResource(bgResId);
            holder.mIndicator.setExpandedState(isExpanded, animateIndicator);
        }
    }

    @Override
    public void onBindChildViewHolder(MyChildViewHolder holder, int groupPosition, int childPosition, int viewType) {

        switch (groupPosition) {
            case 0:
                holder.mTitle.setText(Utils.formatTime(mDaytime.getMorning().get(childPosition).getStartTime())+" - "+Utils.formatTime(mDaytime.getMorning().get(childPosition).getEndTime()));
                break;
            case 1:
                holder.mTitle.setText(Utils.formatTime(mDaytime.getAfternoon().get(childPosition).getStartTime())+" - "+Utils.formatTime(mDaytime.getAfternoon().get(childPosition).getEndTime()));
                break;
            case 2:
                holder.mTitle.setText(Utils.formatTime(mDaytime.getEvening().get(childPosition).getStartTime())+" - "+Utils.formatTime(mDaytime.getEvening().get(childPosition).getEndTime()));
                break;
        }

        holder.mContainer.setBackgroundResource(R.color.bg_item_normal_state);
    }

    @Override
    public boolean onCheckCanExpandOrCollapseGroup(MyGroupViewHolder holder, int groupPosition, int x, int y, boolean expand) {
        // check is enabled
        if (!(holder.itemView.isEnabled() && holder.itemView.isClickable())) {
            return false;
        }

        return true;
    }
}