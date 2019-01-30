package org.ricone.api.oneroster.request.courses;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.oneroster.component.BaseFieldSelector;
import org.ricone.api.oneroster.component.ControllerData;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.model.Class;
import org.springframework.stereotype.Component;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2019-01-30
 */

@Component("OneRoster:Courses:CourseFieldSelector")
public class CourseFieldSelector extends BaseFieldSelector<Course> {
	public CourseFieldSelector() {
		super(Course.class);
	}

	CoursesResponse apply(CoursesResponse response, ControllerData metadata) {
		if(metadata.getFieldSelection().hasFieldSelection() && metadata.getFieldSelection().isValidFieldSelection(getModelClass())) {
			for (Course c : response.getCourse()) {
				selectBaseFields(c, metadata);
			}

			if (CollectionUtils.isEmpty(response.getCourse())) {
				return null;
			}
		}
		return response;
	}

	CourseResponse apply(CourseResponse response, ControllerData metadata) {
		if(metadata.getFieldSelection().hasFieldSelection() && metadata.getFieldSelection().isValidFieldSelection(getModelClass())) {
			selectBaseFields(response.getCourse(), metadata);
			if (response.getCourse() == null) {
				return null;
			}
		}
		return response;
	}

	@Override
	public void selectFields(Course instance, ControllerData metaData) {
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "title")) {
			instance.setTitle(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "schoolYear")) {
			instance.setSchoolYear(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "courseCode")) {
			instance.setCourseCode(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "grades")) {
			instance.setGrades(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "subjects")) {
			instance.setSubjects(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "subjectCodes")) {
			instance.setSubjectCodes(null);
		}
		if(!CollectionUtils.containsAny(metaData.getFieldSelection().getFields(), "org")) {
			instance.setOrg(null);
		}
	}
}