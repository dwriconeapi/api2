package org.ricone.api.xpress.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Objects;
import java.util.stream.Stream;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"@refId", "localId", "stateProvinceId", "ncesId", "leaName", "address", "phoneNumber", "otherPhoneNumbers", "metadata"})
@JsonRootName(value = "xLea")
public class XLea {
	@JsonProperty("@refId")
	@JacksonXmlProperty(localName = "refId", isAttribute = true)
	private String refId;
	@JsonProperty("localId")
	private String localId;
	@JsonProperty("stateProvinceId")
	private String stateProvinceId;
	@JsonProperty("ncesId")
	private String ncesId;
	@JsonProperty("leaName")
	private String leaName;
	@JsonProperty("address")
	private Address address;
	@JsonProperty("phoneNumber")
	private PhoneNumber phoneNumber;
	@JsonProperty("otherPhoneNumbers")
	private OtherPhoneNumbers otherPhoneNumbers;
	@JsonProperty("metadata")
	private Metadata metadata;

	public XLea() {
	}

	public XLea(String refId, String localId, String stateProvinceId, String ncesId, String leaName, Address address, PhoneNumber phoneNumber, OtherPhoneNumbers otherPhoneNumbers, Metadata metadata) {
		this.refId = refId;
		this.localId = localId;
		this.stateProvinceId = stateProvinceId;
		this.ncesId = ncesId;
		this.leaName = leaName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.otherPhoneNumbers = otherPhoneNumbers;
		this.metadata = metadata;
	}

	@JsonProperty("@refId")
	@JacksonXmlProperty(localName = "refId", isAttribute = true)
	public String getRefId() {
		return refId;
	}

	@JsonProperty("@refId")
	public void setRefId(String refId) {
		this.refId = refId;
	}

	@JsonProperty("localId")
	public String getLocalId() {
		return localId;
	}

	@JsonProperty("localId")
	public void setLocalId(String localId) {
		this.localId = localId;
	}

	@JsonProperty("stateProvinceId")
	public String getStateProvinceId() {
		return stateProvinceId;
	}

	@JsonProperty("stateProvinceId")
	public void setStateProvinceId(String stateProvinceId) {
		this.stateProvinceId = stateProvinceId;
	}

	@JsonProperty("ncesId")
	public String getNcesId() {
		return ncesId;
	}

	@JsonProperty("ncesId")
	public void setNcesId(String ncesId) {
		this.ncesId = ncesId;
	}

	@JsonProperty("leaName")
	public String getLeaName() {
		return leaName;
	}

	@JsonProperty("leaName")
	public void setLeaName(String leaName) {
		this.leaName = leaName;
	}

	@JsonProperty("address")
	public Address getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(Address address) {
		this.address = address;
	}

	@JsonProperty("phoneNumber")
	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	@JsonProperty("phoneNumber")
	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JsonProperty("otherPhoneNumbers")
	public OtherPhoneNumbers getOtherPhoneNumbers() {
		return otherPhoneNumbers;
	}

	@JsonProperty("otherPhoneNumbers")
	public void setOtherPhoneNumbers(OtherPhoneNumbers otherPhoneNumbers) {
		this.otherPhoneNumbers = otherPhoneNumbers;
	}

	@JsonProperty("metadata")
	public Metadata getMetadata() {
		return metadata;
	}

	@JsonProperty("metadata")
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	@JsonIgnore
	public boolean isEmptyObject() {
		return Stream.of(refId, localId, stateProvinceId, ncesId, leaName, address, phoneNumber, otherPhoneNumbers, metadata).allMatch(Objects::isNull);
	}

	@Override
	public String toString() {
		return "XLea{" + "refId='" + refId + '\'' + ", localId='" + localId + '\'' + ", stateProvinceId='" + stateProvinceId + '\'' + ", ncesId='" + ncesId + '\'' + ", leaName='" + leaName + '\'' + ", address=" + address + ", phoneNumber=" + phoneNumber + ", otherPhoneNumbers=" + otherPhoneNumbers + ", metadata=" + metadata + '}';
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof XLea)) return false;

		XLea xLea = (XLea) o;

		if(refId != null ? !refId.equals(xLea.refId) : xLea.refId != null) return false;
		if(localId != null ? !localId.equals(xLea.localId) : xLea.localId != null) return false;
		if(stateProvinceId != null ? !stateProvinceId.equals(xLea.stateProvinceId) : xLea.stateProvinceId != null)
			return false;
		if(ncesId != null ? !ncesId.equals(xLea.ncesId) : xLea.ncesId != null) return false;
		if(leaName != null ? !leaName.equals(xLea.leaName) : xLea.leaName != null) return false;
		if(address != null ? !address.equals(xLea.address) : xLea.address != null) return false;
		if(phoneNumber != null ? !phoneNumber.equals(xLea.phoneNumber) : xLea.phoneNumber != null) return false;
		if(otherPhoneNumbers != null ? !otherPhoneNumbers.equals(xLea.otherPhoneNumbers) : xLea.otherPhoneNumbers != null)
			return false;
		return metadata != null ? metadata.equals(xLea.metadata) : xLea.metadata == null;
	}

	@Override
	public int hashCode() {
		int result = refId != null ? refId.hashCode() : 0;
		result = 31 * result + (localId != null ? localId.hashCode() : 0);
		result = 31 * result + (stateProvinceId != null ? stateProvinceId.hashCode() : 0);
		result = 31 * result + (ncesId != null ? ncesId.hashCode() : 0);
		result = 31 * result + (leaName != null ? leaName.hashCode() : 0);
		result = 31 * result + (address != null ? address.hashCode() : 0);
		result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
		result = 31 * result + (otherPhoneNumbers != null ? otherPhoneNumbers.hashCode() : 0);
		result = 31 * result + (metadata != null ? metadata.hashCode() : 0);
		return result;
	}
}