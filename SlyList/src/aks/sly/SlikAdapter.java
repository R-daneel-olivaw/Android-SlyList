package aks.sly;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SlikAdapter extends BaseAdapter {

	private final List<? extends ISlickItem> data;
	private final Context context;

	public SlikAdapter(List<? extends ISlickItem> data, Context context) {
		this.data = data;
		this.context = context;
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
			convertView = View.inflate(context, R.layout.sly_item_1, null);
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

		return convertView;
	}
}
