package com.emilstrom.balloon.Effect;

import android.opengl.GLES20;

import com.emilstrom.balloon.Helper.*;
/**
 * Created by Emil on 2014-05-25.
 */
public class RingEffect {
	Sprite ringSprite = new Sprite(Art.circleBig);
	Timer ringTimer = new Timer(0.5f, false);

	Vertex position;

	float diameter = 6f, ringWidth = 0.8f;

	public RingEffect(Vertex pos) {
		position = pos;
	}

	public void logic() {
		if (ringTimer.isDone()) return;
		ringTimer.logic();
	}

	public void draw() {
		if (ringTimer.isDone()) return;

		float r = (float)(1 - Math.pow(Math.E, -ringTimer.percentageDone() * 4f));

		GLES20.glEnable(GLES20.GL_STENCIL_TEST);

			GLES20.glStencilMask(0xFF);
			GLES20.glClear(GLES20.GL_STENCIL_BUFFER_BIT);

			GLES20.glStencilFunc(GLES20.GL_NEVER, 1, 0xFF);
			GLES20.glStencilOp(GLES20.GL_REPLACE, GLES20.GL_KEEP, GLES20.GL_KEEP);

			ringSprite.draw(position, r * diameter - ringWidth * (1f - ringTimer.percentageDone()), 0f);

			GLES20.glStencilMask(0x00);
			GLES20.glStencilFunc(GLES20.GL_NOTEQUAL, 1, 0xFF);

			ringSprite.draw(position, r * diameter, 0f);

		GLES20.glDisable(GLES20.GL_STENCIL_TEST);
	}
}
