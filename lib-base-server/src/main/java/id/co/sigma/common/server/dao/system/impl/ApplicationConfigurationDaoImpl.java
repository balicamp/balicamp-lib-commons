package id.co.sigma.common.server.dao.system.impl;


import id.co.sigma.common.data.entity.FormConfigurationSummary;
import id.co.sigma.common.data.entity.FormLabel;
import id.co.sigma.common.data.entity.I18Code;
import id.co.sigma.common.data.entity.I18NTextGroup;
import id.co.sigma.common.data.entity.I18Text;
import id.co.sigma.common.server.dao.base.BaseJPADao;
import id.co.sigma.common.server.dao.system.ApplicationConfigurationDao;

import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * application configuration dao
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 **/
public class ApplicationConfigurationDaoImpl extends BaseJPADao implements ApplicationConfigurationDao{
	
	
	private static Logger logger = LoggerFactory.getLogger(ApplicationConfigurationDaoImpl.class);

	@Override
	public FormConfigurationSummary readFormConfigurationSummary(String formId,
			String localizationCode) {
		
		String smt = "select a from FormConfigurationSummary a where a.id.formCode =:FORM_CODE AND a.id.localeCode=:LOCALE_CODE"; 
		try {
			return (FormConfigurationSummary) getEntityManager().createQuery(smt)
					.setParameter("FORM_CODE", formId)
					.setParameter("LOCALE_CODE", localizationCode).getSingleResult();
		} 
		catch (NoResultException nrslt){
			// ndak ada yang cocok emang
			return null ; 
		}
		
		catch (Exception e) {
			e.printStackTrace();
			logger.error("readFormConfigurationSummary:param ->  " + formId + ",localizationCode:" + localizationCode + " error message :" +  e.getMessage() , e); 
			return null ; 
		}
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FormLabel> readFormLabelConfigurations(String formId) {
		String smt = "select a from FormLabel a  where a.id.formId=:FORMID";
		return getEntityManager()
				.createQuery(smt).setParameter("FORMID", formId).getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> readFormLabelKeys(String formId) {
		String smt = "select a.labelKey from FormLabel a  where a.id.formId=:FORMID";
		return getEntityManager()
				.createQuery(smt).setParameter("FORMID", formId).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<I18Text> readLabels(String localizationCode,
			Collection<String> textKeys) {
		if ( textKeys==null||textKeys.isEmpty())
			return null ; 
		String smt="select a from I18Text a where a.id.localeCode=:LOCALE and a.id.textKey in :KEYS ";
		return (List<I18Text>) getEntityManager().createQuery(smt)
					.setParameter("LOCALE", localizationCode)
					.setParameter("KEYS", textKeys).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<I18Text> readLabels(String textKey) {
		String smt = "SELECT a FROM I18Text a WHERE a.id.textKey=:KEY";
		
		return getEntityManager().createQuery(smt).setParameter("KEY", textKey).getResultList();
	}

	@Override
	public Integer getLatestLabelVersionNumber() {
		String smt = "select a.version FROM I18Text a order by a.version desc ";
		try{
			return(Integer) getEntityManager()
						.createQuery(smt)
						.setMaxResults(1)
						.getSingleResult();
		}catch(NoResultException noRsltExc){
			logger.error("0 record. return 0");
			return 0;
			
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getImpactedFormIds(String labelKey) {
		String smt = "SELECT distinct a.formId FROM FormLabel a where a.labelKey=:KEY  "; 
		return getEntityManager().createQuery(smt).setParameter("KEY", labelKey).getResultList();
	}

	@Override
	public void eraseFormSummaryBiFormIds(List<String> formIds) {
		if ( formIds==null||formIds.isEmpty())
			return ;
		String smt = "DELETE FROM FormConfigurationSummary where id.formCode in (" + this.genarateInStatement("ID", formIds.size()) + ")";
		this.fillInParams(  getEntityManager().createQuery(smt), "ID" , formIds).executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<I18Text> getI18TextGroup(I18Text parameter, int pageSize, int rowPosition) {
		List<I18Text> result = null;
		try {
			String whereStr = "SELECT a FROM I18Text a WHERE 1=1 ";
			
			if(parameter.getGroupId() != null){
				if(parameter.getGroupId().getId() != null){
					whereStr += "AND a.groupId.id=:GROUP_ID ";
				}				
			}
			if(parameter.getLabel() != null){
				whereStr += "AND a.label=:LABEL";
			}
			if(parameter.getId() != null){
				if(parameter.getId().getLocaleCode() != null){
					whereStr += "AND a.id.localeCode=:LOCALE_CODE";
				}				
			}
			
			Query qry = getEntityManager().createQuery(whereStr);
			
			if(parameter.getGroupId() != null){
				if(parameter.getGroupId().getId() != null){
					qry.setParameter("GROUP_ID", parameter.getGroupId().getId());
				}							
			}
			if(parameter.getLabel() != null){
				qry.setParameter("LABEL", parameter.getLabel());
			}
			if(parameter.getId() != null){
				if(parameter.getId().getLocaleCode() != null){
					qry.setParameter("LOCALE_CODE", parameter.getId().getLocaleCode());
				}							
			}
			
			qry.setFirstResult(rowPosition);
			qry.setMaxResults(pageSize);
			result = qry.getResultList();			
		} catch (Exception e) {
			e.printStackTrace();
		}				
		return result;
	}

	@Override
	public void updateLabel(I18Text data) throws Exception {
		try {
			String sqlString = "UPDATE I18Text a SET a.label=:LABEL, a.version=:VERSION WHERE a.id.localeCode=:LOCALE_CODE AND a.id.textKey=:TEXT_KEY";
			Query qry = getEntityManager().createQuery(sqlString);
			qry.setParameter("LABEL", data.getLabel());
			qry.setParameter("VERSION", data.getVersion());
			qry.setParameter("LOCALE_CODE", data.getId().getLocaleCode());
			qry.setParameter("TEXT_KEY", data.getId().getTextKey());
			qry.executeUpdate();
		} catch (Exception e) {
			logger.error("updateLabel->param:" + data + ". Error messege :" + e.getMessage(),e);
			throw new Exception(e.getMessage());
		}		
	}

	@SuppressWarnings("unchecked")
	@Override
	public FormLabel getFormLabel(FormLabel parameter) throws Exception {
		FormLabel result = null;
		try {
			String sqlString = "SELECT a FROM FormLabel a WHERE a.id.labelKey=:LABEL_KEY";
			Query qry = getEntityManager().createQuery(sqlString);
			qry.setParameter("LABEL_KEY", parameter.getId().getLabelKey());
			List<FormLabel> resultList = qry.getResultList();
			if(!resultList.isEmpty()){
				result = resultList.get(0);
			}
		} catch (Exception e) {
			logger.error("getFormLabel->param:" + parameter + ". Error message :" + e.getMessage(),e);
			throw new Exception(e.getMessage());
		}
		return result;
	}

	@Override
	public void deleteFormCnfSummary(FormConfigurationSummary parameter) throws Exception {
		try {
			String sqlString = "DELETE FROM FormConfigurationSummary a WHERE a.id.formCode=:FORM_CODE AND a.id.localeCode=:LOCALE_CODE";
			Query qry = getEntityManager().createQuery(sqlString);
			qry.setParameter("FORM_CODE", parameter.getId().getFormCode());
			qry.setParameter("LOCALE_CODE", parameter.getId().getLocaleCode());
			qry.executeUpdate();
		} catch (Exception e) {
			logger.error("deleteFormCnfSummary->param:" + parameter + ". Error message : " + e.getMessage(),e);
			throw new Exception(e.getMessage());
		}		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<I18NTextGroup> getTextGroups() {
		return getEntityManager().createQuery("SELECT a FROM I18NTextGroup a").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<I18Text> getTextsOnGroup(String groupId, String i18Code) {
		String hql = "SELECT a from I18Text a where a.groupId.id=:GROUPID AND a.id.localeCode=:LOCALECODE ";
		return getEntityManager().createQuery(hql).setParameter("GROUPID", groupId).setParameter("LOCALECODE", i18Code).getResultList();
	}

	@Override
	public List<I18Code> getAvaliableLanguages() {
		String hql = "select a FROM I18Code a order by a.code";
		 return getEntityManager().createQuery(hql).getResultList();
	}

	@Override
	public List<I18Text> getAllAvailableLanguageTextById(String key) {
		String hql = "SELECT a from I18Text  a inner join fetch a.groupId  where a.id.textKey=:KEY"; 
		return getEntityManager().createQuery(hql).setParameter("KEY", key).getResultList();
	}
}