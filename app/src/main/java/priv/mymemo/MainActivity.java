package priv.mymemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    private String[] stringArray = new String[100];
    int count = 0;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });

        final ListView myListView = (ListView) findViewById(android.R.id.list);
        //String[] stringList = res.getStringArray(R.array.listArray);
        String[] stringList = loadArray("myMemo", this);
        for (int i = 0; i < stringList.length; i++) {
            stringArray[i] = stringList[i];
            count++;
        }
        loadList();
    }


    void loadList() {
        final ListView myListView = (ListView) findViewById(android.R.id.list);
        ArrayList<String> myStringArray1 = new ArrayList<String>();
        String[] stringList = loadArray("myMemo", this);
        count = stringList.length;
        for (int i = 0; i < stringList.length; i++) {
            myStringArray1.add(stringList[i]);
            stringArray[i] = stringList[i];
        }

        adapter = new CustomAdapter(this, R.layout.row, myStringArray1);
        myListView.setAdapter(adapter);

    }

    public void addItem() {
        NewMemoFragment dialog = new NewMemoFragment();
        dialog.show(getFragmentManager(), "newmemofragment");
    }

    public void delete(int delete) {
        if (delete == count - 1) {
            stringArray[delete] = null;
        } else {


            for (int i = delete; i < count; i++) {
                stringArray[i] = stringArray[i + 1];
                stringArray[count] = null;
            }
        }
        count--;
        saveArray(stringArray, "myMemo", this);
        loadList();

    }


    public void save(String s) {
        stringArray[count] = s;
        count++;
        saveArray(stringArray, "myMemo", this);
        loadList();
    }

    public boolean saveArray(String[] array, String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("myMemoPrefereance", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName + "_size", count);
        for (int i = 0; i < array.length; i++)
            editor.putString(arrayName + "_" + i, array[i]);
        return editor.commit();
    }

    public String[] loadArray(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("myMemoPrefereance", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        String array[] = new String[size];
        for (int i = 0; i < size; i++)
            array[i] = prefs.getString(arrayName + "_" + i, null);
        return array;
    }

}
