package com.group15.parkit;

public class Division_Model {

        int id;
        String name;
        int available;
        int total;
        boolean AnyUnkown;
        public Division_Model(int id,String name, int available, int total, boolean anyunknown ) {
            this.id = id;
            this.name=name;
            this.available = available;
            this.total = total;
            this.AnyUnkown = anyunknown;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public int getAvailable() {
            return available;
        }


        public int getTotal() {
            return total;
        }
}
