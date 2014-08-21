package id.co.sigma.common.server.util;

import id.co.sigma.common.data.serializer.json.ObjectSerializerManager;
import id.co.sigma.common.util.json.IJSONFriendlyObject;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.springframework.beans.BeanUtils;




/**
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 **/
public class JSONFriendlyRelatedUtils {
	

	static final  ArrayList< String> EXCLUDED_FIELDS = new ArrayList<String>();
	static{
		
		EXCLUDED_FIELDS.add("class");
		
		
	}
	/**
	 * object yang tidak simple di detach dari dalam array
	 * @return index 0 = set null , index 1 restore object 
	 **/
	protected String[] generateUnBounReboundMethod ( Class<?> objectType , String variableName   ){
		
		StringBuffer beforeMakeSwap = new StringBuffer();
		StringBuffer beforeMakeNull = new StringBuffer();
		StringBuffer after = new StringBuffer(); 
		  
		PropertyDescriptor[] desc =  BeanUtils.getPropertyDescriptors(objectType);
		if ( desc!= null && desc.length>0){
			for ( int i = 0 ; i < desc.length ; i++){
				PropertyDescriptor pd = desc[i];
				Method rm = pd.getReadMethod(); 
				Method wm = pd.getWriteMethod();
				if ( EXCLUDED_FIELDS.contains(pd.getName()))
					continue ; 
				if (   ObjectSerializerManager.isSimpleObject(rm.getReturnType().getName())  )
					continue ; 
				String tmpVarName = variableName + "_" + pd.getName() +"_tmp" ; 
				beforeMakeSwap.append("\t\t\t" + rm.getReturnType().getSimpleName()  + " " + tmpVarName  + " = " + variableName + "."+ rm.getName() +"();\n"  );
				beforeMakeNull.append("\t\t\t" +variableName+"." + wm.getName() + "(null);\n"); 
				after.append("\t\t\t" +variableName+"." + wm.getName() + "(" + tmpVarName+");\n");
			}
			
			
			
		}
		
		
		
		return new String []{
				"//1. Ok tampung dulu variable\n" + 
				beforeMakeSwap.toString()  + 
				"//2. null kan variable \n"  +  
				beforeMakeNull.toString() + 
				"// 3 taruh ke json\n" 
				,
				"//4. restore lagi \n" + 
				after.toString()
		};
	}
	
	
	
	
	
	
	
	
	
	public   String generateMethodRaw (String fqcn  ) {
		try {
			
			Class<?> cls =Class.forName(fqcn) ; 
			
			PropertyDescriptor[] pd =  BeanUtils.getPropertyDescriptors(cls);
			if ( pd!= null ){
				StringBuffer getMethod = new StringBuffer() ;
				StringBuffer writeMethod = new StringBuffer(); 
				int i=0; 
				for ( PropertyDescriptor scn : pd){
					i++ ;
					if (EXCLUDED_FIELDS.contains(scn.getName()))
							continue ; 
					Method rm =  scn.getReadMethod();
					
					String varFqcm = rm.getReturnType().getName(); 
					
					
					 
					if (! ObjectSerializerManager.isSimpleObject(varFqcm) &&! ObjectSerializerManager.getInstance().isArrayofSimpleObject(varFqcm) && ! IJSONFriendlyObject.class.isAssignableFrom( rm.getReturnType())){
						 
						
						getMethod.append("\t\t/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe " + IJSONFriendlyObject.class.getName() +"*/");
						writeMethod.append("\t\t/*hati hati dengan variable ini. ini bukan tipe simple dan bukan tipe " + IJSONFriendlyObject.class.getName() +"*/");
						
					} 
					
					
					
					// kalau json , siapkan bound + un bound. kita perlu trace 2 level
					if (IJSONFriendlyObject.class.isAssignableFrom(rm.getReturnType())){
						writeMethod.append("\t\t  \n");
						getMethod.append("\t\t  \n");
						
						getMethod.append("\t\t " + rm.getReturnType().getSimpleName() + " param" + i +" = " + rm.getName()   +"();   \n");
						getMethod.append("\t\t if ( param" +i+ " != null){ \n");
						
						String[] boundUnBound = generateUnBounReboundMethod(rm.getReturnType(), "param" + i);
						
						getMethod.append("\t\t//FIXME: pertimbangkan kalau lanjutan dari param"+i +" ada refer ke node lain, pertimbangkan untuk di nullkan untuk mengatasi masalah performence\n ");
						getMethod.append(boundUnBound[0]); 
						getMethod.append("\t\t\tjsonContainer.put(\""+  scn.getName() +"\", param" +i+  ");\n"); 
						getMethod.append(boundUnBound[1]);
						
						
						
						getMethod.append("\t\t}\n");
						
					}
					
					
					// OK bound skr
					if ( rm.getReturnType().equals(java.util.List.class)){
						Type tipe  =  scn.getWriteMethod().getGenericParameterTypes()[0];
						if ( tipe instanceof  ParameterizedType) {
							ParameterizedType prmtz = (ParameterizedType)tipe; 
							
							Type [] allTypes =  prmtz.getActualTypeArguments() ;
							if ( allTypes == null|| allTypes.length!= 1 ){
								getMethod.append("\t\t//FIXME : maaf, list " +scn.getName() +" tidak bisa saya serialize kan. terlalu kompleks.maaf yah\n");
							}
							else{
								String paramName = "param" + i ;
								String tipeData = ((Class<?>)allTypes[0]).getName() ;
								
								getMethod.append("\t\t \n");
								getMethod.append("\t\t  List<"  + tipeData + "> "+paramName+" = " +  rm.getName() +  "() ; \n");
								getMethod.append("\t\t if (  " +paramName+  " != null && !" +paramName+".isEmpty()){ \n");
								
								getMethod.append("\t\t\tfor ( " + tipeData + " scn : " + paramName+"){\n");
								String [] unbound = this.generateUnBounReboundMethod(  Class.forName(tipeData)   , "scn");
								getMethod.append("\t\t\t\t" + unbound[0] + "\n");
								getMethod.append("\t\t\t\t	jsonContainer.appendToArray(\""+scn.getName()+ "\", scn);\n"); 
								getMethod.append("\t\t\t\t" + unbound[1] +"\n");
								getMethod.append("\t\t\t\t}\n"); 
								
								getMethod.append("\t\t}\n");
								
							}
							
						}
							
						else{
							getMethod.append("\t\t//FIXME : maaf, list " +scn.getName() +" tidak bisa saya serialize kan. terlalu kompleks.maaf yah\n");
						}
						
					}
					
					else if ( ObjectSerializerManager.getInstance().isArrayofSimpleObject(varFqcm) ){
						
					}
					
					else{
						getMethod.append("\t\tjsonContainer.put(\"" +  scn.getName()  +"\"," +rm.getName()+"());\n"); 
						writeMethod.append("\t\tretval."+  scn.getWriteMethod().getName()    +  "( ("+rm.getReturnType().getSimpleName() +")jsonContainer.get(\"" +  scn.getName()  +"\" ,  "+rm.getReturnType().getSimpleName()+".class.getName()));\n");
					}
					
				}
				
				
				StringBuffer retval = new StringBuffer();
				retval.append("\t@Override\n");
				retval.append("\tpublic void translateToJSON(ParsedJSONContainer jsonContainer) {\n");
				retval.append(getMethod);
				retval.append("\t}\n");
				
				
				retval.append("\t@Override\n");
				retval.append("\tpublic " +  cls.getSimpleName() +" instantiateFromJSON(ParsedJSONContainer jsonContainer) {\n");
				retval.append("\t\t"   +cls.getSimpleName() +" retval = new " + cls.getSimpleName()  + "();\n");
				retval.append(writeMethod);
				retval.append("\t\treturn retval; \n");
				retval.append("\t}\n");
				
				return retval.toString(); 
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null ; 
	}
	
	
	
	public static void main (String[] args){
		
		
		
		
		JSONFriendlyRelatedUtils u = new JSONFriendlyRelatedUtils(); 
		
		System.out.println(  u.generateMethodRaw("id.co.sigma.common.data.approval.CommonApprovalHeader"));
		/*
		System.out.println(String[].class.getName());
		*/
	}

}
