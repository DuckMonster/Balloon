package com.emilstrom.balloon.Game;

import com.emilstrom.balloon.Effect.ExplodeEffect;
import com.emilstrom.balloon.Helper.Art;
import com.emilstrom.balloon.Helper.GameMath;
import com.emilstrom.balloon.Helper.Sprite;
import com.emilstrom.balloon.Helper.Vertex;

/**
 * Created by Emil on 2014-05-25.
 */
public class Balloon {
	Map map;
	Sprite balloonSprite;
	ExplodeEffect explodeEffect;

	Vertex position;
	float gravity = 15f, balloonSize = 3f;
	boolean popped = false;

	public Balloon(Map map) {
		this.map = map;
		balloonSprite = new Sprite(Art.balloon);

		float w = Game.currentGame.gameWidth/2 - 1.5f;
		position = new Vertex((float)GameMath.getRndDouble(-w, w), -Game.currentGame.gameHeight/2 - 3f);
	}

	public boolean getCollision(Vertex pos) {
		if (popped) return false;
		return (pos.x >= position.x - balloonSize/2 && pos.x < position.x + balloonSize/2 &&
			pos.y >= position.y - balloonSize/2 && pos.y < position.y + balloonSize/2);
	}

	public void checkPop(Vertex pos) {
		if (getCollision(pos)) {
			pop();
		}
	}

	public void pop() {
		popped = true;
		explodeEffect = new ExplodeEffect(position);
	}

	public void logic() {
		if (explodeEffect != null) explodeEffect.logic();

		if (popped) return;
		position.y += gravity * Game.updateTime;
	}

	public void draw() {
		if (explodeEffect != null) explodeEffect.draw();

		if (popped) return;
		balloonSprite.draw(position, new Vertex(balloonSize, balloonSize), 0f);
	}
}
