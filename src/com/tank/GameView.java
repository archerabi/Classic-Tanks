package com.tank;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.View;

public class GameView extends View{
	
	private SceneStatus model;
	private Paint paint;
	private ArrayList<Point> terrain;
	
	public GameView(Context context) {
		super(context);

		paint = new Paint();
		// set's the paint's colour
		paint.setColor(Color.GREEN);
		// set's paint's text size
		paint.setTextSize(25);
		// smooth's out the edges of what is being drawn
		paint.setAntiAlias(true);
		
		terrain = new TerrainGenerator().generateTerrain(800); 
	}

	public void setModel(SceneStatus status){
		model = status;
	}
	
//	public void onSurfaceCreated(SurfaceHolder surfHolder){
//		
//	}
//	
//	public void onSurfaceChanged(SurfaceHolder surfHolder){
//		
//	}
//	
	public void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
		setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setColor(Color.GREEN);
		Point p = model.getCurrentBulletPos();
		canvas.drawCircle(p.x, p.y,3, paint);
		
		paint.setColor(0xff8b4513);
		
		Point prev = terrain.get(0);
		for (Point terrainPoint : terrain) {
			canvas.drawLine(prev.x,prev.y,terrainPoint.x, terrainPoint.y, paint);
			drawQuad(prev, terrainPoint, new Point(terrainPoint.x,300),new Point(prev.x,300), paint, canvas);			
			prev=terrainPoint;
		}
	}
	
	private void drawQuad(Point p1,Point p2,Point p3,Point p4,Paint paint,Canvas canvas){
		
		Path path = new Path();
		path.moveTo(p1.x, p1.y);
		path.lineTo(p2.x, p2.y);
		path.lineTo(p3.x, p3.y);
		path.lineTo(p4.x, p4.y);
		path.lineTo(p1.x, p1.y);
		path.close();
	    
        canvas.drawPath(path, paint);
	}
}
