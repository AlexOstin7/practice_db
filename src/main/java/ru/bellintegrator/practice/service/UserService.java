package ru.bellintegrator.practice.service;

import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.view.OfficeFilterView;
import ru.bellintegrator.practice.view.OfficeView;
import ru.bellintegrator.practice.view.UserView;

import java.util.List;

/**
 * Some service. Just for test
 */
public interface UserService {
    
   // List<UserView> listUsers(UserView office);

    List<UserView> users();

    /*User getUserById(Long id);

    List<UserFilterView> filterUserList(UserFilterView office);

    void updateUser(UserView office);

    void deleteUser(UserView office);


    void add(UserView office);*/

   // User getUserByName(String name);


}