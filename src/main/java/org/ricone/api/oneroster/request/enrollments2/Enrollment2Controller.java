package org.ricone.api.oneroster.request.enrollments2;

import org.ricone.api.oneroster.component.BaseController;
import org.ricone.api.oneroster.model.EnrollmentResponse;
import org.ricone.api.oneroster.model.EnrollmentsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
class Enrollment2Controller extends BaseController {
	@Autowired
	private EnrollmentService service;

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/enrollments2/{id}")
	EnrollmentResponse getEnrollment(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return service.getEnrollment(getMetaData(request, response), id);
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/enrollments2")
	EnrollmentsResponse getAllEnrollments(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return service.getAllEnrollments(getMetaData(request, response));
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/schools/{id}/enrollments2")
	EnrollmentsResponse getEnrollmentsForSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "id") String id) throws Exception {
		return null;
	}

	@ResponseBody
	@GetMapping(value = "/ims/oneroster/v1p1/schools/{schoolId}/classes/{classId}/enrollments2")
	EnrollmentsResponse getEnrollmentsForClassInSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "schoolId") String schoolId, @PathVariable(value = "classId") String classId) throws Exception {
		return null;
	}
}