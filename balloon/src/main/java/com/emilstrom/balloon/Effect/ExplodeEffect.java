package com.emilstrom.balloon.Effect;

import com.emilstrom.balloon.Helper.*;

/**
 * Created by Emil on 2014-05-25.
 */
public class ExplodeEffect {
	RingEffect ringEffect;

	public ExplodeEffect(Vertex pos) {
		ringEffect = new RingEffect(pos);
	}

	public void logic() {
		ringEffect.logic();
	}

	public void draw() {
		ringEffect.draw();
	}
}
