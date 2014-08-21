package id.co.sigma.commonlib.importengine.io;

import java.util.List;

import id.co.sigma.commonlib.importengine.data.SimpleFlatFileData;

import org.springframework.batch.item.ItemWriter;

public class ItemWriterTester implements ItemWriter<SimpleFlatFileData> {

	public void write(List<? extends SimpleFlatFileData> dataToWrites) throws Exception {
		if(dataToWrites!=null&&!dataToWrites.isEmpty()){
			int idx =0;
			for (SimpleFlatFileData scn : dataToWrites ){
				String wrt = "" ; 
				for ( Object scObject : scn.getData()){
					wrt +="," + scObject;
				}
				System.out.println(">>" +  idx + ">>" + wrt);
				idx++;
			}
		}
		
	}

}
