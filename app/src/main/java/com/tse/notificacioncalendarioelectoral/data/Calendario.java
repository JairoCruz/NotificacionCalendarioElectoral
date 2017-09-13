package com.tse.notificacioncalendarioelectoral.data;

/**
 * Created by TSE on 7/9/2017.
 */

public class Calendario {


    private String ACTIVIDAD;
    private String INICIO;
    private String FIN;
    private String RESPONSABLES;
    private String ESTADO;

    public Calendario() {
    }


    public Calendario(String ACTIVIDAD, String FECHA_INICIO, String FECHA_FIN, String RESPONSABLES, String ESTADO) {
        this.ACTIVIDAD = ACTIVIDAD;
        this.INICIO = FECHA_INICIO;
        this.FIN = FECHA_FIN;
        this.RESPONSABLES = RESPONSABLES;
        this.ESTADO = ESTADO;
    }


    public String getACTIVIDAD() {
        return ACTIVIDAD;
    }

    public void setACTIVIDAD(String ACTIVIDAD) {
        this.ACTIVIDAD = ACTIVIDAD;
    }

    public String getINICIO() {
        return INICIO;
    }

    public void setINICIO(String FECHA_INICIO) {
        this.INICIO = FECHA_INICIO;
    }

    public String getFIN() {
        return FIN;
    }

    public void setFIN(String FECHA_FIN) {
        this.FIN = FECHA_FIN;
    }

    public String getRESPONSABLES() {
        return RESPONSABLES;
    }

    public void setRESPONSABLES(String RESPONSABLE) {
        this.RESPONSABLES = RESPONSABLE;
    }


    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }
}
