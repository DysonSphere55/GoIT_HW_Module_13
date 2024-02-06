package org.goit.task1.user;

import lombok.Data;

@Data
public class Geo {
    private double lat;
    private double lng;

    public Geo(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }
}


/*
"geo": {
        "lat": "-37.3159",
        "lng": "81.1496"
        }
 */