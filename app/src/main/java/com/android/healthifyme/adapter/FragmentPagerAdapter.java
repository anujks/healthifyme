package com.android.healthifyme.adapter;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;

import com.android.healthifyme.R;
import com.android.healthifyme.Utils.TextDrawable;
import com.android.healthifyme.Utils.Utils;
import com.android.healthifyme.model.Slot;
import com.android.healthifyme.ui.SlotFragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by anuj on 16/06/17.
 */

public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    Slot mSlot;
    List<String> DateKeys;

    public FragmentPagerAdapter(FragmentManager fm,Slot mSlot) {
        super(fm);
        this.mSlot=mSlot;
        DateKeys = new ArrayList<>(mSlot.getSlot().keySet());
        Collections.sort(DateKeys);
    }

    @Override
    public int getCount() {
        return mSlot.getSlot().size();
    }

    @Override
    public Fragment getItem(int position) {
        return SlotFragment.newInstance(mSlot.getSlot().get(DateKeys.get(position)));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Utils.formatDate(DateKeys.get(position));
    }
}
