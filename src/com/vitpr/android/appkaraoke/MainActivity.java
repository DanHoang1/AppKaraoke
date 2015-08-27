package com.vitpr.android.appkaraoke;

import java.util.List;

import com.vitpr.android.appkaraoke.adapters.MainAdapter;
import com.vitpr.android.appkaraoke.db.DatabaserReader;
import com.vitpr.android.appkaraoke.models.Contact;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;;

public class MainActivity extends Activity {
	ListView mLvList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ArrayList<Contact> contacts = new ArrayList<Contact>();
		DatabaserReader db = new DatabaserReader(this);
		// List<Contact> contacts= getAllContacts();
		List<Contact> contacts = Contact.getAllContacts(this);
		mLvList = (ListView) findViewById(R.id.ListView);
		MainAdapter adapter = new MainAdapter(this, contacts);
		mLvList.setAdapter(adapter);
	}
}
