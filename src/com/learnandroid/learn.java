package com.learnandroid;

import android.app.Activity;
import android.os.Bundle;

import com.tank.GameController;

public class learn extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        GameController controller =new GameController();
        setContentView(controller.createView(this));
//        controller.playTurn(70,45);
        
    }
}