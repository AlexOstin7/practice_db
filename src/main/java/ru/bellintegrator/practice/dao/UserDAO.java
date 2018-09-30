package ru.bellintegrator.practice.dao;

import ru.bellintegrator.practice.model.*;
import ru.bellintegrator.practice.view.UserFilterView;

import java.util.List;

public interface UserDAO {

    /**
     * Получить все объекты User
     *
     * @return
     */
    List<User> all();

    /**
     * Получить User по идентификатору
     *
     * @param id
     * @return
     */
    User loadById(Long id);

    /**
     * Получить User по имени
     *
     * @param name
     * @return
     */
    User loadByName(String name);

    /**
     * Получить User по имени
     *
     * @param name, inn, isActive
     * @return
     */
    User loadByName(String name, Long inn, Boolean isActive);



    /**
     * Сохранить User
     *
     * @param user
     */
    void save(User user);



    /**
     * Удалить User
     *
     * @param user
     */

    void remove(User user);

    /**
     * Фильтр Users by *OfficeId, firstName, , secondName, middleName , possition
     *
     * @param userFilterView
     */

    List<User> filterUserList(UserFilterView userFilterView);

    List<Doc> loadDocsByCountryId(Integer id);

    List<Doc> allDocs();

    List<Country> allCountries();

    Doc loadDocById(Integer id);

    Office loadOfficeById(Long id);

}
