package com.emilstrom.balloon.Game;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.util.Log;

import com.emilstrom.balloon.BalloonGame;
import com.emilstrom.balloon.Helper.Art;
import com.emilstrom.balloon.Helper.Camera;
import com.emilstrom.balloon.Helper.ShaderHelper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Emil on 2014-05-25.
 */
public class Game implements GLSurfaceView.Renderer {
	public static Game currentGame;
	public static float updateTime;

	private static long oldTime;

	Map currentMap;

	public Game() {
		currentGame = this;
		currentMap = new Map();
		currentCamera = new Camera();
	}

	public void logic() {
		//Calculate updatetime
		if (updateTime == -1) {
			oldTime = SystemClock.uptimeMillis();
			Log.v(BalloonGame.TAG, "First time...");
		}

		long newTime = SystemClock.uptimeMillis();
		updateTime = (newTime - oldTime) * 0.001f;
		oldTime = newTime;

		currentMap.logic();
	}

	public void draw() {
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

		GLES20.glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		GLES20.glEnable(GLES20.GL_BLEND);

		currentMap.draw();
	}


	//GL STUFF
	float projection[] = new float[16];
	public Camera currentCamera;

	public float gameWidth, gameHeight;

	public float[] getViewProjection() {
		float ret[] = new float[16];
		Matrix.multiplyMM(ret, 0, projection, 0, currentCamera.getView(), 0);
		return ret;
	}

	public void onSurfaceCreated(GL10 unused, EGLConfig config) {
		GLES20.glClearColor(
				92f / 255f,
				199f / 255f,
				255f / 255f,
				255f / 255f);
	}

	public void onDrawFrame(GL10 unused) {
		logic();
		draw();
	}

	public void onSurfaceChanged(GL10 unused, int width, int height) {
		final int screenSize = 10;
		float ratio = (float)width / (float)height;

		gameHeight = screenSize*2;
		gameWidth = gameHeight * ratio;

		Matrix.orthoM(projection, 0, -ratio * screenSize, ratio * screenSize, -screenSize, screenSize, 1f, 10f);

		ShaderHelper.loadShader();
		Art.loadAssets();

		updateTime = -1;
	}
}
