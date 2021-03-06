package org.ricone.api.oneroster.request.academicSessions;

import org.ricone.api.oneroster.component.BaseFilterer;
import org.ricone.api.oneroster.component.error.exception.InvalidDataException;
import org.ricone.api.oneroster.component.error.exception.InvalidFilterFieldException;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Path;

@Component("OneRoster:AcademicSessions:AcademicSessionFilterer")
public class AcademicSessionFilterer extends BaseFilterer {
	public AcademicSessionFilterer() {
	}

	@Override
	public Path getPath(String field) throws InvalidFilterFieldException, InvalidDataException {
		switch(field) {
			case "sourcedId": return from.get(field);
			case "status": return from.get(field);
			case "dateLastModified": return from.get(field);
			case "metadata.ricone.schoolYear": return from.get("sourcedSchoolYear");
			case "metadata.ricone.districtId": return from.get("districtId");

			case "title": return from.get(field);
			case "startDate": return from.get(field);
			case "endDate": return from.get(field);
			case "type": return from.get(field);
			case "schoolYear": return from.get(field);

			case "parent.sourcedId": return from.get("academicSession").get("sourcedId");
			case "parent.href": throw new InvalidDataException(buildInvalidDataException(field));
			case "parent.type": throw new InvalidDataException(buildInvalidDataException(field));

			case "children.sourcedId": return getJoin("children").get("child").get("sourcedId");
			case "children.href": throw new InvalidDataException(buildInvalidDataException(field));
			case "children.type": throw new InvalidDataException(buildInvalidDataException(field));
			default: break;
		}
		throw new InvalidFilterFieldException("The filter parameter [" + field + "] is a non-existent field");
	}
}
