package ru.bellintegrator.practice.service.Impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import javassist.compiler.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.OfficeDAO;
import ru.bellintegrator.practice.exception.CustomErrorException;
import ru.bellintegrator.practice.exception.CustomNotFoundException;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.service.OfficeService;
import ru.bellintegrator.practice.view.OfficeFilterView;
import ru.bellintegrator.practice.view.OfficeView;


import java.util.ArrayList;
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
    public List<OfficeView> offices() {
        List<Office> all = dao.all();

        Function<Office, OfficeView> mapOffice = p -> {
            OfficeView view = new OfficeView();
            view.id = String.valueOf(p.getId());
            view.name = p.getName();
            view.address = p.getAddress();
            view.phone = p.getPhone();
            view.isActive = p.getActive();
            view.orgId = p.getOrganization().getId();

            log.info(view.toString());

            return view;
        };
        return all.stream().map(mapOffice).collect(Collectors.toList());
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
            view.orgId = p.getOrganization().getId();

            log.info(view.toString());

            return view;
        };
        // if (office.getName() != null && !office.getName().isEmpty()) {
        return all.stream().map(mapOffice).collect(Collectors.toList());
        //}
    }


    @Override
    @Transactional(readOnly = true)
    public Office getOfficeById(Long id) {
        Office office = dao.loadById(id);
        if (office == null) {
            throw new CustomErrorException(String.format("Mismatch parametr- Id* is %s", id));
        }
        if (id < 1) {
            throw new CustomErrorException(String.format("Mismatch parametr- Id* is %s", id));
        }
        return office;
    }

    @Override
    public List<OfficeFilterView> filterOfficeList(OfficeFilterView officeFilterView) {

        if (officeFilterView.getOrgId() == null || (officeFilterView.getOrgId() < 1)) {
            throw new CustomErrorException(String.format("Mismatch parametr- ogrId* is %s", officeFilterView.getOrgId()));
        }
        log.info("before DAO filtr "+ officeFilterView.toString());

        List<Office> all = dao.filterOfficeList(officeFilterView);
      //  log.info("before all filtr"+ all);
        List<OfficeFilterView> officesView = new ArrayList<>();
log.info("before filtrOfficeList"+ officeFilterView.toString());

Function<Office, OfficeFilterView> mapOffice = p -> {
            OfficeFilterView view = new OfficeFilterView();
            view.id = String.valueOf(p.getId());
            view.name = p.getName();
            view.isActive = p.getActive();
            //view.orgId = p.getOrganization().getId();

            log.info("after filtr" +view.toString());

            return view;
        };

        return all.stream().map(mapOffice).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public void updateOffice(OfficeView view) {
        Office office = dao.loadById(Long.valueOf(view.getId()));
        log.info("before service update " + view.toString());
        office.setName(view.name);
        office.setPhone(view.phone);
        office.setAddress(view.address);
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

    /*
    @Override
    @Transactional
    public void add(OfficeView view) {
        Office office = new Office(view.name, view.fullName, view.inn, view.kpp, view.address, view.phone, view.isActive);
        dao.save(office);
    }










    */
}
