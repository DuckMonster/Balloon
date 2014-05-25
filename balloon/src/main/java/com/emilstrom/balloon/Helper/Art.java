package com.emilstrom.balloon.Helper;


import com.emilstrom.balloon.R;

/**
 * Created by Emil on 2014-05-25.
 */
public class Art {
	public static SpriteData blank = new SpriteData(R.drawable.blank),
		circle = new SpriteData(R.drawable.circle),
		circleBig = new SpriteData(R.drawable.circlebig),
		ring = new SpriteData(R.drawable.ring),

		balloon = new SpriteData(R.drawable.balloon),
		pop = new SpriteData(R.drawable.pop);

	public static void loadAssets() {
		blank.loadAssets();
		circle.loadAssets();
		circleBig.loadAssets();
		ring.loadAssets();

		balloon.loadAssets();
		pop.loadAssets();
	}
}