package org.ricone.api.xpress.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.ricone.config.ConfigService;
import org.ricone.config.model.App;
import org.ricone.error.exception.BadRequestException;
import org.ricone.security.jwt.Application;
import org.ricone.util.Util;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Dan Whitehouse <daniel.whitehouse@neric.org>
 * @version 2.0.0
 * @since 2018-11-20
 */

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class ControllerData {
	public static final String LEA_LOCAL_ID = "leaId";
	public static final String SCHOOL_YEAR_KEY = "schoolYear";
	private final String CHANGES_SINCE_MARKER = "changesSinceMarker";
	private final String AUPP_CREATE = "createUsers";
	private final String AUPP_GET = "getUsers";
	private final String AUPP_DELETE = "deleteUsers";
	private final String AUPP_DELETE_PASSWORDS = "deletePasswords";
	private final String AUPP_DELETE_USERNAMES = "usernames";
	public final String ID_TYPE = "IdType";
	private final String BASIC_SINGLE = "Basic_Single";
	private final String BASIC_MULTI = "Basic_Multi";
	private final String SORT = "sort";
	private final String ORDER_BY = "orderBy";

	/* MetaData Vars */
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Pageable pageable;
	private Pageable pageable2;
	private String providerId;
	private Application application;


	ControllerData(HttpServletRequest request, HttpServletResponse response, Pageable pageable) throws BadRequestException {
		super();
		this.request = request;
		this.response = response;
		this.pageable = getPaging(pageable);

		this.pageable2 = new PageData(request).getPageable();
		this.application = (Application) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	private Pageable getPaging(Pageable pageable) throws BadRequestException {
		Pageable paging = pageable;
		if(NumberUtils.isDigits(getHeader("page")) && NumberUtils.isDigits(getHeader("size"))) {
			int page = Integer.parseInt(getHeader("page"));
			int size = Integer.parseInt(getHeader("size"));
			if(page < 1) {
				//If it is, the server will throw 500, and say that the Page index must not be less than zero!
				throw new BadRequestException("Page index must not be less than one!");
			}
			paging = PageRequest.of(page-1, size, null);
		}

		if(pageable.isPaged()) {
			response.setHeader("X-Page",  String.valueOf(paging.getPageNumber()+1));
			response.setHeader("X-Size", String.valueOf(paging.getPageSize()));
		}

		return paging;
	}


	// Custom Methods - Headers
	public String getHeader(String header) {
		return request.getHeader(header);
	}

	public boolean isHeaderValue(String headerName, String value) { return StringUtils.equalsIgnoreCase(request.getHeader(headerName), value); }

	public boolean isRequestParameterValue(String param, String value) { return StringUtils.equalsIgnoreCase(request.getParameter(param), value); }

	public boolean hasUrlQueryParameter(String param) { return StringUtils.isNotBlank(request.getParameter(param)); }


	// Custom Methods - Query Parameters - AUPP
	public boolean isCreateAUPP() {
		return "true".equalsIgnoreCase(request.getParameter(AUPP_CREATE));
	}

	public boolean isGetAUPP() {
		return "true".equalsIgnoreCase(request.getParameter(AUPP_GET));
	}

	public boolean isDeleteAUPP() { return "true".equalsIgnoreCase(request.getParameter(AUPP_DELETE)); }

	public boolean isDeletePasswordsAUPP() { return "true".equalsIgnoreCase(request.getParameter(AUPP_DELETE_PASSWORDS)); }

	public boolean isDeleteUsernamesAUPP() { return "true".equalsIgnoreCase(request.getParameter(AUPP_DELETE_USERNAMES)); }


	// Custom Methods - Changes Since - Event Log
	public boolean hasChangesSinceMarker() { return request.getParameter(CHANGES_SINCE_MARKER) != null; }

	private String getChangesSinceMarker() {
		return request.getParameter(CHANGES_SINCE_MARKER);
	}

	public Date getChangesSinceLocalDateTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		LocalDateTime ldt = LocalDateTime.parse(getChangesSinceMarker(), formatter);
		return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
	}

	// Custom Methods - School Year Rollover
	public boolean hasSchoolYear() {
		String schoolYear = request.getHeader(SCHOOL_YEAR_KEY);
		return StringUtils.isNotBlank(schoolYear) && Util.isValidSchoolYear(schoolYear);
	}

	public String getSchoolYear() {
		return request.getHeader(SCHOOL_YEAR_KEY);
	}

	public boolean hasSortAndOrderBy() {
		return pageable.getSort() != null;
	}

	public Sort getSort() {
		return pageable.getSort();
	}

	// Custom Methods - Basic_Single & Basic_Multi App


	// GETTERS & SETTERS
	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Application getApplication() {
		return application;
	}

	public Pageable getPaging() {
		System.out.println(pageable);
		return pageable;
	}

	public Pageable getPaging2() {
		System.out.println(pageable2);
		return pageable2;
	}

}