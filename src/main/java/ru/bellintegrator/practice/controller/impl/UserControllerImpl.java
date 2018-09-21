package ru.bellintegrator.practice.controller.impl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.controller.UserController;
import ru.bellintegrator.practice.message.Response;
import ru.bellintegrator.practice.message.ResponseSuccess;
import ru.bellintegrator.practice.model.Doc;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.service.Impl.UserServiceImpl;
import ru.bellintegrator.practice.service.UserService;
import ru.bellintegrator.practice.view.UserFilterView;
import ru.bellintegrator.practice.view.UserView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class UserControllerImpl implements UserController {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserService userService;
    //private final OrganizationService organizationService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
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

    @Override
    @ApiOperation(value = "loadDocs", nickname = "loadDocs", httpMethod = "GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 405, message = "I don't know"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/loadDocs/{id}", method = {GET})
    public Response loadDocs(@PathVariable(value = "id") Integer id) {
        log.info("view-loadDocs" );
        List<Doc> loadDocs = userService.loadDocs(id);

        return new ResponseSuccess("success", loadDocs);
    }

    @Override
    @ApiOperation(value = "allDocs", nickname = "allDocs", httpMethod = "POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 405, message = "I don't know"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/docs", method = {POST})
    public Response allDocs() {
        log.info("view-loadDocs" );
        //List<Doc> loadDocs = userService.allDocs();
        return new ResponseSuccess("success", userService.allDocs());
    }

    @Override
    @ApiOperation(value = "allCountries", nickname = "allCountries", httpMethod = "POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 405, message = "I don't know"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/countries", method = {POST})
    public Response allCountries() {
        log.info("view-Countries" );
        //List<Doc> loadDocs = userService.allDocs();
        return new ResponseSuccess("success", userService.allCountries());
    }


    @Override
    @ApiOperation(value = "updateUser", nickname = "updateUser", httpMethod = "POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 405, message = "I don't know"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/user/update", method = {POST})
    public Response updateUser(@RequestBody UserView user) {
        log.info("User controller update view-update " + user.toString());
        userService.updateUser(user);
        return new ResponseSuccess("success");
    }

    @Override
    @ApiOperation(value = "deleteUserById", nickname = "deleteUserById", httpMethod = "POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 405, message = "I don't know"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/user/delete", method = {POST})
    public Response deleteUser(@RequestBody UserView user) {
        userService.deleteUser(user);
        return new ResponseSuccess("success");
    }
/*



    @Override
    @ApiOperation(value = "saveUser", nickname = "saveUser", httpMethod = "POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = String.class), @ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 405, message = "I don't know"), @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/user/save", method = {POST})
    public Response user(@RequestBody UserView user) {
        log.info("user contr save before " + user.toString());
        userService.add(user);
        log.info("user contr save after " + user.toString());
        return new ResponseSuccess("success");
    }*/

    //FOR TEST ONLY!!!!
    @Override
    @ApiOperation(value = "getUsersFull", nickname = "getUsersFull", httpMethod = "GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 405, message = "I don't know"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/users", method = {GET})
    public Response users() {
        return new ResponseSuccess("success", userService.users());
    }


    /*@Override
    @ApiOperation(value = "listUsers", nickname = "listUsers", httpMethod = "POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 405, message = "I don't know"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/user/lists", method = {POST})
    public Response users(@RequestBody UserView user) {
        log.info("view-list" + user.toString());
        return new ResponseSuccess("success", userService.listUsers(user));
    }
*/

/*
    @Override
    @ApiOperation(value = "getUserByName", nickname = "getUserByName", httpMethod = "GET")
    @RequestMapping(value = "/user/name", method = {GET})
    public Response getUserByName(String name) {
        return new Response("success", userService.getUserByName(name));
    }
*/
}

