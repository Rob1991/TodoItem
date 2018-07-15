package am.aca.todoitem.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import am.aca.todoitem.R;
import am.aca.todoitem.model.TodoItem;
import am.aca.todoitem.room.AppDatabase;
import am.aca.todoitem.sql.DatabaseHelper;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TodoItemFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TodoItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodoItemFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static TodoItem todoItem=new TodoItem(1,"llk00", "lklkl","25/05/2015");
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private DatabaseHelper databaseHelper;
    public TodoItemFragment() {
        // Required empty public constructor
    }




    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TodoItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TodoItemFragment newInstance(String param1, String param2) {
        TodoItemFragment fragment = new TodoItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fragment_todo_item_btnSave:
                    submit();
                    break;

            }
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_item, container, false);
    }
    private EditText mTitleInput;
    private EditText mDescriptionInput;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        databaseHelper = new DatabaseHelper(getActivity());
        mTitleInput=view.findViewById(R.id.fragment_todo_item_editTextTitle);

                mDescriptionInput=view.findViewById(R.id.fragment_todo_item_editTextDescription);
        Button button =view.findViewById(R.id.fragment_todo_item_btnSave);
if(button!=null){
        button.setOnClickListener(mOnClickListener);}
    }
    public void submit(){
        if(mListener!=null){
            mListener.onItemCreated(createTodoItemFromInput());
        }
    }
    private TodoItem mTodoItem;

    private TextView mDateLabel;
    private CheckBox mReminderCheckBox;
    private CheckBox mRepeatCheckBox;
    private RadioGroup mRepeatRadioGroup;
    private TextView mPriorityLabel;
    private AppDatabase mAppDatabase;
    private TodoItem createTodoItemFromInput() {
        if (mTodoItem == null) {
            // If item is newly created initialize with uuid
            mTodoItem = new TodoItem();
          //  mTodoItem.setId(UUID.randomUUID().toString());
        }
        mTodoItem.setTitle(mTitleInput.getText().toString());
        mTodoItem.setDescription(mDescriptionInput.getText().toString());
        mTodoItem.setDate("25/05/2015");
        mAppDatabase = AppDatabase.getAppDatabase(getActivity());
        addTodoItem(mAppDatabase,mTodoItem);
        return mTodoItem;
    }
    public void setOnInteractionListener(OnFragmentInteractionListener listener) {
        mListener = listener;
    }
    public interface OnFragmentInteractionListener {
        void onItemCreated(TodoItem todoItem);
        void onItemChanged(TodoItem todoItem);
    }
    private static TodoItem addTodoItem(final AppDatabase db, TodoItem todoItem) {
if(db!=null){
        db.mTodoItemDao().insertAll(todoItem);}
        return todoItem;
    }

}
