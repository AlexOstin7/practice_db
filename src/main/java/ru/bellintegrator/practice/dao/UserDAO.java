package ru.bellintegrator.practice.dao;

import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.view.OfficeFilterView;

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
     * Фильтр Users by *OrgId, name, phone, active
     *
     * @param userFilterView
    */
    //List<User> filterUserByOrgId(User user);
   // List<User> filterUserList(UserFilterView userFilterView);




}
