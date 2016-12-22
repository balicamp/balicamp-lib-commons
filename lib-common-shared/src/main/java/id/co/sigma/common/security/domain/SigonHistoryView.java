package id.co.sigma.common.security.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import id.co.sigma.common.data.SingleKeyEntityData;

/**
 * controller budget usage detail list
 * 
 * @author <a href="mailto:gusti.darmawan@sigma.co.id">Eka D.</a>
 */
@Entity
@Table(name="v_sec_sigon_history")
public class SigonHistoryView implements SingleKeyEntityData<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id	
	@Column(name="pk")
	private String id;
	
	@Column(name="pk", insertable=false, updatable=false)
	private Long pk;
	
	@Column(name="user_id")
	private Long userId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", insertable=false, updatable=false)
	private User user;
	
	@Column(name="app_id")
	private Long applicationId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="app_id", insertable=false, updatable=false)
	private Application application;
	
	@Column(name="terminal", length=64)
	private String terminal;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="waktu")
	private Date waktu;
	
	@Column(name="description", length=256)
	private String description;
	
	@Column(name="activity", length=256)
	private String activity;
	
	

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public void setId(String id) {
		this.id = id;
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public Date getWaktu() {
		return waktu;
	}

	public void setWaktu(Date waktu) {
		this.waktu = waktu;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activity == null) ? 0 : activity.hashCode());
		result = prime * result + ((terminal == null) ? 0 : terminal.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((waktu == null) ? 0 : waktu.hashCode());
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
		SigonHistoryView other = (SigonHistoryView) obj;
		if (activity == null) {
			if (other.activity != null)
				return false;
		} else if (!activity.equals(other.activity))
			return false;
		if (terminal == null) {
			if (other.terminal != null)
				return false;
		} else if (!terminal.equals(other.terminal))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (waktu == null) {
			if (other.waktu != null)
				return false;
		} else if (!waktu.equals(other.waktu))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SigonHistoryView [userId=" + userId + ", terminal=" + terminal + ", waktu=" + waktu + ", activity="
				+ activity + "]";
	}

	@Override
	public String getId() {
		return id;
	}

}
