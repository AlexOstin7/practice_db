package ru.bellintegrator.practice.controller.impl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.controller.RegUserController;
import ru.bellintegrator.practice.controller.UserController;
import ru.bellintegrator.practice.exception.CustomErrorException;
import ru.bellintegrator.practice.message.Response;
import ru.bellintegrator.practice.message.ResponseSuccess;
import ru.bellintegrator.practice.model.Doc;
import ru.bellintegrator.practice.service.Impl.UserServiceImpl;
import ru.bellintegrator.practice.service.RegUserService;
import ru.bellintegrator.practice.service.UserService;
import ru.bellintegrator.practice.view.RegUserView;
import ru.bellintegrator.practice.view.UserFilterView;
import ru.bellintegrator.practice.view.UserView;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class RegUserControllerImpl implements RegUserController {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final RegUserService regUserService;

    @Autowired
    public RegUserControllerImpl(RegUserService regUserService) {
        this.regUserService = regUserService;
    }

    @Override
    @ApiOperation(value = "regUsers", nickname = "regUsers", httpMethod = "POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 405, message = "I don't know"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/register", method = {POST})
    public Response register(@RequestBody RegUserView regUser) {
        log.info("view-reg user " + regUser.toString());
        regUserService.addUser(regUser);
        return new ResponseSuccess("success");
    }

    @Override
    @ApiOperation(value = "getActivationCode", nickname = "getActivationCode", httpMethod = "GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 405, message = "I don't know"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/activation", method = {GET})
    public Response activation(@RequestParam(value = "code", defaultValue = "") String code)
    {
        regUserService.activation(code);
        return new ResponseSuccess("success");
    }

    @Override
    @ApiOperation(value = "postLogin", nickname = "postLogin", httpMethod = "POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 405, message = "I don't know"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/login", method = {POST})
    @ExceptionHandler(NoSuchAlgorithmException.class)
    public Response login(@RequestBody RegUserView regUserView) {
        regUserService.login(regUserView);
       /* try {
            log.info("controller login try 0 "+ regUserView.toString());

            log.info("controller login try 1 "+ regUserView.toString());
        }
        catch (NoSuchAlgorithmException e)
        {
            log.info("controller login catch "+ regUserView.toString());
            throw new CustomErrorException(e.toString());
        }*/
        return new ResponseSuccess("success");
    }
    //For test only
    @Test
    @Override
    @ApiOperation(value = "getRegUserFull", nickname = "getRegUserFull", httpMethod = "GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 405, message = "I don't know"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/registers", method = {GET})
    public Response registers() {
        return new ResponseSuccess("success", regUserService.registers());
    }

}

/*@Override
    @ApiOperation(value = "listUsersByOfficeId", nickname = "listUsersByOfficeId", httpMethod = "POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 405, message = "I don't know"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/user/list", method = {POST})
    public Response filterUsers(@RequestBody UserFilterView user) {
        log.info("view-listByOfficeId" + user.toString());
        List<UserFilterView> userFilterViewList = userService.filterUserList(user);

        return new ResponseSuccess("success", userFilterViewList);
    }
    @Override
    @ApiOperation(value = "getUserById", nickname = "getUserById", httpMethod = "GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 405, message = "I don't know"),
            @ApiResponse(code = 500, message = "Failure")})
    //@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @RequestMapping(value = "/user/{id}", method = {GET})
    public Response getUserById(@PathVariable(value = "id") Long id) {
        log.info("controller before service.getUserByID "+ id.toString());
        UserView user = userService.getUserById(id);
        log.info("controller after service.getUserByID "+ id.toString() + " user - " + user.toString());
        return new ResponseSuccess("success", user);
    }
    */