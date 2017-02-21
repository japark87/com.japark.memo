package com.japark.memo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jinapark on 2016. 8. 11..
 */
public class Write extends AppCompatActivity {

    EditText title, contents;
    Button save;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.write);

        title = (EditText)findViewById(R.id.title);
        contents = (EditText)findViewById(R.id.contents);
        save = (Button)findViewById(R.id.save);

        final MySQLiteOpenHelper dbHelper = new MySQLiteOpenHelper(getApplicationContext(), "MEMO.db", null, 1);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputTitle = title.getText().toString();
                String inputContents = contents.getText().toString();
                long now = System.currentTimeMillis();
                Date date = new Date(now);

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String inputDate = format.format(date);

                dbHelper.insert(inputTitle, inputContents, inputDate);

                Toast.makeText(getApplicationContext(), "작성이 완료 되었습니다.", Toast.LENGTH_LONG).show();
                finish();

            }
        });
    }
}
