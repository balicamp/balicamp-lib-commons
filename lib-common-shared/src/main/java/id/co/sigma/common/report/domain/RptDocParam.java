package id.co.sigma.common.report.domain;

import id.co.sigma.common.data.SingleKeyEntityData;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;


/**
 * The persistent class for the rpt_doc_param database table.
 * 
 */
@Entity
@Table(name="rpt_doc_param")
@NamedQuery(name="RptDocParam.findAll", query="SELECT r FROM RptDocParam r")
public class RptDocParam implements Serializable, SingleKeyEntityData<RptDocParamPK> {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RptDocParamPK id = new RptDocParamPK();
	
	@Column(name="param_code", insertable=false, updatable=false)
	private String paramCode;

	@Column(name="data_format")
	private String dataFormat;

	@Column(name="default_value")
	private String defaultValue;

	private String description;

	@Column(name="param_label")
	private String paramLabel;

	@Column(name="param_type")
	private String paramType;
	
	@Column(name="rpt_doc_id", insertable=false, updatable=false)
	private Integer rptDocId;

	//bi-directional many-to-one association to RptDocument
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=RptDocument.class)
	@JoinColumn(name="rpt_doc_id", insertable=false, updatable=false, referencedColumnName="id")
	private RptDocument rptDocument;

	public RptDocParam() {
	}


	/**
	 * @return the id
	 */
	public RptDocParamPK getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(RptDocParamPK id) {
		this.id = id;
	}


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


	public String getDataFormat() {
		return this.dataFormat;
	}

	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}

	public String getDefaultValue() {
		return this.defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParamLabel() {
		return this.paramLabel;
	}

	public void setParamLabel(String paramLabel) {
		this.paramLabel = paramLabel;
	}

	public String getParamType() {
		return this.paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
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

	public RptDocument getRptDocument() {
		return this.rptDocument;
	}

	public void setRptDocument(RptDocument rptDocument) {
		this.rptDocument = rptDocument;
	}

	@PrePersist
	public void prePersist() {
		if ( id == null)
			id = new RptDocParamPK() ;
		id.setParamCode(this.paramCode);
		id.setRptDocId(this.rptDocId);
	}

}