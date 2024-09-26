package com.backendgip.model;

import java.util.List;

import lombok.Data;

@Data
public class EstimacionContenidoAgil {
    public EstimacionUfs estimacion;
    public List<EstimacionAgil> estimacionAgil;
    public List<EsfuerzoAgil> esfuerzoAgil;
    
    @Override
    public String toString() {
        return "EstimacionContenidoAgil [estimacion=" + estimacion + ", estimacionAgil=" + estimacionAgil
                + ", esfuerzoAgil=" + esfuerzoAgil + "]";
    }
}
