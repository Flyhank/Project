package com.n9s.flyjet.project.data;

import java.util.ArrayList;

/**
 * Created by Flyha on 2018/1/29.
 */

public class PhoneTelDAO
{
    public ArrayList<Phone> mylist;

    public PhoneTelDAO()
    {
        mylist = new ArrayList<>();
    }

    public boolean add(Phone p)   //新增資料, s:變數
    {
        mylist.add(p);
        return true;
    }
    public ArrayList<Phone> getList() //取出整筆資料
    {
        return mylist;
    }

    public Phone getPhone(int id)   //取出id資料
    {
        for (Phone p : mylist)  //for_each, 從ARRAY內拉出每筆資料
        {
            if (p.id == id)         //用id比對,再傳回整組資料
            {
                return p;
            }
        }
        return null;
    }

    public boolean update(Phone p)
    {
        for (Phone t : mylist)
        {
            if(t.id == p.id)    //用id比對,再做update
            {
                t.name = p.name;
                t.tel = p.tel;
                return true;    //如果動作完成,回傳true
            }
        }
        return false;
    }

    public boolean delete(int id)
    {
        for (int i=0; i<mylist.size(); i++)
        {
            if (mylist.get(i).id ==id)  //用id比對,再做delete
            {
                mylist.remove(i);
                return true;
            }
        }
        return false;
    }
}
