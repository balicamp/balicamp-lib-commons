package id.co.sigma.common.data;

import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;



/**
 * holder untuk data yang di akses dengan model paging. Jadinya dalam data ini sudah di pack data : 
 * <ol>
 * 	<li>page size</li>
 *  	<li>page(posisi berapa, basis 0)</li>
 *   	<li>total data yang ada dengan current filter</li>
 *    	<li>total page yang ada dengan<i>current filter</i></li>
 *     	<li>data pada page ini</li>
 * </ol>
 * 
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @param <DATA> data yang di select dari server
 **/
public class PagedResultHolder<DATA> implements Serializable, IsSerializable , IJSONFriendlyObject<PagedResultHolder<DATA>>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5228211456120608286L;
	
	/**
	 * posisi page yang di tampilkan dalam current pack. <i>Basis Index 1 </i>
	 **/
	private Integer page = 0 ; 
	/**
	 * ukuran page per pembacaan data
	 * 
	 **/
	private Integer pageSize = 20 ; 
	
	/**
	 * total data ini bakalan di pecah dalam berapa page
	 **/
	private Integer totalPage ; 
	/**
	 * total data yang available dalam table sesuai dengan current filter
	 **/
	private Integer totalData; 
	
	
	
	/**
	 * data yang di wrap dalam pack data 
	 **/
	protected List<DATA> holdedData ;
	
	
	/**
	 * posisi row pertama. ini untuk konsumsi query database
	 **/
	private Integer firstRowPosition ; 
	
	
	/**
	 * atur ulang paging. model ini dnegan mengirimkan sekalian ukuran page, page di baca dan tota data berdasarkan hasil count. method ini return firsr row yang perlu di baca
	 * @param page posisi page yang akan di baca
	 * @param pageSize ukuran page per pembacaan
	 * @param totalData total data yang match
	 * @return posisi row pertama yang akan di baca
	 **/
	public int adjustPagination (int page, int pageSize , int totalData){
		this.page = page ; 
		this.pageSize = pageSize ; 
		this.totalData = totalData ; 
		adjustPagination();
		return firstRowPosition ; 
	}
	
	
	public void adjustPagination (){
		 
		 
		this.totalPage =(int)Math.ceil( (double)totalData/(double)pageSize) ;
		if ( page<0)
			page=0; 
		if ( page>totalPage)
			page=totalPage; 
		firstRowPosition=  (page) * pageSize; 
	}

	
	/**
	 * posisi row pertama. ini untuk konsumsi query database
	 **/
	public Integer getFirstRowPosition() {
		return firstRowPosition;
	}

        /**
         * posisi row pertama. utnuk kemudahan di serial-kan
        */
        public void setFirstRowPosition(Integer firstRowPosition) {
            this.firstRowPosition = firstRowPosition;
        }
	
	
	
	
	
	
	
	/**
	 * posisi page yang di tampilkan dalam current pack. <i>Basis Index 0</i>
	 **/
	public void setPage(Integer page) {
		this.page = page;
	}
	/**
	 * posisi page yang di tampilkan dalam current pack. <i>Basis Index 0</i>
	 **/
	public Integer getPage() {
		return page;
	}
	
	/**
	 * ukuran page per pembacaan data
	 * 
	 **/
	public Integer getPageSize() {
		return pageSize;
	}
	
	/**
	 * ukuran page per pembacaan data
	 * 
	 **/
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * total data ini bakalan di pecah dalam berapa page
	 **/
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	/**
	 * total data ini bakalan di pecah dalam berapa page
	 **/
	public Integer getTotalPage() {
		
		
		
		
		return totalPage;
	}
	
	
	
	/**
	 * total data yang available dalam table sesuai dengan current filter
	 **/
	public void setTotalData(Integer totalData) {
		this.totalData = totalData;
	}
	
	/**
	 * total data yang available dalam table sesuai dengan current filter
	 **/
	public Integer getTotalData() {
		return totalData;
	}
	
	
	
	/**
	 * data yang di wrap dalam pack data 
	 **/
	public void setHoldedData(List<DATA> holdedData) {
		this.holdedData = holdedData;
	}
	
	/**
	 * data yang di wrap dalam pack data 
	 **/
	public List<DATA> getHoldedData() {
		return holdedData;
	}


	@Override
	public void translateToJSON(ParsedJSONContainer jsonContainer) {
		jsonContainer.put("page", getPage());
		jsonContainer.put("pageSize", getPageSize());
		jsonContainer.put("totalPage", getTotalPage());
		jsonContainer.put("totalData", getTotalData());
		jsonContainer.put("firstRowPosition", getFirstRowPosition());
		jsonContainer.putIfJsonEnableObjects("holdedData", getHoldedData());
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public PagedResultHolder<DATA> instantiateFromJSON(
			ParsedJSONContainer jsonContainer) {
		PagedResultHolder<DATA> retval = new PagedResultHolder< DATA>(); 
		retval.setFirstRowPosition(jsonContainer.getAsInteger("firstRowPosition"));
		retval.setPage(jsonContainer.getAsInteger("page"));
		retval.setPageSize(jsonContainer.getAsInteger("pageSize"));
		retval.setTotalData(jsonContainer.getAsInteger("totalData")); 
		retval.setTotalPage(jsonContainer.getAsInteger("totalPage"));
		String fqcn = jsonContainer.getArrayTypeFQCN("holdedData");
		if ( fqcn!= null){
			
			List tmp =  jsonContainer.getAsArraylist("holdedData", fqcn) ;
			if ( tmp!= null && !tmp.isEmpty() ){
				ArrayList<DATA> actual = new ArrayList< DATA>();
				actual.addAll(tmp);
				retval.setHoldedData(actual  );
			}
			
			
		}
		
		return retval;
	}

}
