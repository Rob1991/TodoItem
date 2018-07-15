package am.aca.todoitem.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import am.aca.todoitem.R;

public class TodoItemViewHolder extends RecyclerView.ViewHolder {
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.content_todo_item_remove:
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onRemoveClick(getAdapterPosition());
                    }
                    break;
                default: // Left for root view
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(getAdapterPosition());
                    }
            }
        }
    };
    private TextView mTitle;
    private TextView mDescription;
    private TextView mDate;

    public TodoItemViewHolder(View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.content_todo_item_title);
        mDescription = itemView.findViewById(R.id.content_todo_item_description);
        mDate = itemView.findViewById(R.id.content_todo_item_date);
        itemView.findViewById(R.id.content_todo_item_remove).setOnClickListener(mOnClickListener);
        itemView.setOnClickListener(mOnClickListener);
    }

    public TextView getTitle() {
        return mTitle;
    }

    public void setTitle(TextView title) {
        mTitle = title;
    }

    public TextView getDescription() {
        return mDescription;
    }

    public void setDescription(TextView description) {
        mDescription = description;
    }

    public TextView getDate() {
        return mDate;
    }

    public void setDate(TextView date) {
        mDate = date;
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int adapterPosition);

        void onRemoveClick(int adapterPosition);
    }
}
