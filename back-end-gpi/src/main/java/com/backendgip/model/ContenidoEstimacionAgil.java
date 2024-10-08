package com.backendgip.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ContenidoEstimacionAgil implements Serializable{
    
    public EstimacionUfs estimacion;
    public List<EstimacionAgil> activiadesAgil;
    public List<EsfuerzoAgil> esfuerzo;
    public List<ActividadesComplementarias> actividadesComplementarias;

    @Override
    public String toString() {
        return "ContenidoEstimacionAgil [estimacion=" + estimacion + ", activiadesAgil=" + activiadesAgil
                + ", esfuerzo=" + esfuerzo + ", actividadesComplementarias=" + actividadesComplementarias + "]";
    }

}
