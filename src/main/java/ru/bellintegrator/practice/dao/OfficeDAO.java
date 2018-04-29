package ru.bellintegrator.practice.dao;

import ru.bellintegrator.practice.model.Organization;


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
    Organization loadByName(String name);

    /**
     * Получить Organization по имени
     *
     * @param name, inn, isActive
     * @return
     */
    Organization loadByName(String name, Long inn, Boolean isActive);

    /**
     * Сохранить Organization
     *
     * @param Organization
     */
    void save(Organization Organization);

    void remove(Organization Organization);

}
