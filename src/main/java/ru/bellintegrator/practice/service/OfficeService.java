package ru.bellintegrator.practice.service;

import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.view.OfficeView;
import ru.bellintegrator.practice.view.OfficeView;

import java.util.List;

/**
 * Some service. Just for test
 */
public interface OfficeService {
    
    List<OfficeView> listOffices(OfficeView office);

    List<OfficeView> offices();

    Office getOfficeById(Long id);
/*
    void add(OfficeView office);

    void updateOffice(OfficeView office);

    
    void deleteOffice(OfficeView office);





   // Office getOfficeByName(String name);



    */

}