package com.example.malon_000.bookcounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewCounterActivity extends AppCompatActivity {
    private EditText nameText;
    private EditText numberText;
    private EditText commentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_counter);
        Button enterResult = (Button) findViewById(R.id.OK);


        nameText = (EditText) findViewById(R.id.counterName);
        numberText = (EditText) findViewById(R.id.counterStart);
        commentText = (EditText) findViewById(R.id.counterComment);

        enterResult.setOnClickListener(new View.OnClickListener() {
            int num;
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String number = numberText.getText().toString();
                String comment = commentText.getText().toString();
                try{
                    num=Integer.parseInt(number);
                }catch (NumberFormatException e) {
                    Intent returnIntent=new Intent();
                    setResult(AppCompatActivity.RESULT_CANCELED);
                    finish();
                }
                if (num>=0) {
                    Intent data = new Intent();
                    data.putExtra("name", name);
                    data.putExtra("number", num);
                    data.putExtra("comment", comment);
                    setResult(RESULT_OK, data);
                    finish();
                }
                else{
                    setResult(AppCompatActivity.RESULT_CANCELED);
                    finish();
                }
            }
        });

    }
}
