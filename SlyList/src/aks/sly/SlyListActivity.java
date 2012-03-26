package aks.sly;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

public class SlyListActivity extends ListActivity {
	private ListView listView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		listView = getListView();
		
		setupDummyTest();
	}

	void setupDummyTest() {

		List<SlyListActivity.TestImpl> testList = new ArrayList<SlyListActivity.TestImpl>();

		for (int i = 0; i < 10; i++) {
			testList.add(new TestImpl());
		}

		SlikAdapter mySlikAdapter = new SlikAdapter(testList, this);
		setListAdapter(mySlikAdapter);
	}

	class TestImpl implements ISlickItem {

		private int current;

		@Override
		public String getHeading1() {
			return "Akshat";
		}

		@Override
		public String getHeading2() {
			return "Yo !";
		}

		@Override
		public long getItemId() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void storeVal(int current) {
			this.current = current;

		}

		@Override
		public int getCachedVal() {
			return current;
		}

	}
}
