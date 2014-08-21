package id.co.sigma.common.util;


import id.co.sigma.common.control.I18TextProvider;


public final  class I18Utilities {


	
	

	private static I18Utilities instance ;



	/**
	 * worker untuk menyediakan text yang inernationalize
	 **/
	private I18TextProvider i18TextProvider;  




	/**
	 * kode localization current. Locale yang di pakai skarang apa
	 **/
	private String currentLocalizationCode ="en"; 

	private I18Utilities(){


	}



	/**
	 * singleton instanc
	 **/
	public static I18Utilities getInstance() {
		if ( instance==null){
			instance = new I18Utilities();
		}
		return instance;
	}
	/**
	 * worker untuk menyediakan text yang inernationalize
	 **/
	protected I18TextProvider getI18TextProvider() {
		return i18TextProvider;
	}


	/**
	 * membaca i18 freindly text dengan data key
	 **/
	public String getInternalitionalizeText (String key ){
		if ( getI18TextProvider()!=null)
			return getI18TextProvider().getText(key);
		System.out.println(   "i18n provider null, key : " + key +" tidak di temukan"); 
		return null ; 
	}



	/**
	 * versi ini , kalau null atau 0 length akan return default label
	 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
	 * @param key key dari internationalized text
	 * @param defaultLabel text default
	 **/
	public String getInternalitionalizeText (String key , String defaultLabel ){
		if (key==null)
			return defaultLabel ; 
		String swap = getInternalitionalizeText(key); 
		if ( swap!=null&&swap.length()>0)
			return swap ; 
		return defaultLabel  ; 

	}
	
	
	
	
	/**
	 * membaca internationalized message. versi ini sekaligus juga akan mereplace message dengan argument yang di perlukan
	 **/
	public String getInternalitionalizeText (String key , String defaultLabel , Object[] replacementArg  ){
		if (key==null)
			return defaultLabel ; 
		String swap = getInternalitionalizeText(key); 
		if ( swap!=null&&swap.length()>0){
			if ( replacementArg!=null && replacementArg.length > 0  )
				swap =  I18Utilities.format(replacementArg, swap); 
			return swap ; 
		}
		return defaultLabel  ; 

	}


	/**
	 * to replace internalization pattern(format) from database with the real value
	 * @param replacementArg
	 * @param pattern
	 * @return
	 */
	public String replacedFormattedParam(Object[] replacementArg, String pattern){
		String stringWithPattern = format(replacementArg, pattern);

		return stringWithPattern;
	}
		
	/**
	 * the real worker to replace internalization pattern(format) from database with the real value
	 * @param stringWithPattern string yang mengandung format {0} , {1} dst. ini yang content nya akan di replace dengan arfs
	 * @param args replacement
	 * @return  
	 */
	public static String format(final Object[] args,   String stringWithPattern) {
		if ( args== null||args.length==0)
			return stringWithPattern;	
		for ( int i = 0 ; i < args.length; i++){
			if ( args[i]== null)
				continue ; 
			stringWithPattern = stringWithPattern.replaceAll("\\{" + i + "\\}", args[i] + ""); 
		}
		
		return stringWithPattern ; 
	}

	/**
	 * worker untuk menyediakan text yang inernationalize
	 **/
	public void setI18TextProvider(I18TextProvider i18TextProvider) {
		this.i18TextProvider = i18TextProvider;
	}

	/**
	 * kode localization current. Locale yang di pakai skarang apa
	 **/
	public void setCurrentLocalizationCode(String currentLocalizationCode) {
		this.currentLocalizationCode = currentLocalizationCode;
	}
	/**
	 * kode localization current. Locale yang di pakai skarang apa
	 **/
	public String getCurrentLocalizationCode() {
		return currentLocalizationCode;
	}
}
