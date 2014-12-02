package id.co.sigma.common.report.domain;

import id.co.sigma.common.data.SingleKeyEntityData;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the rpt_document database table.
 * 
 */
@Entity
@Table(name="rpt_document")
@NamedQuery(name="RptDocument.findAll", query="SELECT r FROM RptDocument r")
public class RptDocument implements Serializable, SingleKeyEntityData<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String description;

	private String name;

	private String template;

	//bi-directional many-to-one association to RptDocParam
	@OneToMany(fetch=FetchType.LAZY, targetEntity=RptDocParam.class, cascade={CascadeType.ALL})
	@JoinColumn(name="rpt_doc_id", insertable=false, updatable=false)
	private List<RptDocParam> rptDocParams;

	public RptDocument() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTemplate() {
		return this.template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public List<RptDocParam> getRptDocParams() {
		return this.rptDocParams;
	}

	public void setRptDocParams(List<RptDocParam> rptDocParams) {
		this.rptDocParams = rptDocParams;
	}

	public RptDocParam addRptDocParam(RptDocParam rptDocParam) {
		getRptDocParams().add(rptDocParam);
		rptDocParam.setRptDocument(this);

		return rptDocParam;
	}

	public RptDocParam removeRptDocParam(RptDocParam rptDocParam) {
		getRptDocParams().remove(rptDocParam);
		rptDocParam.setRptDocument(null);

		return rptDocParam;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (!(obj instanceof RptDocument))
			return false;
		RptDocument other = (RptDocument) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}