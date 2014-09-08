package id.co.sigma.common.server.data.system;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * common sequence table. table : 
 * ct_common_sequence
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
@Entity
@Table(name="ct_common_sequence" )
public class CommonSequenceTable implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7577380929165303628L;



	/**
	 * column : sequence_name
	 * id sequence
	 */
	@Id
	@Column(name="sequence_name" , length=128, nullable=false, updatable=false )
	private String id;
	
	
	
	/**
	 * column : latest_seq
	 * sequence terakhir nomor berapa
	 */
	@Column(name="latest_seq" , nullable=false)
	private Long latesSequence = 0L ; 
	
	/**
	 * remark dari sequence<br/>
	 * column : seq_remark
	 */
	@Column(name="seq_remark", length=128 , nullable=false)
	private String remark ; 
	
	/**
	 * column : sequence_name
	 * id sequence
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * column : sequence_name
	 * id sequence
	 */
	public String getId() {
		return id;
	}
	/**
	 * remark dari sequence<br/>
	 * column : seq_remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * remark dari sequence<br/>
	 * column : seq_remark
	 */
	public String getRemark() {
		return remark;
	}
	
	
	/**
	 * column : latest_seq
	 * sequence terakhir nomor berapa
	 */
	public void setLatesSequence(Long latesSequence) {
		this.latesSequence = latesSequence;
	}
	/**
	 * column : latest_seq
	 * sequence terakhir nomor berapa
	 */
	public Long getLatesSequence() {
		return latesSequence;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((latesSequence == null) ? 0 : latesSequence.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
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
		CommonSequenceTable other = (CommonSequenceTable) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (latesSequence == null) {
			if (other.latesSequence != null)
				return false;
		} else if (!latesSequence.equals(other.latesSequence))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CommonSequenceTable [id=" + id + ", latesSequence="
				+ latesSequence + ", remark=" + remark + "]";
	}
	

}
