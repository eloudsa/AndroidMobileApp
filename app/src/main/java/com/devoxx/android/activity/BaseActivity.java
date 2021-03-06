package com.devoxx.android.activity;

import com.devoxx.utils.DeviceUtil;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

@EActivity
public abstract class BaseActivity extends AppCompatActivity {

	@Bean
	DeviceUtil deviceUtil;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		if (!deviceUtil.isTablet()) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
		}
		super.onCreate(savedInstanceState);
	}
}
