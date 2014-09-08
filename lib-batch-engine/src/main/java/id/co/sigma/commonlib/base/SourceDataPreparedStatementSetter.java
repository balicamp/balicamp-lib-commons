package id.co.sigma.commonlib.base;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.PreparedStatementSetter;



/**
 * setter prepared statment untuk journal source
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 **/
public class SourceDataPreparedStatementSetter implements PreparedStatementSetter{
	
	
	private static final Logger logger = LoggerFactory.getLogger(SourceDataPreparedStatementSetter.class);
	
	/**
	 * array of named parameter
	 **/
	private String [] namedParameters ;

	
	private Map<String, Object> indexedStatementParam ; 
	private Map<String, Class<?>> valueDataTypes;
	
	private String selectStatementParameterGroupKey ;
	
	
	static Map<String, Class<?> > indexedTypes = new HashMap<String, Class<?>>(); 
	static {
		indexedTypes.put(String.class.getName(), String.class) ; 
		indexedTypes.put(Long.class.getName(), Long.class) ;
		indexedTypes.put(Double.class.getName(), Double.class) ;
		indexedTypes.put(Date.class.getName(), Date.class) ;
		
		
	}
	
	
	
	
	private Map<Class<?>, Integer> dataTypes = new HashMap<Class<?>, Integer>();
	
	public SourceDataPreparedStatementSetter() {
		dataTypes.put(Date.class, 0);
		dataTypes.put(String.class, 1);
		dataTypes.put(Number.class, 2);
		dataTypes.put(Long.class, 3);
		dataTypes.put(Integer.class, 4);
		dataTypes.put(Double.class, 5);
		dataTypes.put(BigInteger.class, 6);
		dataTypes.put(BigDecimal.class, 7);
		dataTypes.put(java.util.Date.class, 0);
	}
	
	
	@Override
	public void setValues(PreparedStatement prepStatment) throws SQLException {
		if ( namedParameters== null|| namedParameters.length==0){
			System.out.println("named parameter null atau 0 length");
		}
		else{
			for ( int i =0 ; i < namedParameters.length ; i++){
				try {
					String namedParam =  namedParameters[i]; 
					Object swap = indexedStatementParam.get(namedParam) ; 
					Class<?> dataType = valueDataTypes.get(namedParam);
					switch (dataTypes.get(dataType)) {
					case 0:
						if ( swap instanceof java.util.Date){
							java.util.Date actDate = (java.util.Date)swap ; 
							prepStatment.setDate(i+1,  new Date( actDate.getTime()));
						}else{
							prepStatment.setDate(i+1, (Date)swap);
						}
						
						break;
					case 1:
						prepStatment.setString(i+1, (String)swap);
						break;
					case 2:
						prepStatment.setBigDecimal(i+1, (BigDecimal)swap);
						break;
					case 3:
						prepStatment.setLong(i+1, (Long)swap);
						break;
					case 4:
						prepStatment.setInt(i+1, (Integer)swap);
						break;
					case 5:
						prepStatment.setDouble(i+1, (Double)swap);
						break;
					case 6 : //biginteger
						prepStatment.setLong(i+1, ((BigInteger)swap).longValue());
						break ; 
					case 7 : 
						prepStatment.setBigDecimal(i+1, (BigDecimal)swap);
						break ; 	
					default : 
						prepStatment.setObject(i+1, (Double)swap);
						break ; 
					}
				} catch (Exception e) {
					logger.error("gagal set parameter " + i+1 + ",error message :" + e.getMessage() , e);
					e.printStackTrace(); 
					if(e instanceof SQLException)
						throw (SQLException)e ; 
				}
				 
			}
		}
		
	}
	
	public void setNamedParameters(String[] namedParameters) {
		this.namedParameters = namedParameters;
	}

	public void setIndexedStatementParam(
			Map<String, Object> indexedStatementParam) {
		this.indexedStatementParam = indexedStatementParam;
	}
	
	
	
	
	/**
	 * menaruh parameter dengan SQL param holder
	 */
	public void setIndexedStatementParam( SQLParameterHolderArray namedParameter) throws ClassNotFoundException{
		this.indexedStatementParam = new HashMap<String, Object>(); 
		this.valueDataTypes = new HashMap<String, Class<?>>(); 
		Iterator<SQLParameterHolder> itrs =  namedParameter.iterator();
		//Object swap = indexedStatementParam.get(namedParam) ; 
		//Class<?> dataType = valueDataTypes.get(namedParam);
		while (itrs.hasNext()) {
			SQLParameterHolder h =  itrs.next() ;
			indexedStatementParam.put(h.getParamName(), h.getParameter());
			valueDataTypes.put(h.getParamName(), Class.forName(h.getParamFqcn()));
		}
		
	}

	public String getSelectStatementParameterGroupKey() {
		return selectStatementParameterGroupKey;
	}

	public void setSelectStatementParameterGroupKey(
			String selectStatementParameterGroupKey) {
		this.selectStatementParameterGroupKey = selectStatementParameterGroupKey;
	}


	public Map<String, Class<?>> getValueDataTypes() {
		return valueDataTypes;
	}


	public void setValueDataTypes(Map<String, Class<?>> valueDataTypes) {
		this.valueDataTypes = valueDataTypes;
	}

	
	
	

}
