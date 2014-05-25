package com.emilstrom.balloon;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by Emil on 2014-05-25.
 */
public class BalloonGame extends Activity {
	public static final String TAG = "BalloonTag";

	public static Context context;

	GameSurface gameSurface;

	public void onCreate(Bundle savedState) {
		super.onCreate(savedState);
		context = this;

		gameSurface = new GameSurface(this);
		setContentView(gameSurface);
	}
}
