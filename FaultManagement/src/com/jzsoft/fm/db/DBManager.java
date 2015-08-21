package com.jzsoft.fm.db;

import java.util.ArrayList;
import java.util.List;

import com.jzsoft.fm.conf.Config;
import com.jzsoft.fm.model.Job;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBManager {
	private DatabaseHelper helper;
	private SQLiteDatabase db;

	public DBManager(Context context) {
		Log.d(Config.LOG_TAG, "DBManager --> Constructor");
		helper = new DatabaseHelper(context);
		// 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0,
		// mFactory);
		// 所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
		db = helper.getWritableDatabase();
	}

	public void add(Job job) {
		Log.d(Config.LOG_TAG, "DBManager --> add");
		// 采用事务处理，确保数据完整性
		db.beginTransaction(); // 开始事务
		try {
			db.execSQL(
					"INSERT INTO " + DatabaseHelper.TABLE_NAME
							+ " VALUES(?,?, ?, ?, null,?,?,null,null,null)",
					new Object[] { job.getId(), job.getWorkTicketNumber(),
							job.getJobName(), job.getStartTime(),
							job.getEndMark(), job.getSyncMark() });
			db.setTransactionSuccessful(); // 设置事务成功完成
		} finally {
			db.endTransaction(); // 结束事务
		}
	}

	// public void add(List<Job> jobs) {
	// Log.d(Config.LOG_TAG, "DBManager --> add");
	// // 采用事务处理，确保数据完整性
	// db.beginTransaction(); // 开始事务
	// try {
	// for (Job job : jobs) {
	// db.execSQL("INSERT INTO " + DatabaseHelper.TABLE_NAME
	// + " VALUES(null, ?, ?, ?,?)", new Object[] { job.name,
	// job.age, job.info });
	// // 带两个参数的execSQL()方法，采用占位符参数？，把参数值放在后面，顺序对应
	// // 一个参数的execSQL()方法中，用户输入特殊字符时需要转义
	// // 使用占位符有效区分了这种情况
	// }
	// db.setTransactionSuccessful(); // 设置事务成功完成
	// } finally {
	// db.endTransaction(); // 结束事务
	// }
	// }
	// /**
	// *
	// */
	public void update(Job job) {
		Log.d(Config.LOG_TAG, "DBManager --> updateAge");
		ContentValues cv = new ContentValues();
		cv.put("jobName", job.getJobName());
		cv.put("endTime", job.getEndTime());
		cv.put("endMark", job.getEndMark());
		cv.put("syncMark", job.getSyncMark());
		cv.put("photo", job.getPhoto());
		cv.put("video", job.getVideo());
		cv.put("jobRecord", job.getJobRecord());
		db.update(DatabaseHelper.TABLE_NAME, cv, "_id = ?",
				new String[] { job.getId() });
	}

	//

	public void deleteOldJob(String _id) {
		Log.d(Config.LOG_TAG, "DBManager --> deleteOldPerson");
		db.delete(DatabaseHelper.TABLE_NAME, "_id = ?", new String[] { _id });
	}

	/**
	 *
	 */
	public List<Job> query(String workTicketNumber) {
		Log.d(Config.LOG_TAG, "DBManager --> query");
		ArrayList<Job> jobs = new ArrayList<Job>();
		Cursor cursor = queryTheCursor(workTicketNumber);
		while (cursor.moveToNext()) {
			Job job = new Job();
			job.setId(cursor.getString(cursor.getColumnIndex("_id")));
			job.setJobName(cursor.getString(cursor.getColumnIndex("jobName")));
			job.setStartTime(cursor.getLong(cursor.getColumnIndex("startTime")));
			job.setEndTime(cursor.getLong(cursor.getColumnIndex("endTime")));
			job.setEndMark(cursor.getInt(cursor.getColumnIndex("endMark")));
			job.setSyncMark(cursor.getInt(cursor.getColumnIndex("syncMark")));
			job.setPhoto(cursor.getString(cursor.getColumnIndex("photo")));
			job.setVideo(cursor.getString(cursor.getColumnIndex("video")));
			job.setJobRecord(cursor.getString(cursor
					.getColumnIndex("jobRecord")));
			jobs.add(job);
		}
		cursor.close();
		return jobs;
	}

	/**
	 * 
	 * @return Cursor
	 */
	public Cursor queryTheCursor(String workTicketNumber) {
		Log.d(Config.LOG_TAG, "DBManager --> queryTheCursor");
		Cursor c = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME
				+ " where workTicketNumber = ?",
				new String[] { workTicketNumber });
		return c;
	}

	/**
	 * close database
	 */
	public void closeDB() {
		Log.d(Config.LOG_TAG, "DBManager --> closeDB");
		// 释放数据库资源
		db.close();
	}

}