package ru.bellintegrator.practice.controller.impl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.bellintegrator.practice.controller.OrganizationController;
import ru.bellintegrator.practice.exception.CustomNotFoundException;
import ru.bellintegrator.practice.message.Message;
import ru.bellintegrator.practice.message.Response;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.service.OrganizationService;
import ru.bellintegrator.practice.view.OrganizationView;
import ru.bellintegrator.practice.view.OrganizationViewByName;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class OrganizationControllerImpl implements OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationControllerImpl(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @Override
    @ApiOperation(value = "saveOrganization", nickname = "saveOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 405, message = "I don't know"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/organiazation/save", method = {POST})
    public Response organization(@RequestBody OrganizationView organization) {
        organizationService.add(organization);
        return new Response("success");
    }

    @Override
    @ApiOperation(value = "updateOrganization", nickname = "updateOrganization", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 405, message = "I don't know"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/organization/update", method = {POST})
    public Response updateOrganizaton(@RequestBody OrganizationView organization) {
        organizationService.updateOrganization(organization);
        return new Response("success");
    }
    @Override
    @ApiOperation(value = "listOrganizations", nickname = "listOrganizations", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 405, message = "I don't know"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/organization/list", method = {POST})
    public Response organizations(@RequestBody OrganizationView organization) {
        List<OrganizationView> view = organizationService.listOrganizations(organization);
        if (view.isEmpty()) {
            throw new CustomNotFoundException("Not found");
        } else {
            return new Response("success", view);
        }
    }

    @Override
    @ApiOperation(value = "deleteOrganizationById", nickname = "deleteOrganizationById", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 405, message = "I don't know"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/organization/delete", method = {POST})
    public Response deleteOrganizaton(@RequestBody OrganizationView organization) {
        organizationService.deleteOrganization(organization);
        return new Response("success");
    }

    @Override
    @ApiOperation(value = "getOrganizationsFull", nickname = "getOrganizationsFull", httpMethod = "GET")
    @RequestMapping(value = "/organization", method = {GET})
    public Message organizations() {
        return new Response("success", organizationService.organizations());
    }

    @Override
    @ApiOperation(value = "getOrganizationById", nickname = "getOrganizationById", httpMethod = "GET")
    //@RequestMapping(value = "/organization/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @RequestMapping(value = "/organization/{id}", method = {GET})
    public Response getOrganizationById(@PathVariable(value = "id") Long id) {
        return new Response("success", organizationService.getOrganizationById(id));
    }

}
