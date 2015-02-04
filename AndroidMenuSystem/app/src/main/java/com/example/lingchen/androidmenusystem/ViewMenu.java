package com.example.lingchen.androidmenusystem;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ViewMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_menu);

        MenuItemDBHelper dbHelper = new MenuItemDBHelper(getApplicationContext());
        SQLiteDatabase read = dbHelper.getReadableDatabase();
        String[] projection = {
                "id",
                "name"
        };


        ListView list = (ListView) findViewById(R.id.listView);

        Cursor c= read.query("items",projection,"",new String[0],null,null,"");

        ArrayList<String> v = new ArrayList<String>();
        while (c.moveToNext()){
            long itemId = c.getLong(0);
            String name = c.getString(1);

            v.add(itemId + ":" + name);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_view_layout,R.id.txtvalue,v.toArray(new String[v.size()]));
        list.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
