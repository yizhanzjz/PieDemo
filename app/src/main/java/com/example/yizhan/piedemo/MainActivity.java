package com.example.yizhan.piedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PieCircle pieCircle = (PieCircle) findViewById(R.id.pieCircle);

        List<Pie> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Pie pie = new Pie();
            pie.value = i + 1;
            list.add(pie);
        }
        pieCircle.setDatas(list);
    }
}
