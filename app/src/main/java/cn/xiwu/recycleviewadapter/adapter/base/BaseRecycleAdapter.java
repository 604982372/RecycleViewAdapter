public abstract class BaseRecycleAdapter<K, T extends BaseRecycleHolder> extends RecyclerView.Adapter<T>
{
    protected T holder;
    protected Context mContext;
    private LayoutInflater mInflater;
    protected T obj;
    protected List<K> mDataList = new ArrayList<>();
    protected MyItemClickListener mItemClickListener;

    public BaseRecycleAdapter(Context context)
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
        //放在try外面，防止xml文件错误被捕获,而获取不到精确的报错位置
        View itemView = mInflater.inflate(getLayoutId(), parent, false);
        try
        {
            Class<T> clazz = getTClass();
            Constructor<T> constructor = clazz.getConstructor(View.class);
            T t = constructor.newInstance(itemView);
            holder = t;
            holder.setListener(mItemClickListener);
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
        mDataList.clear();
        mDataList.addAll(list);
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

    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener)
    {
        mItemClickListener = listener;
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
