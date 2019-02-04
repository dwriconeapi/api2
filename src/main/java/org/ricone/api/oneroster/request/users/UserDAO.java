package org.ricone.api.oneroster.request.users;

import org.ricone.api.core.model.view.UserView;
import org.ricone.api.oneroster.component.ControllerData;

import java.util.List;

interface UserDAO {
	/* Find */
	UserView getUser(ControllerData metadata, String refId);

	List<UserView> getAllUsers(ControllerData metadata);

	UserView getStudent(ControllerData metadata, String refId);

	List<UserView> getAllStudents(ControllerData metadata);

	List<UserView> getStudentsForSchool(ControllerData metadata, String refId);

	List<UserView> getStudentsForClass(ControllerData metadata, String refId);

	List<UserView> getStudentsForClassInSchool(ControllerData metadata, String refId, String classRefId);

	UserView getTeacher(ControllerData metadata, String refId);

	List<UserView> getAllTeachers(ControllerData metadata);

	List<UserView> getTeachersForSchool(ControllerData metadata, String refId);

	List<UserView> getTeachersForClass(ControllerData metadata, String refId);

	List<UserView> getTeachersForClassInSchool(ControllerData metadata, String schoolRefId, String classRefId);

	UserView getContact(ControllerData metadata, String refId);

	List<UserView> getAllContacts(ControllerData metadata);

	int countAllUsers(ControllerData metadata);

	int countAllStudents(ControllerData metadata);

	int countStudentsForSchool(ControllerData metadata, String refId);

	int countStudentsForClass(ControllerData metadata, String refId);

	int countStudentsForClassInSchool(ControllerData metadata, String refId, String classRefId);

	int countAllTeachers(ControllerData metadata);

	int countTeachersForSchool(ControllerData metadata, String refId);

	int countTeachersForClass(ControllerData metadata, String refId);

	int countTeachersForClassInSchool(ControllerData metadata, String schoolRefId, String classRefId);

	int countAllContacts(ControllerData metadata);
}