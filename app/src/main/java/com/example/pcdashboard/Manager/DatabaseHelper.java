package com.example.pcdashboard.Manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pcdashboard.Model.ClassPost;
import com.example.pcdashboard.Model.DepartmentPost;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DATABASE";
    public static final String TABLE_DEPARTMENT = "DEPARTMENT";
    public static final String TABLE_CLASS = "CLASS";
    public static final String TABLE_CHAT = "CHAT";
    public static final String TABLE_STUDENT = "STUDENT";
    public static final String TABLE_TEACHER = "TEACHER";
    public static final String TABLE_EXAM = "EXAM";
    public static final String TABLE_SCHEDULE = "SCHEDULE";

    public static final String COLUMN_POSTID = "POSTID";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_TIME = "TIME";
    public static final String COLUMN_CONTENT = "CONTENT";
    public static final String COLUMN_IMAGE = "IMAGE";
    public static final String COLUMN_AVATAR = "AVATAR";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_USERID = "USERID";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static DatabaseHelper databaseHelper;

    public static DatabaseHelper getInstance(Context context) {
        if (databaseHelper == null)
            databaseHelper = new DatabaseHelper(context);
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_DEPARTMENT = "CREATE TABLE " + TABLE_DEPARTMENT + "(" + COLUMN_POSTID + " TEXT," + COLUMN_TITLE + " TEXT," + COLUMN_CONTENT + " TEXT," + COLUMN_IMAGE + " TEXT," + COLUMN_TIME + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_DEPARTMENT);
        String CREATE_TABLE_CLASS = "CREATE TABLE " + TABLE_CLASS + "(" + COLUMN_POSTID + " TEXT," + COLUMN_CONTENT + " TEXT," + COLUMN_IMAGE + " TEXT," + COLUMN_TIME + " TEXT," + COLUMN_USERID + " TEXT," + COLUMN_NAME + " TEXT," + COLUMN_AVATAR + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_CLASS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPARTMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS);
        onCreate(db);
    }

    //add objects to database
    public void insertDepartmentPost(DepartmentPost departmentPost) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_POSTID, departmentPost.getId());
        values.put(COLUMN_TITLE, departmentPost.getTitle());
        values.put(COLUMN_CONTENT, departmentPost.getContent());
        values.put(COLUMN_IMAGE, departmentPost.getImage());
        values.put(COLUMN_TIME, departmentPost.getTime());
        db.insert(TABLE_DEPARTMENT, null, values);
    }

    public void insertClassPost(ClassPost classPost) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_POSTID, classPost.getId());
        values.put(COLUMN_CONTENT, classPost.getContent());
        values.put(COLUMN_IMAGE, classPost.getImage());
        values.put(COLUMN_TIME, classPost.getTime());
        values.put(COLUMN_USERID, classPost.getTime());
        values.put(COLUMN_NAME, classPost.getUserName());
        values.put(COLUMN_AVATAR, classPost.getUserAvatar());
        db.insert(TABLE_CLASS, null, values);
    }

    public ArrayList<DepartmentPost> loadDepartmentPosts() {
        String query = "Select * FROM " + TABLE_DEPARTMENT;
        ArrayList<DepartmentPost> departmentPosts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            DepartmentPost departmentPost = new DepartmentPost();
            departmentPost.setId(cursor.getString(0));
            departmentPost.setTitle(cursor.getString(1));
            departmentPost.setContent(cursor.getString(2));
            departmentPost.setImage(cursor.getString(3));
            departmentPost.setTime(cursor.getString(4));
            departmentPosts.add(departmentPost);
        }
        cursor.close();
        return departmentPosts;
    }

    public ArrayList<ClassPost> loadClassPosts() {
        String query = "Select * FROM " + TABLE_CLASS;
        ArrayList<ClassPost> classPosts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            ClassPost classPost = new ClassPost();
            classPost.setId(cursor.getString(0));
            classPost.setContent(cursor.getString(1));
            classPost.setImage(cursor.getString(2));
            classPost.setTime(cursor.getString(3));
            classPost.setUserId(cursor.getString(4));
            classPost.setUserName(cursor.getString(5));
            classPost.setUserAvatar(cursor.getString(6));
            classPosts.add(classPost);
        }
        cursor.close();
        return classPosts;
    }

    public void deleteDepartmentPosts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DEPARTMENT, null, null);
    }

    public void deleteClassPosts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CLASS, null, null);
    }
    //delete all database
//    public void deleteDrinkDatabase() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_DRINK, null, null);
//    }
//
//    public void insertFoodDatabase(Food food) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_ID, food.getFoodID());
//        values.put(KEY_NAME, food.getFoodName());
//        values.put(KEY_PRICE, food.getFoodPrice());
//        values.put(KEY_IMAGE, food.getFoodImage());
//        db.insert(TABLE_FOOD, null, values);
//    }
//
//    //get all objects
//    public ArrayList<Food> loadFoodDatabase() {
//        String query = "Select * FROM " + TABLE_FOOD;
//        ArrayList<Food> foods = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//        while (cursor.moveToNext()) {
//            Food food = new Food();
//            food.setFoodID(cursor.getInt(0));
//            food.setFoodName(cursor.getString(1));
//            food.setFoodPrice(cursor.getDouble(2));
//            food.setFoodImage(cursor.getString(3));
//            foods.add(food);
//        }
//        cursor.close();
//        return foods;
//    }
//
//    //delete all database
//    public void deleteFoodDatabase() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_FOOD, null, null);
//    }
}