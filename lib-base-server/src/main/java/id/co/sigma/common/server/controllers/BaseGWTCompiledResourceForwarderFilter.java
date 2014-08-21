package id.co.sigma.common.server.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *@author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public abstract class BaseGWTCompiledResourceForwarderFilter implements Filter{

	
	
	
	
	/**
	 * replacer kalau path != /
	 **/
	Pattern slashPattern = Pattern.compile("/"); 
	
	/**
	 * path getter
	 **/
	protected interface  RealPathGetter {
		  String getFileRealPath ( HttpServletRequest httpReq , String path ); 
		
	}
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(BaseGWTCompiledResourceForwarderFilter.class); 
	public static final String SUFFIX_MARKER_VERSIONED_JS ="versioned";
	
	private static final String GZIP_EXT =".gzip"; 
	
	
	 
	
	
	
	
	
	protected static ArrayList<String> EXT_TO_ZIPS =new ArrayList<String>();
	
	
	
	/**
	 * true kalau path separator != /
	 **/
	private boolean doPathSeperatorReplacement = false; 
	
	/**
	 * path getter untuk servlet 3(jetty 8 ke atas) 
	 **/
	private static final RealPathGetter SERVLET_3_PATH_GETTER = new RealPathGetter(){
		public String getFileRealPath(HttpServletRequest httpReq, String path) {
			return httpReq.getServletContext().getRealPath(path); 
		};
	};
	
	private static final RealPathGetter SERVLET_OLDER_PATH_GETTER = new RealPathGetter(){
		public String getFileRealPath(HttpServletRequest httpReq, String path) {
			return httpReq.getRealPath(path); 
		};
	};
	
	
	
	static {
		EXT_TO_ZIPS.add(".html");
		EXT_TO_ZIPS.add(".htm");
		EXT_TO_ZIPS.add(".js");	
		EXT_TO_ZIPS.add(".rpc");
		EXT_TO_ZIPS.add(".css");
		EXT_TO_ZIPS.add(".xml");
	}
	
	
	
	protected Map<String , String> pathWithVersion ;
	
	
	
	protected RealPathGetter pathGetter ; 
	
	
	
	/**
	 * ini untuk mematikan gzip
	 **/
	public boolean isTurnOffGzip() {
		return false;
	}
	
	
	/**
	 * lokasi di mana file di zip akan di create pada saat applikasi di bootstrap
	 **/
	protected String gzipFolderLocation ; 
	
	@Override
	public void destroy() {
		if ( this.cachedGZIPPath.isEmpty()) 
			return ; 
		for ( String pathTmp : cachedGZIPPath.values()){
			try {
				(new File(pathTmp)).delete(); 
			} catch (Exception e) {
				logger.error("gagal hapus file : "  + pathTmp + ", error : " + e.getMessage()  , e);
			}
		}
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		if (!( request instanceof HttpServletRequest)){
			chain.doFilter(request, response);
			return; 
		}
		HttpServletRequest httpReq = (HttpServletRequest)request ;
		
		
		  
		HttpServletResponse httpResp = (HttpServletResponse)response; 
		String uri =  httpReq.getRequestURI();
		for ( String scn : pathWithVersion.keySet()){
			if ( uri.startsWith(scn)){
				String orgUrl = pathWithVersion.get(scn); 
				String uriActual  =  uri.replaceFirst(scn, orgUrl);
				if ( acceptGZIP(request, response)){
					for (String ext : EXT_TO_ZIPS){
						if (uri.endsWith(ext)){
							uriActual+=GZIP_EXT;
							httpResp.setHeader("Content-Encoding", "gzip");
							String absPath = getGzipPath( uriActual);
							File absFile  = new File(absPath); 
							if ( !absFile.exists()){
								logger.debug("file gzip: " + absPath + " , tidak di temukan. serve dengan normal page");
								httpReq.getRequestDispatcher(uriActual).forward(request, response);
								return  ; 
							}
							
							logger.debug("mengirimkan versi gzip dari absolute path : " + absPath);
							IOUtils.copy(new FileInputStream(new File(absPath)), httpResp.getOutputStream());
							return     ; 
						}
					}
					
				} else {
					logger.debug("client tidak menerima gzip. mengirim data normal");
					httpReq.getRequestDispatcher(uriActual).forward(request, response);
					return ; 
				}
			}
		}
		
		chain.doFilter(request, response);
		
	}
	
	
	
	
	
	
	private Map<String, String> cachedGZIPPath = new HashMap<String, String>(); 
	
	
	
	
	/**
	 * cari path gzip dengan uri 
	 **/
	protected String getGzipPath ( String browserUri){
		if ( cachedGZIPPath.containsKey(browserUri))
			return cachedGZIPPath.get(browserUri); 
		if ( doPathSeperatorReplacement){
			if ( "\\".equals(File.separator)){
				browserUri = slashPattern.matcher(browserUri).replaceAll( File.separator +File.separator );
			}else{
			browserUri = slashPattern.matcher(browserUri).replaceAll( File.separator );
			}
		}
		if ( !browserUri.startsWith(File.separator))
			browserUri = File.separator + browserUri ; 
		String absPath = this.gzipFolderLocation +  browserUri ; 
		cachedGZIPPath.put(browserUri, absPath); 
		return absPath ; 
	}
	
	
	
	/**
	 * check ada support gzip ndak
	 **/
	protected boolean acceptGZIP (ServletRequest request, ServletResponse response ){
		if ( isTurnOffGzip())
			return false; 
		if (!( request instanceof HttpServletRequest ) || this.gzipFolderLocation== null ||gzipFolderLocation.isEmpty()) 
			return false; 
		HttpServletRequest rq = (HttpServletRequest)request;
		String acEnc =  rq.getHeader("accept-encoding") ;
		return acEnc!= null && acEnc.indexOf("gzip")!=-1 ; 
	}
	
	
	
	

	@Override
	public void init(FilterConfig config) throws ServletException {
		pathWithVersion = new HashMap<String, String>(); 
		String cVersion  = getCurrentVersion();
		String basePath = config.getServletContext().getContextPath(); 
		
		
		if ( !isTurnOffGzip()){
			try {
				this.gzipFolderLocation =  Files.createTempDirectory("automated-gzip-" ).toFile().getAbsolutePath();
				if ( gzipFolderLocation.endsWith(File.separator))
					gzipFolderLocation = gzipFolderLocation.substring(0, gzipFolderLocation.length()-1); 
				logger.debug("lokasi gzip file akan di taruh di :" + gzipFolderLocation); 
			} catch (IOException e) {
				logger.error("gagal membuat file, error di laporkan : " + e.getMessage() ) ; 
				this.gzipFolderLocation = null  ; 
				e.printStackTrace();
			} 
			if (! basePath.endsWith("/"))
				basePath +="/";
			
			String bashAppAbsPath =  config.getServletContext().getRealPath("/");
			if (! bashAppAbsPath.endsWith(File.separator)){
				bashAppAbsPath += File.separator; 
			}
			
			ArrayList<File> pathToZip = new ArrayList<File>(); 
			
			for ( String scn : getVersionedPaths()){
				pathWithVersion.put(basePath + scn +"-"+SUFFIX_MARKER_VERSIONED_JS+"/" + cVersion +"/" , "/" + scn +"/");
				pathToZip.add(new File(bashAppAbsPath + File.separator + scn));
			}
			for ( File f : pathToZip){
				String namaFolder =  f.getName();
				File folder = new File( gzipFolderLocation + File.separator  + namaFolder);
				folder.mkdir(); 
				makeAllGzipped (folder.getAbsolutePath(),   f );
			}
		}
		
		if ( config.getServletContext().getMajorVersion()>2){
			pathGetter = SERVLET_3_PATH_GETTER ; 
		}else{
			pathGetter = SERVLET_OLDER_PATH_GETTER; 
		}
		doPathSeperatorReplacement = !( File.separator.equals("/")); 
	}
	
	
	
	
	
	
	
	
	
	/**
	 * path yang di kasi versi. sederhana nya ini folder compiled GWT. ini tidak boleh di akhiri dengan /
	 **/
	protected abstract String[] getVersionedPaths () ; 
	
	
	
	/**
	 * versi saat ini/ versi berapa
	 **/
	protected abstract String getCurrentVersion () ; 
	
	
	
	
	
	/**
	 * rekursif. ini meng-gzip file dalam folder
	 * @param gzipRootPath root path dari tmp di mana gzip file perlu di taruh 
	 **/
	protected void makeAllGzipped ( final String currentPath ,   File baseFolder ) {
		File[] files =  baseFolder.listFiles();
		if ( files!= null&&files.length>0){
			for ( File scn : files){
				if ( scn.isDirectory()){
					String nextPath = currentPath + File.separator + scn.getName() ; 
					File fld = new File(nextPath); 
					fld.mkdir(); 
					makeAllGzipped( nextPath ,   scn ); 
					continue ; 
				}
				else{	
					String outPath = currentPath + File.separator + scn.getName() +GZIP_EXT;
					makeGZIPCloneFile(scn, new File(outPath));
				}
			}	
			
		}
		
	}
	
	
	
	 
	
	
	
	
	
	
	/**
	 * membuat gzip clone pada folder yang di minta
	 **/
	public void makeGZIPCloneFile ( File fileToGZip , File targetGzipPath ){
		
		if ( !fileToGZip.exists() || gzipFolderLocation == null||gzipFolderLocation.isEmpty())
			return ;
		
		boolean match = false ; 
		for ( String ext :   EXT_TO_ZIPS){
			if ( fileToGZip.getAbsolutePath().endsWith(ext)){
				match = true ; 
				break ; 
			}
		}
		if ( !match)
			return ; 
		byte[] buffer = new byte[1024];
		try {
			
			
			 
			
			
			GZIPOutputStream gzos =  
		    		new GZIPOutputStream(new FileOutputStream(targetGzipPath));
			 FileInputStream in = 
			            new FileInputStream(fileToGZip);
			 int len;
		        while ((len = in.read(buffer)) > 0) {
		        	gzos.write(buffer, 0, len);
		        }
		 
		        in.close();
		 
		    	gzos.finish();
		    	gzos.close();
		    	System.out.println("sukses gzip file : " + fileToGZip +"> " + targetGzipPath.getAbsolutePath());
			
		} catch (Exception e) {
			e.printStackTrace() ; 
		}
	}
	
	 

}
