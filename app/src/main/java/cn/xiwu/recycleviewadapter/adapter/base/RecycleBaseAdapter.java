package cn.xiwu.recycleviewadapter.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Created by zuzu on 2017/8/1.
 */

public abstract class RecycleBaseAdapter<K, T extends RecycleBaseHolder> extends RecyclerView.Adapter<T>
{
    protected T holder;
    protected Context mContext;
    private LayoutInflater mInflater;
    protected T obj;
    protected List<K> mDataList = new ArrayList<>();

    public RecycleBaseAdapter(Context context)
    {
        mContext = context;
        if (mContext != null)
        {
            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
    }


    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType)
    {
        try
        {
            View itemView = mInflater.inflate(getLayoutId(), parent, false);
            Class<T> clazz = getTClass();
            Constructor<T> constructor = clazz.getConstructor(View.class);
            T t = constructor.newInstance(itemView);
            holder = t;
            return t;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onBindViewHolder(T holder, int position)
    {
        onBindItemHolder(holder, position);
    }

    //局部刷新关键：带payload的这个onBindViewHolder方法必须实现
    @Override
    public void onBindViewHolder(T holder, int position, List<Object> payloads)
    {
        if (this.holder == null)
        {
            this.holder = holder;
        }
        if (payloads.isEmpty())
        {
            onBindViewHolder(holder, position);
        }
        else
        {
            onBindItemHolder(holder, position, payloads);
        }
    }

    public abstract int getLayoutId();

    public abstract void onBindItemHolder(T holder, int position);

    public void onBindItemHolder(T holder, int position, List<Object> payloads)
    {
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public int getItemCount()
    {
        return mDataList != null ? mDataList.size() : 0;
    }

    public List<K> getDataList()
    {
        return mDataList;
    }

    public void setDataList(Collection<K> list)
    {
        this.mDataList.clear();
        this.mDataList.addAll(list);
        notifyDataSetChanged();
    }

    public void addAll(Collection<K> list)
    {
        int lastIndex = this.mDataList.size();
        if (this.mDataList.addAll(list))
        {
            notifyItemRangeInserted(lastIndex, list.size());
        }
    }

    public void remove(int position)
    {
        this.mDataList.remove(position);
        notifyItemRemoved(position);

        if (position != (getDataList().size()))
        { // 如果移除的是最后一个，忽略
            notifyItemRangeChanged(position, this.mDataList.size() - position);
        }
    }

    public void clear()
    {
        mDataList.clear();
        notifyDataSetChanged();
    }

    protected Class<T> getTClass()
    {
        Class<T> entityClass = null;
        Type t = getClass().getGenericSuperclass();
        if (t instanceof ParameterizedType)
        {
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            entityClass = (Class<T>) p[1];
        }
        return entityClass;
    }
}

