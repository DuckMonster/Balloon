package com.emilstrom.balloon.Helper;

import android.opengl.Matrix;

/**
 * Created by Emil on 2014-03-17.
 */
public class Camera {
	public Vertex position, offset;
	float rotation;

	public Camera() {
		position = new Vertex(0f, 0f);
		offset = new Vertex(0,0);
	}

	public void setRotation(float r) { rotation = r / 180f * (float)Math.PI; }
	public void setOffset(Vertex v) { offset = new Vertex(v); }

	public float[] getView() {
		float view[] = new float[16];
		Matrix.setLookAtM(view, 0, position.x + offset.x, position.y + offset.y, 5f, position.x + offset.x, position.y + offset.y, 0f, -(float)Math.sin(rotation), (float)Math.cos(rotation), 0f);
		return view;
	}
}