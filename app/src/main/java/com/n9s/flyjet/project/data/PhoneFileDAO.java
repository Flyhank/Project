package com.n9s.flyjet.project.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Student on 2018/1/18.
 */

public class PhoneFileDAO
{
    public ArrayList<Phone> mylist;
    Context context;

    public PhoneFileDAO(Context context)
    {
        this.context = context;
        mylist = new ArrayList<>();
    }
    public boolean add(Phone p)
    {
        mylist.add(p);
        saveFile();
        return true;
    }
    private void saveFile()
    {
        File f = new File(context.getFilesDir(), "mydata.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            Gson gson = new Gson();
            String data = gson.toJson(mylist);
            fw.write(data);
            fw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void load()
    {
        File f = new File(context.getFilesDir(), "mydata.txt");
        FileReader fr = null;
        try {
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            Gson gson = new Gson();
            mylist = gson.fromJson(str, new TypeToken<ArrayList<Phone>>(){}.getType());
            br.close();
            fr.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    public ArrayList<Phone> getList()
    {
        load();
        return mylist;
    }
    public Phone getPhone(int id)
    {
        load();
        for (Phone p : mylist)
        {
            if (p.id == id)
            {
                return p;
            }
        }
        return null;
    }
    public boolean update(Phone p)
    {
        load();
        for (Phone t : mylist)
        {
            if (t.id == p.id)
            {
                t.name = p.name;
                t.tel = p.tel;
                saveFile();
                return true;
            }
        }
        return false;
    }

    public boolean delete(int id)
    {
        load();
        for (int i=0;i<mylist.size();i++)
        {
            if (mylist.get(i).id == id)
            {
                mylist.remove(i);
                saveFile();
                return true;
            }
        }
        return false;
    }

}

