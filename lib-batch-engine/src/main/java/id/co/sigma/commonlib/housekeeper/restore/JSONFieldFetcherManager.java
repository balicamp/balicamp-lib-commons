package id.co.sigma.commonlib.housekeeper.restore;

import id.co.sigma.commonlib.housekeeper.restore.impl.BaseJSONFieldFetcher;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import sun.security.util.BigInt;

import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public final class JSONFieldFetcherManager implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1326010354164382941L;



	private Map<Integer, IJSONFieldFetcher> indexedFetcher ; 
	
	
	
	private static JSONFieldFetcherManager instance ; 
	
	private JSONFieldFetcherManager (){
		indexedFetcher= new HashMap<Integer, IJSONFieldFetcher>();
		register(new BaseJSONFieldFetcher() {
			   /**
			 * 
			 */
			private static final long serialVersionUID = -2937933439645206374L;

			@Override
			   public int getAcceptedType() {  return java.sql.Types.BIT; }
			   @Override
			   public Object getActualRepresentation(JsonElement element) {
				   return element.getAsBoolean();
			   }
			   
			   @Override
			protected Object getActualRepresentation(JsonReader reader) throws Exception {
				return (Integer)reader.nextInt();
			}
			  });
			register(new BaseJSONFieldFetcher() {
			   /**
				 * 
				 */
				private static final long serialVersionUID = -3696817144670150314L;
			@Override
			   public int getAcceptedType() {  return java.sql.Types.TINYINT; }
			   @Override
			   public Object getActualRepresentation(JsonElement element) {
				   return element.getAsShort();
			   }
			   protected Object getActualRepresentation(JsonReader reader) throws Exception {
					return (Integer)reader.nextInt();
				}
			  });
			register(new BaseJSONFieldFetcher() {
			   /**
				 * 
				 */
				private static final long serialVersionUID = 2695581956660460559L;
			@Override
			   public int getAcceptedType() {  return java.sql.Types.SMALLINT; }
			   @Override
			   public Object getActualRepresentation(JsonElement element) {return element.getAsShort();}
			   protected Object getActualRepresentation(JsonReader reader) throws Exception {
					return (Integer)reader.nextInt();
				}
			  });
			register(new BaseJSONFieldFetcher() {
			   /**
				 * 
				 */
				private static final long serialVersionUID = 1123721914534801641L;
			@Override
			   public int getAcceptedType() {  return java.sql.Types.INTEGER; }
			   @Override
			   public Object getActualRepresentation(JsonElement element) {return element.getAsInt();}
			   protected Object getActualRepresentation(JsonReader reader) throws Exception {
					return (Integer)reader.nextInt();
				}
			  });
			register(new BaseJSONFieldFetcher() {
			   /**
				 * 
				 */
				private static final long serialVersionUID = 1970696695682488330L;
			@Override
			   public int getAcceptedType() {  return java.sql.Types.BIGINT; }
			   @Override
			   public Object getActualRepresentation(JsonElement element) {return element.getAsBigInteger();}
			   protected Object getActualRepresentation(JsonReader reader) throws Exception {
					int swap =  reader.nextInt();
					return new BigInteger(swap +"");
				}
			  });
			register(new BaseJSONFieldFetcher() {
			   /**
				 * 
				 */
				private static final long serialVersionUID = 7253744100995681674L;
			@Override
			   public int getAcceptedType() {  return java.sql.Types.FLOAT; }
			   @Override
			   public Object getActualRepresentation(JsonElement element) {return element.getAsFloat();}
			   protected Object getActualRepresentation(JsonReader reader) throws Exception {
					return new Float( reader.nextDouble());
				}
			  });
			register(new BaseJSONFieldFetcher() {
			   /**
				 * 
				 */
				private static final long serialVersionUID = -4897320087726637037L;
			@Override
			   public int getAcceptedType() {  return java.sql.Types.REAL; }
			   @Override
			   public Object getActualRepresentation(JsonElement element) {return element.getAsNumber();}
			   protected Object getActualRepresentation(JsonReader reader) throws Exception {
					return new Float( reader.nextDouble());
				}
			  });
			register(new BaseJSONFieldFetcher() {
			   /**
				 * 
				 */
				private static final long serialVersionUID = 2972875041318114097L;
			@Override
			   public int getAcceptedType() {  return java.sql.Types.DOUBLE; }
			   @Override
			   public Object getActualRepresentation(JsonElement element) {return element.getAsDouble();}
			   protected Object getActualRepresentation(JsonReader reader) throws Exception {
					return (Double) reader.nextDouble();
				}
			  });
			register(new BaseJSONFieldFetcher() {
			   @Override
			   public int getAcceptedType() {  return java.sql.Types.NUMERIC; }
			   @Override
			   public Object getActualRepresentation(JsonElement element) {return element.getAsBigDecimal();}
			   protected Object getActualRepresentation(JsonReader reader) throws Exception {
				   
					double d =  reader.nextDouble();
					return new BigDecimal(d); 
				}
			  });
			register(new BaseJSONFieldFetcher() {
			   /**
				 * 
				 */
				private static final long serialVersionUID = 4102467811136506969L;
			@Override
			   public int getAcceptedType() {  return java.sql.Types.DECIMAL; }
			   @Override
			   public Object getActualRepresentation(JsonElement element) {return element.getAsBigDecimal();}
			   protected Object getActualRepresentation(JsonReader reader) throws Exception {
				   
					double d =  reader.nextDouble();
					return new BigDecimal(d); 
				}
			  });
			register(new BaseJSONFieldFetcher() {
			   @Override
			   public int getAcceptedType() {  return java.sql.Types.CHAR; }
			   @Override
			   public Object getActualRepresentation(JsonElement element) {return element.getAsString();}
			   protected Object getActualRepresentation(JsonReader reader) throws Exception {
				 return reader.nextString();  
				}
			  });
			register(new BaseJSONFieldFetcher() {
			   /**
				 * 
				 */
				private static final long serialVersionUID = 7333294100966921661L;
			@Override
			   public int getAcceptedType() {  return java.sql.Types.VARCHAR; }
			   @Override
			   public Object getActualRepresentation(JsonElement element) {return element.getAsString();}
			   protected Object getActualRepresentation(JsonReader reader) throws Exception {
					 return reader.nextString();  
					}
			  });
			register(new BaseJSONFieldFetcher() {
			   /**
				 * 
				 */
				private static final long serialVersionUID = 1888603096211421713L;
			@Override
			   public int getAcceptedType() {  return java.sql.Types.LONGVARCHAR; }
			   @Override
			   public Object getActualRepresentation(JsonElement element) {return element.getAsString();}
			   protected Object getActualRepresentation(JsonReader reader) throws Exception {
					 return reader.nextString();  
					}
			  });
			register(new BaseJSONFieldFetcher() {
			   /**
				 * 
				 */
				private static final long serialVersionUID = -5385578655102480815L;
			@Override
			   public int getAcceptedType() {  return java.sql.Types.DATE; }
			   @Override
			   public Object getActualRepresentation(JsonElement element) {Long l = element.getAsLong(); 
			   return new Date(l);
			   }
			   protected Object getActualRepresentation(JsonReader reader) throws Exception {
					 long d =  reader.nextLong();
					 return new Date(d);
					}
			  });
			register(new BaseJSONFieldFetcher() {
			   @Override
			   public int getAcceptedType() {  return java.sql.Types.TIME; }
			   @Override
			   public Object getActualRepresentation(JsonElement element) {
				   Long l = element.getAsLong(); 
				   return new Date(l);
				}
			   protected Object getActualRepresentation(JsonReader reader) throws Exception {
					 long d =  reader.nextLong();
					 return new Date(d);
					}
			  });
			register(new BaseJSONFieldFetcher() {
			   @Override
			   public int getAcceptedType() {  return java.sql.Types.TIMESTAMP; }
			   @Override
			   public Object getActualRepresentation(JsonElement element) {Long l = element.getAsLong(); 
			   return new Date(l);}
			   protected Object getActualRepresentation(JsonReader reader) throws Exception {
					 long d =  reader.nextLong();
					 return new Date(d);
					}
			  });
			register(new BaseJSONFieldFetcher() {
			   @Override
			   public int getAcceptedType() {  return java.sql.Types.BOOLEAN; }
			   @Override
			   public Object getActualRepresentation(JsonElement element) {return element.getAsBoolean();}
			   protected Object getActualRepresentation(JsonReader reader) throws Exception {
					 return reader.nextBoolean(); 
					 
					}
			  });
			register(new BaseJSONFieldFetcher() {
			   @Override
			   public int getAcceptedType() {  return java.sql.Types.CLOB; }
			   @Override
			   public Object getActualRepresentation(JsonElement element) {return element.getAsString();}
			   protected Object getActualRepresentation(JsonReader reader) throws Exception {
					 return reader.nextString();  
					}
			  });
			/*register(new BaseJSONFieldFetcher() {
			   @Override
			   public int getAcceptedType() {  return java.sql.Types.ROWID; }
			   @Override
			   public Object getActualRepresentation(JsonElement element) {}
			  });*/
			register(new BaseJSONFieldFetcher() {
			   @Override
			   public int getAcceptedType() {  return java.sql.Types.NCHAR; }
			   @Override
			   public Object getActualRepresentation(JsonElement element) {return element.getAsString();}
			   protected Object getActualRepresentation(JsonReader reader) throws Exception {
					 return reader.nextString();  
					}
			  });
			register(new BaseJSONFieldFetcher() {
			   @Override
			   public int getAcceptedType() {  return java.sql.Types.NVARCHAR; }
			   @Override
			   public Object getActualRepresentation(JsonElement element) {return element.getAsString();}
			   protected Object getActualRepresentation(JsonReader reader) throws Exception {
					 return reader.nextString();  
					}
			  });
			register(new BaseJSONFieldFetcher() {
			   @Override
			   public int getAcceptedType() {  return java.sql.Types.LONGNVARCHAR; }
			   @Override
			   public Object getActualRepresentation(JsonElement element) {return element.getAsString();}
			   protected Object getActualRepresentation(JsonReader reader) throws Exception {
					 return reader.nextString();  
					}
			  });
			register(new BaseJSONFieldFetcher() {
			   @Override
			   public int getAcceptedType() {  return java.sql.Types.NCLOB; }
			   @Override
			   public Object getActualRepresentation(JsonElement element) {return element.getAsString();}
			   protected Object getActualRepresentation(JsonReader reader) throws Exception {
					 return reader.nextString();  
					}
			  });


	}
	
	
	/**
	 * register fetcher
	 **/
	public void register( IJSONFieldFetcher fetcher){
		indexedFetcher.put(fetcher.getAcceptedType(), fetcher); 
		 
	}
	
	
	
	/**
	 * membaca fetcher dengan data type
	 **/
	public IJSONFieldFetcher getFetcher ( int sqlType ){
		return indexedFetcher.get(sqlType);
	}
	
	
	public static JSONFieldFetcherManager getInstance() {
		if ( instance == null)
			instance = new JSONFieldFetcherManager(); 
		return instance;
	}
	
	

}
