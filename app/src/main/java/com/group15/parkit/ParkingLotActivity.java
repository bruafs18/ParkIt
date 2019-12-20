package com.group15.parkit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Pair;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ParkingLotActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_lot);
        getSupportActionBar().hide();
        Intent i = getIntent();
        String dName = i.getStringExtra("DivisionName");
        int dID = i.getIntExtra("DivisionID",1);

        TextView lblPL = findViewById(R.id.lblPL);
        lblPL.setText(dName);
        Bitmap bm = ParkingLotIDToBitmap(dID);
        ArrayList<Spot> PSpots = ParkingSpotsFromPLID(dID);
        bm = DrawSpotsOnImage(bm,PSpots);

        ImageView img = findViewById(R.id.imgPL);
        img.setImageBitmap(bm);

    }

    Bitmap DrawSpotsOnImage(Bitmap bm,ArrayList<Spot>  Spots)
    {
        Bitmap img = bm.copy(bm.getConfig(),true);
        int r=8;
        for(int idx=0;idx<Spots.size();idx++)
        {
            Spot spot = Spots.get(idx);
            int xs = spot.getX(),ys = spot.getY(),
                    color = AvailabilityToColor(spot.available);

            for(int x = xs-r;x<xs+r;x++)
                for(int y = ys-r;y<ys+r;y++)
                    img.setPixel(x,y,color);
        }

        return img;
    }

    int AvailabilityToColor(Availability available){
        int color = Color.YELLOW;

        switch(available)
        {
            case NO:
                color = Color.RED;
                break;
            case YES:
                color = Color.GREEN;
                break;
        }

        return color;
    }

    ArrayList<Spot> ParkingSpotsFromPLID(int id)
    {

        ArrayList<Spot> Spots = new ArrayList<>();
        ArrayList<Pair<Integer,Integer>> Dots = new ArrayList<>();

        if(id==1)
        {
            Dots = DotsFor1();
            for(int idx=0;idx<Dots.size();idx++)
            {
                int x = Dots.get(idx).first, y = Dots.get(idx).second;
                Availability available = Availability.Dont_Know;
                switch(idx%3){
                    case 0:
                        available = Availability.YES;
                        break;
                    case 1:
                        available = Availability.NO;
                        break;
                }

                Spots.add(new Spot(idx, "A" + (idx+1), x, y, available));
            }
        }

        return Spots;
    }

    ArrayList<Pair<Integer,Integer>> DotsFor1(){
        ArrayList<Pair<Integer,Integer>> Dots = new ArrayList<>();
        Dots.addAll(GenerateDots(145,150,50,0,5));
        Dots.addAll(GenerateDots(30,355,0,50,4));
        Dots.addAll(GenerateDots(120,590,50,0,3));
        Dots.addAll(GenerateDots(175,645,0,50,4));
        Dots.addAll(GenerateDots(405,630,50,0,3));
        Dots.addAll(GenerateDots(595,540,0,-50,3));
        return Dots;
    }

    ArrayList<Pair<Integer,Integer>> GenerateDots(int xStart, int yStart, int xIncrement, int yIncrement, int times)
    {
        ArrayList<Pair<Integer,Integer>> Dots = new ArrayList<>();
        for(int x=xStart,  y=yStart, idx=0; idx<times;x+=xIncrement,y+=yIncrement,idx++)
            Dots.add(new Pair<Integer,Integer>(x,y));

        return Dots;
    }

    Bitmap ParkingLotIDToBitmap(int id)
    {
        if(id==1)
            return drawableToBitmap(getResources().getDrawable(R.drawable.mcn));

        NotImplementedPart();
        return drawableToBitmap(getResources().getDrawable(R.drawable.mcn));
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    void NotImplementedPart()
    {
        Toast.makeText(getBaseContext(), "Not Implemented yet",
                Toast.LENGTH_LONG).show();

        startActivity(new Intent(getBaseContext(),MainActivity.class));
        finish();
    }
}
