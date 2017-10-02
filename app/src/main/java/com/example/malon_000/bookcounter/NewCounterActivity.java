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

/**
 * The activity to create a new counter
 * Will have editText for the user to enter information in for the new counter
 * @author long6
 * @version 3
 *
 */

public class NewCounterActivity extends AppCompatActivity {
    private EditText nameText;
    private EditText numberText;
    private EditText commentText;
    /**
     * Prints out the screen and buttons
     * @return string
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_counter);
        Button enterResult = (Button) findViewById(R.id.OK);


        nameText = (EditText) findViewById(R.id.counterName);
        numberText = (EditText) findViewById(R.id.counterStart);
        commentText = (EditText) findViewById(R.id.counterComment);

        enterResult.setOnClickListener(new View.OnClickListener() {
            int num; //int that will refer to the number entered

            /**
             * Takes the information entered and checks if they are suitable for making a counter
             * @param v the view
             * @return void
             * @throws NumberFormatException
             */
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString(); // get the name entered
                String number = numberText.getText().toString(); // get the number entered as a string
                String comment = commentText.getText().toString(); // get the comment
                try{
                    num=Integer.parseInt(number); // try to change the string in the number to an int
                }catch (NumberFormatException e) {
                    Intent returnIntent=new Intent(); // if it is not an int return to the previous screen
                    setResult(AppCompatActivity.RESULT_CANCELED); // sets the result as canceled
                    finish();
                }
                if (num>=0) { // the count cannot be negative
                    Intent data = new Intent(); // saves the data and finishes the activity
                    data.putExtra("name", name);
                    data.putExtra("number", num);
                    data.putExtra("comment", comment);
                    setResult(RESULT_OK, data);
                    finish();
                }
                else{ // if the count is negative set result as canceled as well
                    setResult(AppCompatActivity.RESULT_CANCELED);
                    finish();
                }
            }
        });

    }
}
