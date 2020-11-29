package com.museumtickets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.listview);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1. The Metropolitan Museum of Art");
        arrayList.add("2. The Museum of Modern Art");
        arrayList.add("3. American Museum of Natural History");
        arrayList.add("4. Solomon R. Guggenheim Museum");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent secActivity = new Intent(view.getContext(), MuseumActivity.class);

                if(position == 0)
                    startActivityForResult(secActivity, 0);

                else if(position == 1)
                    startActivityForResult(secActivity, 1);

                else if(position == 2)
                    startActivityForResult(secActivity, 2);

                else if(position == 3)
                    startActivityForResult(secActivity, 3);

            }
        });


    }
}