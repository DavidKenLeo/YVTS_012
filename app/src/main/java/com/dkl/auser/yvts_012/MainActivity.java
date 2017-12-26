package com.dkl.auser.yvts_012;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {
    MyAdapter adapter;
    private EditText mail;
    private EditText ph;
    private EditText na;
    private RecyclerView rv;
    RecyclerView.LayoutManager lm;
    private GestureDetector touchRv;
    private ArrayList<data> data;
//    private RecyclerView.Adapter adapter;
    private ArrayList<Object> showList;
    private ArrayList<data> mylist;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = (RecyclerView)findViewById(R.id.rv);
        EditText na = (EditText)findViewById(R.id.na);
        EditText ph = (EditText)findViewById(R.id.ph);
        EditText mail = (EditText)findViewById(R.id.mail);
/////////////////////////////////////////////////////////
        rv.setLayoutManager(lm);
        adapter = new MyAdapter(MainActivity.this,data.id);
        rv.setAdapter(adapter);
        rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent it = new Intent(MainActivity.this, DetailActivity.class);
//                it.putExtra("id", mylist.get(position).id);
//                startActivity(it);
            }
        });

////////////////////////////////////////////////////////


        /////////////////////////////////////
        data = new ArrayList<>();
        DBInfo.DB_FILE = getFilesDir() + File.separator + "mydata.sqlite";
        copyDBFile();
      }
    @Override
    protected void onResume() {
        super.onResume();
        mylist.clear();
        showList.clear();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(DBInfo.DB_FILE, null, SQLiteDatabase.OPEN_READWRITE);
        // Cursor c = db.rawQuery("Select * from phone", null);
        Cursor c = db.query("phone", new String[] {"id", "username", "tel"}, null,null,null,null,null);

        if (c.moveToFirst())
        {
            do {

                mylist.add(new data(c.getInt(0), c.getString(1), c.getString(2),c.getString(3)));
                showList.add(c.getString(1));
            } while (c.moveToNext());
        }
        adapter.notifyDataSetChanged();
    }
    private void copyDBFile() {

            try {
                File f = new File(DBInfo.DB_FILE);
                if (! f.exists())
                {
                    InputStream is = getResources().openRawResource(R.raw.mydata);
                    OutputStream os = new FileOutputStream(DBInfo.DB_FILE);
                    int read;
                    while ((read = is.read()) != -1)
                    {
                        os.write(read);
                    }
                    os.close();
                    is.close();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    public void add(View v)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(DBInfo.DB_FILE, null, SQLiteDatabase.OPEN_READWRITE);

        String name = na.getText().toString();
        String tel = ph.getText().toString();
        String email = mail.getText().toString();
        String str = "Insert Into phone (name, tel,email) values ('" + name + "', '" + tel + "','" + email + "')";
        db.execSQL(str);
        finish();
    }
    }



//    public data[] getData() {
//        return data.toArray(new data[data.size()]);
//    }
//    public  void change(){
////        data1 d;
//
//        data.add(na.getText().toString());
//        data.name = na.getText().toString();
//        data1 name = na.getText().toString();
//        data1 tel = ph.getText().toString();
//        data1 email = mail.getText().toString();
//        data2.update(d);
//        finish();
//    }
//    public  void delete(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage("d?");
//        builder.setPositiveButton("okay", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                data2.delete(d);
//                finish();
//            }
//        });
//        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//        builder.show();
//    }
//
//    public static void add(data1 data1) {
//
//    }


    ///////////////////////////////////





