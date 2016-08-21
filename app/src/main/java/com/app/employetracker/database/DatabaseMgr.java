package com.app.employetracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;

import com.app.employetracker.dao.TrackingData;
import com.app.employetracker.utility.Constants;

import org.byteclues.lib.database.SQLiteHelper;
import org.byteclues.lib.logger.Logger;

import java.util.ArrayList;

/**
 * Created by Hitesh kumawat on 21-08-2016.
 */

public class DatabaseMgr {
    public static String TAG = "DatabaseMgr";
    private static SQLiteDatabase sqLiteDb = null;
    private static DatabaseMgr instance = null;
    private static Context context_main;

    public synchronized static DatabaseMgr getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseMgr();
            instance.init(context);
        }
        context_main = context;
        return instance;
    }

    private synchronized static boolean init(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        sqLiteDb = dbHelper.getWritableDatabase();
        return true;
    }

    /**
     * This method is used to insert data in the table.
     *
     * @param tableName
     * @param contentValues
     * @return number of insert value
     */
    public synchronized int insertRows(String tableName, ContentValues[] contentValues) {

        int numberOfRowInsert = 0;
        try {
            getInstance(context_main).sqLiteDb.beginTransaction();
            for (ContentValues contactValue : contentValues) {
                try {
                    if (contactValue == null)
                        return 0;
                    sqLiteDb.insertWithOnConflict(tableName, null, contactValue, SQLiteDatabase.CONFLICT_REPLACE);
                    numberOfRowInsert++;
                } catch (Exception e) {
                    e.printStackTrace();
                    if (Logger.IS_DEBUG) {
                        Logger.error(TAG, "insertRows(): exception [" + e + "] tableName [" + tableName + "] values [" + ((contentValues != null) ? contentValues.toString() : "" + "]"));
                    }
                }
            }
        } catch (Exception e) {
            if (Logger.IS_DEBUG) {
                Logger.error(TAG, "insertRows(): exception ["
                        + e + "] tableName [" + tableName + "] values [" + ((contentValues != null) ? contentValues.toString() : "" + "]"));
                e.printStackTrace();
            }
        } finally {
            if (sqLiteDb != null) {
                sqLiteDb.setTransactionSuccessful();
                sqLiteDb.endTransaction();
            }
        }
        return numberOfRowInsert;
    }


    public synchronized int clearTableData(String tableName) {
        int deletedRow = 0;
        try {
            getInstance(context_main).sqLiteDb.beginTransaction();
            deletedRow = getInstance(context_main).sqLiteDb.delete(tableName, null, null);
            getInstance(context_main).sqLiteDb.delete("SQLITE_SEQUENCE", "NAME = ?", new String[]{tableName});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqLiteDb != null) {
                sqLiteDb.setTransactionSuccessful();
                sqLiteDb.endTransaction();
            }
            return deletedRow;
        }

    }

    public synchronized static int insertRow(String tableName, ContentValues[] contentValues) {

        int retCode = -1;
        try {
            getInstance(context_main).sqLiteDb.beginTransaction();
            for (ContentValues contactValue : contentValues) {
                try {
                    if (contactValue == null)
                        return 0;
                    retCode = (int) sqLiteDb.insertWithOnConflict(
                            tableName, null, contactValue,
                            SQLiteDatabase.CONFLICT_REPLACE);
                } catch (Exception e) {
                    if (Logger.IS_DEBUG) {
                        Logger.error(TAG, "insertRow(): Exception [" + e + "] tableName [" + tableName + "] values [" + ((contentValues != null) ? contentValues.toString() : "") + "]");
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            if (Logger.IS_DEBUG) {
                Logger.error(TAG, "insertRow(): Exception [" + e + "] tableName [" + tableName + "] values [" + ((contentValues != null) ? contentValues.toString() : "") + "]");
                e.printStackTrace();
            }
        } finally {
            if (sqLiteDb != null) {
                sqLiteDb.setTransactionSuccessful();
                sqLiteDb.endTransaction();
            }
        }
        return retCode;
    }


    public static ContentValues[] saveLocationToDB(Context context, Location location) {
        ContentValues[] contentValuesArray = new ContentValues[1];
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("lat", String.valueOf(location.getLatitude()));
            contentValues.put("lng", String.valueOf(location.getLongitude()));
            contentValues.put("time_stamp", String.valueOf(""));
            contentValuesArray[0] = contentValues;
            DatabaseMgr.getInstance(context).insertRows(Constants.TABLE_NAME, contentValuesArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentValuesArray;
    }

    public static ArrayList<TrackingData> getUserLocations() {
        ArrayList<TrackingData> list = null;
        list = new ArrayList<TrackingData>();
        try {
            Cursor cursor = sqLiteDb.query(Constants.TABLE_NAME, null, null, null, null, null, "id", null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    list.add(getTaskLocationData(cursor));
                    cursor.moveToNext();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public static TrackingData getTaskLocationData(Cursor cursor) {
        TrackingData trackingData = new TrackingData();
        try {
            trackingData.lat = cursor.getString(cursor.getColumnIndex("lat"));
            trackingData.lng = cursor.getString(cursor.getColumnIndex("lng"));
            trackingData.timeStamp = cursor.getString(cursor.getColumnIndex("time_stamp"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trackingData;
    }
}
