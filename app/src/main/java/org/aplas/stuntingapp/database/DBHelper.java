package org.aplas.stuntingapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.aplas.stuntingapp.model.User;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Stunting_app.db";
    private static final String TABLE_NAME = "user";

    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_NAME = "user_nama";
    public static final String COLUMN_USER_ADDRESS = "user_alamat";
    public static final String COLUMN_USER_TELEPHONE = "user_telp";
    public static final String COLUMN_USER_EMAIL = "user_email";
    public static final String COLUMN_USER_PASSWORD = "user_pass";

//    private String createUserTable = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//            + COLUMN_USER_NAME + "TEXT," + COLUMN_USER_ADDRESS + "TEXT," + COLUMN_USER_TELEPHONE + "TEXT," + COLUMN_USER_EMAIL + "TEXT,"
//            + COLUMN_USER_PASSWORD + "TEXT" + ")";

    private String dropUserTable = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_USER_NAME + " TEXT," + COLUMN_USER_ADDRESS + " TEXT," + COLUMN_USER_TELEPHONE + " TEXT," + COLUMN_USER_EMAIL + " TEXT,"
                + COLUMN_USER_PASSWORD + " TEXT" + ")";
        db.execSQL(createUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(dropUserTable);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getNama());
        values.put(COLUMN_USER_ADDRESS, user.getAlamat());
        values.put(COLUMN_USER_TELEPHONE, user.getTelp());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getNama());
        values.put(COLUMN_USER_ADDRESS, user.getAlamat());
        values.put(COLUMN_USER_TELEPHONE, user.getTelp());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        db.update(TABLE_NAME, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public boolean checkUser(String email) {
        String[] columns = {COLUMN_USER_ID};
        SQLiteDatabase db = this.getReadableDatabase();

        String select = COLUMN_USER_EMAIL + " = ?";

        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_NAME,
                columns,
                select,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkUser(String email, String pass) {
        String[] columns = {COLUMN_USER_EMAIL,
                            COLUMN_USER_PASSWORD,
        };
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        String[] selectionArgs = {email, pass};

        Cursor cursor = db.query(TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        return cursorCount > 0;
    }

//    public User authenticate(User user){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query(TABLE_NAME,
//                new String[]{COLUMN_USER_ID, COLUMN_USER_NAMA,
//                         COLUMN_USER_ALAMAT, COLUMN_USER_TELP,
//                        COLUMN_USER_EMAIL, COLUMN_USER_PASSWORD}, COLUMN_USER_EMAIL + " =?",
//                new String[]{user.getEmail(), null, null, null, null, null})
//    }

    public User getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { COLUMN_USER_ID,
                        COLUMN_USER_NAME, COLUMN_USER_ADDRESS, COLUMN_USER_TELEPHONE,
                COLUMN_USER_EMAIL, COLUMN_USER_PASSWORD}, COLUMN_USER_EMAIL + "=?",
                new String[] { String.valueOf(email) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(cursor.getString(0),
        cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4));
        // return contact
        return user;
    }


    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM User ", null);
        return cursor;
    }
}
