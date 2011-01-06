package com.tank;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Point;
import android.view.View;

public class GameController {

	private double DegreeToRadian = 0.0174532925;
	private int timeIntervalMsec = 62;
	private double timeIntervalSec = (double)(timeIntervalMsec) / 1000; 
	private float accelerationDueTogravity = 60;
	private int velocityMultiplicationFactor=2;
	
	private GameView view;
	private SceneStatus model;
	
	private int playerTurn=0;
	
	public GameController(){
		model = new SceneStatus();
	}
	
	public View createView(Context context){
		view = new GameView(context);
		view.setModel(model);
		return view;
	}
	
	public void setView(GameView view){
		this.view = view; 
		view.setModel(model);
		
	}
	public void playTurn(final int velocity,final int angle){
		
		Runnable mUpdateTimeTask = new Runnable() {
			   public void run() {
				   
				   float x;
				   float y;
				   
				   Point p = model.getCurrentBulletPos();
				   if(playerTurn == 0){
					   /*Bullet at Top corner*/
					   p.x = model.getPlayer1Position().x + 10;
				   		p.y = model.getPlayer1Position().y - 10;
				   }
				   else{
					   /*Bullet at Top corner*/
					   p.x = model.getPlayer2Position().x;
					   p.y = model.getPlayer2Position().y - 10;
				   }
				   
				   x=p.x;
				   y=p.y;
				   
				   float vx=(float) (velocity*Math.cos(angle * DegreeToRadian));
				   vx*=velocityMultiplicationFactor;
				   float vy=(float) (velocity*Math.sin(angle * DegreeToRadian));
				   vy*=velocityMultiplicationFactor;
				   
				   if(playerTurn == 1){
					   vx = -vx;
				   }
				   while(model.getCurrentBulletPos().x < view.getMeasuredWidth() && model.getCurrentBulletPos().y < view.getMeasuredHeight() && !hasBulletHitSomething())
				   {
					   try{
						   Thread.sleep(timeIntervalMsec);
						   
						   x+= vx * timeIntervalSec ;
						   y-= (vy * timeIntervalSec - 0.5 * accelerationDueTogravity * timeIntervalSec * timeIntervalSec);
						   
						   vy+=timeIntervalSec*(-accelerationDueTogravity);
						   
						   p.x=(int)x;
						   p.y=(int)y;
						   
						   view.postInvalidate();
					   }
					   catch(Exception e){
						   e.printStackTrace();}
				   }
				   
				   model.setIsPlaying(false);
				   
				   playerTurn = (++playerTurn) % 2;
   			   }
		};
		model.setIsPlaying(true);
		new Thread(mUpdateTimeTask).start();
		
	}
	
	public void generateTerrain(int sceneWidth,int sceneHeight){
		ArrayList<Point>terrain = new TerrainGenerator().generateTerrain(sceneWidth,sceneHeight);
		model.setTerrain(terrain);
//		boolean[][] map = new boolean[sceneWidth][sceneHeight];
//		for(int i=0; i<sceneWidth; i++)
//		{
//			for(int j=0; j<sceneHeight; j++)
//			{
//				map[i][j] =  isPointOnTerrain(new Point(i,j));
//			}
//		}
		
//		model.setTerrainArray(map);
		
	}
	
	public boolean hasBulletHitSomething(){
		Point p = model.getCurrentBulletPos();
		boolean result =isPointOnTerrain(p); 
		return result;
	}
	
	public boolean isPointOnTerrain(Point p)
	{
		int index=0;
		for(Point terrainPoint: model.getTerrain())
		{
			if(terrainPoint.x >= p.x)
			{
				Point p1;
				if(index==0)
					continue;
				p1=model.getTerrain().get(index - 1);
				Point p2 = model.getTerrain().get(index);
				
				int yOfGroundAtpx = (p.x - p1.x)*(p2.y - p1.y)/(p2.x - p1.x) + p1.y;
				
				if(yOfGroundAtpx <= p.y)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			index++;
		}
		return false;
	}	
	public void setRandomTankPositions(){
		Random rand = new Random();
		
		int temp1 = (model.getTerrain().size()/3);
		int temp2 = (int) (1000 * rand.nextDouble());
		int tank1Index  = temp2  % temp1 ;
		int tank2Index  = model.getTerrain().size()/2 +  (int) (( 1000 * rand.nextGaussian() )  % (model.getTerrain().size()/3)) ;
		
		Point p1 = new Point(model.getTerrain().get(tank1Index));
		model.setPlayer1Position(p1);
		
		Point p2 = new Point(model.getTerrain().get(tank2Index));
		model.setPlayer2Position(p2);
	}
}
