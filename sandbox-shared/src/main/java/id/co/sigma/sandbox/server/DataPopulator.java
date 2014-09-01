package id.co.sigma.sandbox.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import id.co.sigma.common.security.domain.Application;
import id.co.sigma.common.security.domain.User;
import id.co.sigma.common.security.domain.lov.LookupDetail;
import id.co.sigma.common.security.domain.lov.LookupHeader;
import id.co.sigma.common.server.dao.IGeneralPurposeDao;
import id.co.sigma.common.server.data.lookup.Common1LevelLOVDetail;
import id.co.sigma.sandbox.shared.domain.Department;
import id.co.sigma.sandbox.shared.domain.Person;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 
 * @author <a href='mailto:gede.sutarsa@gmail.com'>Gede Sutarsa</a>
 */
public class DataPopulator implements InitializingBean{

	
	@Autowired
	private IGeneralPurposeDao generalPurposeDao ;
	
	@Autowired
	private PlatformTransactionManager transactionManager ; 
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		TransactionTemplate tmpl = new TransactionTemplate(transactionManager);
		tmpl.execute(new TransactionCallback<Integer>() {
			@Override
			public Integer doInTransaction(TransactionStatus arg0) {
				try {
					populatePerson();
					populateLOV();
					populateDepartement();
					populateUser();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return null;
			}
		});
		try {
			System.out.println("LOV content");
			cekLov();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	private void populatePerson () throws Exception {
		ArrayList<Serializable> ps = new ArrayList<Serializable>(); 
		for ( int i = 1 ; i < 200; i++){
			Person p = new Person(); 
			ps.add(p); 
			p.setEmail("sample" + i + "@balicamp.com");
			p.setName("Person - " + i );
		}
		generalPurposeDao.inserts(ps);
	}
	
	
	private void populateLOV() throws Exception {
		LookupHeader head1 = new LookupHeader(); 
		head1.setCacheable(true);
		head1.setI18Key("id");
		head1.setId("JENIS_KELAMIN");
		head1.setRemark("Sample1");
		head1.setDetails(new ArrayList<LookupDetail>());
		LookupDetail d = new LookupDetail(); 
		d.setHeaderId("JENIS_KELAMIN");
		d.setDetailCode("L");
		d.setExtField1("E1");
		d.setExtField2("E2");
		d.setI18Key("id");
		d.setHeader(head1);
		d.setLabel("Laki-laki");
		head1.getDetails().add(d);
		
		
		LookupDetail d2 = new LookupDetail(); 
		d2.setHeaderId("JENIS_KELAMIN");
		d2.setDetailCode("P");
		d2.setExtField1("E1");
		d2.setExtField2("E2");
		d2.setI18Key("id");
		d2.setHeader(head1);
		d2.setLabel("Perempuan");
		head1.getDetails().add(d2);
		
		generalPurposeDao.insert(head1);
		
		
		
		
		LookupHeader headKab = new LookupHeader();
		headKab.setId("KABUPATEN");
		headKab.setCacheableFlag("N");
		headKab.setRemark("sample Kab");
		headKab.setI18Key("id");
		headKab.setDetails(new ArrayList<LookupDetail>());
		
		headKab.getDetails().add(new LookupDetail(headKab, "TBN", "Tabanan",1));
		headKab.getDetails().add(new LookupDetail(headKab, "DPS", "Denpasar",2));
		headKab.getDetails().add(new LookupDetail(headKab, "BDG", "Badung", 3));
		headKab.getDetails().add(new LookupDetail(headKab, "GYR", "Gianyar",4));
		headKab.getDetails().add(new LookupDetail(headKab , "BGL" , "Bangli" , 5));
		headKab.getDetails().add(new LookupDetail(headKab , "KRS" , "Karangasem" ,6));
		headKab.getDetails().add(new LookupDetail(headKab , "NGR" , "Negara" ,7));
		headKab.getDetails().add(new LookupDetail(headKab , "SGR" , "Singaraja" ,8));
		headKab.getDetails().add(new LookupDetail(headKab , "KLK" , "Klungkung" ,8));
		
		generalPurposeDao.insert(headKab);
		
	}
	
	
	private void cekLov () throws Exception {
		List<Common1LevelLOVDetail> ds = generalPurposeDao.list(Common1LevelLOVDetail.class, null);
		if ( ds != null ) {
			for (Common1LevelLOVDetail scn : ds ){
				System.out.println(">>>" + scn.toString() );
			}
			
		}
	}

	
	
	private void populateDepartement () throws Exception {
		generalPurposeDao.insert(new Department("ICT", "Information Communication Technology", 25));
		generalPurposeDao.insert(new Department("FIN", "Finance", 5));
		generalPurposeDao.insert(new Department("HRD", "Human Resource", 3));
	}
	
	
	
	
	
	
	private void populateUser () throws Exception{
		Application app = new Application(); 
		app.setId(1L);
		app.setApplicationCode("SANDBOX");
		app.setApplicationName("Sandbox Apps");
		
		generalPurposeDao.insert(app);
		
		generalPurposeDao.insert(new User("GPS", "gede.sutarsa@gmail.com", "Gede Sutarsa", "id"));
		generalPurposeDao.insert(new User("WYNARY", "wayan.agustina@sigma.co.id", "Wayan Ari Agustina", "id"));
		generalPurposeDao.insert(new User("SUBAGIA", "bagus.subagia@sigma.co.id", "Ida Bagus Subagia", "id"));
		generalPurposeDao.insert(new User("RAKA", "raka.sanjaya@sigma.co.id", "Raka Sanjaya", "id"));
		generalPurposeDao.insert(new User("AAA", "arie.anggreani@sigma.co.id", "A.A. Arie Anggreani", "id"));
		generalPurposeDao.insert(new User("GUSDE", "ida.suartama@sigma.co.id", "Ida Bagus Rai Suartama", "id"));
	}
}
