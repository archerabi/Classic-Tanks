package com.tank;

import android.content.Context;

import android.graphics.Point;
import android.view.View;
import java.lang.Runnable;

public class GameController {

	private double DegreeToRadian = 0.0174532925;
	private int timeIntervalMsec = 62;
	private double timeIntervalSec = (double)(timeIntervalMsec) / 1000; 
	private float accelerationDueTogravity = 60;
	private int velocityMultiplicationFactor=2;
	
	private GameView view;
	private SceneStatus model;
	
	public GameController(){
		model = new SceneStatus();
	}
	
	public View createView(Context context){
		view = new GameView(context);
		view.setModel(model);
		return view;
	}
	
	public void playTurn(final int velocity,final int angle){
		
		Runnable mUpdateTimeTask = new Runnable() {
			   public void run() {
				   
				   float x;
				   float y;
				   Point p = model.getCurrentBulletPos();
				   x=p.x;
				   y=p.y;
				   
				   float vx=(float) (velocity*Math.cos(angle * DegreeToRadian));
				   vx*=velocityMultiplicationFactor;
				   float vy=(float) (velocity*Math.sin(angle * DegreeToRadian));
				   vy*=velocityMultiplicationFactor;
				   
				   while(model.getCurrentBulletPos().x < 800 && model.getCurrentBulletPos().y < 480)
				   {
					   try{
						   Thread.sleep(timeIntervalMsec);
						   
						   x+= vx * timeIntervalSec ;
						   y-= (vy * timeIntervalSec - 0.5 * accelerationDueTogravity * timeIntervalSec * timeIntervalSec);
						   
						   vy+=timeIntervalSec*(-accelerationDueTogravity);
						   
						   p.x=(int)x;
						   p.y=(int)y;
						   
						   view.invalidate();
					   }
					   catch(Exception e){e.printStackTrace();}
				   }
   			   }
		};
		new Thread(mUpdateTimeTask).start();
		
	}
	
}
