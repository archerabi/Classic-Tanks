package com.tank;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Point;

public class TerrainGenerator {

	private int smallestChunkLengthInPixels = 18;
	private int MaxWidth = 800 ;
	private int MaxHeight = 300 ;
	private double DegreeToRadian = 0.0174532925;
	private int groundY ;
	public TerrainGenerator(){
		
	}
	
	 public ArrayList<Point> generateTerrain(int widthOfTerrain,int heightOfTerrain){
		ArrayList<Point> pointList = new ArrayList<Point>();
		
		MaxHeight = heightOfTerrain;
		MaxWidth = widthOfTerrain;
		Point p = new Point();
		p.x=0;
		groundY = (int) (heightOfTerrain * 0.85);
		p.y=groundY;
		pointList.add(p);
		
		int hillHeight = getRandomHillHeight();
		int hillWidth = getRandomHillWidth();
		int hillStartPosition = getRandomHillPosition();
		
		ArrayList<Point> hill = generateHill(hillWidth, hillHeight, hillStartPosition);
		boolean hillDrawn = false;
		Random rand = new Random();
		for(int i=0; i < widthOfTerrain/smallestChunkLengthInPixels ; i++)
		{
			
			Point newPoint = new Point();
			Point lastPoint = pointList.get(pointList.size() - 1);
			
			if(lastPoint.x >= hillStartPosition && 	!hillDrawn)
			{
				pointList.addAll(hill);
				hillDrawn=true;
				i+=hillWidth/smallestChunkLengthInPixels;
				continue;
			}
			
			
			newPoint.x = lastPoint.x + smallestChunkLengthInPixels;	
			newPoint.y = (int) (groundY + ( 1000 * ( 0.5-rand.nextGaussian() ) ) % 22);
			
			pointList.add(newPoint);
		}
		return pointList;
	}
	
	private ArrayList<Point> generateHill(int width,int height,int startPosition){
		ArrayList<Point> wavePoints = new ArrayList<Point>();
		Random rand = new Random();
		int x=startPosition;
		while(x < startPosition + width){
			Point p = new Point();
			p.x = x;
			int xmapped = (x-startPosition)*180/width;
			int siny = (int) (groundY- (height * Math.sin((xmapped)*DegreeToRadian)));
			
			p.y = siny + (int) (1000 * ( 0.5-rand.nextGaussian() ) % 22);
			x+=smallestChunkLengthInPixels;
			wavePoints.add(p);
		}
		return wavePoints;
	}
	private int getRandomHillHeight(){
		Random rand = new Random();
		int max= 5;
		int min = 4;
		return (int) ((MaxHeight * (rand.nextInt(max-min+1) + min) )*0.1);
	}
	private int getRandomHillWidth(){
		Random rand = new Random();
		int max= 3;
		int min = 2;
		return (int) ((MaxWidth * (rand.nextInt(max-min+1) + min) )*0.1);	
	}
	
	private int getRandomHillPosition(){
		Random rand = new Random();
		int max= 4;
		int min = 2;
		return (int) ((MaxWidth * (rand.nextInt(max-min+1) + min) )*0.1);
	}
}
