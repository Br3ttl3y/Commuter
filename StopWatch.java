package com.brettley.helloworld;

import java.util.ArrayList;
import java.util.List;

public class StopWatch {
  private long _start, _duration;
	
	public StopWatch(){
		_start = _duration = 0;
	}
	
	private void setStart(long time){
		_start = time;
	}
	
	private void setDuration(long time){
		_duration = time;
	}
	
	public long getDuration(){ return System.currentTimeMillis() - _start; }
	
	public void Start(){
		if(isRunning()) return;
		setStart(System.currentTimeMillis());
	}
	
	public long Stop(){
		if(notRunning()) return 0;
		setDuration(getDuration());
		setStart(0);
		return _duration;
	}
	
	private Boolean notRunning(){ return _start == 0 ? true : false; }
	private Boolean isRunning(){ return !notRunning(); }
}
