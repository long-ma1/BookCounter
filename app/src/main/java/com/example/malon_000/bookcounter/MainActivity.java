package com.example.malon_000.bookcounter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME= "file1.sav";

    private ListView CounterListView;

    private ArrayList<Counter> countList;
    private ArrayAdapter<Counter> adapter;
    Counter chosenCounter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        CounterListView = (ListView) findViewById(R.id.CounterList);
        //// TODO: 2017-09-25 remove this log
        Log.v("trial","starts");
        CounterListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object a=CounterListView.getItemAtPosition(position);
                chosenCounter = (Counter) a;
                String counterName= chosenCounter.get_name();
                String counterComment=chosenCounter.get_comment();
                int counter=chosenCounter.get_value();
                Date counterTime=chosenCounter.get_time();
                int original = chosenCounter.get_origin();
                Intent intent = new Intent(MainActivity.this, SelectedCounter.class);
                intent.putExtra("name",counterName);
                intent.putExtra("time",counterTime);
                intent.putExtra("comment",counterComment);
                intent.putExtra("current",counter);
                intent.putExtra("original",original);
                startActivityForResult(intent,2);
            }
        });
    }


    public void onClick(View view){
        Intent intent = new Intent(this,NewCounterActivity.class);
        startActivityForResult(intent,1);
        //taken from https://stackoverflow.com/questions/10407159/how-to-manage-startactivityforresult-on-android
        //2017-09-24
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String name = data.getStringExtra("name");
                int start = data.getIntExtra("number", 0);
                String comment = data.getStringExtra("comment");
                Counter c = new Counter(name, start, comment);
                countList.add(c);
                adapter.notifyDataSetChanged();
                saveInFile();
            }

        }
        if (requestCode==2){
            if (resultCode==Activity.RESULT_OK) {
                int newValue = data.getIntExtra("update",0);
                if (newValue==-1){
                    countList.remove(chosenCounter);
                    adapter.notifyDataSetChanged();
                    saveInFile();
                }
                else{
                    chosenCounter.setValue(newValue);
                    String newName = data.getStringExtra("name");
                    String newComment = data.getStringExtra("comment");
                    chosenCounter.set_name(newName);
                    chosenCounter.set_comment(newComment);
                    adapter.notifyDataSetChanged();
                    saveInFile();
                }

            }


        }
    }


    @Override
    protected void onStart(){
        super.onStart();
        loadFromFile();
        adapter=new ArrayAdapter<Counter>(this,R.layout.textlist,countList);
        CounterListView.setAdapter(adapter);
    }


    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Counter>>(){}.getType();
            countList = gson.fromJson(in, listType);
            //Taken from https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-09-24

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            countList = new ArrayList<Counter>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }

    }


    private void saveInFile(){
        try{
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(countList,out);
            out.flush();
            fos.close();

        }catch (FileNotFoundException e){
            throw new RuntimeException();
        }catch (IOException e){
            throw new RuntimeException();
        }

    }

}
