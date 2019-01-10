package org.ricone.api.oneroster.request.users;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.ricone.api.core.model.*;
import org.ricone.api.core.model.wrapper.StudentContactWrapper;
import org.ricone.api.oneroster.model.*;
import org.ricone.api.oneroster.util.MappingUtil;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("OneRoster:Users:ContactMapper")
class ContactMapper {
    ContactMapper() {
    }

    UsersResponse convert(List<StudentContactWrapper> instance) {
        List<User> list = new ArrayList<>();
        for (StudentContactWrapper wrapper : instance) {
            User user = map(wrapper.getStudentContact(), wrapper.getDistrictId());
            if(user != null) {
                list.add(user);
            }
        }

        UsersResponse response = new UsersResponse();
        response.setUsers(list);
        return response;
    }

    UserResponse convert(StudentContactWrapper wrapper) {
        if(wrapper != null) {
            UserResponse response = new UserResponse();
            response.setUser(map(wrapper.getStudentContact(), wrapper.getDistrictId()));
            return response;
        }
        return null;
    }

    private User map(StudentContact instance, String districtId) {
        User user = new User();
        user.setSourcedId(instance.getStudentContactRefId());
        user.setStatus(StatusType.active);
        user.setDateLastModified(null);
        user.setMetadata(mapMetadata(instance, districtId));

        user.setRole(getRoleTypeFromRelationships(instance.getStudentContactRelationships()));
        user.setGivenName(instance.getFirstName());
        user.setFamilyName(instance.getLastName());
        user.setMiddleName(instance.getMiddleName());
        //user.setIdentifier();

        //Account
        //user.setUsername();
        //user.setPassword();
        //user.setEnabledUser();

        //Orgs
        if(CollectionUtils.isNotEmpty(instance.getStudentContactRelationships())) {
            Set<String> schools = new HashSet<>();
            Set<String> districts = new HashSet<>();

            instance.getStudentContactRelationships().forEach(studentContactRelationship -> {
                if(studentContactRelationship.getStudent() != null) {
                    studentContactRelationship.getStudent().getStudentEnrollments().forEach(studentEnrollment -> {
                        if(studentEnrollment.getSchool() != null) {
                            schools.add(studentEnrollment.getSchool().getSchoolRefId());

                            if(studentEnrollment.getSchool().getLea() != null) {
                                districts.add(studentEnrollment.getSchool().getLea().getLeaRefId());
                            }
                        }
                    });
                }
            });

            schools.forEach(schoolRefId -> {
                user.getOrgs().add(MappingUtil.buildGUIDRef("schools", schoolRefId, GUIDType.org));
            });

            districts.forEach(districtRefId -> {
                user.getOrgs().add(MappingUtil.buildGUIDRef("orgs", districtRefId, GUIDType.org));
            });
        }

        //Agents - ie: Contacts
        if(CollectionUtils.isNotEmpty(instance.getStudentContactRelationships())) {
            instance.getStudentContactRelationships().forEach(scr -> {
                if(scr.getStudent() != null) {
                    user.getAgents().add(MappingUtil.buildGUIDRef("students", scr.getStudent().getStudentRefId(), GUIDType.user));
                }
            });
        }

        //Email
        if(CollectionUtils.isNotEmpty(instance.getStudentContactEmails())) {
            Optional<StudentContactEmail> primaryEmail = instance.getStudentContactEmails().stream().filter(email -> BooleanUtils.isTrue(email.getPrimaryEmailAddressIndicator())).findFirst();
            primaryEmail.ifPresent(studentEmail -> user.setEmail(studentEmail.getEmailAddress()));
        }

        //Phone
        if(CollectionUtils.isNotEmpty(instance.getStudentContactTelephones())) {
            Optional<StudentContactTelephone> primaryPhone = instance.getStudentContactTelephones().stream().filter(telephone -> BooleanUtils.isTrue(telephone.getPrimaryTelephoneNumberIndicator())).findFirst();
            primaryPhone.ifPresent(telephone -> user.setPhone(telephone.getTelephoneNumber()));

            Optional<StudentContactTelephone> smsPhone = instance.getStudentContactTelephones().stream().filter(telephone -> "SMS".equalsIgnoreCase(telephone.getTelephoneNumberTypeCode())).findFirst();
            smsPhone.ifPresent(telephone -> user.setSms(telephone.getTelephoneNumber()));
        }

        //UserIds
        if(CollectionUtils.isNotEmpty(instance.getStudentContactIdentifiers())) {
            instance.getStudentContactIdentifiers().forEach(si -> user.getUserIds().add(new UserId(si.getIdentificationSystemCode(), si.getStudentContactId())));
        }
        return user;
    }

    private Metadata mapMetadata(StudentContact instance, String districtId) {
        Metadata metadata = new Metadata();
        metadata.getAdditionalProperties().put("ricone.schoolYear", instance.getStudentContactSchoolYear());
        metadata.getAdditionalProperties().put("ricone.districtId", districtId);
        return metadata;
    }

    private RoleType getRoleTypeFromRelationships(Set<StudentContactRelationship> studentContactRelationships) {
        //TODO - This isn't completely correct.
        int parent;
        int guardian;

        parent = (int) studentContactRelationships.stream().filter(scr -> StringUtils.containsIgnoreCase(scr.getRelationshipCode(), "Mother") || StringUtils.containsIgnoreCase(scr.getRelationshipCode(), "Father")).count();
        guardian = (int) studentContactRelationships.stream().filter(scr -> StringUtils.containsIgnoreCase(scr.getRelationshipCode(), "Guardian")).count();

        if(parent > 0) {
            return RoleType.parent;
        }

        if(guardian > 0) {
            return RoleType.guardian;
        }

        return RoleType.relative;
    }
}