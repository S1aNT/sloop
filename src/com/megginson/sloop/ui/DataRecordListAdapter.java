package com.megginson.sloop.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.megginson.sloop.R;
import com.megginson.sloop.model.DataEntry;
import com.megginson.sloop.model.DataRecord;

/**
 * Adapt a single {@link DataRecord} for display as a list of entries.
 * 
 * The {@link DataRecordFragment} class uses this adapter to display the data
 * record in a {@link ListView}.
 * 
 * @author David Megginson
 */
public class DataRecordListAdapter extends BaseAdapter {

	private Context mContext;

	private DataRecord mDataRecord;
	
	public DataRecordListAdapter(Context context, DataRecord dataRecord) {
		mContext = context;
		mDataRecord = dataRecord;
	}

	@Override
	public int getCount() {
		return mDataRecord.size();
	}

	@Override
	public Object getItem(int position) {
		return mDataRecord.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout layout;
		TextView labelView;
		TextView valueView;

		// Reuse existing components if we can
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			layout = (LinearLayout)inflater.inflate(R.layout.component_data_entry, null);
		} else {
			layout = (LinearLayout)convertView;
		}

		// Set the appropriate text for this entry
		DataEntry entry = mDataRecord.get(position);

		labelView = (TextView)layout.findViewById(R.id.field_name);
		labelView.setText(entry.getKey());
		
		valueView = (TextView)layout.findViewById(R.id.field_value);
		valueView.setText(entry.getValue());
		
		if (entry.hasFilter()) {
			layout.setBackgroundColor(Color.LTGRAY);
		} else {
			layout.setBackgroundColor(Color.TRANSPARENT);
		}

		return layout;
	}

}
