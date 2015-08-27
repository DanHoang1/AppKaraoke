package com.vitpr.android.appkaraoke;

import java.util.List;

import com.vitpr.android.appkaraoke.models.Contact;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomListView extends ArrayAdapter<Contact>{
	Context context;
	int resource;
	List<Contact>objects;
	
	public CustomListView(Context context,int resource,List<Contact>objects){
		super(context,resource,objects);
		this.context=context;
		this.objects=objects;
		this.resource=resource;
		
	}
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		View view1=View.inflate(context,resource, null);
		
		TextView mabaihat = (TextView) view1.findViewById(R.id.view_tv_mabaihat);
		TextView baihat = (TextView) view1.findViewById(R.id.view_tv_baihat);
		TextView tenbaihat = (TextView) view1.findViewById(R.id.view_tv_tenbaihat);
		TextView tacgia = (TextView) view1.findViewById(R.id.view_tv_tacgia);
		
		Contact item =objects.get(position);
		mabaihat.setText(item.getMabaihat().toString());
		baihat.setText(item.getBaihat().toString());
		tenbaihat.setText(item.getTenbaihat().toString());
		tacgia.setText(item.getTacgia().toString());
		// TODO Auto-generated method stub
		return view;
	}  
}
