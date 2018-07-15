package am.aca.todoitem.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import am.aca.todoitem.model.TodoItem;

@Dao
public interface TodoItemDao  {
    @Query("SELECT * FROM todoitem")
    List<TodoItem> getAll();
    @Insert
    void insertAll(TodoItem... todoItems);

    @Delete
    void delete(TodoItem todoitem);
}
