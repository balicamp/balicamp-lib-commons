package id.co.sigma.common.server.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import id.co.sigma.common.server.dao.IGeneralPurposeDao;
import id.co.sigma.common.server.service.IGeneralPurposeService;
import id.co.sigma.common.server.service.AbstractService;

/**
 * 
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public class GeneralPurposeServiceImpl extends AbstractService implements IGeneralPurposeService {

	@Autowired
	IGeneralPurposeDao generalPurposeDao ;

	@Override
	public void insert(Serializable object) throws Exception {
		generalPurposeDao.insert(object);
		
	}

	@Override
	public void delete(Serializable object) throws Exception {
		generalPurposeDao.delete(object);
		
	}

	@Override
	public void update(Serializable object) throws Exception {
		generalPurposeDao.update(object);
		
	}

	@Override
	public Integer delete(Class<?> objectClass, Serializable pk,
			String pkFieldName) {
		return generalPurposeDao.delete(objectClass, pk, pkFieldName);  
	}

	@Override
	@Transactional(readOnly = false)
	public Serializable merge(Serializable object) {
		return generalPurposeDao.merge(object);
	} 
}
