package com.tank;

import android.graphics.Point;

public class SceneStatus {

	private Point currentBulletPos;
	
	public SceneStatus(){
		currentBulletPos = new Point(10,200);
	}
	
	public Point getCurrentBulletPos(){
		return currentBulletPos;
	}
	
	public void setCurrentBulletPos(Point p){
		currentBulletPos = p;
	}
	
}
