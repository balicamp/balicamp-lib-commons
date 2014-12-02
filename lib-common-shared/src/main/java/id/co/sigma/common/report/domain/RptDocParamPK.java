package id.co.sigma.common.report.domain;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Report Dokumen parameter composite primary key
 * rpt_doc_id (FK dari Report Document)
 * param_code
 * @author windu
 *
 */
@Embeddable
public class RptDocParamPK implements Serializable, IsSerializable, IJSONFriendlyObject<RptDocParamPK> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6824384306122474274L;

	@Column(name="param_code")
	private String paramCode;
	
	@Column(name="rpt_doc_id")
	private Integer rptDocId;
		
	/**
	 * @return the paramCode
	 */
	public String getParamCode() {
		return paramCode;
	}

	/**
	 * @param paramCode the paramCode to set
	 */
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	/**
	 * @return the rptDocId
	 */
	public Integer getRptDocId() {
		return rptDocId;
	}

	/**
	 * @param rptDocId the rptDocId to set
	 */
	public void setRptDocId(Integer rptDocId) {
		this.rptDocId = rptDocId;
	}

	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RptDocParamPK instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((paramCode == null) ? 0 : paramCode.hashCode());
		result = prime * result
				+ ((rptDocId == null) ? 0 : rptDocId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof RptDocParamPK))
			return false;
		RptDocParamPK other = (RptDocParamPK) obj;
		if (paramCode == null) {
			if (other.paramCode != null)
				return false;
		} else if (!paramCode.equals(other.paramCode))
			return false;
		if (rptDocId == null) {
			if (other.rptDocId != null)
				return false;
		} else if (!rptDocId.equals(other.rptDocId))
			return false;
		return true;
	}

}
