package ru.bellintegrator.practice.controller.impl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.bellintegrator.practice.controller.OfficeController;
import ru.bellintegrator.practice.exception.CustomErrorException;
import ru.bellintegrator.practice.exception.CustomNotFoundException;
import ru.bellintegrator.practice.message.Response;
import ru.bellintegrator.practice.message.ResponseSuccess;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.service.Impl.OfficeServiceImpl;
import ru.bellintegrator.practice.service.OfficeService;
import ru.bellintegrator.practice.view.OfficeFilterView;
import ru.bellintegrator.practice.view.OfficeView;


import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class OfficeControllerImpl implements OfficeController {

    private final Logger log = LoggerFactory.getLogger(OfficeServiceImpl.class);
    private final OfficeService officeService;

    @Autowired
    public OfficeControllerImpl(OfficeService officeService) {
        this.officeService = officeService;
    }

    @Override
    @ApiOperation(value = "getOfficesFull", nickname = "getOfficesFull", httpMethod = "GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 405, message = "I don't know"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/offices", method = {GET})
    public Response offices() {
        return new ResponseSuccess("success", officeService.offices());
    }

    @Override
    @ApiOperation(value = "listOffices", nickname = "listOffices", httpMethod = "POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 405, message = "I don't know"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/office/lists", method = {POST})
    public Response offices(@RequestBody OfficeView office) {
        return new ResponseSuccess("success", officeService.listOffices(office));
    }


    @Override
    @ApiOperation(value = "getOfficeById", nickname = "getOfficeById", httpMethod = "GET")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 405, message = "I don't know"),
            @ApiResponse(code = 500, message = "Failure")})
    //@RequestMapping(value = "/office/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @RequestMapping(value = "/office/{id:.+}", method = {GET})
    public Response getOfficeById(@PathVariable(value = "id") Long id) {
        log.info("before ID!!!!!!!!!!!!!! "+ id.toString());
        Office office = officeService.getOfficeById(id);
        log.info("after ID!!!!!!!!!!!!!! "+ id.toString());
        return new ResponseSuccess("success", office);
    }

    @Override
    @ApiOperation(value = "listOfficesByOrgId", nickname = "listOfficesByOrgId", httpMethod = "POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 405, message = "I don't know"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/office/list", method = {POST})
    public Response offices(@RequestBody OfficeFilterView office) {
        List<OfficeFilterView> officeFilterViewList = officeService.filterOfficeList(office);

        return new ResponseSuccess("success", officeFilterViewList);
    }
    @Override
    @ApiOperation(value = "updateOffice", nickname = "updateOffice", httpMethod = "POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 405, message = "I don't know"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/office/update", method = {POST})
    public Response updateOffice(@RequestBody OfficeView office) {
        officeService.updateOffice(office);
        return new ResponseSuccess("success");
    }

    @Override
    @ApiOperation(value = "deleteOfficeById", nickname = "deleteOfficeById", httpMethod = "POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = String.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 405, message = "I don't know"),
            @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/office/delete", method = {POST})
    public Response deleteOffice(@RequestBody OfficeView office) {
        officeService.deleteOffice(office);
        return new ResponseSuccess("success");
    }

    /*
    @Override
    @ApiOperation(value = "saveOffice", nickname = "saveOffice", httpMethod = "POST")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = String.class), @ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 405, message = "I don't know"), @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/organiazation/save", method = {POST})
    public Response office(@RequestBody OfficeView office) {
        officeService.add(office);
        return new Response("success");
    }




    @Override
    @ApiOperation(value = "getOfficeByName", nickname = "getOfficeByName", httpMethod = "GET")
    @RequestMapping(value = "/office/name", method = {GET})
    public Response getOfficeByName(String name) {
        return new Response("success", officeService.getOfficeByName(name));
    }
    */
}

