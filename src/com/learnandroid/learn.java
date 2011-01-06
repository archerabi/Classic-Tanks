package com.learnandroid;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.tank.GameController;
import com.tank.GameView;

public class learn extends Activity implements OnClickListener{
	
	private GameController controller;
	private boolean layoutDone=false;
	
    public void onClick(View v) {
	
    	if(controller != null)
    	{
    		SeekBar velBar = (SeekBar)findViewById(R.id.seekbarVel);
    		SeekBar angleBar = (SeekBar)findViewById(R.id.seekbarAngle);
    		Button playButton =(Button)findViewById(R.id.playButton);
    		TextView labelVel = (TextView)findViewById(R.id.labelVel);
    		TextView labelAng = (TextView)findViewById(R.id.labelAngle);
    		
//    		velBar.setEnabled(false);
//    		angleBar.setEnabled(false);
//    		playButton.setClickable(false);
    		
    		controller.playTurn(velBar.getProgress(), angleBar.getProgress());
    	}
	}

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
        setContentView(R.layout.mylayout);
        GameView view = (GameView)findViewById(R.id.gameview);
        controller =new GameController();
        controller.setView(view);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Button playButton =(Button)findViewById(R.id.playButton);
        playButton.setOnClickListener(this);
        
        ViewTreeObserver observer = view.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
            	if(!layoutDone)
            	{
            		GameView view = (GameView)findViewById(R.id.gameview);
            		controller.generateTerrain(view.getMeasuredWidth(), view.getMeasuredHeight());
            		controller.setRandomTankPositions();
            		layoutDone = true;
            	}
            }
        });
    }
    
}    
