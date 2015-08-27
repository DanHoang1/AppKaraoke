package com.vitpr.android.appkaraoke.adapters;

import java.util.List;

import com.vitpr.android.appkaraoke.R;
import com.vitpr.android.appkaraoke.models.Contact;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainAdapter extends ArrayAdapter<Contact> {

	public MainAdapter(Context context, List<Contact> items) {
		super(context, 0, items);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.karaoke_view, parent, false);
			holder = new ViewHolder();
			holder.tvMabaihat = (TextView) convertView.findViewById(R.id.view_tv_mabaihat);
			holder.tvBaihat = (TextView) convertView.findViewById(R.id.view_tv_baihat);
			holder.tvTenbaihat = (TextView) convertView.findViewById(R.id.view_tv_tenbaihat);
			holder.tvTacgia = (TextView) convertView.findViewById(R.id.view_tv_tacgia);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Contact item = getItem(position);
		Log.e("Ma bai hat", "" + item.getMabaihat());
		holder.tvMabaihat.setText(item.getMabaihat());
		holder.tvTenbaihat.setText(item.getTenbaihat());
		holder.tvBaihat.setText(item.getBaihat());
		holder.tvTacgia.setText(item.getTacgia());

		return convertView;
	}

	private class ViewHolder {
		public TextView tvBaihat;
		private TextView tvMabaihat, tvTenbaihat, tvTacgia;
	}
}
