package cn.xiwu.recycleviewadapter.bean;

/**
 * Created by zuzu on 2017/11/30.
 */

public class Teacher
{
    private String name;
    private String id;
    private String sub;

    public Teacher(String name, String id, String sub)
    {
        this.name = name;
        this.id = id;
        this.sub = sub;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getSub()
    {
        return sub;
    }

    public void setSub(String sub)
    {
        this.sub = sub;
    }
}
