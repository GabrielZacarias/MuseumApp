package com.museumtickets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.listview);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(getString(R.string.museumOneList));
        arrayList.add(getString(R.string.museumTwoList));
        arrayList.add(getString(R.string.museumThreeList));
        arrayList.add(getString(R.string.museumFourList));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent secActivity = new Intent(view.getContext(), MuseumActivity.class);

            if(position == 0)
                secActivity.putExtra("Position", 0);


            else if(position == 1)
                secActivity.putExtra("Position", 1);

            else if(position == 2)
                secActivity.putExtra("Position", 2);


            else if(position == 3)
                secActivity.putExtra("Position", 3);

            startActivity(secActivity);

        });

    }
}