package com.group15.parkit;

public class Spot {
    int x,y;
    String Name;
    int ID;
    Availability available;

    Spot(int ID,String Name, int x, int y, Availability available)
    {
        this.ID=ID;
        this.Name = Name;
        this.x = x;
        this.y = y;
        this.available = available;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return Name;
    }

    public int getID() {
        return ID;
    }

    public Availability getAvailable() {
        return available;
    }
}
