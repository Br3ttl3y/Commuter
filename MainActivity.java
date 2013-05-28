package com.brettley.helloworld;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

  private Route route = new Route();
	private Button _btnStart, _btnStop, _btnLap;
	private TextView _txtTime;
	private Handler _mHandler = new Handler();
	private final int REFRESH_RATE = 1;
	private Runnable _displayTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list); // Points to ./res/layout/main
		
		InitializeComponents();
	}
	
	private void InitializeComponents(){
		_btnStart = (Button) findViewById(R.id.btnStart);
		_btnStop = (Button) findViewById(R.id.btnStop);
		_btnLap = (Button) findViewById(R.id.btnLap);
		_txtTime = (TextView) findViewById(R.id.textView1);
		
		_displayTime = new Runnable(){
			@Override
			public void run() {
				_txtTime.setText(Long.toString(route.totalRouteTime()));
				_mHandler.postDelayed(this, REFRESH_RATE);
			}
		};
		
		_btnStart.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				_mHandler.removeCallbacks(_displayTime);
				if(route.totalRouteTime() > 0)
					route.addStopTime();
				else
					route.startRoute();
				_mHandler.postDelayed(_displayTime, 0);
			}
		});
		
		_btnStop.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(route.totalDrivingTime() <= 0) return;
				
					route.addDrivingTime();
				_mHandler.removeCallbacks(_displayTime);
			}
		});
		
		_btnLap.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Add lap to table
				
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list_item, menu);
		return true;
	}
}
