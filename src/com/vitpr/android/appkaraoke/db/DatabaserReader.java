package com.vitpr.android.appkaraoke.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaserReader {
	private DatabaseHelper mDatabaseHelper;
	private SQLiteDatabase mSQLDatabase;

	/**
	 * 
	 * @param context
	 */
	public DatabaserReader(Context context) {
		this.mDatabaseHelper = new DatabaseHelper(context);
		// this.mContext = context;
	}


	/**
	 * open database to read
	 */
	public void open() {
		try {
			mDatabaseHelper.createDataBase();
			mDatabaseHelper.openDataBase();
			mDatabaseHelper.close();
			mSQLDatabase = mDatabaseHelper.getReadableDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * close database
	 */
	public void close() {
		mDatabaseHelper.close();
	}

	/**
	 * get all data
	 * 
	 * @param sql
	 *            string query
	 * @return cursor
	 */
	public Cursor getDatas(String sql) {
		try {
			Cursor cursor = mSQLDatabase.rawQuery(sql, null);
			if (cursor != null) {
				cursor.moveToNext();
			}
			return cursor;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}




	
  
}
