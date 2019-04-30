package org.ricone.api.xpress.request.xSchool;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.xpress.component.BaseController;
import org.ricone.api.xpress.component.acl.XSchoolsACL;
import org.ricone.api.xpress.error.exception.NotFoundException;
import org.ricone.api.xpress.model.XErrorResponse;
import org.ricone.api.xpress.model.XSchoolResponse;
import org.ricone.api.xpress.model.XSchoolsResponse;
import org.ricone.api.xpress.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("XPress:XSchools:XSchoolController")
@Api(value = "xSchools", description = "REST API for xSchools", tags = {"xSchools"})
public class XSchoolController extends BaseController {
	@Autowired
	private XSchoolService service;

	@XSchoolsACL.Get.ById
	@ResponseBody @GetMapping(value = "/requests/xSchools/{id}")
	@ApiOperation(value = "Return xSchool by refId or localId", tags = {"xSchools"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XSchoolResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XSchoolResponse getXSchoolById(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		if(Util.isRefId(id)) {
			return service.findByRefId(getMetaData(request, response), id);
		}
		else if(StringUtils.equalsIgnoreCase(request.getHeader("idType"), "local")) {
			return service.findByLocalId(getMetaData(request, response), id);
		}
		throw new NotFoundException("Id: " + id + " is not a valid refId. You may be missing the 'IdType' header.");
	}

	@XSchoolsACL.Get.All
	@ResponseBody @GetMapping(value = "/requests/xSchools")
	@ApiOperation(value = "Return all xSchools", tags = {"xSchools"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XSchoolsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XSchoolsResponse getXSchools(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.findAll(getMetaData(request, response));
	}

	@XSchoolsACL.Get.ByXLea
	@ResponseBody @GetMapping(value = "/requests/xLeas/{refId}/xSchools")
	@ApiOperation(value = "Return all xSchools by xLea refId", tags = {"xSchools"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XSchoolsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XSchoolsResponse getXSchoolsByXLea(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByLea(getMetaData(request, response), refId);
	}

	@XSchoolsACL.Get.ByXCalendar
	@ResponseBody @GetMapping(value = "/requests/xCalendars/{refId}/xSchools")
	@ApiOperation(value = "Return all xSchools by xCalendar refId", tags = {"xSchools"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XSchoolsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XSchoolsResponse getXSchoolsByXCalendar(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByCalendar(getMetaData(request, response), refId);
	}

	@XSchoolsACL.Get.ByXCourse
	@ResponseBody @GetMapping(value = "/requests/xCourses/{refId}/xSchools")
	@ApiOperation(value = "Return all xSchools by xCourse refId", tags = {"xSchools"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XSchoolsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XSchoolsResponse getXSchoolsByXCourse(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByCourse(getMetaData(request, response), refId);
	}

	@XSchoolsACL.Get.ByXRoster
	@ResponseBody @GetMapping(value = "/requests/xRosters/{refId}/xSchools")
	@ApiOperation(value = "Return all xSchools by xRoster refId", tags = {"xSchools"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XSchoolsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XSchoolsResponse getXSchoolsByXRoster(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByRoster(getMetaData(request, response), refId);
	}

	@XSchoolsACL.Get.ByXStaff
	@ResponseBody @GetMapping(value = "/requests/xStaffs/{refId}/xSchools")
	@ApiOperation(value = "Return all xSchools by xStaff refId", tags = {"xSchools"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XSchoolsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XSchoolsResponse getXSchoolsByXStaff(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByStaff(getMetaData(request, response), refId);
	}

	@XSchoolsACL.Get.ByXStudent
	@ResponseBody @GetMapping(value = "/requests/xStudents/{refId}/xSchools")
	@ApiOperation(value = "Return all xSchools by xStudent refId", tags = {"xSchools"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XSchoolsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XSchoolsResponse getXSchoolsByXStudent(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByStudent(getMetaData(request, response), refId);
	}

	@XSchoolsACL.Get.ByXContact
	@ResponseBody @GetMapping(value = "/requests/xContacts/{refId}/xSchools")
	@ApiOperation(value = "Return all xSchools by xContact refId", tags = {"xSchools"})
	@ApiResponses({
			@ApiResponse(code = 200, message = "Success", response = XSchoolsResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = XErrorResponse.class),
			@ApiResponse(code = 401, message = "Unauthorized", response = XErrorResponse.class),
			@ApiResponse(code = 403, message = "Forbidden", response = XErrorResponse.class),
			@ApiResponse(code = 404, message = "Not Found", response = XErrorResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = XErrorResponse.class)
	})
	public XSchoolsResponse getXSchoolsByXContact(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "refId") String refId) throws Exception {
		return service.findAllByContact(getMetaData(request, response), refId);
	}
}
