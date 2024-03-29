package com.emilstrom.balloon.Helper;

import android.graphics.Shader;
import android.opengl.GLES20;
import android.opengl.Matrix;

import com.emilstrom.balloon.Game.Game;

/**
 * Created by Emil on 2014-03-18.
 */
public class Sprite {
	float modelMatrix[] = new float[16];
	Color spriteColor, spriteAlphaColor;

	SpriteData spriteData;

	public Sprite(SpriteData data) {
		spriteData = data;

		setColor(1f, 1f, 1f, 1f);
		setAlphaColor(1f, 0f, 1f);
	}

	public void resetMatrix() {
		Matrix.setIdentityM(modelMatrix, 0);
	}

	public void setColor(Color c) {
		spriteColor = new Color(c);
	}
	public void setColor(float r, float g, float b, float a) {
		setColor(new Color(r, g, b, a));
	}

	public void setAlphaColor(Color c) {
		spriteAlphaColor = new Color(c);
	}
	public void setAlphaColor(float r, float g, float b) {
		setAlphaColor(new Color(r, g, b));
	}

	public void translate(Vertex v) {
		Matrix.translateM(modelMatrix, 0, v.x, v.y, 0f);
	}
	public void translate(float x, float y) {
		Matrix.translateM(modelMatrix, 0, x, y, 0f);
	}
	public void setPosition(Vertex v) {
		resetMatrix();
		translate(v);
	}
	public void setPosition(float x, float y) {
		resetMatrix();
		translate(x, y);
	}

	public void rotate(float a) {
		Matrix.rotateM(modelMatrix, 0, a, 0f, 0f, 1f);
	}

	public void scale(Vertex v) {
		Matrix.scaleM(modelMatrix, 0, v.x, v.y, 0f);
	}
	public void scale(float x, float y) {
		Matrix.scaleM(modelMatrix, 0, x, y, 0f);
	}

	public float[] getMVPMatrix() {
		float mvp[] = new float[16];
		Matrix.multiplyMM(mvp, 0, Game.currentGame.getViewProjection(), 0, modelMatrix, 0);
		return mvp;
	}

	public void uploadData() {
		spriteData.loadAttributes();
		spriteData.uploadData(spriteColor, spriteAlphaColor, getMVPMatrix());
	}

	public void draw() {
		spriteData.shaderProgram = ShaderHelper.shaderProgram2D;
		uploadData();
		GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);
	}


	public void draw(Vertex pos) {
		setPosition(pos);
		draw();
	}
	public void draw(float x, float y) {
		draw(new Vertex(x, y));
	}

	public void draw(Vertex pos, Vertex scale, float r) {
		setPosition(pos);
		rotate(r);
		scale(scale);
		draw();
	}
	public void draw(Vertex pos, float scale, float r) {
		draw(pos, new Vertex(scale, scale), r);
	}
	public void draw(float x, float y, Vertex scale, float r) {
		draw(new Vertex(x, y), scale, r);
	}
	public void draw(float x, float y, float scalex, float scaley, float r) {
		draw(new Vertex(x, y), new Vertex(scalex, scaley), r);
	}
	public void draw(float x, float y, float scale, float r) {
		draw(new Vertex(x, y), new Vertex(scale, scale), r);
	}
}