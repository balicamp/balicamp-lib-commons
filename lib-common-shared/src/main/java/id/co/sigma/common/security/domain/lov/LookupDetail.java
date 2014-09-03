package id.co.sigma.common.security.domain.lov;

import id.co.sigma.common.data.lov.ILookupDetail;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;



/**
 * Lookup details
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 * @version $Id
 **/
@Entity
@Table(name="m_lookup_details" )
@NamedQuery(name="LOV::GET_LOV_BY-I18AND_IDS",query="SELECT "+
	"		a "+
	"	FROM "+
	"		LookupHeader a "+
	"	where "+
	"		a.i18Key=:I18"+
	"		and a.id in :IDS	")
public class LookupDetail implements Serializable , ILookupDetail, IJSONFriendlyObject<LookupDetail>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4290526898112024416L;

	
	
	
	
	/**
	 * id
	 **/
	@EmbeddedId
	private LookupDetailPK id = new LookupDetailPK(); 
	
	
	@ManyToOne(targetEntity=LookupHeader.class , fetch=FetchType.LAZY  )
	@JoinColumns( 
				value={
						@JoinColumn(name="LOV_ID" , updatable=false , insertable=false , referencedColumnName="LOV_ID") 
				} 
			)
	
	private LookupHeader header ;
	
	
	
	
	/**
	 * column : DETAIL_CODE, di redundan dengan ID. INi non insertable + non updateable
	 **/
	@Column(name="DETAIL_CODE" , insertable=false, updatable=false)
	private String detailCode ;
	
	@Column(name="LOV_LABEL")
	private String label ;
	
	@Column(name="SEQ_NO")
	private Integer sequence ;
	
	@Column(name="LOV_ID" , updatable=false  , insertable=false)
	private String headerId ;
	
	@Column(name="VAL_1")
	private String extField1 ;
	
	@Column(name="VAL_2")
	private String extField2 ;
	
	
	/**
	 * kode internalization
	 * column : i18_CODE. 
	 **/
	@Column(name="i18_CODE", updatable = false , insertable = false )
	private String i18Key ; 
	
	
	public LookupDetail() {
		
	}

	
	public LookupDetail(LookupHeader header, String detailCode, String label,
			Integer sequence) {
		super();
		this.header = header;
		this.detailCode = detailCode;
		this.label = label;
		this.sequence = sequence;
		this.headerId = header.getId(); 
	}


	public LookupHeader getHeader() {
		return header;
	}

	public void setHeader(LookupHeader header) {
		this.header = header;
	}
	/**
	 * column : DETAIL_CODE, di redundan dengan ID. INi non insertable + non updateable
	 **/
	public String getDetailCode() {
		return detailCode;
	}
	/**
	 * column : DETAIL_CODE, di redundan dengan ID. INi non insertable + non updateable
	 **/
	public void setDetailCode(String detailCode) {
		this.detailCode = detailCode;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	
	
	public String getHeaderId() {
		return headerId;
	}

	public String getExtField1() {
		return extField1;
	}

	public void setExtField1(String extField1) {
		this.extField1 = extField1;
	}

	public String getExtField2() {
		return extField2;
	}

	public void setExtField2(String extField2) {
		this.extField2 = extField2;
	}

	public void setHeaderId(String headerId) {
		this.headerId = headerId;
	}
	

	@Override
	public String getParentId() {
		
		return headerId;
	}

	@Override
	public String getDataValue() {
		return this.detailCode;
	}

	@Override
	public String getAdditionalData2() {
		return this.extField2;
	}

	@Override
	public String getAdditionalData1() {
		return this.extField1;
	}
	
	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("detailCode",getDetailCode());
		jsonContainer.put("extField1",getExtField1());
		jsonContainer.put("extField2",getExtField2());
		  
		 LookupHeader param5 = getHeader();   
		 if ( param5 != null){ 
		
 //1. Ok tampung dulu variable
			List param5_details_tmp = param5.getDetails();
//2. null kan variable 
			param5.setDetails(null);
// 3 taruh ke json
			jsonContainer.put("header", param5);
//4. restore lagi 
			param5.setDetails(param5_details_tmp);
		}
		jsonContainer.put("header",getHeader());
		jsonContainer.put("headerId",getHeaderId());
		jsonContainer.put("label",getLabel());
		jsonContainer.put("sequence",getSequence());
	}
	
	@Override
	public LookupDetail instantiateFromJSON(ParsedJSONContainer jsonContainer) {
		LookupDetail retval = new LookupDetail();
		retval.setDetailCode( (String)jsonContainer.get("detailCode" ,  String.class.getName()));
		retval.setExtField1( (String)jsonContainer.get("extField1" ,  String.class.getName()));
		retval.setExtField2( (String)jsonContainer.get("extField2" ,  String.class.getName()));
		  
		retval.setHeader( (LookupHeader)jsonContainer.get("header" ,  LookupHeader.class.getName()));
		retval.setHeaderId( (String)jsonContainer.get("headerId" ,  String.class.getName()));
		retval.setLabel( (String)jsonContainer.get("label" ,  String.class.getName()));
		retval.setSequence( (Integer)jsonContainer.get("sequence" ,  Integer.class.getName()));
		return retval; 
	}
	
	
	@PrePersist
	public void prePersist() {
		if ( id == null)
			id = new LookupDetailPK() ;
		id.setLovID(headerId);
		id.setLovDetailId(detailCode);
		id.setI18Code(i18Key);
	}
	/**
	 * kode internalization
	 * column : i18_CODE. 
	 **/
	public void setI18Key(String i18Key) {
		this.i18Key = i18Key;
	}
	/**
	 * kode internalization
	 * column : i18_CODE. 
	 **/
	public String getI18Key() {
		return i18Key;
	}
	
	

}
