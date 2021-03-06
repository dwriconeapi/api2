package org.ricone.api.xpress.request.xLea;

import org.ricone.api.xpress.component.ControllerData;
import org.ricone.api.xpress.model.XLeaResponse;
import org.ricone.api.xpress.model.XLeasResponse;

public interface XLeaService {
	XLeaResponse findByRefId(ControllerData metadata, String refId) throws Exception;

	XLeaResponse findById(ControllerData metadata, String id, String idType) throws Exception;

	XLeasResponse findAll(ControllerData metadata) throws Exception;

	XLeasResponse findAllBySchool(ControllerData metadata, String refId) throws Exception;

	XLeasResponse findAllByCalendar(ControllerData metadata, String refId) throws Exception;

	XLeasResponse findAllByCourse(ControllerData metadata, String refId) throws Exception;

	XLeasResponse findAllByRoster(ControllerData metadata, String refId) throws Exception;

	XLeasResponse findAllByStaff(ControllerData metadata, String refId) throws Exception;

	XLeasResponse findAllByStudent(ControllerData metadata, String refId) throws Exception;

	XLeasResponse findAllByContact(ControllerData metadata, String refId) throws Exception;
}