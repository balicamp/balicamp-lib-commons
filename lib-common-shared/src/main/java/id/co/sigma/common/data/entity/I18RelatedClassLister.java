package id.co.sigma.common.data.entity;

import id.co.sigma.common.data.JPAClassLister;



/**
 * class lister I18 Related items
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 * @version $Id
 **/
public class I18RelatedClassLister implements JPAClassLister{

	@Override
	public Class<?>[] getAnnotatedClasses() {
		return new Class<?>[]{
				FormConfigurationSummary.class  ,
				FormConfigurationSummaryPK.class , 
				FormLabel.class , 
				FormLabelPK.class , 
				I18Code.class , 
				I18NTextGroup.class , 
				I18Text.class , 
				I18TextPK.class,
				FormElementConfiguration.class,
				FormElementConfigurationPK.class, 
		};
	}

	
}
