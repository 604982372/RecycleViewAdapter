package cn.xiwu.recycleviewadapter.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;


/**
 * Created by zuzu on 2017/8/1.
 */

public abstract class RecycleBaseHolder extends RecyclerView.ViewHolder
{

    private SparseArray<View> views;

    public RecycleBaseHolder(View view)
    {
        super(view);
        this.views = new SparseArray<>();
        ButterKnife.bind(this, itemView);
    }

    public static int getLayoutId()
    {
        return 0;
    }


    /**
     * 得到带构造的类的实例
     *//*
    public static RecycleBaseHolder newInstance(String className, Object... args) throws Exception
    {
        Class newoneClass = Class.forName(className);
        Class[] argsClass = new Class[args.length];
        for (int i = 0, j = args.length; i < j; i++)
        {
            argsClass[i] = args[i].getClass();
        }
        Constructor<RecycleBaseHolder> cons = newoneClass.getConstructor(argsClass);
        return cons.newInstance(args);
    }*/
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

    public RecycleBaseHolder setText(int viewId, String text)
    {
        TextView tv = (TextView) this.getView(viewId);
        tv.setText(text);
        return this;
    }

    public RecycleBaseHolder setTextColor(Context mContext, int viewId, int color)
    {
        TextView tv = (TextView) this.getView(viewId);
        tv.setTextColor(mContext.getResources().getColor(color));
        return this;
    }

    public RecycleBaseHolder setOnClickListener(int viewId, View.OnClickListener listener)
    {
        View view = this.getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}
