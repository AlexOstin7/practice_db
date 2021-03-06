package ru.bellintegrator.practice.dao;

import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.OrganizationFilterView;
import ru.bellintegrator.practice.view.OrganizationView;


import java.util.List;

public interface OrganizationDAO {

    /**
     * Получить все объекты Organization
     *
     * @return
     */
    List<Organization> all();

    /**
     * Получить Organization по идентификатору
     *
     * @param id
     * @return
     */
    Organization loadById(Long id);

    /**
     * Получить Organization по имени
     *
     * @param name
     * @return
     */

    /**
     * Сохранить Organization
     *
     * @param Organization
     */
    void save(Organization Organization);

    void remove(Organization Organization);

    List<Organization> filterOrganizationList(OrganizationFilterView organizationFilterView);

}
