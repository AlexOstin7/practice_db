package ru.bellintegrator.practice.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.OfficeDAO;
import ru.bellintegrator.practice.dao.UserDAO;
import ru.bellintegrator.practice.dao.OrganizationDAO;
import ru.bellintegrator.practice.exception.CustomErrorException;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.service.UserService;
import ru.bellintegrator.practice.service.UserService;
import ru.bellintegrator.practice.view.UserFilterView;
import ru.bellintegrator.practice.view.UserView;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class UserServiceImpl implements UserService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDAO dao;
    private final OfficeDAO daoOffice;

    @Autowired
    public UserServiceImpl(UserDAO dao, OfficeDAO daoOffice) {
        this.dao = dao;
        this.daoOffice = daoOffice;
    }

    @Override
    public List<UserFilterView> filterUserList(UserFilterView userFilterView) {

        if (userFilterView.getOfficeId() == null || (userFilterView.getOfficeId() < 1)) {
            throw new CustomErrorException(String.format("Mismatch parametr- officeId* is %s", userFilterView.getOfficeId()));
        }
        log.info("User DAO filtrUserList " + userFilterView.toString());

        List<User> all = dao.filterUserList(userFilterView);

        //List<UserFilterView> usersView = new ArrayList<>();
        log.info("User DAO filtrUserList " + userFilterView.toString());

        Function<User, UserFilterView> mapUser = p -> {
            UserFilterView view = new UserFilterView();
            view.id = String.valueOf(p.getId());
            view.firstName = p.getFirstName();
            view.secondName = p.getSecondName();
            view.middleName = p.getMiddleName();
            view.possition = p.getPossition();
            view.docCode = p.getDoc().getCode();
            //view.citizenShipCode = p.getDoc().getCountries()

            log.info("after filter " + view.toString());

            return view;
        };

        return all.stream().map(mapUser).collect(Collectors.toList());

    }



   /* @Override
    @Transactional(readOnly = true)
    public List<UserView> listUsers(UserView user) {
        List<User> all = dao.all();
        Function<User, UserView> mapUser = p -> {
            UserView view = new UserView();
            view.id = String.valueOf(p.getId());
            view.name = p.getName();
            view.phone = p.getPhone();
            view.isActive = p.getActive();
            view.orgId = p.getOrganization().getId();

            log.info(view.toString());

            return view;
        };
        // if (user.getName() != null && !user.getName().isEmpty()) {
        return all.stream().map(mapUser).collect(Collectors.toList());
        //}
    }


    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        log.info("before service getId " + id);
        User user = dao.loadById(id);

        if (user == null) {
            throw new CustomErrorException(String.format("Service says Mismatch parametr- Id* is %s", id));
        }
        if (id < 1) {
            throw new CustomErrorException(String.format("Service says Mismatch parametr- Id* is %s", id));
        }
        log.info("user service getId " + user.toString());
        return user;
    }



    @Override
    @Transactional
    public void updateUser(UserView view) {
        if (view.getId() == null || view.getActive() == null || view.getAddress() == null || view.getName() == null || view.getPhone() == null || view.orgId == null) {
            throw new CustomErrorException("Service says Mismatch one ore more parametr(s)- null  ");
        }
        if (Long.valueOf(view.getId()) < 1) {
            throw new CustomErrorException(String.format("Service says Mismatch parametr- Id* is %s", view.getId()));
        }
        log.info("before service update ID" + view.toString());
        User user = dao.loadById(Long.valueOf(view.getId()));
        if (user == null) {
            throw new CustomErrorException(String.format("Service says Mismatch parametr- Id* is %s", view.getId()));
        }
        log.info("before service update " + view.toString());
        user.setName(view.name);
        user.setPhone(view.phone);
        user.setAddress(view.address);
        user.setActive(view.isActive);

        log.info(view.toString());

        dao.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(UserView view) {
        log.info("delete-view 1 " + view.toString());
        if (view.getId().isEmpty()) {
            throw new CustomErrorException("Mismatch parameter- Id is empty");
        }
        User user = dao.loadById(Long.valueOf(view.getId()));
        log.info("delete-view 2" + view.toString());
        if (user == null || (Long.valueOf(view.getId()) < 1) ) {
            throw new CustomErrorException("Mismatch parameter- Id is " + view.getId().toString());
        }

        //else {
       // Organization organization = user.getOrganization();
        //organization.removeUser(user);
        dao.remove(user);
        //}
    }

    @Override
    @Transactional
    public void add(UserView view) {
        log.info("user serv offview view  " + view.toString());
        if (view.getOrgId() == null) {
            throw new CustomErrorException("Mismatch parameter- orgId is null ");
        }
        if ((Long.valueOf(view.getOrgId()) < 1) ) {
            throw new CustomErrorException("Mismatch parameter- orgId is " + view.getOrgId().toString());
        }

      // User user = new User(view.name, view.address, view.phone, view.isActive, view.orgId);
       // User user = new User("SSSSS","FFFFFFFFFFFF", 123, true, 1l);
        User user = new User();


        log.info("user serv offview before 2  " + user.toString());

        Organization organization = daoOrg.loadById(view.orgId);
        if (organization == null) {
            throw new CustomErrorException(String.format("Service says Mismatch parametr- Id* is %s", view.orgId));
        }

        user.setName(view.name);
        user.setAddress(view.address);
        user.setPhone(view.phone);
        user.setActive(view.isActive);
        user.setOrganization(organization);

        log.info("user serv offview before user  " + user.toString());
        log.info("user serv offview before org " + organization.toString());
        //organization.addUser(user);
        //Organization organization = user.getOrganization();
       // Organization organization = user.getOrganization();
      //  organization.addUser(user);

        dao.save(user);

    }*/


//   Test only

    @Override
    @Transactional(readOnly = true)
    public List<UserView> users() {
        List<User> all = dao.all();

        Function<User, UserView> mapUser = p -> {
            UserView view = new UserView();
            view.id = String.valueOf(p.getId());
            view.firstName = p.getFirstName();
            view.secondName = p.getSecondName();
            view.middleName = p.getMiddleName();
            view.possition = p.getPossition();
            view.phone = p.getPhone();
            view.docDate = p.getDocDate();
            view.docNumber = p.getDocNumber();
            view.officeId = p.getOffice().getId();
            view.docId = p.getDoc().getId();
            view.isIdentified = p.getIdentified();

            log.info(view.toString());

            return view;
        };
        return all.stream().map(mapUser).collect(Collectors.toList());
    }
}
