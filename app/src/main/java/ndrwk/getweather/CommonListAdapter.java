package ndrwk.getweather;

/**
 * Created by drew on 12.02.16.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;


abstract public class CommonListAdapter extends RecyclerView.Adapter<CommonListAdapter.CommonListViewHolder>{

    public interface IOnListItemClick {
        void onClick(int pos);
        void onLongClick(int pos);
    }

    public interface IOnViewHolderClick {
        void onListItemClick(View v, int position);
        void onListItemLongClick(View v, int position);
    }

    private IOnListItemClick adapterListener;
    public Context context;

    public IOnViewHolderClick viewHolderClickCallback = new IOnViewHolderClick() {
        @Override
        public void onListItemClick(View v, int position) {
            adapterListener.onClick(position);
        }

        @Override
        public void onListItemLongClick(View v, int position) {
            adapterListener.onLongClick(position);

        }
    };

    public CommonListAdapter(Context cntxt, IOnListItemClick callback) {
        adapterListener = callback;
        context = cntxt;
    }

    abstract public static class CommonListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public int position;
        public IOnViewHolderClick clickListener;

        public CommonListViewHolder(View v, IOnViewHolderClick listener) {
            super(v);
            clickListener = listener;
            findControls(v);
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
        }

        abstract void findControls (View v);

        @Override
        public void onClick(View v) {
            clickListener.onListItemClick(v, position);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onListItemLongClick(v, position);
            return true;
        }

    }
}