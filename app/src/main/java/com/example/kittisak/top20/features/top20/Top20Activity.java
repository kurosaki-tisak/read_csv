package com.example.kittisak.top20.features.top20;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.kittisak.top20.R;

/**
 * Created by kittisak on 9/19/2017 AD.
 */

public class Top20Activity extends AppCompatActivity {

    public static final String EXTRA_DOWNLOAD_DATA = "EXTRA_DOWNLOAD_DATA";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_twenty);


        Bundle bundle = getIntent().getExtras();
        String filePath = bundle.getString(EXTRA_DOWNLOAD_DATA);

        if (savedInstanceState == null)
            replaceFragment(Top20Fragment.newInstance(filePath));
    }

    protected void replaceFragment(@NonNull Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tr = fm.beginTransaction();
        tr.replace(R.id.content_frame, fragment);
        tr.commit();
    }
}
