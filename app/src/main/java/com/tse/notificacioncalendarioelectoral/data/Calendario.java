package com.tse.notificacioncalendarioelectoral.data;

/**
 * Created by TSE on 7/9/2017.
 */

public class Calendario {


    private String ACTIVIDAD;
    private String FECHA_INICIO;
    private String FECHA_FIN;
    private String RESPONSABLE;

    public Calendario() {
    }


    public Calendario(String ACTIVIDAD, String FECHA_INICIO, String FECHA_FIN, String RESPONSABLE) {
        this.ACTIVIDAD = ACTIVIDAD;
        this.FECHA_INICIO = FECHA_INICIO;
        this.FECHA_FIN = FECHA_FIN;
        this.RESPONSABLE = RESPONSABLE;
    }


    public String getACTIVIDAD() {
        return ACTIVIDAD;
    }

    public void setACTIVIDAD(String ACTIVIDAD) {
        this.ACTIVIDAD = ACTIVIDAD;
    }

    public String getFECHA_INICIO() {
        return FECHA_INICIO;
    }

    public void setFECHA_INICIO(String FECHA_INICIO) {
        this.FECHA_INICIO = FECHA_INICIO;
    }

    public String getFECHA_FIN() {
        return FECHA_FIN;
    }

    public void setFECHA_FIN(String FECHA_FIN) {
        this.FECHA_FIN = FECHA_FIN;
    }

    public String getRESPONSABLE() {
        return RESPONSABLE;
    }

    public void setRESPONSABLE(String RESPONSABLE) {
        this.RESPONSABLE = RESPONSABLE;
    }
}
