package am.aca.todoitem.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import am.aca.todoitem.model.TodoItem;

public class DatabaseHelper extends SQLiteOpenHelper {
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "TodoItemManager.db";




    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TODOITEMS_TABLE = "CREATE TABLE " + TodoItemContract.TodoItemEntry.TABLE_NAME + " (" +
                TodoItemContract.TodoItemEntry._ID + " INTEGER PRIMARY KEY," +
                TodoItemContract.TodoItemEntry.COLUMN_TODOITEM_TITLE + " TEXT NOT NULL, " +
                TodoItemContract.TodoItemEntry.COLUMN_TODOITEM_DESCRIPTION + " TEXT NOT NULL, " +
                TodoItemContract.TodoItemEntry.COLUMN_TODOITEM_DATE + " TEXT NOT NULL " +
                "); ";

        db.execSQL(SQL_CREATE_TODOITEMS_TABLE);

    }

    private String DROP_BENEFICIARY_TABLE = "DROP TABLE IF EXISTS " + TodoItemContract.TodoItemEntry.TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //---opens the database---
    public DatabaseHelper open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }


    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db1, int oldVersion, int newVersion) {

        //Drop User Table if exist

        db1.execSQL(DROP_BENEFICIARY_TABLE);

        // Create tables again
        onCreate(db1);

    }


    //Method to create beneficiary records

    public void addTodoItem(TodoItem todoItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TodoItemContract.TodoItemEntry._ID, todoItem.getId());
        values.put(TodoItemContract.TodoItemEntry.COLUMN_TODOITEM_TITLE, todoItem.getTitle());
        values.put(TodoItemContract.TodoItemEntry.COLUMN_TODOITEM_DESCRIPTION, todoItem.getDescription());
        values.put(TodoItemContract.TodoItemEntry.COLUMN_TODOITEM_DATE, todoItem.getDate());


        db.insert(TodoItemContract.TodoItemEntry.TABLE_NAME, null, values);
        db.close();
    }

    public List<TodoItem> getAllTodoItems() {
        // array of columns to fetch
        String[] columns = {
                TodoItemContract.TodoItemEntry._ID,
                TodoItemContract.TodoItemEntry.COLUMN_TODOITEM_TITLE,
                TodoItemContract.TodoItemEntry.COLUMN_TODOITEM_DESCRIPTION,
                TodoItemContract.TodoItemEntry.COLUMN_TODOITEM_DATE,

        };
        // sorting orders
        String sortOrder =
                TodoItemContract.TodoItemEntry.COLUMN_TODOITEM_TITLE + " ASC";
        List<TodoItem> todoItems = new ArrayList<TodoItem>();

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(TodoItemContract.TodoItemEntry.TABLE_NAME, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TodoItem todoItem = new TodoItem();
                todoItem.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(TodoItemContract.TodoItemEntry._ID))));
                todoItem.setTitle(cursor.getString(cursor.getColumnIndex(TodoItemContract.TodoItemEntry.COLUMN_TODOITEM_TITLE)));
                todoItem.setDescription(cursor.getString(cursor.getColumnIndex(TodoItemContract.TodoItemEntry.COLUMN_TODOITEM_DESCRIPTION)));
                todoItem.setDate(cursor.getString(cursor.getColumnIndex(TodoItemContract.TodoItemEntry.COLUMN_TODOITEM_DATE)));
                // Adding user record to list
                todoItems.add(todoItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return todoItems;
    }
}
