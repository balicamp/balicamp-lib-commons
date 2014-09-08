package id.co.sigma.common.server.service.dualcontrol;

import id.co.sigma.common.server.dao.IGeneralPurposeDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseCustomBulkFieldIntegrityValidator<T> implements ICustomBulkFieldIntegrityValidator<T>, 
	InitializingBean {
	
	@Autowired
	private ICustomBulkFieldIntegrityValidatorManager customBulkFieldIntegrityValidatorManager;
	
	@Autowired
	protected IGeneralPurposeDao generalPurposeDao;
	
	protected List<T> mergeList(List<T> update, List<T> newData) {
		
		if(update != null && newData != null ) {
			List<T> result = new ArrayList<T>(update.size() + newData.size());
			
			for(T data : update) 
				result.add(data);
			
			for(T data : newData) 
				result.add(data);
			
			return result;
		} else {
			return null;
		}
		
	}
	
	protected <DATA extends Serializable> List<DATA> copyToList(Set<DATA> data) {
		List<DATA> list = new ArrayList<DATA>();
		
		for(DATA d : data) {
			list.add(d);
		}
		
		return list;
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected List<? extends Serializable> loadDataFromDB(Class clazz,
			String propertyName, Set propertyValues) {
		
		List<Serializable> data = null;
		List<Serializable> values = copyToList(propertyValues);
		
		try {
			 data  = generalPurposeDao.loadDataByKeys(clazz, propertyName,  values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		customBulkFieldIntegrityValidatorManager.register(this);
	}
	
	@Override
	public boolean isAfterClone() {
		return false;
	}
}
