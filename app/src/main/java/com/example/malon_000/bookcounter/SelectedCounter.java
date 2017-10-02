/*
 * CMPUT 301 LEC A1
 *
 * Version 3
 * Sept 30. 2017
 *
 */

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

/**
 * The activity set up for when an user selects a specific counter to see the detained information
 * or edit the information of a counter.
 * @author long6
 * @version 3
 *
 */
public class SelectedCounter extends AppCompatActivity {

    private int number; // the current count number of the counter
    private String name; // the name of the counter selected
    private String comment; // the comment of the counter selected
    private Date date; // the date the selected counter was last changed
    private int original; // the number the selected counter resets to

    /**
     * returns the counter as a string
     * @return string
     */
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

        final TextView originalNumber = (EditText) findViewById(R.id.originalNumber);
        originalNumber.setText(String.valueOf(original));

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
            /**
             * increases the current count by 1, called when the user presses the + button
             * @param v view
             */
            public void onClick(View v) {
                setResult(RESULT_OK);
                number++;
                TextView current = (TextView) findViewById(R.id.Current);
                current.setText(String.valueOf(number));
            }

        });
        decreaseButton.setOnClickListener(new View.OnClickListener() {
            /**
             * decreases the current count by 1, called when the user presses the - button
             * @param v view
             */
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
            /**
             * resets the current count to the reset number, occurs when the user presses reset button
             * @param v view
             */
            public void onClick(View v) {
                setResult(RESULT_OK);
                number=original;
                TextView current = (TextView) findViewById(R.id.Current);
                current.setText(String.valueOf(number));
            }

        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            /**
             * called when the user presses the save button to indicate finished changing
             * and wants to save the changes made to the counter
             * saves the data and returns to the original activity
             * @param v view
             * @throws NumberFormatException if the new number that the counter resets to is not an int
             */
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("update", number);
                name = currentName.getText().toString();
                data.putExtra("name",name);
                comment = currentComment.getText().toString();
                data.putExtra("comment",comment);
                try{
                    original = Integer.parseInt(originalNumber.getText().toString());
                    data.putExtra("orig",original);
                    setResult(RESULT_OK, data);
                    finish();
                } catch (NumberFormatException na) {
                    setResult(RESULT_CANCELED);
                    finish();
                }


            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            /**
             * called if the user decided to delete the counter, returns -1 as the new count so
             * the main activity knows to delete this counter
             * @param v view
             */
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
