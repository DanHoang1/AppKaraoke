package com.vitpr.android.appkaraoke.models;

import java.util.ArrayList;
import java.util.List;

import com.vitpr.android.appkaraoke.db.DatabaserReader;

import android.content.Context;
import android.database.Cursor;

public class Contact {
	// private variables
	String mId;
	String mMabaihat;
	String mBaihat;
	String mTenbaihat;
	String mTacgia;

	protected Contact() {

	}

	public String getID() {
		return this.mId;
	}

	// setting id
	public void setID(String id) {
		this.mId = id;
	}

	// getting mabaihat
	public String getMabaihat() {
		return this.mMabaihat;
	}

	// setting mabaihat
	public void setMabaihat(String mabaihat) {
		this.mMabaihat = mabaihat;
	}

	// getting baihat
	public String getBaihat() {
		return this.mBaihat;
	}

	// setting baihat
	public void setBaihat(String baihat) {
		this.mBaihat = baihat;
	}

	// getting tenbaihat
	public String getTenbaihat() {
		return this.mTenbaihat;
	}

	// setting tenbaihat
	public void setTenbaihat(String tenbaihat) {
		this.mTenbaihat = tenbaihat;
	}

	// getting tacgia
	public String getTacgia() {
		return this.mTacgia;
	}

	// setting tacgia
	public void setTacgia(String tacgia) {
		this.mTacgia = tacgia;
	}

	/**
	 * create Contact model
	 * 
	 * @author Admin
	 *
	 */
	public static class Builder {
		private Contact mContact;

		public Builder() {
			mContact = new Contact();
		}

		public Builder setID(String value) {
			mContact.setID(value);
			return this;
		}

		public Builder setMabaihat(String value) {
			mContact.setMabaihat(value);
			return this;
		}

		public Builder setBaihat(String value) {
			mContact.setBaihat(value);
			return this;
		}

		public Builder setTenbaihat(String value) {
			mContact.setTenbaihat(value);
			return this;
		}

		public Builder setTacgia(String value) {
			mContact.setTacgia(value);
			return this;
		}

		public Contact create() {
			return mContact;
		}
	}

	public static List<Contact> getAllContacts(Context context) {
		DatabaserReader reader = new DatabaserReader(context);
		reader.open();
		String query = "select * from song limit 0,300";
		Cursor cursor = reader.getDatas(query);
		if (cursor == null) {
			return null;
		}
		cursor.moveToFirst();
		List<Contact> items = new ArrayList<>();
		while (cursor.moveToNext()) {
			// Contact contacts = new Contact();
			String mabaihat = cursor.getString(cursor.getColumnIndex("id"));
			String baihat = cursor.getString(cursor.getColumnIndex("title"));
			String tenbaihat = cursor.getString(cursor.getColumnIndex("title_simple"));
			String tacgia = cursor.getString(cursor.getColumnIndex("source"));
			Contact item = new Contact.Builder().setMabaihat(mabaihat).setBaihat(baihat).setTenbaihat(tenbaihat)
					.setTacgia(tacgia).create();
			items.add(item);
		}
		return items;
	}
}
