package ru.bellintegrator.practice.service;

import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.Doc;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.view.OfficeFilterView;
import ru.bellintegrator.practice.view.OfficeView;
import ru.bellintegrator.practice.view.UserFilterView;
import ru.bellintegrator.practice.view.UserView;

import java.util.List;

/**
 * Some service. Just for test
 */
public interface UserService {
    
   // List<UserView> listUsers(UserView office);

    List<UserView> users();

    List<UserFilterView> filterUserList(UserFilterView office);

    UserView getUserById(Long id);

    List<Doc> loadDocs(Integer id);

    List<Doc> allDocs();

    List<Country> allCountries();

    void updateUser(UserView office);

    void deleteUser(UserView office);

    void addUser(UserView office);

   // User getUserByName(String name);


}