package id.co.sigma.commonlib.exportengine;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import id.co.sigma.commonlib.exportengine.data.ExportToTextFileMetadata;
import id.co.sigma.commonlib.exportengine.io.ITargetFileLocationGenerator;




public class TargetFileLocationGeneratortesterImpl implements ITargetFileLocationGenerator{

	@Override
	public Resource generateTargetForWritingOutput(
			ExportToTextFileMetadata metaData,
			Map<String, Object> jobStartParameter) {
		try {
			File f = File.createTempFile("batch", "engine") ;
			String path =f.getAbsolutePath() ;
			System.out.println("menulis ke path : " + path );
			return new FileSystemResource(f); 
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
