package com.backendgip.service;

import java.util.List;

import com.backendgip.model.Esfuerzo;

public interface EsfuerzoService {


    List<Esfuerzo> gEsfuerzos();  

    Esfuerzo sEsfuerzo(Esfuerzo esfuerzo);

    void deleteEsfuerzo(Esfuerzo esfuerzo);
}
