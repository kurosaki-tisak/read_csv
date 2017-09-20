package com.example.kittisak.top20.features.main;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kittisak.top20.R;
import com.example.kittisak.top20.features.top20.Top20Activity;
import com.example.kittisak.top20.model.Download;
import com.example.kittisak.top20.service.DownloadService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kittisak on 9/20/2017 AD.
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_download)
    AppCompatButton mBtnDownload;
    @BindView(R.id.progress)
    ProgressBar mProgress;
    @BindView(R.id.progress_text)
    TextView mProgressText;
    @BindView(R.id.btn_next)
    AppCompatButton mBtnNext;

    public static final String MESSAGE_PROGRESS = "message_progress";
    private static final int PERMISSION_REQUEST_CODE = 1;

    private Download mDownload;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        registerReceiver();
    }

    @OnClick(R.id.btn_download)
    public void downLoadClick(View view) {
        if (checkPermission()) {
            startDownload();
        } else {
            requestPermission();
        }
    }

    @OnClick(R.id.btn_next)
    public void btnNextClick(View view) {
        Intent intent = new Intent(this, Top20Activity.class);
        intent.putExtra(Top20Activity.EXTRA_DOWNLOAD_DATA, mDownload.getFilePath());
        startActivity(intent);
    }

    private void startDownload() {
        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
    }

    private void registerReceiver() {

        LocalBroadcastManager bManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MESSAGE_PROGRESS);
        bManager.registerReceiver(broadcastReceiver, intentFilter);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(MESSAGE_PROGRESS)) {

                mDownload = intent.getParcelableExtra("download");
                mProgress.setProgress(mDownload.getProgress());

                mBtnNext.setVisibility(View.VISIBLE);

                if (mDownload.getProgress() == 100) {
                    mProgressText.setText("File Download Complete");
                } else {
                    mProgressText.setText(String.format("Downloaded (%d/%d) MB",
                            mDownload.getCurrentFileSize(), mDownload.getTotalFileSize()));
                }
            }
        }
    };

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startDownload();
                } else {

                    Snackbar.make(findViewById(R.id.coordinatorLayout), "Permission Denied, Please allow to proceed !", Snackbar.LENGTH_LONG).show();

                }
                break;
        }
    }
}
