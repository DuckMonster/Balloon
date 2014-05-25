package com.emilstrom.balloon.Game;

import android.util.Log;

import com.emilstrom.balloon.BalloonGame;
import com.emilstrom.balloon.Effect.PopEffect;
import com.emilstrom.balloon.Helper.Art;
import com.emilstrom.balloon.Helper.Input;
import com.emilstrom.balloon.Helper.InputHelper;
import com.emilstrom.balloon.Helper.Sprite;
import com.emilstrom.balloon.Helper.Timer;
import com.emilstrom.balloon.Helper.Vertex;

/**
 * Created by Emil on 2014-05-25.
 */
public class Map {
	Balloon balloonList[] = new Balloon[10];
	int balloonI = 0;

	PopEffect popEffectList[] = new PopEffect[5];
	int popEffectI = 0;

	Timer balloonSpawnTimer = new Timer(1.5f, false);

	Input oldInput;

	public Map() {
	}

	public void spawnBalloon() {
		balloonList[balloonI] = new Balloon(this);
		balloonI = (balloonI + 1) % balloonList.length;
	}

	public void checkBalloonPop(Vertex pos) {
		for(Balloon b : balloonList) if (b != null) b.checkPop(pos);
		createPopEffect(pos);
	}

	public void createPopEffect(Vertex pos) {
		popEffectList[popEffectI] = new PopEffect(pos);
		popEffectI = (popEffectI + 1) % popEffectList.length;
	}

	public void logic() {
		Input in = InputHelper.getInput();
		if (oldInput == null) oldInput = in;

		for(int i=0; i<Input.NMBR_OF_FINGERS; i++)
			if (in.isPressed(i) && !oldInput.isPressed(i)) {
				checkBalloonPop(in.getPosition(i));
			}

		balloonSpawnTimer.logic();
		if (balloonSpawnTimer.isDone()) {
			spawnBalloon();
			balloonSpawnTimer.reset();
		}

		for(Balloon b : balloonList) if (b != null) b.logic();
		for(PopEffect p : popEffectList) if (p != null) p.logic();

		oldInput = in;
	}

	public void draw() {
		for(Balloon b : balloonList) if (b != null) b.draw();
		for(PopEffect p : popEffectList) if (p != null) p.draw();
	}
}
