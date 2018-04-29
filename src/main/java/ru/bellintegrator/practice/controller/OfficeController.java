package ru.bellintegrator.practice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.practice.message.Message;
import ru.bellintegrator.practice.message.Response;
import ru.bellintegrator.practice.message.ResponseMsg;
import ru.bellintegrator.practice.view.OfficeView;

public interface OfficeControler {

    Response office(@RequestBody OfficeView office);

    Response updateOffice(@RequestBody OfficeView office);

    Response deleteOffice(@RequestBody OfficeView office);

    Response offices();

    Response offices(@RequestBody OfficeView office);

    Response getOfficeById(@PathVariable("id") Long id) ;

    Response getOfficeByName(String name);
}
