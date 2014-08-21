package id.co.sigma.common.server.service.system.impl;

import id.co.sigma.common.data.ModificationDataContainer;
import id.co.sigma.common.data.PagedResultHolder;
import id.co.sigma.common.data.app.AppFormConfiguration;
import id.co.sigma.common.data.app.ConfigurationLabel;
import id.co.sigma.common.data.app.LabelDataWrapper;
import id.co.sigma.common.data.entity.FormConfigurationSummary;
import id.co.sigma.common.data.entity.FormConfigurationSummaryPK;
import id.co.sigma.common.data.entity.FormElementConfiguration;
import id.co.sigma.common.data.entity.FormLabel;
import id.co.sigma.common.data.entity.FormLabelPK;
import id.co.sigma.common.data.entity.I18Text;
import id.co.sigma.common.server.dao.system.ApplicationConfigurationDao;
import id.co.sigma.common.server.dao.system.impl.ApplicationConfigFieldControlImpl;
import id.co.sigma.common.server.service.system.ApplicationConfigService;
import id.co.sigma.common.server.util.JaxbConverterUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;




/**
 * layer perantara antara rpc vs db utnuk akses ke konfigurasi applikasi
 * 
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 * @version $Id
 * @since 25-aug-2012
 **/

public class ApplicationConfigServiceImpl implements ApplicationConfigService{

	@Autowired
	private ApplicationConfigurationDao  applicationConfigurationDao;
	
	@Autowired
	private ApplicationConfigFieldControlImpl applicationConfigField;
	
	private static Logger logger = LoggerFactory.getLogger(ApplicationConfigServiceImpl.class);
	
	@Override
	public AppFormConfiguration generateConfigurationObjectFromCachedData(
			FormConfigurationSummary cachedConfig) {
		
		return null;
	}

	@Override
	@Transactional(readOnly=false , propagation=Propagation.REQUIRED)
	public AppFormConfiguration createAndWriteConfiguration(String formId,
			String localeCode) {
		
		AppFormConfiguration retval = new AppFormConfiguration();
		retval.setLocaleId(localeCode);
		retval.setParentId(formId);
		int maxLabelVersion = -1 ; // max dari labels. nanti di vote vs konfig
		int maxControlConfigVersion = -1 ;
		List<String> keys =  applicationConfigurationDao.readFormLabelKeys(formId);
		if ( keys!=null&& ! keys.isEmpty()){
			List<I18Text> texts  =  applicationConfigurationDao.readLabels(localeCode, keys);
			if ( texts!=null && !texts.isEmpty()){
				ConfigurationLabel[] lbls4Client = new ConfigurationLabel[texts.size()]; 
				retval.setConfigurationLabel(lbls4Client);
				int i=0;
				for( I18Text scn : texts){
					if(maxLabelVersion<scn.getVersion())
						maxLabelVersion = scn.getVersion();
					ConfigurationLabel lbl1= new ConfigurationLabel(scn.getId().getTextKey() , scn.getLabel());
					lbls4Client[i++] = 	lbl1;	
					keys.remove(scn.getId().getTextKey());
				}
				// build missing one
				if ( !keys.isEmpty()){
					String[] missingKeys = new String[keys.size()];
					retval.setMissingLabelKeys(missingKeys);
					i=0;
					for ( String key:keys){
						missingKeys[i++]= key;
					}
				}
			}
			
		}
			 
		
		retval.setVersion(maxControlConfigVersion>maxLabelVersion?maxControlConfigVersion:maxLabelVersion);
		//FIXME anda harus membuild dan save FormConfigurationSummary
		// kebalikan nya 
		//1. buat class FormConfigurationSummary
		//2. FormConfigurationSummary#labels di isi dengan XML dari AppFormConfiguration#configurationLabels
		
		/*Sudah di fix dg code dibawah ini*/
		String xml = JaxbConverterUtils.getInstance().convertPojoToXml(retval, retval.getClass());
		FormConfigurationSummaryPK pk = new FormConfigurationSummaryPK();
		pk.setFormCode(formId);
		pk.setLocaleCode(localeCode);
		
		FormConfigurationSummary formConfig = new FormConfigurationSummary();		
		formConfig.setLabels(xml);
		formConfig.setId(pk);		
		try {
			applicationConfigurationDao.insert(formConfig);
		} catch (Exception e) {			
			e.printStackTrace();
		}//end try
		
		return retval;
	}

	@Override
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void saveLabels(String textKey, LabelDataWrapper[] labels)
			throws Exception {
		if ( labels==null||labels.length==0)
			return ;
		
		
		ModificationDataContainer<I18Text> modifContainer = new ModificationDataContainer<I18Text>();
		HashMap<String, LabelDataWrapper> indexed=new HashMap<String, LabelDataWrapper>();
		for (LabelDataWrapper scn : labels){
			indexed.put(scn.getLocaleCode(), scn);
		}
		Integer latestVersion = applicationConfigurationDao.getLatestLabelVersionNumber();
		latestVersion=latestVersion==null?1:latestVersion;
		List<I18Text> texts = applicationConfigurationDao.readLabels(textKey);
		if ( texts!=null){
			for (I18Text txt : texts ){
				if (indexed.containsKey(txt.getId().getLocaleCode()) ){
					modifContainer.getEditedData().add(txt);
					txt.setLabel(indexed.get(txt.getId().getLocaleCode()).getLabel());
					indexed.remove(txt.getId().getLocaleCode());
					txt.setVersion(latestVersion++);
				}
					
			}
		}
		//
		if(!indexed.isEmpty()){
			for(LabelDataWrapper scn : indexed.values()){
				I18Text txtBaru = new I18Text();
				txtBaru.getId().setTextKey(textKey);
				txtBaru.getId().setLocaleCode(scn.getLocaleCode());
				txtBaru.setLabel(scn.getLabel());
				txtBaru.setVersion(latestVersion++);
				modifContainer.getNewlyAppendedData().add(txtBaru);
				
			}
		}
		// siap di save. but perlu destroy
		if (! modifContainer.getNewlyAppendedData().isEmpty())
			applicationConfigurationDao.inserts(modifContainer.getNewlyAppendedData());
		if ( !modifContainer.getEditedData().isEmpty())
			applicationConfigurationDao.updates(modifContainer.getEditedData());
		// hapus form cnf summary
		applicationConfigurationDao.eraseFormSummaryBiFormIds(applicationConfigurationDao.getImpactedFormIds(textKey));
	}
	
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRES_NEW)
	public PagedResultHolder<LabelDataWrapper> getI18NGroupId(I18Text groupId, int pageSize, int rowPosition) {
		PagedResultHolder<LabelDataWrapper> retval = new PagedResultHolder<LabelDataWrapper>();
		List<I18Text> result = applicationConfigurationDao.getI18TextGroup(groupId, pageSize, rowPosition);
		
		List<LabelDataWrapper> listDataWraper = new ArrayList<LabelDataWrapper>();
		for (I18Text data : result) {
			LabelDataWrapper dto = new LabelDataWrapper();
			dto.setKey(data.getId().getTextKey());
			dto.setLabel(data.getLabel());
			dto.setLocaleCode(data.getId().getLocaleCode());
			dto.setVersion(data.getVersion());
			dto.setGroupId(data.getGroupId().getId());
			listDataWraper.add(dto);
		}
		
		//FIXME parameter utk pagedResultHolder belum diset semuanya
		retval.setHoldedData(listDataWraper);
		retval.setPageSize(pageSize);		
		return retval;				
	}
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public void saveLabel(I18Text data) throws Exception{
		try {						
			applicationConfigurationDao.insert(data);
		} catch (Exception e) {	
			logger.error(this.getClass() + ",saveLabel(" + data + ") : " + e.getMessage(),e);
			throw new Exception(e.getMessage());
		}		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public void updateLabel(I18Text data) throws Exception {
		try {
			FormLabel parameter = new FormLabel();
			FormLabelPK id = new FormLabelPK();
			id.setLabelKey(data.getId().getTextKey());
			parameter.setId(id);			
			FormLabel labelKey = applicationConfigurationDao.getFormLabel(parameter);
			
			if(labelKey != null){
				FormConfigurationSummary idKeyLabel = new FormConfigurationSummary();
				FormConfigurationSummaryPK idKeyLabelPk = new FormConfigurationSummaryPK();
				idKeyLabelPk.setFormCode(labelKey.getFormId());
				idKeyLabelPk.setLocaleCode(data.getId().getLocaleCode());
				idKeyLabel.setId(idKeyLabelPk);
				applicationConfigurationDao.deleteFormCnfSummary(idKeyLabel);
			}			
						
			applicationConfigurationDao.updateLabel(data);
		} catch (Exception e) {
			logger.error(this.getClass() + ",updateLabel(" + data + ") : " + e.getMessage(),e);
			throw new Exception(e.getMessage());
		}	
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public void saveControlConfiguration(FormElementConfiguration data)
			throws Exception {
		applicationConfigField.saveOrUpdate(data);		
	}

	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRES_NEW)
	public FormElementConfiguration readControlConfiguration(String formId,
			String elementId) throws Exception {		
		return applicationConfigField.getFormElementConfigurationData(formId, elementId);
	}

	@Override
	@Transactional(readOnly=false)
	public void saveLabels(I18Text[] texts, String username,
			Date applicationDate) throws Exception {
		if ( texts==null||texts.length==0)
			return ;
		List<I18Text> dbData = applicationConfigurationDao.getAllAvailableLanguageTextById(texts[0].getId().getTextKey());
		
		HashMap<String, I18Text> indexedDbData = new HashMap<String, I18Text>();
		if(dbData!=null){
			for (I18Text scn : dbData ){
				indexedDbData.put(scn.getId().getLocaleCode(), scn);
			}
		}
		
		ArrayList<I18Text> toUpdateList = new ArrayList<I18Text>();
		ArrayList<I18Text> toAddList = new ArrayList<I18Text>();
		for (I18Text scn : texts ){
			if( indexedDbData.containsKey(scn.getId().getLocaleCode())){
				I18Text db = indexedDbData.get(scn.getId().getLocaleCode() ) ; 
				db.setLabel(scn.getLabel());
				db.setGroupCode(scn.getGroupCode());
				db.setVersion(scn.getVersion());
				toUpdateList.add(db);
			}else{
				toAddList.add(scn);
			}
		}
		applicationConfigurationDao.updates(toUpdateList);
		applicationConfigurationDao.inserts(toAddList);
		
	}
}