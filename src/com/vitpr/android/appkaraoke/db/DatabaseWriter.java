package com.vitpr.android.appkaraoke.db;

import java.util.ArrayList;
import java.util.List;

import com.vitpr.android.appkaraoke.models.Contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseWriter {
	private DatabaseHelper mDatabaseHelper;
	private SQLiteDatabase mSQLiteDatabase;
	
	public static final String DATABASE_NAME="song_db";
	public static final String TABLE_SONG="song";
	
	public static final String SONG_ID="rowid";
	public static final String SONG_MABAIHAT="id";
	public static final String SONG_BAIHAT="title";
	public static final String SONG_TENBAIHAT="title_simple";
	public static final String SONG_TACGIA="source";
	
	//public static final String SONG_CASI="song_casi";
	private static final int DATABASE_VERSION = 1;
	/**
	 * 
	 * @param context
	 */
	public DatabaseWriter(Context context) {
		this.mDatabaseHelper = new DatabaseHelper(context);

	}

	/**
	 * open database
	 */
	public void open() {
		try {
			mDatabaseHelper.createDataBase();
			mDatabaseHelper.openDataBase();
			mDatabaseHelper.close();
			mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// Adding new contact
	 	void addContact(Contact contact) {
	         SQLiteDatabase db =null;
	  
	         ContentValues values = new ContentValues();
	         values.put(SONG_ID, contact.getID()); // Contact ID
	         values.put(SONG_MABAIHAT, contact.getMabaihat()); // Contact Mabaihat
	         values.put(SONG_BAIHAT, contact.getBaihat()); // Contact baihat
	         values.put(SONG_TENBAIHAT, contact.getTenbaihat()); // Contact Tenbaihat
	         values.put(SONG_TACGIA, contact.getTacgia()); // Contact Tacgia
	         //values.put(KEY_PLACE, contact.getPlace());
	  
	         // Inserting Row
	         db.insert(TABLE_SONG, null, values);
	         db.close(); // Closing database connection
	     }
	 // Getting All Contacts
	     public List<Contact> getAllContacts() {
	         List<Contact> contactList = new ArrayList<Contact>();
	         // Select All Query
	         String selectQuery = "SELECT  * FROM " + TABLE_SONG;
	  
	         SQLiteDatabase db = this.mSQLiteDatabase;
	         Cursor cursor = db.rawQuery(selectQuery, null);
	  
	         // looping through all rows and adding to list
	         if (cursor.moveToFirst()) {
	             do {
	                 Contact contact = null;//= new Contact();
	               //contact.setID(Integer.parseInt(cursor.getString(0)));
	                 contact.setID(cursor.getString(0));
	                 contact.setMabaihat(cursor.getString(1));
	                 contact.setTenbaihat(cursor.getString(2));
	                 contact.setTacgia(cursor.getString(3));
	                 //contact.setCasi(cursor.getString(4));
	                 //contact.setPlace(cursor.getString(5));
	                 // Adding contact to list
	                 contactList.add(contact);
	             } while (cursor.moveToNext());
	         }
	  
	         // return contact list
	         return contactList;
	     }
	  
	     // Updating single contact
	     public int updateContact(Contact contact) {
	         SQLiteDatabase db =null;
	         
	         
	         ContentValues values = new ContentValues();
	         values.put(SONG_ID, contact.getID());
	         values.put(SONG_MABAIHAT, contact.getMabaihat());
	         values.put(SONG_BAIHAT, contact.getBaihat());
	         values.put(SONG_TENBAIHAT, contact.getTenbaihat());
	         values.put(SONG_TACGIA, contact.getTacgia());
	         
	  
	         // updating row
	         return db.update(TABLE_SONG, values,SONG_ID + " = ?",
	                 new String[] { String.valueOf(contact.getID()) });
	     }
	  
	     // Deleting single contact
	     public void deleteContact(Contact contact) {
	         SQLiteDatabase db = this.mSQLiteDatabase;
	         db.delete(TABLE_SONG, SONG_MABAIHAT + " = ?",
	                 new String[] { String.valueOf(contact.getID()) });
	         db.close();
	     }
	     public Cursor searchByInputText(String inputText)  {
	     	DatabaseHelper db = this.mDatabaseHelper;
	         String query = "SELECT docid as rowid," +
	                 SONG_MABAIHAT +SONG_BAIHAT + " from " + TABLE_SONG +
	                 " where " + SONG_MABAIHAT + " MATCH '" + inputText + "or"+ SONG_BAIHAT + " MATCH '" + inputText +"';";
	         //Cursor cursor = db.rawQuery(selectQuery, null);
	         Cursor mCursor = db.rawQuery(query,null);
	   
	         if (mCursor != null) {
	             mCursor.moveToFirst();
	         }
	         return mCursor;
	   
	     }
	   
	     // Getting contacts Count
	     public int getContactsCount() {
	         String countQuery = "SELECT  * FROM " + TABLE_SONG;
	         SQLiteDatabase db = this.mSQLiteDatabase;
	         Cursor cursor = db.rawQuery(countQuery, null);
	         cursor.close();
	  
	         // return count
	         return cursor.getCount();
	     }
	/**
	 * close database
	 */
	public void close() {
		mDatabaseHelper.close();
	}

	/**
	 * insert into database
	 * 
	 * @param a
	 */
	public void insert(String sql) {
		mSQLiteDatabase.execSQL(sql);
	}
}
