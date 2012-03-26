package aks.sly;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.SeekBar;

public class SlyBar extends SeekBar {

	public SlyBar(Context context, AttributeSet attrs) {
		super(context, attrs);

	}



	public boolean onTouchEvent(MotionEvent event) {

		// Log.i((event.getHistoricalX(1) - event.getX()) + "",
		// "" + (event.getHistoricalY(1) - event.getY()));

		if (Math.abs(event.getX(1) - event.getX()) > Math.abs(event.getY(1)
				- event.getY()))
			return propagateToSuperior(event);

		// slyGestureDetector.onTouchEvent(event);

		return false;
	}

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
