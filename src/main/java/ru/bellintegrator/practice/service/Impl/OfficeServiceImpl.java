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
import ru.bellintegrator.practice.dao.OrganizationDAO;
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
    private final OrganizationDAO daoOrg;

    @Autowired
    public OfficeServiceImpl(OfficeDAO dao, OrganizationDAO daoOrg) {
        this.dao = dao;
        this.daoOrg = daoOrg;
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
        log.info("before service getId " + id);
        Office office = dao.loadById(id);

        if (office == null) {
            throw new CustomErrorException(String.format("Service says Mismatch parametr- Id* is %s", id));
        }
        if (id < 1) {
            throw new CustomErrorException(String.format("Service says Mismatch parametr- Id* is %s", id));
        }
        log.info("office service getId " + office.toString());
        return office;
    }

    @Override
    public List<OfficeFilterView> filterOfficeList(OfficeFilterView officeFilterView) {

        if (officeFilterView.getOrgId() == null || (officeFilterView.getOrgId() < 1)) {
            throw new CustomErrorException(String.format("Mismatch parametr- ogrId* is %s", officeFilterView.getOrgId()));
        }
        log.info("before DAO filtr " + officeFilterView.toString());

        List<Office> all = dao.filterOfficeList(officeFilterView);
        //  log.info("before all filtr"+ all);
        List<OfficeFilterView> officesView = new ArrayList<>();
        log.info("before filtrOfficeList" + officeFilterView.toString());

        Function<Office, OfficeFilterView> mapOffice = p -> {
            OfficeFilterView view = new OfficeFilterView();
            view.id = String.valueOf(p.getId());
            view.name = p.getName();
            view.isActive = p.getActive();
            //view.orgId = p.getOrganization().getId();

            log.info("after filtr" + view.toString());

            return view;
        };

        return all.stream().map(mapOffice).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public void updateOffice(OfficeView view) {
        log.info("before service update ID" + view.toString());
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
        log.info("delete-view 1 " + view.toString());
        if (view.getId().isEmpty()) {
            throw new CustomErrorException("Mismatch parameter- Id is empty");
        }
        Office office = dao.loadById(Long.valueOf(view.getId()));
        log.info("delete-view 2" + view.toString());
        if (office == null || (Long.valueOf(view.getId()) < 1) ) {
            throw new CustomErrorException("Mismatch parameter- Id is " + view.getId().toString());
        }

        //else {
        Organization organization = office.getOrganization();
       // organization.removeOffice(office);
        dao.remove(office);
        //}
    }

    @Override
    @Transactional
    public void add(OfficeView view) {
        log.info("office serv offview view  " + view.toString());

        if ((Long.valueOf(view.getOrgId()) < 1) ) {
            throw new CustomErrorException("Mismatch parameter- orgId is " + view.getOrgId().toString());
        }

      // Office office = new Office(view.name, view.address, view.phone, view.isActive, view.orgId);
       // Office office = new Office("SSSSS","FFFFFFFFFFFF", 123, true, 1l);
        Office office = new Office();
        office.setName(view.name);
        office.setAddress(view.address);
        office.setPhone(view.phone);
        office.setActive(view.isActive);

        log.info("office serv offview before 2  " + office.toString());

        Organization organization = daoOrg.loadById(view.orgId);
        organization.addOffice(office);
        //Organization organization = office.getOrganization();
        log.info("office serv offview before office  " + office.toString());
        log.info("office serv offview before org " + organization.toString());



        //Office office = new Office("SSSSS","FFFFFFFFFFFF", 123, true);
        log.info("office serv office after  " + office.toString());

        /*Person person = dao.loadById(1L);
        House house = person.getHouses().iterator().next();
        person.removeHouse(house);*/

       // Organization organization = office.getOrganization();
      //  organization.addOffice(office);

        dao.save(office);

    /*@Override
    @Transactional
    public void add(OfficeView view) {
        Office office = new Office(view.name, view.address, view.phone, view.isActive);
        dao.save(office);
    }*/

    }
}
