package org.ricone.api.xpress.request.xSchool;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.core.model.wrapper.LeaWrapper;
import org.ricone.api.core.model.wrapper.SchoolWrapper;
import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.error.exception.NoContentException;
import org.ricone.api.xpress.error.exception.NotFoundException;
import org.ricone.api.xpress.model.XLeaResponse;
import org.ricone.api.xpress.model.XLeasResponse;
import org.ricone.api.xpress.model.XSchoolResponse;
import org.ricone.api.xpress.model.XSchoolsResponse;
import org.ricone.api.xpress.request.xLea.XLeaDAO;
import org.ricone.api.xpress.request.xLea.XLeaFilterer;
import org.ricone.api.xpress.request.xLea.XLeaMapper;
import org.ricone.api.xpress.request.xLea.XLeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class XSchoolServiceImp implements XSchoolService {
    @Autowired private XSchoolDAO dao;
    @Autowired private XSchoolMapper mapper;
    @Autowired private XSchoolFilterer filterer;

    @Override
    public XSchoolResponse findByRefId(ControllerData metadata, String refId) throws Exception {
        SchoolWrapper instance = dao.findByRefId(metadata, refId);
        if(instance == null) {
            throw new NotFoundException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XSchoolResponse findByLocalId(ControllerData metadata, String localId) throws Exception {
        SchoolWrapper instance = dao.findByLocalId(metadata, localId);
        if(instance == null) {
            throw new NotFoundException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XSchoolsResponse findAll(ControllerData metadata) throws Exception {
        List<SchoolWrapper> instance = dao.findAll(metadata);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XSchoolsResponse findAllByLea(ControllerData metadata, String refId) throws Exception {
        List<SchoolWrapper> instance = dao.findAllByLeaRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XSchoolsResponse findAllByCalendar(ControllerData metadata, String refId) throws Exception {
        List<SchoolWrapper> instance = dao.findAllByCalendarRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XSchoolsResponse findAllByCourse(ControllerData metadata, String refId) throws Exception {
        List<SchoolWrapper> instance = dao.findAllByCourseRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XSchoolsResponse findAllByRoster(ControllerData metadata, String refId) throws Exception {
        List<SchoolWrapper> instance = dao.findAllByRosterRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XSchoolsResponse findAllByStaff(ControllerData metadata, String refId) throws Exception {
        List<SchoolWrapper> instance = dao.findAllByStaffRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XSchoolsResponse findAllByStudent(ControllerData metadata, String refId) throws Exception {
        List<SchoolWrapper> instance = dao.findAllByStudentRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }

    @Override
    public XSchoolsResponse findAllByContact(ControllerData metadata, String refId) throws Exception {
        List<SchoolWrapper> instance = dao.findAllByContactRefId(metadata, refId);
        if(CollectionUtils.isEmpty(instance)) {
            throw new NoContentException();
        }
        return filterer.apply(mapper.convert(instance), metadata);
    }
}