package com.example.malon_000.bookcounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;

public class SelectedCounter extends AppCompatActivity {

    private int number;
    private String name;
    private String comment;
    private Date date;
    private int original;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_item);
        number=getIntent().getIntExtra("current",0);
        name = getIntent().getStringExtra("name");
        comment = getIntent().getStringExtra("comment");
        date = (Date)getIntent().getSerializableExtra("time");
        original = getIntent().getIntExtra("original",0);

        final TextView currentName = (EditText) findViewById(R.id.CurrentName);
        currentName.setText(name);

        final TextView currentComment = (EditText) findViewById(R.id.CurrentComment);
        currentComment.setText(comment);

        TextView currentTime = (TextView) findViewById(R.id.LastTime);
        currentTime.setText("last edited:"+date.toString());

        TextView current = (TextView) findViewById(R.id.Current);
        current.setText(String.valueOf(number));

        Button increaseButton = (Button) findViewById(R.id.increase);
        Button decreaseButton = (Button) findViewById(R.id.decrease);
        Button resetButton = (Button) findViewById(R.id.reset);
        Button doneButton = (Button) findViewById(R.id.update);
        Button deleteButton = (Button) findViewById(R.id.delete);

        increaseButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                number++;
                TextView current = (TextView) findViewById(R.id.Current);
                current.setText(String.valueOf(number));
            }

        });
        decreaseButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (number>0){
                    setResult(RESULT_OK);
                    number=number-1;
                    TextView current = (TextView) findViewById(R.id.Current);
                    current.setText(String.valueOf(number));
                }
            }

        });
        resetButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                number=original;
                TextView current = (TextView) findViewById(R.id.Current);
                current.setText(String.valueOf(number));
            }

        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("update", number);
                name = currentName.getText().toString();
                data.putExtra("name",name);
                comment = currentComment.getText().toString();
                data.putExtra("comment",comment);
                setResult(RESULT_OK, data);
                finish();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("update", -1);
                setResult(RESULT_OK, data);
                finish();
            }
        });

    }

}
