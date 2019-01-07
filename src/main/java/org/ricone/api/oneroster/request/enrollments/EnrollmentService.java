package org.ricone.api.oneroster.request.enrollments;

import org.ricone.api.oneroster.model.EnrollmentResponse;
import org.ricone.api.oneroster.model.EnrollmentsResponse;
import org.ricone.api.oneroster.model.UserResponse;
import org.ricone.api.oneroster.model.UsersResponse;
import org.ricone.api.xpress.component.ControllerData;

public interface EnrollmentService {
	EnrollmentResponse getEnrollment(ControllerData metadata, String refId) throws Exception;

	EnrollmentsResponse getAllEnrollments(ControllerData metadata) throws Exception;

	EnrollmentsResponse getEnrollmentsForSchool(ControllerData metadata, String refId) throws Exception;

	EnrollmentsResponse getEnrollmentsForClassInSchool(ControllerData metadata, String refId) throws Exception;
}