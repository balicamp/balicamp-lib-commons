package id.co.sigma.common.data.query;

import com.google.gwt.user.client.rpc.IsSerializable;

import id.co.sigma.common.data.reflection.ClientReflectableClass;
import id.co.sigma.common.util.json.IJSONFriendlyObject;
import id.co.sigma.common.util.json.ParsedJSONContainer;



/**
 * pindahan dari cams-data. di bikinkian base class agar lebih low impact bagi code teman2
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
@ClientReflectableClass
public class SimpleSortArgument implements IsSerializable, IJSONFriendlyObject<SimpleSortArgument>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6961355138817706370L;

	/**
	 * field sort
	 **/
	protected String sortField ;
	
	/**
	 * ascding sort atau sebaliknya
	 **/
	protected boolean ascendingSort ;
	
	
	
	
	

	
	public SimpleSortArgument(){}
	
	
	/**
	 * konstruktor dengan sort field + asc/desc
	 **/
	public SimpleSortArgument(String sortField  ,  boolean ascendingSort ){
		this.sortField=sortField ; 
		this.ascendingSort=ascendingSort;
		
	}
	/**
	 * field sort
	 **/
	public String getSortField() {
		return sortField;
	}

	/**
	 * field sort
	 **/
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	
	/**
	 * ascding sort atau sebaliknya
	 **/
	public boolean isAscendingSort() {
		return ascendingSort;
	}
	/**
	 * ascding sort atau sebaliknya
	 **/
	public void setAscendingSort(boolean ascendingSort) {
		this.ascendingSort = ascendingSort;
	}

	
	@Override
	public String toString() {
		return  "{\"sortField\":\""+sortField+"\" , \"ascendingSort\":\""+ascendingSort+"\"}" ;
	}

    @Override
    public void translateToJSON(ParsedJSONContainer jsonContainerData) {
      jsonContainerData.put("sortField", getSortField());
      jsonContainerData.put("ascendingSort", isAscendingSort());
    }

    @Override
    public SimpleSortArgument instantiateFromJSON(ParsedJSONContainer jsonContainer) {
        return  new SimpleSortArgument(jsonContainer.getAsString("sortField"), jsonContainer.getAsBoolean("ascendingSort"));
    }
}
