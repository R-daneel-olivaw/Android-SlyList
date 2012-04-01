package aks.sly;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;

public class SlyBar extends SeekBar {

	public SlyBar(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	public boolean onTouchEvent(MotionEvent event) {

		// Log.i((event.getHistoricalX(1) - event.getX()) + "",
		// "" + (event.getHistoricalY(1) - event.getY()));

		float distX = Math.abs(event.getHistoricalX(1) - event.getX());
		float distY = Math.abs(event.getHistoricalY(1) - event.getY());

		Rect r = new Rect();
		this.getGlobalVisibleRect(r);
		if (!isPointInsideView(event.getRawX(), event.getRawY(), this)) {
			return false;
		}

		Log.i("-------", "-------");
		Log.i("distX", distX + "");
		Log.i("distY", distY + "");

		if (distX > distY) {
			propagateToSuperior(event);
			return true;
		}

		// slyGestureDetector.onTouchEvent(event);

		return false;
	}

	private boolean isPointInsideView(float x, float y, View view) {
		int location[] = new int[2];
		view.getLocationOnScreen(location);
		int viewX = location[0];
		int viewY = location[1];

		// point is inside view bounds
		if ((x > viewX && x < (viewX + view.getWidth()))
				&& (y > viewY && y < (viewY + view.getHeight()))) {
			return true;
		} else {
			return false;
		}
	}

	// public boolean onTouchEvent(MotionEvent event) {
	// Log.i("-------", "-------"+event.getAction());
	// switch (event.getAction()) {
	// case MotionEvent.ACTION_MOVE:
	// float distX = Math.abs(event.getX(1) - event.getX());
	// float distY = Math.abs(event.getY(1) - event.getY());
	//
	// Log.i("-------", "-------");
	// Log.i("distX", distX + "");
	// Log.i("distY", distY + "");
	//
	// if (distX > distY) {
	// propagateToSuperior(event);
	// return true;
	// }
	// default:
	// return false;
	// }
	// }

	SlyGestureListner slyGestureListner = new SlyGestureListner();
	GestureDetector slyGestureDetector = new GestureDetector(getContext(),
			this.slyGestureListner);

	private class SlyGestureListner extends
			GestureDetector.SimpleOnGestureListener {

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			if (distanceX > distanceY)
				return propagateToSuperior(e1);
			return false;
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			if ((e1.getX() - e2.getX()) > (e1.getY() - e2.getY()))
				return propagateToSuperior(e1);
			return false;
		}

	}

	private boolean propagateToSuperior(MotionEvent e1) {
		return super.onTouchEvent(e1);
	}

}
