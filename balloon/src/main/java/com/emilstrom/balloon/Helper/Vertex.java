package com.emilstrom.balloon.Helper;

/**
 * Created by Emil on 2014-02-20.
 */
public class Vertex {
	public float x, y;
	public Vertex() {
		x = 0;
		y = 0;
	}
	public Vertex(float xx, float yy) {
		x = xx;
		y = yy;
	}
	public Vertex(Vertex v) {
		x = v.x;
		y = v.y;
	}

	public void add(Vertex v) {
		x += v.x;
		y += v.y;
	}
	public void subtract(Vertex v) {
		x -= v.x;
		y -= v.y;
	}
	public void multiply(Vertex v) {
		x *= v.x;
		y *= v.y;
	}
	public void multiply(float d) {
		x *= d;
		y *= d;
	}

	public Vertex plus(Vertex v) {
		return Vertex.add(this, v);
	}
	public Vertex minus(Vertex v) {
		return Vertex.subtract(this, v);
	}
	public Vertex times(Vertex v) {
		return Vertex.multiply(this, v);
	}
	public Vertex times(float d) {
		return Vertex.multiply(this, d);
	}

	public void normalize() {
		float l = (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		x /= l;
		y /= l;
	}
	public void reverse() { multiply(-1); }

	public void copy(Vertex v) { x = v.x; y = v.y; }

	public float getLength() { return getLength(this); }
	public float getDirection() { return getDirection(this); }
	public Vertex getNormalized() {
		Vertex v = new Vertex(this);
		v.normalize();

		return v;
	}

	public boolean compare(Vertex v) { return (x == v.x && y == v.y); }

	public static Vertex add(Vertex a, Vertex b) {
		return new Vertex(a.x + b.x, a.y + b.y);
	}

	public static Vertex subtract(Vertex a, Vertex b) {
		return new Vertex(a.x - b.x, a.y - b.y);
	}

	public static Vertex multiply(Vertex a, Vertex b) {
		return new Vertex(a.x * b.x, a.x * b.y);
	}

	public static Vertex multiply(Vertex a, float d) {
		return new Vertex(a.x * d, a.y * d);
	}

	public static Vertex normalize(Vertex v) {
		if (v.x == 0 && v.y == 0) return new Vertex(0,0);

		float l = (float)Math.sqrt(Math.pow(v.x, 2) + Math.pow(v.y, 2));
		return new Vertex(v.x / l, v.y / l);
	}

	public static Vertex getDirectionVertex(Vertex a, Vertex b) {
		return normalize(subtract(b, a));
	}
	public static Vertex getDirectionVertex(float direction) {
		return new Vertex(-(float)Math.sin(direction / 180f * Math.PI), (float)Math.cos(direction / 180f * Math.PI));
	}

	public static float getLength(Vertex a, Vertex b) {
		Vertex v = subtract(a, b);
		if (v.x == 0f && v.y == 0f) return 0f;

		return v.getLength();
	}

	public static float getDirection(Vertex a, Vertex b) {
		return (float)GameMath.getDirection(a.x, a.y, b.x, b.y);
	}

	public static float getLength(Vertex v) {
		return (float)Math.sqrt(Math.pow(v.x, 2) + Math.pow(v.y, 2));
	}

	public static float getDirection(Vertex v) {
		return (float)GameMath.getDirection(v.x, v.y, 0, 0);
	}
}