package org.ricone.api.oneroster.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.ricone.api.oneroster.component.BaseMultiResponse;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"demographics"})
public class DemographicsResponse extends BaseMultiResponse<Demographic> implements Serializable {
	private final static long serialVersionUID = 6089973485073338986L;

	public DemographicsResponse(List<Demographic> demographics) {
		super(demographics);
	}

	public DemographicsResponse(List<Demographic> demographics, List<StatusInfoSet> errors) {
		super(demographics, errors);
	}

	@JsonProperty("demographics")
	@Override public List<Demographic> getData() {
		return super.getData();
	}

	@JsonProperty("demographics")
	@Override public void setData(List<Demographic> demographics) {
		super.setData(demographics);
	}
}