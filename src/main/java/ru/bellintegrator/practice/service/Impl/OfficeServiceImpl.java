package ru.bellintegrator.practice.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.OfficeDAO;
import ru.bellintegrator.practice.exception.CustomNotFoundException;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.service.OfficeService;
import ru.bellintegrator.practice.view.OfficeView;
import ru.bellintegrator.practice.view.OfficeView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class OfficeServiceImpl implements OfficeService {
    private final Logger log = LoggerFactory.getLogger(OfficeServiceImpl.class);

    private final OfficeDAO dao;

    @Autowired
    public OfficeServiceImpl(OfficeDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfficeView> listOffices(OfficeView office) {
        List<Office> all = dao.all();
        Function<Office, OfficeView> mapOffice = p -> {
            OfficeView view = new OfficeView();
            view.id = String.valueOf(p.getId());
            view.name = p.getName();
            view.phone = p.getPhone();
            view.isActive = p.getActive();

            log.info(view.toString());

            return view;
        };
        if (office.getName() != null && !office.getName().isEmpty()) {
            return all.stream().map(mapOffice)
                    .filter(p -> (
                                    ( p.getActive() == office.getActive() && p.getName().contains(office.getName()) && String.valueOf(p.getPhone()).contains(String.valueOf(office.getPhone())) )

                                            || (
                                            p.getName().contains(office.getName()) && office.getActive() == null && p.getName().contains(office.getName()) && office.getPhone() == null )
                            )
                                    ||      (
                                    (String.valueOf(p.getPhone()).contains(String.valueOf(office.getPhone())) && p.getName().contains(office.getName()) && office.getActive() == null)
                            )

                                    ||      (
                                    p.getName().contains(office.getName()) && p.getActive() == office.getActive() && office.getPhone() == null
                            )
                    )
                    .collect(Collectors.toList());
        }

        else {throw new CustomNotFoundException(String.format("Not found organizaton with Id Phone Active are %s %l %b ", office.getId(), office.getPhone(), office.isActive));//return Collections.emptyList();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfficeView> offices() {
        List<Office> all = dao.all();

        Function<Office, OfficeView> mapOffice = p -> {
            OfficeView view = new OfficeView();
            view.id = String.valueOf(p.getId());
            view.name = p.getName();
            view.address = p.getAddress();
            view.phone = p.getPhone();
            view.isActive = p.getActive();
            //view.orgId = p.getOrganization()

            log.info(view.toString());

            return view;
        };
        return all.stream()
                .map(mapOffice)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Office getOfficeById(Long id) {
        return dao.loadById(id);
    }
/*
    @Override
    @Transactional
    public void add(OfficeView view) {
        Office office = new Office(view.name, view.fullName, view.inn, view.kpp, view.address, view.phone, view.isActive);
        dao.save(office);
    }

    @Override
    @Transactional
    public void updateOffice(OfficeView view) {
        Office office = dao.loadById(Long.valueOf(view.id));
        office.setName(view.name);
        office.setFullName(view.fullName);
        office.setInn(view.inn);
        office.setKpp(view.kpp);
        office.setAddress(view.address);
        office.setPhone(view.phone);
        office.setActive(view.isActive);

        log.info(view.toString());

        dao.save(office);
    }

    @Override
    @Transactional
    public void deleteOffice(OfficeView view) {
        Office office = dao.loadById(Long.valueOf(view.getId()));
        log.info(view.toString());
        if (office == null) {
            //throw new CustomNotFoundException("Not found organizaton with Id is " + view.getId());
            throw new CustomNotFoundException("Not found organizaton with Id is " + view.getId());
        }
        //else {
            dao.remove(office);
        //}
    }






    */
}
