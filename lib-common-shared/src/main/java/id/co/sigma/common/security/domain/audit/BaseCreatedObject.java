/**
 * File Name : BaseCreatedObject.java
 * Package   : id.co.sigma.arium.security.shared.domain.audit
 * Project   : security-data
 */
package id.co.sigma.common.security.domain.audit;

import com.google.gwt.user.client.rpc.IsSerializable;

import id.co.sigma.common.data.ICreateAuditedData;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Mapped class yg berisi field : CREATED_BY, CREATED_ON
 * @author I Gede Mahendra
 * @since Nov 9, 2012, 11:52:14 AM
 * @version $Id
 */
@MappedSuperclass
public class BaseCreatedObject implements Serializable,IsSerializable, ICreateAuditedData{

	private static final long serialVersionUID = 1L;
	
	@Column(name="creator_name", length=32)
	private String createdBy;
	
	@Column(name="created_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	/* (non-Javadoc)
	 * @see id.co.sigma.arium.security.shared.domain.audit.ICreateAuditedObject#getCreatedBy()
	 */
	@Override
	public String getCreatedBy() {
		return createdBy;
	}
	
	/* (non-Javadoc)
	 * @see id.co.sigma.arium.security.shared.domain.audit.ICreateAuditedObject#setCreatedBy(java.lang.String)
	 */
	@Override
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	/* (non-Javadoc)
	 * @see id.co.sigma.arium.security.shared.domain.audit.ICreateAuditedObject#getCreatedOn()
	 */
	@Override
	public Date getCreatedOn() {
		return createdOn;
	}
	
	/* (non-Javadoc)
	 * @see id.co.sigma.arium.security.shared.domain.audit.ICreateAuditedObject#setCreatedOn(java.util.Date)
	 */
	@Override
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdOn == null) ? 0 : createdOn.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseCreatedObject other = (BaseCreatedObject) obj;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaseCreatedObject [createdBy=" + createdBy + ", createdOn="
				+ createdOn + "]";
	}

	@Override
	public void setCreatorIPAddress(String ipAddress) {
		
		
	}

	@Override
	public String getCreatorIPAddress() {
		
		return null;
	}
}