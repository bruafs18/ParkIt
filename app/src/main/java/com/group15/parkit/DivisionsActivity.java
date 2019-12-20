package com.group15.parkit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DivisionsActivity extends AppCompatActivity {

    ArrayList<Division_Model> Divs;
    TextView lblPartner;
    ListView lvwDivisions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divisions);
        getSupportActionBar().hide();
        lvwDivisions = findViewById(R.id.lvwDivisions);
        Intent i = getIntent();
        String pName = i.getStringExtra("PartnerName");
        int pID = i.getIntExtra("PartnerID",1);
        lblPartner = findViewById(R.id.lblPartner);
        lblPartner.setText(pName);

        Divs = GetDivisionsByPartnerID(pID);

        ArrayAdapterDivisionModel adapter =
                new ArrayAdapterDivisionModel(Divs,getApplicationContext());

        lvwDivisions.setAdapter(adapter);

        lvwDivisions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(getBaseContext(),ParkingLotActivity.class); //Change class when the new one is implemented -------------------------------------------------------------------------------------------
                        i.putExtra("DivisionID", Divs.get(position).getId());
                        i.putExtra("DivisionName", Divs.get(position).getName());
                        startActivity(i);
                    }
                }
        );
    }


    ArrayList<Division_Model> GetDivisionsByPartnerID(int id)
    {
        ArrayList<Division_Model> arr = new ArrayList<>();
        if(id==1)
        {
            arr.add(new Division_Model(1, "Pharmacy NHS",8,22, true));
            arr.add(new Division_Model(2, "Flemming Hall",13,32, false));
            arr.add(new Division_Model(3, "Eastern Gateway",2,23, false));
        }

        return arr;
    }
}
