package com.android.healthifyme.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;

import com.android.healthifyme.R;
import com.android.healthifyme.adapter.FragmentPagerAdapter;
import com.android.healthifyme.model.Daytime;
import com.android.healthifyme.model.Slot;
import com.android.healthifyme.service.BookingService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Slot mSlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSlots();
    }

    private void getSlots() {
        BookingService.Creator.getService().getSlots("alok@x.coz","a4aeb4e27f27b5786828f6cdf00d8d2cb44fe6d7","276","neha@healthifyme.com","json").enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                //Create HashMap of Date and Object
                Type type = new TypeToken<HashMap<String,Daytime>>(){}.getType();
                HashMap<String,Daytime> slotMap=new Gson().fromJson(response.body().getAsJsonObject().get("slots"),type);

                //Create Object
                try {
                    mSlot = new Slot(slotMap);

                    //Setup View
                    setupBookingView();
                }catch (JsonSyntaxException e){}
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.d("Slot Error",t.getMessage());
            }
        });
    }

    private void setupBookingView() {

        ViewPager viewPager = (ViewPager) findViewById(R.id.slot_tab_pager);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(),mSlot));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.slot_tab);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons(tabLayout);
    }

    private void setupTabIcons(TabLayout tabLayout) {

        //Show proper title for pager
        for(int i=0;i<tabLayout.getTabCount();i++) {
            AppCompatTextView tabOne = (AppCompatTextView) LayoutInflater.from(this).inflate(R.layout.custom_text_view, null);
            tabOne.setText(tabLayout.getTabAt(i).getText().toString().replace(" ","\n"));
            tabLayout.getTabAt(i).setCustomView(tabOne);
        }
    }
}
