package ru.bellintegrator.practice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bellintegrator.practice.message.Response;
import ru.bellintegrator.practice.model.RegUser;
import ru.bellintegrator.practice.view.RegUserView;
import ru.bellintegrator.practice.view.UserFilterView;
import ru.bellintegrator.practice.view.UserView;

public interface RegUserController {

    Response register(@RequestBody RegUserView regUser);

    Response activation(@RequestParam(name = "code") String code) ;

    Response login(@RequestBody RegUserView regUser);

    Response registers();

}
