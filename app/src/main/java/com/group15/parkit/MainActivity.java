package com.group15.parkit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    ListView lvwPartners;
    ArrayList<String> list;
    ArrayList<Integer> PartnersID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        lvwPartners = findViewById(R.id.lvwPartners);
        FillTheListView(getPartners());

        lvwPartners.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getBaseContext(),DivisionsActivity.class); //Change class when the new one is implemented -------------------------------------------------------------------------------------------
                i.putExtra("PartnerID", PartnersID.get(position));
                i.putExtra("PartnerName", list.get(position));
                startActivity(i);
            }
        });
    }

    ArrayList<String> getPartners()
    {
        String[] arr = new String[]{
                "Brunel University",
                "Shopping Mall",
                "Another Partner"
        };

        Integer[] ids = new Integer[]{1,2,3};

        list =  new ArrayList<String>(Arrays.asList(arr));
        PartnersID = new ArrayList<Integer>(Arrays.asList(ids));
        return list;
    }

    void FillTheListView(ArrayList<String> list)
    {
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.partner_item,list);
        lvwPartners.setAdapter(adapter);
    }
}
