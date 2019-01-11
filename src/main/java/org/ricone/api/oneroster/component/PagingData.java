package org.ricone.api.oneroster.component;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ricone.api.xpress.error.exception.BadRequestException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PagingData {
	private Logger logger = LogManager.getLogger(PagingData.class);
	private final String OFFSET = "offset";
	private final String LIMIT = "limit";

	private HttpServletResponse response;
	private Integer offset = null;
	private Integer limit = null;

	PagingData(HttpServletRequest request, HttpServletResponse response) throws BadRequestException {
		this.response = response;

		if(NumberUtils.isDigits(request.getParameter(OFFSET))) {
			offset = Integer.parseInt(request.getParameter(OFFSET));
		}

		if(NumberUtils.isDigits(request.getParameter(LIMIT))) {
			limit = Integer.parseInt(request.getParameter(LIMIT));
		}

		if(isPaged()) {
			if(offset == null) offset = 0;
			if(limit == null) limit = 100;

			response.setHeader(OFFSET, String.valueOf(offset));
			response.setHeader(LIMIT, String.valueOf(limit));
		}
	}

	public boolean isPaged() {
		return limit != null;
	}

	public Integer getOffset() {
		return offset;
	}

	public Integer getLimit() {
		return limit;
	}

	@Override
	public String toString() {
		return "Paging{" + "offset=" + offset + ", limit=" + limit + '}';
	}
}