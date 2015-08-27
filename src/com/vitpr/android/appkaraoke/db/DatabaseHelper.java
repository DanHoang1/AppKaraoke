package com.vitpr.android.appkaraoke.db;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	/** Tag just for the LogCat window */
	private static String sTAG = "DataBaseHelper";
	/**
	 * destination path (location) of our database on device
	 */
	private static String sDbPath = "";
	/**
	 * Database name
	 */
	private static String sDbName = "song_db.sqlite";
	private SQLiteDatabase mDataBase;
	private final Context mContext;
	
	/**
	 * contructor
	 * 
	 * @param context
	 */
	public DatabaseHelper(Context context) {
		super(context, sDbName, null, 1);// 1? its Database Version
		if (android.os.Build.VERSION.SDK_INT >= 17) {
			sDbPath = context.getApplicationInfo().dataDir + "/databases/";
		} else {
			sDbPath = "/data/data/" + context.getPackageName() + "/databases/";
		}
		this.mContext = context;
	}

	/**
	 * create database
	 * 
	 * @throws IOException
	 *             IOException
	 */
	public void createDataBase() throws IOException {
		// If database not exists copy it from the assets

		boolean mDataBaseExist = checkDataBase();
		if (!mDataBaseExist) {

			mDataBase = this.getReadableDatabase();
			mDataBase.close();
			// this.close();
			try {
				// Copy the database from assests
				copyDataBase();
				Log.e(sTAG, "createDatabase database created");
			} catch (IOException mIOException) {
				throw new Error("ErrorCopyingDataBase");
			}
		}
	}

	/**
	 * Check that the database exists here: /data/data/your package/databases/Da
	 * Name
	 * 
	 * @return true if exists
	 */
	private boolean checkDataBase() {
		SQLiteDatabase checkDB = null;

		try {
			String myPath = sDbPath + sDbName;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);
		} catch (SQLiteException e) {
			// database does't exist yet.
			e.printStackTrace();
		}

		if (checkDB != null) {

			checkDB.close();

		}

		return checkDB != null ? true : false;
	}

	/**
	 * Copy the database from assets
	 * 
	 * @throws IOException
	 *             IOExeption
	 */
	private void copyDataBase() throws IOException {
		InputStream mInput = mContext.getAssets().open(sDbName);
		String outFileName = sDbPath + sDbName;
		OutputStream mOutput = new FileOutputStream(outFileName);
		byte[] mBuffer = new byte[1024];
		int mLength;
		while ((mLength = mInput.read(mBuffer)) > 0) {
			mOutput.write(mBuffer, 0, mLength);
		}
		mOutput.flush();
		mOutput.close();
		mInput.close();

	}

	/**
	 * Open the database, so we can query it
	 * 
	 * @return true if SQLException SQLException
	 */
	public boolean openDataBase() {
		String path = sDbPath + sDbName;
		mDataBase = SQLiteDatabase.openDatabase(path, null,
				SQLiteDatabase.OPEN_READWRITE);
		// mDataBase = SQLiteDatabase.openDatabase(path, null,
		// SQLiteDatabase.NO_LOCALIZED_COLLATORS);
		return mDataBase != null;
	}

	@Override
	public synchronized void close() {
		if (mDataBase != null) {
			mDataBase.close();
		}
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public Cursor rawQuery(String selectQuery, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(String tableSong, String string, String[] strings) {
		// TODO Auto-generated method stub
		
	}

}