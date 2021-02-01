package com.carlostorres.ibericajava.ui.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.carlostorres.ibericajava.R;

public class AddActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.carlostorres.ibericajava.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.carlostorres.ibericajava.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.carlostorres.ibericajava.EXTRA_DESCRIPTION";
    public static final String EXTRA_TIMESTAMP = "com.carlostorres.ibericajava.EXTRA_TIMESTAMP";

    private EditText etTitle;
    private EditText etDescription;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);

        btnSave = findViewById(R.id.btnSave);

        getIntentData();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUpdateNote();
            }
        });


    }

    private void getIntentData() {
        Intent intent = getIntent();

        if ( intent.hasExtra(EXTRA_ID) ) {
            etTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            etDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            btnSave.setText(getString(R.string.update));
        } else {
            btnSave.setText(getString(R.string.save));
        }
    }

    private void saveUpdateNote() {
        String title = etTitle.getText().toString();
        String description = etDescription.getText().toString();
        Long timeStamp = System.currentTimeMillis();

        if ( !title.isEmpty() && !description.isEmpty() ) {
            Intent dataSave = new Intent();
            dataSave.putExtra(EXTRA_TITLE, title);
            dataSave.putExtra(EXTRA_DESCRIPTION, description);
            dataSave.putExtra(EXTRA_TIMESTAMP, timeStamp);
            setResult(RESULT_OK, dataSave);

            int id = getIntent().getIntExtra(EXTRA_ID, -1);

            if ( id != -1 ) {
                dataSave.putExtra(EXTRA_ID, id);
            }

            setResult(RESULT_OK, dataSave);
            finish();
        }
    }

    private void updateNote() {
    }
}