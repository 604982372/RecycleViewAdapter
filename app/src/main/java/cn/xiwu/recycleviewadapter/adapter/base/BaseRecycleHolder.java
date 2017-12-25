package cn.xiwu.recycleviewadapter.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;


/**
 * Created by xiwu on 2017/8/1.
 */

public abstract class BaseRecycleHolder extends RecyclerView.ViewHolder
{

    private SparseArray<View> views;

    public BaseRecycleHolder(View view)
    {
        super(view);
        this.views = new SparseArray<>();
        ButterKnife.bind(this, itemView);
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId)
    {
        View view = views.get(viewId);
        if (view == null)
        {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public BaseRecycleHolder setText(int viewId, String text)
    {
        TextView tv = (TextView) this.getView(viewId);
        tv.setText(text);
        return this;
    }

    public BaseRecycleHolder setTextColor(Context mContext, int viewId, int color)
    {
        TextView tv = (TextView) this.getView(viewId);
        tv.setTextColor(mContext.getResources().getColor(color));
        return this;
    }

    public BaseRecycleHolder setOnClickListener(int viewId, View.OnClickListener listener)
    {
        View view = this.getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}
