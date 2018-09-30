package ru.bellintegrator.practice.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import ru.bellintegrator.practice.dao.OfficeDAO;
import ru.bellintegrator.practice.dao.RegUserDAO;
import ru.bellintegrator.practice.dao.UserDAO;
import ru.bellintegrator.practice.exception.CustomErrorException;
import ru.bellintegrator.practice.model.*;
import ru.bellintegrator.practice.service.RegUserService;
import ru.bellintegrator.practice.service.UserService;
import ru.bellintegrator.practice.view.OfficeView;
import ru.bellintegrator.practice.view.RegUserView;
import ru.bellintegrator.practice.view.UserFilterView;
import ru.bellintegrator.practice.view.UserView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class RegUserServiceImpl implements RegUserService {
    private final Logger log = LoggerFactory.getLogger(RegUserServiceImpl.class);

    private final RegUserDAO dao;

    @Autowired
    public RegUserServiceImpl(RegUserDAO dao) {
        this.dao = dao;
    }

   private static String bytesToHex(byte[] hash) {
       StringBuffer hexString = new StringBuffer();
       for (int i = 0; i < hash.length; i++) {
           String hex = Integer.toHexString(0xff & hash[i]);
           if(hex.length() == 1) hexString.append('0');
           hexString.append(hex);
       }
       return hexString.toString();
   }

    public byte[] hash(String password) throws NoSuchAlgorithmException {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] passBytes = password.getBytes();
        byte[] passHash = sha256.digest(passBytes);
        return passHash;
    }
    @Override
    @Transactional
    public void addUser(RegUserView view) {
        log.info("user serv regview view  " + view.toString());
        log.info("user serv revview string.length  " + view.getPassword().length());
        if (view.login == null || view.name == null || view.password == null ) {
            throw new CustomErrorException("Mismatch parameter- login is null ");
        }
        if (view.getPassword().length() < 3) {
            throw new CustomErrorException("Mismatch parameter- password is less 3 symbols " + view.getPassword());
        } else {
            if (view.getPassword().length() > 32 ) {
                throw new CustomErrorException("Mismatch parameter- password is longer 32 symbols " + view.getPassword());
            }
        }

        if (view.getLogin().length() > 50 ) {
            throw new CustomErrorException("Mismatch parameter- password is longer 50 symbols " + view.getLogin());
        }

        if (view.getName().length() > 50 ) {
            throw new CustomErrorException("Mismatch parameter- password is longer 50 symbols " + view.getName());
        }
        RegUser regUser = new RegUser();
        log.info("user reg addUser  " + regUser.toString());


        regUser.setLogin(view.login);
        regUser.setName(view.name);
        //regUser.setPassword(view.password);
        //regUser.setPassword(bytesToHex(view.password));
//        String sha256hex = DigestUtils.sha256Hex(view.getPassword());


        String generatedPassword = null;
        try {
            generatedPassword= bytesToHex(hash(view.password));
            regUser.setPassword(generatedPassword);
            log.info("user 1 generatedPassword  " + regUser.toString());
            dao.save(regUser);
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new CustomErrorException(e.toString());

        }
        log.info("user 2 generatedPassword  " + regUser.toString());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegUserView> registers() {
        List<RegUser> all = dao.all();

        Function<RegUser, RegUserView> mapRegUser = p -> {
            RegUserView view = new RegUserView();

            view.login = p.getLogin();
            view.name = p.getName();
            view.password = p.getPassword();

            log.info(view.toString());

            return view;
        };
        return all.stream().map(mapRegUser).collect(Collectors.toList());
    }

}