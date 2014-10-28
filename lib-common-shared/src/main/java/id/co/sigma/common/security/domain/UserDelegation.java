package id.co.sigma.common.security.domain;

import id.co.sigma.common.data.SingleKeyEntityData;
import id.co.sigma.common.security.domain.audit.BaseCreatedObject;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * POJO untuk tabel sec_user_delegate
 * @author <a href="mailto:ida.suartama@sigma.co.id">Goesde Rai</a>
 */

@Entity
@Table(name="sec_user_delegate")
public class UserDelegation extends BaseCreatedObject implements SingleKeyEntityData<Long>{
	
	private static final long serialVersionUID = -444707034539729931L;

	//static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserDelegation.class.getName());
	
	@Id
	@Column(name="id", nullable=false, updatable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="source_user_id")
	private Long sourceUserId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="source_user_id", insertable=false, updatable=false)
	private User sourceUser;
	
	@Column(name="dest_user_id")
	private Long destUserId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="dest_user_id", insertable=false, updatable=false)
	private User destUser;
	
	@Column(name="data_status")
	private String dataStatus;
	
	@Column(name="start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(name="end_date")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@Column(name="remark")
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSourceUserId() {
		return sourceUserId;
	}

	public void setSourceUserId(Long sourceUserId) {
		this.sourceUserId = sourceUserId;
	}
	
	public User getSourceUser() {
		return sourceUser;
	}
	
	public void setSourceUser(User sourceUser) {
		this.sourceUser = sourceUser;
	}

	public Long getDestUserId() {
		return destUserId;
	}

	public void setDestUserId(Long destUserId) {
		this.destUserId = destUserId;
	}
	
	public User getDestUser() {
		return destUser;
	}
	
	public void setDestUser(User destUser) {
		this.destUser = destUser;
	}

	public String getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dataStatus == null) ? 0 : dataStatus.hashCode());
		result = prime * result
				+ ((destUserId == null) ? 0 : destUserId.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime * result
				+ ((sourceUserId == null) ? 0 : sourceUserId.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof UserDelegation)) {
			return false;
		}
		UserDelegation other = (UserDelegation) obj;
		if (dataStatus == null) {
			if (other.dataStatus != null) {
				return false;
			}
		} else if (!dataStatus.equals(other.dataStatus)) {
			return false;
		}
		if (destUserId == null) {
			if (other.destUserId != null) {
				return false;
			}
		} else if (!destUserId.equals(other.destUserId)) {
			return false;
		}
		if (endDate == null) {
			if (other.endDate != null) {
				return false;
			}
		} else if (!endDate.equals(other.endDate)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (remark == null) {
			if (other.remark != null) {
				return false;
			}
		} else if (!remark.equals(other.remark)) {
			return false;
		}
		if (sourceUserId == null) {
			if (other.sourceUserId != null) {
				return false;
			}
		} else if (!sourceUserId.equals(other.sourceUserId)) {
			return false;
		}
		if (startDate == null) {
			if (other.startDate != null) {
				return false;
			}
		} else if (!startDate.equals(other.startDate)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "UserDelegation [id=" + id + ", sourceUserId=" + sourceUserId
				+ ", sourceUser=" + sourceUser + ", destUserId=" + destUserId
				+ ", destUser=" + destUser + ", dataStatus=" + dataStatus
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", remark=" + remark + "]";
	}
	
}
