package id.co.sigma.commonlib.exportengine.io;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class ConsoleWriter implements ItemWriter<String[]>{

	@Override
	public void write(List<? extends String[]> datum) throws Exception {
		if(datum==null||datum.isEmpty())
			return ;
		for ( String[] scn : datum){
			StringBuffer bfr = new StringBuffer("data:"); 
			for  ( String str : scn){
				bfr.append(str); 
				bfr.append("|");
				
			}
			bfr.deleteCharAt(bfr.length()-1);
			System.out.println(bfr.toString());
		}
					
		
	}

}
