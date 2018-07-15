package am.aca.todoitem.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import am.aca.todoitem.R;
import am.aca.todoitem.adapter.viewholder.TodoItemViewHolder;
import am.aca.todoitem.model.TodoItem;
import am.aca.todoitem.room.AppDatabase;


public class TodoItemRecyclerAdapter extends RecyclerView.Adapter<TodoItemViewHolder> {
    private TodoItemViewHolder.OnItemClickListener mOnItemClickListener =
            new TodoItemViewHolder.OnItemClickListener() {
                @Override
                public void onItemClick(int adapterPosition) {
                    if (mOnItemSelectedListener != null) {
                        mOnItemSelectedListener.onItemSelected(mTodoItemList.get(adapterPosition));
                    }
                }

                @Override
                public void onRemoveClick(int adapterPosition) {


                    removeItem(adapterPosition);
                }
            };
    //private AdapterView.OnItemClickListener
    public static ArrayList<TodoItem> mTodoItemList;
    private AppDatabase mAppDatabase;
    private OnItemSelectedListener mOnItemSelectedListener;

    public TodoItemRecyclerAdapter(AppDatabase db) {

        mTodoItemList = new ArrayList<>();
        mAppDatabase=db;

    }

    @NonNull
    @Override
    public TodoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TodoItemViewHolder todoItemViewHolder = new TodoItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_todo_item, parent, false));


        todoItemViewHolder.setOnItemClickListener(mOnItemClickListener);
        return todoItemViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull TodoItemViewHolder holder, int position) {


        TodoItem todoItem = mTodoItemList.get(position);
        holder.getTitle().setText(todoItem.getTitle());
        holder.getDescription().setText(todoItem.getDescription());
        holder.getDate().setText(todoItem.getDate().toString());
    }

    @Override
    public int getItemCount() {
        return mTodoItemList.size();
    }

    public void addItem(TodoItem todoItem) {

        mTodoItemList.add(todoItem);
        notifyItemInserted(mTodoItemList.size() - 1);
    }

    private void removeItem(int position) {
        mAppDatabase.mTodoItemDao().delete( mTodoItemList.get(position));

        mTodoItemList.remove(position);
        notifyItemRemoved(position);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        mOnItemSelectedListener = onItemSelectedListener;
    }

    public interface OnItemSelectedListener {
        void onItemSelected(TodoItem todoItem);
    }


}
