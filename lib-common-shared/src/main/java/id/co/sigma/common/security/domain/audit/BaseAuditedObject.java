/**
 * File Name : BaseAuditedObject.java
 * Package   : id.co.sigma.arium.security.shared.domain.audit
 * Project   : security-data
 */
package id.co.sigma.common.security.domain.audit;



import id.co.sigma.common.data.IModifyAuditedData;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Mapped class yg berisi field : MODIFIED_BY, MODIFIED_ON, CREATED_BY, CREATED_ON
 * @author I Gede Mahendra
 * @since Nov 9, 2012, 11:42:27 AM
 * @version $Id
 */
@MappedSuperclass
public class BaseAuditedObject extends BaseCreatedObject implements IModifyAuditedData{

	private static final long serialVersionUID = -100757937025456226L;

	@Column(name="modified_by", length=32)
	private String modifiedBy;
	
	@Column(name="modified_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;
	
	/* (non-Javadoc)
	 * @see id.co.sigma.arium.security.shared.domain.audit.IModifyAuditedObject#getModifiedBy()
	 */
	@Override
	public String getModifiedBy() {
		return modifiedBy;
	}
	
	/* (non-Javadoc)
	 * @see id.co.sigma.arium.security.shared.domain.audit.IModifyAuditedObject#setModifiedBy(java.lang.String)
	 */
	@Override
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	/* (non-Javadoc)
	 * @see id.co.sigma.arium.security.shared.domain.audit.IModifyAuditedObject#getModifiedOn()
	 */
	@Override
	public Date getModifiedOn() {
		return modifiedOn;
	}
	
	/* (non-Javadoc)
	 * @see id.co.sigma.arium.security.shared.domain.audit.IModifyAuditedObject#setModifiedOn(java.util.Date)
	 */
	@Override
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result
				+ ((modifiedOn == null) ? 0 : modifiedOn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseAuditedObject other = (BaseAuditedObject) obj;
		if (modifiedBy == null) {
			if (other.modifiedBy != null)
				return false;
		} else if (!modifiedBy.equals(other.modifiedBy))
			return false;
		if (modifiedOn == null) {
			if (other.modifiedOn != null)
				return false;
		} else if (!modifiedOn.equals(other.modifiedOn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaseAuditedObject [modifiedBy=" + modifiedBy + ", modifiedOn="
				+ modifiedOn + "]";
	}

	@Override
	public void setCreatorIPAddress(String ipAddress) {
		
		
	}

	@Override
	public String getCreatorIPAddress() {
		
		return null;
	}

	@Override
	public void setModifiedByIPAddress(String ipAddress) {
		
		
	}

	@Override
	public String getModifiedByIPAddress() {
		
		return null;
	}
}