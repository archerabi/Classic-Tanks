package com.tank;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Point;

public class TerrainGenerator {

	private int smallestChunkLengthInPixels = 24;
	private int MaxWidth = 800 ;
	private int MaxHeight = 300 ;
	
	public TerrainGenerator(){
		
	}
	
	 public ArrayList<Point> generateTerrain(int widthOfTerrain){
		ArrayList<Point> pointList = new ArrayList<Point>();
		
		Point p = new Point();
		p.x=0;
		p.y=200;
		pointList.add(p);
		
		for(int i=0; i < widthOfTerrain/smallestChunkLengthInPixels ; i++)
		{
			Point newPoint = new Point();
			Point lastPoint = pointList.get(pointList.size() - 1);
			
			Random rand = new Random();
			newPoint.x = lastPoint.x + smallestChunkLengthInPixels;	
			newPoint.y = (int) (200 + ( 1000 * ( 0.5-rand.nextGaussian() ) ) % 10);
			
			pointList.add(newPoint);
		}
		@SuppressWarnings("unused")
		Point xxxx = pointList.get(pointList.size() - 1);
		return pointList;
	}
	
	private ArrayList<Point> generateHill(){
		ArrayList<Point> wavePoints = new ArrayList<Point>();
		int height = getRandomHillHeight();
		int width = getRandomHillWidth();
		int startPosition = getRandomHillPosition();
		
		return wavePoints;
	}
	private int getRandomHillHeight(){
		Random rand = new Random();
		int max= 7;
		int min = 2;
		return (int) ((MaxHeight * (rand.nextInt(max-min+1) + min) )*0.1);
	}
	private int getRandomHillWidth(){
		Random rand = new Random();
		int max= 6;
		int min = 3;
		return (int) ((MaxWidth * (rand.nextInt(max-min+1) + min) )*0.1);	
	}
	
	private int getRandomHillPosition(){
		Random rand = new Random();
		int max= 4;
		int min = 2;
		return (int) ((MaxWidth * (rand.nextInt(max-min+1) + min) )*0.1);
	}
}
