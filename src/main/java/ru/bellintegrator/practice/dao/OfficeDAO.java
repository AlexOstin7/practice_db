package ru.bellintegrator.practice.dao;

import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.view.OfficeFilterView;

import java.util.List;

public interface OfficeDAO {

    /**
     * Получить все объекты Office
     *
     * @return
     */
    List<Office> all();

    /**
     * Получить Office по идентификатору
     *
     * @param id
     * @return
     */
    Office loadById(Long id);

    /**
     * Получить Office по имени
     *
     * @param name
     * @return
     */
    Office loadByName(String name);

    /**
     * Получить Office по имени
     *
     * @param name, inn, isActive
     * @return
     */
    Office loadByName(String name, Long inn, Boolean isActive);

    /**
     * Сохранить Office
     *
     * @param office
     */
    void save(Office office);
    /**
     * Удалить Office
     *
     * @param office
     */

    void remove(Office office);

    /**
     * Фильтр Offices by OrgId
     *
     * @param officeFilterView
    */
    //List<Office> filterOfficeByOrgId(Office office);
    List<Office> filterOfficeList(OfficeFilterView officeFilterView);

}
