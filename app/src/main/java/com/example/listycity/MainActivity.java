/*
Beginning Comments:
Cmput 301: Lab 2
Name: Derek Dubrule, CCID: dubrule1
Date: Jan 14, 2025
Task: Implementing a add/delete function to the demo code.
 */

package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Declare the variables so that you will be able to reference later
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    // added code for exercise:
    EditText cityInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // need

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main); // need

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);

        // added code for lab exercise
        Button addButton = findViewById(R.id.add_city);
        Button deleteButton = findViewById(R.id.delete_city);
        cityInput = findViewById(R.id.city_input);
        //

        String[] cities = {"Edmonton", "Paris", "London"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

    // added code for lab exercise
        // Click on City
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                cityList.setTag(position);
            }
        });

        // Add a new city
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = cityInput.getText().toString().trim();
                if (!city.isEmpty()) {
                    dataList.add(city);
                    cityAdapter.notifyDataSetChanged();
                    cityInput.setText("");
                }
            }
        });

        // Delete the selected city
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object tag = cityList.getTag();
                if (tag != null) {
                    int position = (int) tag;
                    dataList.remove(position);
                    cityAdapter.notifyDataSetChanged();
                    cityList.setTag(null);
                }
            }
        });
    }
}
