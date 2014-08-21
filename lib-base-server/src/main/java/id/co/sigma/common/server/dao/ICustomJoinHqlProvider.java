package id.co.sigma.common.server.dao;

/**
 * 
 * beberapa table bisa mengalamy lazy initialization exception. cara yang paling sederhana dalam menyediakan join statement untuk data. jadi data di left outer join. pekerjaan di sini adalah membuat join statmeent
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface ICustomJoinHqlProvider<T> {
	
	
	/**
	 * ini memproduce join statement yang di perlukan. misal object <i>Person</i> ada reference ke <i>Address</i>. agar efisien, misalnya di perlu di bikin left outer join 2 table ini<br/>
	 * method ini mereturn misalnya  Person <strong>a</strong> left outer join fetch <strong>a</strong>.address <br/>
	 * sebagai catatan :<br/>
	 * <ol>
	 * <li>a = nilai dari primaryTableAlias<li>
	 * <li>address adalah reference ke address</li>
	 * </ol>
	 **/
	public String generateCustomJoinStatement ( String primaryTableAlias  ) ;
	
	
	
	
	/**
	 * generate count statement
	 */
	public String generateCountStatement ( String primaryTableAlias  ) ;
	
	
	/**
	 * class yang di handle provider ini
	 **/
	public Class<T> getHandledClass () ; 

}
