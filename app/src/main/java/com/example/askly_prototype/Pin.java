package com.example.askly_prototype;

import android.graphics.PointF;

public class Pin {
    Pin(String owner, PointF point, String target, Integer idPregunta) {
        this.point=point;
        this.owner=owner;
        this.target=target;
        this.idPregunta=idPregunta;
    }
    PointF point;
    String owner;
    String target;
    Integer idPregunta;
}
