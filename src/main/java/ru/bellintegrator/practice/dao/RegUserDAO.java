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
     * Получить все объекты RegUser
     *
     * @return
     */
    List<RegUser> all();

}
