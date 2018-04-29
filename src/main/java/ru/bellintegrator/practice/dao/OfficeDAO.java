package ru.bellintegrator.practice.dao;

import ru.bellintegrator.practice.model.Office;

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
     * @param Office
     */
    void save(Office Office);

    void remove(Office Office);

}
