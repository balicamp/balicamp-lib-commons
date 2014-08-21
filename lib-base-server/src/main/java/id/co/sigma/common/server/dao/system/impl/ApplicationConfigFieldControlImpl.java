package id.co.sigma.common.server.dao.system.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import id.co.sigma.common.data.entity.FormElementConfiguration;
import id.co.sigma.common.server.dao.base.BaseJPADao;
import id.co.sigma.common.server.dao.system.IApplicationConfigFieldControl;

/**
 * Dao application konfig field control
 * @author I Gede Mahendra
 * @version $Id
 */
@Repository
public class ApplicationConfigFieldControlImpl extends BaseJPADao implements IApplicationConfigFieldControl{

	@SuppressWarnings("unchecked")
	@Override
	public FormElementConfiguration getFormElementConfigurationData(String formId, String elementId) throws Exception {
		FormElementConfiguration result = null;
		
		String strQry = "SELECT a FROM FormElementConfiguration a WHERE a.id.formId=:FORM_ID AND a.id.elementId=:ELEMENT_ID";
		Query qry = getEntityManager().createQuery(strQry);
		qry.setParameter("FORM_ID", formId);
		qry.setParameter("ELEMENT_ID", elementId);
		List<FormElementConfiguration> tempResult = qry.getResultList();
		if(tempResult.size() > 0){
			result = tempResult.get(0);
		}
		return result;
	}

	@Override
	public void saveOrUpdate(FormElementConfiguration data) throws Exception {
		FormElementConfiguration findData = getEntityManager().find(FormElementConfiguration.class, data.getId());
		if(findData == null) {
			insert(data);
		}else {
			findData.setHintI18NKey(data.getHintI18NKey());
			findData.setMandatory(data.getMandatory());
			findData.setMaxLength(data.getMaxLength());
			findData.setMaxValue(data.getMaxValue());
			findData.setMinValue(data.getMinValue());
			findData.setPlaceHolderI18NKey(data.getPlaceHolderI18NKey());
			update(findData);
		}
	}

	@Override
	public List<FormElementConfiguration> getFormElementConfigurationsData(
			String groupId) {
		String hql  = "SELECT a from FormElementConfiguration a where a.groupId =:GROUPID"; 
		@SuppressWarnings("unchecked")
		List<FormElementConfiguration> swap = getEntityManager().createQuery(hql).setParameter("GROUPID", groupId).getResultList();
		return swap;
	}
	
	@Override
	public List<String> getFormConfigurationGroups() {
		String hql  = "SELECT DISTINCT a.groupId from FormElementConfiguration a "; 
		return getEntityManager().createQuery(hql).getResultList();
	}
}