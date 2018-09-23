package ru.bellintegrator.practice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.bellintegrator.practice.message.Response;
import ru.bellintegrator.practice.model.Doc;
import ru.bellintegrator.practice.view.OfficeFilterView;
import ru.bellintegrator.practice.view.OfficeView;
import ru.bellintegrator.practice.view.UserFilterView;
import ru.bellintegrator.practice.view.UserView;

import java.util.List;

public interface UserController {
//    Response users(@RequestBody UserView user);

    Response users();

    Response filterUsers(@RequestBody UserFilterView user);

    Response getUserById(@PathVariable("id") Long id) ;

    Response loadDocs(@PathVariable("id") Integer id);

    Response allDocs();

    Response allCountries();

    Response updateUser(@RequestBody UserView user);

    Response deleteUser(@RequestBody UserView user);

    Response saveUser(@RequestBody UserView user);

 /*


    Response getUserByName(String name);
    */
}
