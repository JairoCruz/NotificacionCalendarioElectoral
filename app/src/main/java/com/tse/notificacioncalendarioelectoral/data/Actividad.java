package com.tse.notificacioncalendarioelectoral.data;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import java.util.Map;

/**
 * Created by TSE on 7/9/2017.
 */

public class Actividad implements Comparable<Actividad> {


    private String ACTIVIDAD;
    private String INICIO;
    private String FIN;
    private String RESPONSABLES;
    private Integer INICIO_DIA;
    private String INICIO_MES_T;
    private Integer INICIO_MES;
    private Integer INICIO_AÑO;
    private Integer FIN_DIA;
    private Integer FIN_MES;
    private String FIN_MES_T;
    private Integer FIN_AÑO;
    private long TIMESTAMP_INICIO;
    private long TIMESTAMP_FIN;
    private Integer FASE;
    private String ESTADO;
    private Map<String, Integer> DIAS_RESTANTES;


    public Actividad() {
    }


    public Actividad(String ACTIVIDAD, String INICIO, String FIN, String RESPONSABLES, Integer INICIO_DIA, String INICIO_MES_T, Integer INICIO_MES, Integer INICIO_AÑO, Integer FIN_DIA, Integer FIN_MES, String FIN_MES_T, Integer FIN_AÑO, long TIMESTAMP_INICIO, long TIMESTAMP_FIN, Integer FASE, String ESTADO, Map<String, Integer> DIAS_RESTANTES) {
        this.ACTIVIDAD = ACTIVIDAD;
        this.INICIO = INICIO;
        this.FIN = FIN;
        this.RESPONSABLES = RESPONSABLES;
        this.INICIO_DIA = INICIO_DIA;
        this.INICIO_MES_T = INICIO_MES_T;
        this.INICIO_MES = INICIO_MES;
        this.INICIO_AÑO = INICIO_AÑO;
        this.FIN_DIA = FIN_DIA;
        this.FIN_MES = FIN_MES;
        this.FIN_MES_T = FIN_MES_T;
        this.FIN_AÑO = FIN_AÑO;
        this.TIMESTAMP_INICIO = TIMESTAMP_INICIO;
        this.TIMESTAMP_FIN = TIMESTAMP_FIN;
        this.FASE = FASE;
        this.ESTADO = ESTADO;
        this.DIAS_RESTANTES = DIAS_RESTANTES;
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

    public void setINICIO(String INICIO) {
        this.INICIO = INICIO;
    }

    public String getFIN() {
        return FIN;
    }

    public void setFIN(String FIN) {
        this.FIN = FIN;
    }

    public String getRESPONSABLES() {
        return RESPONSABLES;
    }

    public void setRESPONSABLES(String RESPONSABLES) {
        this.RESPONSABLES = RESPONSABLES;
    }

    public Integer getINICIO_DIA() {
        return INICIO_DIA;
    }

    public void setINICIO_DIA(Integer INICIO_DIA) {
        this.INICIO_DIA = INICIO_DIA;
    }

    public String getINICIO_MES_T() {
        return INICIO_MES_T;
    }

    public void setINICIO_MES_T(String INICIO_MES_T) {
        this.INICIO_MES_T = INICIO_MES_T;
    }

    public Integer getINICIO_MES() {
        return INICIO_MES;
    }

    public void setINICIO_MES(Integer INICIO_MES) {
        this.INICIO_MES = INICIO_MES;
    }

    public Integer getINICIO_AÑO() {
        return INICIO_AÑO;
    }

    public void setINICIO_AÑO(Integer INICIO_AÑO) {
        this.INICIO_AÑO = INICIO_AÑO;
    }

    public Integer getFIN_DIA() {
        return FIN_DIA;
    }

    public void setFIN_DIA(Integer FIN_DIA) {
        this.FIN_DIA = FIN_DIA;
    }

    public Integer getFIN_MES() {
        return FIN_MES;
    }

    public void setFIN_MES(Integer FIN_MES) {
        this.FIN_MES = FIN_MES;
    }

    public String getFIN_MES_T() {
        return FIN_MES_T;
    }

    public void setFIN_MES_T(String FIN_MES_T) {
        this.FIN_MES_T = FIN_MES_T;
    }

    public Integer getFIN_AÑO() {
        return FIN_AÑO;
    }

    public void setFIN_AÑO(Integer FIN_AÑO) {
        this.FIN_AÑO = FIN_AÑO;
    }

    public long getTIMESTAMP_INICIO() {
        return TIMESTAMP_INICIO;
    }

    public Map<String, Integer> getDIAS_RESTANTES() {
        return DIAS_RESTANTES;
    }

    public void setDIAS_RESTANTES(Map<String,Integer> DIAS_RESTANTES) {
        this.DIAS_RESTANTES = DIAS_RESTANTES;
    }

    public void setTIMESTAMP_INICIO(long TIMESTAMP_INICIO) {
        this.TIMESTAMP_INICIO = TIMESTAMP_INICIO;
    }

    public long getTIMESTAMP_FIN() {
        return TIMESTAMP_FIN;
    }

    public void setTIMESTAMP_FIN(long TIMESTAMP_FIN) {
        this.TIMESTAMP_FIN = TIMESTAMP_FIN;
    }

    public Integer getFASE() {
        return FASE;
    }

    public void setFASE(Integer FASE) {
        this.FASE = FASE;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int compareTo(@NonNull Actividad actividad) {
        //return getINICIO().toString().compareTo(actividad.getINICIO().toString());
        return Long.compare(getTIMESTAMP_INICIO(), actividad.TIMESTAMP_INICIO);
        //return getINICIO_MES_T().toString().compareTo(actividad.getINICIO_MES_T().toString());
    }
}
