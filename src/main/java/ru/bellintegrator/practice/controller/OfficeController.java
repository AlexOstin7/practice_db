package ru.bellintegrator.practice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.practice.message.Response;
import ru.bellintegrator.practice.view.OfficeFilterView;
import ru.bellintegrator.practice.view.OfficeView;

public interface OfficeController {
    Response offices(@RequestBody OfficeView office);

    Response offices();

    Response getOfficeById(@PathVariable("id") Long id) ;

    Response offices(@RequestBody OfficeFilterView office);

    Response updateOffice(@RequestBody OfficeView office);

    Response deleteOffice(@RequestBody OfficeView office);

 /*







    Response getOfficeByName(String name);
    */
}
