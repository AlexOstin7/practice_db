package ru.bellintegrator.practice.dao;

import ru.bellintegrator.practice.model.*;
import ru.bellintegrator.practice.view.UserFilterView;

import java.util.List;

public interface RegUserDAO {


    /**
     * Сохранить regUser
     *
     * @param regUser
     */
    void save(RegUser regUser);

    /**
     * Получить RegUser по code
     *
     * @param code
     * @return
     */
    RegUser loadByCode(String code);

    /**
     * Получить RegUser по login
     *
     * @param login
     * @return
     */
    RegUser loadByLogin(String login);

    /**
     * Получить RegUser по password
     *
     * @param password
     * @return
     */
    RegUser loadByPassword(String password);


    /**
     * Получить все объекты RegUser
     *
     * @return
     */
    List<RegUser> all();

}
