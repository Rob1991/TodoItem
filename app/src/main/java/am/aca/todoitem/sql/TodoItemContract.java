package am.aca.todoitem.sql;

import android.provider.BaseColumns;

public class TodoItemContract {
    public static final class TodoItemEntry implements BaseColumns {

        public static final String TABLE_NAME = "todoitem";
        public static final String COLUMN_TODOITEM_ID = "todoitem_id";
        public static final String COLUMN_TODOITEM_TITLE = "todoitem_title";
        public static final String COLUMN_TODOITEM_DESCRIPTION = "todoitem_description";
        public static final String COLUMN_TODOITEM_DATE = "todoitem_date";

    }
}
