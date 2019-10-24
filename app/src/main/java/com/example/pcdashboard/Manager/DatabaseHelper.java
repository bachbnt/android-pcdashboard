package com.example.pcdashboard.Manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pcdashboard.Model.ChatMessage;
import com.example.pcdashboard.Model.ClassPost;
import com.example.pcdashboard.Model.DepartmentPost;
import com.example.pcdashboard.Model.Exam;
import com.example.pcdashboard.Model.Schedule;
import com.example.pcdashboard.Model.Subject;
import com.example.pcdashboard.Model.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DATABASE";
    private static final String TABLE_DEPARTMENT = "DEPARTMENT";
    private static final String TABLE_CLASS = "CLASS";
    private static final String TABLE_CHAT = "CHAT";
    private static final String TABLE_STUDENT = "STUDENT";
    private static final String TABLE_TEACHER = "TEACHER";
    private static final String TABLE_EXAM = "EXAM";
    private static final String TABLE_SCHEDULE = "SCHEDULE";

    private static final String COLUMN_POSTID = "POSTID";
    private static final String COLUMN_TITLE = "TITLE";
    private static final String COLUMN_TIME = "TIME";
    private static final String COLUMN_CONTENT = "CONTENT";
    private static final String COLUMN_IMAGE = "IMAGE";
    private static final String COLUMN_AVATAR = "AVATAR";
    private static final String COLUMN_TEACHER = "TEACHER";
    private static final String COLUMN_DAY = "DAY";
    private static final String COLUMN_NAME = "NAME";
    private static final String COLUMN_USERID = "USERID";
    private static final String COLUMN_CLASSID = "CLASSID";
    private static final String COLUMN_EMAIL = "EMAIL";
    private static final String COLUMN_PHONE = "PHONE";
    private static final String COLUMN_PLACE = "PLACE";
    private static final String COLUMN_SCORE = "SCORE";

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
        String CREATE_TABLE_CHAT = "CREATE TABLE " + TABLE_CHAT + "(" + COLUMN_CONTENT + " TEXT," + COLUMN_TIME + " TEXT," + COLUMN_USERID + " TEXT," + COLUMN_NAME + " TEXT," + COLUMN_AVATAR + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_CHAT);
        String CREATE_TABLE_STUDENT = "CREATE TABLE " + TABLE_STUDENT + "(" + COLUMN_USERID + " TEXT," + COLUMN_NAME + " TEXT," + COLUMN_AVATAR + " TEXT," + COLUMN_CLASSID + " TEXT," + COLUMN_EMAIL + " TEXT," + COLUMN_PHONE + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_STUDENT);
        String CREATE_TABLE_SCHEDULE = "CREATE TABLE " + TABLE_SCHEDULE + "(" + COLUMN_NAME + " TEXT," + COLUMN_TIME + " TEXT," + COLUMN_TEACHER + " TEXT," + COLUMN_DAY + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_SCHEDULE);
        String CREATE_TABLE_EXAM = "CREATE TABLE " + TABLE_EXAM + "(" + COLUMN_NAME + " TEXT," + COLUMN_TIME + " TEXT," + COLUMN_PLACE + " TEXT," + COLUMN_SCORE + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_EXAM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEPARTMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEDULE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXAM);
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

    public void deleteDepartmentPosts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DEPARTMENT, null, null);
    }

    public void insertClassPost(ClassPost classPost) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_POSTID, classPost.getId());
        values.put(COLUMN_CONTENT, classPost.getContent());
        values.put(COLUMN_IMAGE, classPost.getImage());
        values.put(COLUMN_TIME, classPost.getTime());
        values.put(COLUMN_USERID, classPost.getUserId());
        values.put(COLUMN_NAME, classPost.getUserName());
        values.put(COLUMN_AVATAR, classPost.getUserAvatar());
        db.insert(TABLE_CLASS, null, values);
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

    public void deleteClassPosts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CLASS, null, null);
    }

    public void deleteChatMessages() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CHAT, null, null);
    }

    public void insertChatMessage(ChatMessage chatMessage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CONTENT, chatMessage.getContent());
        values.put(COLUMN_TIME, chatMessage.getTime());
        values.put(COLUMN_USERID, chatMessage.getUserId());
        values.put(COLUMN_NAME, chatMessage.getUserName());
        values.put(COLUMN_AVATAR, chatMessage.getUserAvatar());
        db.insert(TABLE_CHAT, null, values);
    }

    public ArrayList<ChatMessage> loadChatMessages() {
        String query = "Select * FROM " + TABLE_CHAT;
        ArrayList<ChatMessage> chatMessages = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setContent(cursor.getString(0));
            chatMessage.setTime(cursor.getString(1));
            chatMessage.setUserId(cursor.getString(2));
            chatMessage.setUserName(cursor.getString(3));
            chatMessage.setUserAvatar(cursor.getString(4));
            chatMessages.add(chatMessage);
        }
        cursor.close();
        return chatMessages;
    }

    public void insertUserStudent(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERID, user.getId());
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_AVATAR, user.getAvatar());
        values.put(COLUMN_CLASSID, user.getClassId());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PHONE, user.getPhone());
        db.insert(TABLE_STUDENT, null, values);
    }

    public ArrayList<User> loadUserStudents() {
        String query = "Select * FROM " + TABLE_STUDENT;
        ArrayList<User> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getString(0));
            user.setName(cursor.getString(1));
            user.setAvatar(cursor.getString(2));
            user.setClassId(cursor.getString(3));
            user.setEmail(cursor.getString(4));
            user.setPhone(cursor.getString(5));
            users.add(user);
        }
        cursor.close();
        return users;
    }

    public void deleteUserStudents() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENT, null, null);
    }

    public void insertSchedule(Schedule schedule) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        for (Subject subject:schedule.getSubjects()) {
            values.put(COLUMN_NAME, subject.getName());
            values.put(COLUMN_TIME, subject.getTime());
            values.put(COLUMN_TEACHER, subject.getTeacher());
            values.put(COLUMN_DAY, subject.getDay());
            db.insert(TABLE_SCHEDULE, null, values);
        }
    }

    public ArrayList<Schedule> loadSchedules() {
        List<String> days = Arrays.asList("Thứ hai","Thứ ba","Thứ tư","Thứ năm","Thứ sáu","Thứ bảy");
        String query = "Select * FROM " + TABLE_SCHEDULE;
        ArrayList<Subject> allSubjects = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Subject subject = new Subject();
            subject.setName(cursor.getString(0));
            subject.setTime(cursor.getString(1));
            subject.setTeacher(cursor.getString(2));
            subject.setDay(cursor.getString(3));
            allSubjects.add(subject);
        }
        ArrayList<Schedule> schedules = new ArrayList<>();
        for (String day : days) {
            ArrayList<Subject> currentSubjects = new ArrayList<>();
            for (Subject subject : allSubjects) {
                if (subject.getDay().equals(day)) {
                    currentSubjects.add(subject);
                }
            }
            schedules.add(new Schedule(day, currentSubjects));
        }
        cursor.close();
        return schedules;
    }

    public void deleteSchedules() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SCHEDULE, null, null);
    }

    public void insertExam(Exam exam) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, exam.getSubjectName());
        values.put(COLUMN_TIME, exam.getTime());
        values.put(COLUMN_PLACE, exam.getPlace());
        values.put(COLUMN_SCORE, exam.getScore());
        db.insert(TABLE_EXAM, null, values);
    }

    public ArrayList<Exam> loadExams() {
        String query = "Select * FROM " + TABLE_EXAM;
        ArrayList<Exam> exams = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Exam exam = new Exam();
            exam.setSubjectName(cursor.getString(0));
            exam.setTime(cursor.getString(1));
            exam.setPlace(cursor.getString(2));
            exam.setScore(cursor.getDouble(3));
            exams.add(exam);
        }
        cursor.close();
        return exams;
    }

    public void deleteExams() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EXAM, null, null);
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