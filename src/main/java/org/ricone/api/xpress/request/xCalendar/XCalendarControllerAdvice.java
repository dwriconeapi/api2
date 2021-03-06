package org.ricone.api.xpress.request.xCalendar;

import org.apache.commons.collections4.CollectionUtils;
import org.ricone.api.xpress.model.XCalendarsResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

//https://stackoverflow.com/questions/44375435/spring-auto-add-x-total-count-header

@ControllerAdvice("XPress:XCalendars:XCalendarControllerAdvice")
public class XCalendarControllerAdvice implements ResponseBodyAdvice<XCalendarsResponse> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		//Checks if this advice is applicable.
		return XCalendarsResponse.class.isAssignableFrom(returnType.getParameterType());
	}

	@Override
	public XCalendarsResponse beforeBodyWrite(XCalendarsResponse body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		if(body != null && body.getXCalendars() != null) {
			if(CollectionUtils.isNotEmpty(body.getXCalendars().getXCalendar())) {
				response.getHeaders().add("X-Total-Count", String.valueOf(body.getXCalendars().getXCalendar().size()));
			}
		}
		return body;
	}
}