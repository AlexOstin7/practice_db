package ru.bellintegrator.practice.controller.impl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.bellintegrator.practice.controller.DummyOrganizationController;
import ru.bellintegrator.practice.service.DummyOrganizationService;
import ru.bellintegrator.practice.view.OrganizationView;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class DummyOrganizationControllerImpl implements DummyOrganizationController {

    private final DummyOrganizationService dummyOrganizationService;

    @Autowired
    public DummyOrganizationControllerImpl(DummyOrganizationService dummyOrganizationService) {
        this.dummyOrganizationService = dummyOrganizationService;
    }

    @Override
    @RequestMapping(value = "/ping2", method = {GET, POST})
    public String ping() {
        return "pongpong";
    }

    @Override
    @ApiOperation(value = "addOrganization", nickname = "addOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/organiazation", method = {POST})
    public void organization(@RequestBody OrganizationView organization) {
        dummyOrganizationService.add(organization);
    }

    @Override
    @ApiOperation(value = "getOrganizations", nickname = "getOrganizations", httpMethod = "GET")
    @RequestMapping(value = "/organization", method = {GET})
    public List<OrganizationView> organizations() {
        return dummyOrganizationService.organizations();
    }

}
