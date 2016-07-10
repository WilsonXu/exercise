package org.wilson.lambdasinaction.chap5;

/**
 * Created by wilson on 7/10/16.
 */
public class Trader {
    private String name;
    private String city;

    public Trader(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Trader:"+this.name + " in " + this.city;
    }
}
