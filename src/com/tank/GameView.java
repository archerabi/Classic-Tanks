package com.tank;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View{
	
	private SceneStatus model;
	private Paint paint;
	
	public GameView(Context context) {
		super(context);

		paint = new Paint();
		paint.setColor(Color.GREEN);
		paint.setTextSize(25);
	}

	public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
		paint.setColor(Color.GREEN);
		paint.setTextSize(25);
		
//        TypedArray a = context.obtainStyledAttributes(attrs,
//                R.styleable.LabelView);
//
//        CharSequence s = a.getString(R.styleable.LabelView_text);
//        if (s != null) {
//            setText(s.toString());
//        }
//
//        // Retrieve the color(s) to be used for this view and apply them.
//        // Note, if you only care about supporting a single color, that you
//        // can instead call a.getColor() and pass that to setTextColor().
//        setTextColor(a.getColor(R.styleable.LabelView_textColor, 0xFF000000));
//
//        int textSize = a.getDimensionPixelOffset(R.styleable.LabelView_textSize, 0);
//        if (textSize > 0) {
//            setTextSize(textSize);
//        }

//        a.recycle();
    }
	public void setModel(SceneStatus status){
		model = status;
	}
	
	public void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
		setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
	}

	private int measureWidth(int measureSpec) {
        int result = 0;
        @SuppressWarnings("unused")
		int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        result = specSize;
        return result;
    }
	private int measureHeight(int measureSpec) {
        int result = 0;
        @SuppressWarnings("unused")
		int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        result = specSize;
        return result;
    }
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(0xff8b4513);
		
		Point prev = model.getTerrain().get(0);
		
		for (Point terrainPoint : model.getTerrain()) {
			canvas.drawLine(prev.x,prev.y,terrainPoint.x, terrainPoint.y, paint);
			drawQuad(prev, terrainPoint, new Point(terrainPoint.x,300),new Point(prev.x,300), paint, canvas);			
			prev=terrainPoint;
		}
		paint.setColor(Color.GREEN);
		Point p = model.getCurrentBulletPos();
		
		if(model.IsPlaying())
			canvas.drawCircle(p.x, p.y,2, paint);
		
		paint.setColor(Color.RED);
		Point p1 = model.getPlayer1Position();
		canvas.drawRect(new Rect(p1.x,p1.y-10,p1.x+10,p1.y), paint);
		
		paint.setColor(Color.BLUE);
		Point p2 = model.getPlayer2Position();
		canvas.drawRect(new Rect(p2.x,p2.y-10,p2.x+10,p2.y), paint);
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
	
//	public bool isPointBelowTerrain(Point p){
//		if()
//		{
//			
//		}
//	}
}
