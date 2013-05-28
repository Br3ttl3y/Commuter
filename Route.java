package com.brettley.helloworld;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class Route {
  private String name;
	private Calendar startDate;
	private Calendar endDate;
	private StopWatch watch = new StopWatch();
	private List<Long> drivingTimes = new ArrayList<Long>();
	private List<Long> stopTimes = new ArrayList<Long>();

	public Route () {
		name = "";
		drivingTimes.add((long)0);
		stopTimes.add((long)0);
		startDate = Calendar.getInstance();
	}

	public Route (String name){
		this.name = name;
		drivingTimes.add((long)0);
		stopTimes.add((long)0);
		startDate = Calendar.getInstance();
	}
	
	public void startRoute(){
		watch.Start();
	}
	
	public void endRoute(){
		watch.Stop();
	}
	
	public void addDrivingTime(){
		drivingTimes.add(stopTimes.get(stopTimes.size() -1) - watch.getDuration());
	}
	
	public void addStopTime(){
		
		stopTimes.add(drivingTimes.get(drivingTimes.size() -1) - watch.getDuration());
	}
	
	public long totalDrivingTime(){
		int totalDriveTime = 0;
		for(Iterator<Long> i = drivingTimes.iterator(); i.hasNext(); ){
			totalDriveTime += i.next();
		}
		return totalDriveTime;
	}
	
	public long totalStopTime(){
		int totalStopLightTime = 0;
		for(Iterator<Long> i = stopTimes.iterator(); i.hasNext(); ){
			totalStopLightTime += i.next();
		}
		return totalStopLightTime;
	}
	
	public long totalRouteTime(){	return watch.getDuration(); }
	public long percentTime(long time){ return time / totalRouteTime() * 100; }
}
