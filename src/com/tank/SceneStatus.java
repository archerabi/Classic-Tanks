package com.tank;

import java.util.ArrayList;

import android.graphics.Point;

public class SceneStatus {

	private Point currentBulletPos;
	private boolean isPlaying=false;
	
	private Point player1position = new Point(0,0);
	private Point player2position = new Point(0,0);
	
	public boolean [][] map;
	private ArrayList<Point> terrain;
	
	public SceneStatus(){
		currentBulletPos = new Point(10,100);
	}
	
	public void setTerrain(ArrayList<Point> points){
		terrain = points;
	}
	public void setTerrainArray(boolean [][] map){
		this.map = map;
	}
	public ArrayList<Point> getTerrain(){
		return terrain;
	}
	
	public Point getCurrentBulletPos(){
		return currentBulletPos;
	}
	
	public void setCurrentBulletPos(Point p){
		currentBulletPos = p;
	}
	
	public void setIsPlaying(boolean isPlaying){
		this.isPlaying = isPlaying;
	}
	
	public boolean IsPlaying(){
		return isPlaying;
	}
	
	public Point getPlayer1Position(){
		return player1position;
	}
	
	public void setPlayer1Position(Point pos){
		player1position = pos;
	}
	public Point getPlayer2Position(){
		return player2position;
	}
	
	public void setPlayer2Position(Point pos){
		player2position = pos;
	}
}
