package am.aca.todoitem.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;

import am.aca.todoitem.R;
import am.aca.todoitem.adapter.TodoItemRecyclerAdapter;
import am.aca.todoitem.fragment.TodoItemFragment;
import am.aca.todoitem.fragment.TodoItemsListFragmnet;
import am.aca.todoitem.model.TodoItem;
import am.aca.todoitem.room.AppDatabase;
import am.aca.todoitem.sql.DatabaseHelper;

import static am.aca.todoitem.adapter.TodoItemRecyclerAdapter.mTodoItemList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab:
                    openAddTodoItem();
                    break;
            }
        }
    };
    private DatabaseHelper mDatabaseHelper;
    private TodoItemRecyclerAdapter mTodoItemRecyclerAdapter;
    private TodoItemsListFragmnet mTodoItemsListFragment;
    private TodoItemFragment.OnFragmentInteractionListener mInteractionListener = new TodoItemFragment.OnFragmentInteractionListener() {
        @Override
        public void onItemCreated(TodoItem todoItem) {
            delegateItemCreationToFragment(todoItem);
        }

        @Override
        public void onItemChanged(TodoItem todoItem) {

        }
    };

    private void init() {
        mDatabaseHelper = new DatabaseHelper(this);
        mTodoItemRecyclerAdapter = new TodoItemRecyclerAdapter(mAppDatabase);
        mAppDatabase = AppDatabase.getAppDatabase(getApplicationContext());
        getTodoItems(mAppDatabase);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(mOnClickListener);
        mTodoItemsListFragment = (TodoItemsListFragmnet) (getFragmentManager()
                .findFragmentById(R.id.fragment_activity_main));

    }

    private void openAddTodoItem() {
        TodoItemFragment todoItemFragment = TodoItemFragment.newInstance(null, null);
        todoItemFragment.setOnInteractionListener(mInteractionListener);
        openFragmentInContainer(todoItemFragment, true);
    }

    private void delegateItemCreationToFragment(TodoItem todoItem) {
        mTodoItemsListFragment.addTodoItem(todoItem);
        getFragmentManager().popBackStack();
    }

    private void openFragmentInContainer(Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.container_activity_main, fragment);
        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }

        fragmentTransaction.commit();
    }


    private static ArrayList<TodoItem> getTodoItems(final AppDatabase db) {
        ArrayList<TodoItem> todoItems = null;
        if (db != null) {
            mTodoItemList = (ArrayList<TodoItem>) db.mTodoItemDao().getAll();
        }
        return todoItems;
    }

    private AppDatabase mAppDatabase;

}
