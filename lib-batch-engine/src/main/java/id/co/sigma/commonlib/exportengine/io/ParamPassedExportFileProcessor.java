package id.co.sigma.commonlib.exportengine.io;

import org.springframework.batch.item.ItemProcessor;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public class ParamPassedExportFileProcessor implements ItemProcessor<Object[], String[]>{
	
	
	
	/**
	 * converter provider
	 */
	private IHaveConverter converterProvider; 

	@Override
	public String[] process(Object[] item) throws Exception {
		
		if ( item== null||item.length==0)
			return null ; 
		IObjectToStringConverter[] converters =  converterProvider.getConverters();
		int size = converters.length; 
		String [] retval = new String[size];  
		for ( int i = 0 ; i < converters.length; i++){
			retval[i] = converters[i].transform(item[i]); 
		}
		return retval;
	}

	/**
	 * converter provider
	 */
	public IHaveConverter getConverterProvider() {
		return converterProvider;
	}
	/**
	 * converter provider
	 */
	public void setConverterProvider(IHaveConverter converterProvider) {
		this.converterProvider = converterProvider;
	}
}
