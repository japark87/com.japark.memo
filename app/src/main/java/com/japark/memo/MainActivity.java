package com.japark.memo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RelativeLayout btnLeft, btnRight;
    ListView listview;
    WriteAdapter adapter;
    MySQLiteOpenHelper dbHelper;
    ArrayList<Map<String, Object>> dblist;

    @Override
    protected void onResume() {
        super.onResume();

        dblist.clear();
        dblist = dbHelper.getResult();
        adapter.notifyDataSetChanged();
        listview.smoothScrollToPosition(0);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLeft = (RelativeLayout) findViewById(R.id.btn_left);
        btnRight = (RelativeLayout) findViewById(R.id.btn_right);
        listview = (ListView) findViewById(R.id.listview);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_left:

                        Intent intent = new Intent(MainActivity.this, Write.class);
                        startActivity(intent);
                        break;

                    case R.id.btn_right:

                        listview.smoothScrollToPosition(0);
                        break;
                }
            }
        };

        btnLeft.setOnClickListener(listener);
        btnRight.setOnClickListener(listener);

        dbHelper = new MySQLiteOpenHelper(getApplicationContext(), "MEMO.db", null, 1);

        dblist = dbHelper.getResult();

        adapter = new WriteAdapter(MainActivity.this, dblist);
        adapter.notifyDataSetChanged();
        listview.setAdapter(adapter);
    }
}
