package aks.sly;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SlikButtonItemAdapter extends SlikAdapter {

	public SlikButtonItemAdapter(List<? extends ISlickItem> data,
			Context context) {
		super(data, context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return data.get(position).getItemId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ISlickItem item = data.get(position);

		if (convertView == null) {
			convertView = View.inflate(context, R.layout.sly_item_2, null);
		}

		TextView head1 = (TextView) convertView.findViewById(R.id.heading1);
		TextView head2 = (TextView) convertView.findViewById(R.id.heading2);

		SeekBar slyBar = (SeekBar) convertView.findViewById(R.id.slybar);
		slyBar.setTag(item);

		head1.setText(item.getHeading1());
		head2.setText(item.getHeading2());

		slyBar.setProgress(item.getCachedVal());
		slyBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

				Object tag = seekBar.getTag();
				if (tag instanceof ISlickItem) {
					ISlickItem slyItem = (ISlickItem) tag;
					slyItem.storeVal(progress);
				}

			}
		});

		ImageButton minus = (ImageButton) convertView
				.findViewById(R.id.imageButton1);
		minus.setTag(slyBar);
		minus.setOnClickListener(minusListner);

		ImageButton plus = (ImageButton) convertView
				.findViewById(R.id.imageButton2);
		plus.setTag(slyBar);
		plus.setOnClickListener(plusListner);

		return convertView;
	}

	private OnClickListener minusListner = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Object tag = v.getTag();
			if (tag instanceof SeekBar) {
				SeekBar buff = (SeekBar) tag;
				buff.setProgress(buff.getProgress() + 10);
			}
		}
	};

	private OnClickListener plusListner = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Object tag = v.getTag();
			if (tag instanceof SeekBar) {
				SeekBar buff = (SeekBar) tag;
				buff.setProgress(buff.getProgress() - 10);
			}

		}
	};

}
