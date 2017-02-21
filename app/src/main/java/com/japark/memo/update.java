package com.japark.memo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jinapark on 2016. 8. 24..
 */
public class update extends AppCompatActivity {

    private EditText title, contents;
    private Button save, delete;
    private String titleValue, contentsValue, id;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.update);
        Intent intent = getIntent();

        final MySQLiteOpenHelper dbHelper = new MySQLiteOpenHelper(getApplicationContext(), "MEMO.db", null, 1);

        titleValue = intent.getExtras().getString("title");
        contentsValue = intent.getExtras().getString("contents");
        id = intent.getExtras().getString("id");

        title = (EditText)findViewById(R.id.title);
        contents = (EditText)findViewById(R.id.contents);

        save = (Button)findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updateTitle = title.getText().toString();
                String updateContents = contents.getText().toString();
                long now = System.currentTimeMillis();
                Date date = new Date(now);

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String updateDate = format.format(date);

                dbHelper.update(updateTitle, updateContents, updateDate, Integer.parseInt(id));
                finish();
            }
        });

        delete = (Button)findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.delete(Integer.parseInt(id));
                finish();
            }
        });

        title.setText(titleValue);
        contents.setText(contentsValue);
    }
}
