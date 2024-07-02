package com.backendgip.model;

public class EstimacionUfsDTO {

    private Integer UfId;
    private EstimacionUfs estimacionUfs;

    public EstimacionUfsDTO() {
    }

    public EstimacionUfsDTO(Integer ufId, EstimacionUfs estimacionUfs) {
        UfId = ufId;
        this.estimacionUfs = estimacionUfs;
    }

    public Integer getUfId() {
        return UfId;
    }

    public void setUfId(Integer ufId) {
        UfId = ufId;
    }

    public EstimacionUfs getEstimacionUfs() {
        return estimacionUfs;
    }

    public void setEstimacionUfs(EstimacionUfs estimacionUfs) {
        this.estimacionUfs = estimacionUfs;
    }

    
}
