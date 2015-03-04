package id.co.sigma.common.server.data.lookup;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Primary key dari table m_lookup_detail, ini untuk membuat class comply dengan JPA 1 
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 */
@Embeddable
public class Common1LevelLOVHeaderPK implements Serializable {
	
	private static final long serialVersionUID = -7711783511851271575L;

	/**
	 * column : LOV_ID<br/>
	 * Id dari lookup
	 **/
	@Column(name="LOV_ID" )
	private  String lovId;

	/**
	 * column : LOV_ID<br/>
	 * Id dari lookup
	 **/
	public String getLovId() {
		return lovId;
	}

	/**
	 * column : LOV_ID<br/>
	 * Id dari lookup
	 **/
	public void setLovId(String lovId) {
		this.lovId = lovId;
	}

	@Override
	public String toString() {
		return "Common1LevelLOVHeaderPK [lovId=" + lovId + "]";
	}

	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((lovId == null) ? 0 : lovId.hashCode());
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
		return true;
	    }
	    if (obj == null) {
		return false;
	    }
	    if (!(obj instanceof Common1LevelLOVHeaderPK)) {
		return false;
	    }
	    Common1LevelLOVHeaderPK other = (Common1LevelLOVHeaderPK) obj;
	    if (lovId == null) {
		if (other.lovId != null) {
		    return false;
		}
	    } else if (!lovId.equals(other.lovId)) {
		return false;
	    }
	    return true;
	}
	
}


