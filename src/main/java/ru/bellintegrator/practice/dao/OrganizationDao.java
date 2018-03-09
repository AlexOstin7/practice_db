package ru.bellintegrator.practice.dao;

import ru.bellintegrator.practice.model.Organization;
import java.util.List;

public interface OrganizationDao {

    public void addOrganization(Organization organization);

    public void updateOrganization(Organization organization);

    public void removeOrganizarion(Long id);

    public void getOrganizationById(Long id);

    //public List(Organization) listOrganization(String name, Integer inn, Boolean isActive);
}
