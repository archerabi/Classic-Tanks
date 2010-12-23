package com.learnandroid.vortex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;

public class VortexRenderer implements GLSurfaceView.Renderer{

	private float red = 0.9f;
    private float green = 0.2f;
    private float blue = 0.2f;
    private float angle=0;
    
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		gl.glClearColor(red, green, blue, 1.0f);
        // clear the color buffer to show the ClearColor we called above...
		gl.glLoadIdentity();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glRotatef(angle, 1f, 1f, 0f);
        gl.glColor4f(0.5f, 0f, 0f, 0.5f);
      
        // define the vertices we want to draw
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
     
        // finally draw the vertices
        gl.glDrawElements(GL10.GL_TRIANGLES, nrOfVertices, GL10.GL_UNSIGNED_SHORT, indexBuffer);
    }

	

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		gl.glViewport(0, 0, width, height);
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		initTriangle();
	}
	
	public void setColor(float r,float g,float b)
	{
		red=r;blue=b;green=g;
	}
	public void setAngle(float ang) {
	    angle = ang;
	}
	private FloatBuffer vertexBuffer;
	private ShortBuffer indexBuffer;
	
	private short[] indicesArray = {0, 1, 2};
	private int nrOfVertices = 3;
 

	private void initTriangle() {
	    // float has 4 bytes
	    ByteBuffer vbb = ByteBuffer.allocateDirect(nrOfVertices * 3 * 4);
	    vbb.order(ByteOrder.nativeOrder());
	    vertexBuffer = vbb.asFloatBuffer();
	 
	    // short has 2 bytes
	    ByteBuffer ibb = ByteBuffer.allocateDirect(nrOfVertices * 2);
	    ibb.order(ByteOrder.nativeOrder());
	    indexBuffer = ibb.asShortBuffer();
	 
	    float[] coords = {
	        -0.5f, -0.5f, 0f, // (x1, y1, z1)
	        0.5f, -0.5f, 0f, // (x2, y2, z2)
	        0f, 0.5f, 0f // (x3, y3, z3)
	    };
	 
	    vertexBuffer.put(coords);
	    indexBuffer.put(indicesArray);
	 
	    vertexBuffer.position(0);
	    indexBuffer.position(0);
	}

}
