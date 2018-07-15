package am.aca.todoitem.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import am.aca.todoitem.R;
import am.aca.todoitem.adapter.TodoItemRecyclerAdapter;
import am.aca.todoitem.model.TodoItem;
import am.aca.todoitem.room.AppDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TodoItemsListFragmnet.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TodoItemsListFragmnet#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodoItemsListFragmnet extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    public TodoItemsListFragmnet() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TodoItemsListFragmnet.
     */
    // TODO: Rename and change types and number of parameters
    public static TodoItemsListFragmnet newInstance() {
        TodoItemsListFragmnet fragment = new TodoItemsListFragmnet();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }
    private TodoItemRecyclerAdapter.OnItemSelectedListener mOnItemSelectedListener=new TodoItemRecyclerAdapter.OnItemSelectedListener() {
        @Override
        public void onItemSelected(TodoItem todoItem) {

        }
    };
    private TodoItemRecyclerAdapter mTodoItemRecyclerAdapter;
    private AppDatabase mAppDatabase;
    private void init(View root) {
        mAppDatabase=AppDatabase.getAppDatabase(root.getContext());
        mTodoItemRecyclerAdapter = new TodoItemRecyclerAdapter(mAppDatabase);
        mTodoItemRecyclerAdapter.setOnItemSelectedListener(mOnItemSelectedListener);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerTodoItemList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mTodoItemRecyclerAdapter);
    }
    public void addTodoItem(TodoItem todoItem) {
        mTodoItemRecyclerAdapter.addItem(todoItem);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_todo_items_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }


}
