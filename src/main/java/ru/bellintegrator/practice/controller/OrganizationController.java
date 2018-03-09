package ru.bellintegrator.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.service.OrganizationService;

import javax.xml.ws.RequestWrapper;


@Controller
public class OrganizationController {
    private OrganizationService organizationService;

    @Autowired(required = true)
    @Qualifier(value = "organizationService")
    public void setOrganizationService(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @RequestMapping(value = "organization", method = RequestMethod.GET)
    public String listBooks(Model model) {
        model.addAllAttributes("organization", new Organization());
        model.addAttribute("listOrganization", this.organizationService().listOrganization());

        return "organization";
    }

    @RequestMapping(value = "/organization/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("organization") Organization organization) {
        if(organization.getId() == 0) {
            this.organizationService.addOrganization(organization);
        }

        return "redierct:/organization";
    }
}
