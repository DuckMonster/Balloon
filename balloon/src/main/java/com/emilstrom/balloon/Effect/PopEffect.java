package com.emilstrom.balloon.Effect;

import com.emilstrom.balloon.Helper.Art;
import com.emilstrom.balloon.Helper.GameMath;
import com.emilstrom.balloon.Helper.Sprite;
import com.emilstrom.balloon.Helper.Timer;
import com.emilstrom.balloon.Helper.Vertex;

/**
 * Created by Emil on 2014-05-25.
 */
public class PopEffect {
	Timer popTimer = new Timer(0.3f, false);
	Vertex position;

	Sprite popSprite = new Sprite(Art.pop);

	float rotation = (float)GameMath.getRndDouble(0f, 360f);

	public PopEffect(Vertex pos) {
		position = pos;
	}

	public void logic() {
		if (popTimer.isDone()) return;

		popTimer.logic();
	}

	public void draw() {
		if (popTimer.isDone()) return;

		for(int i=0; i<4; i++) {
			float pos = (float)(1 - Math.pow(Math.E, -popTimer.percentageDone() * 4f));

			popSprite.draw(position.plus(Vertex.getDirectionVertex(rotation + 90 * i).times(pos * 2f)), new Vertex(2f, 2f).times(1f - popTimer.percentageDone()), rotation + 90f * i);
		}
	}
}
