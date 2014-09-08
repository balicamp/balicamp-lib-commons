package id.co.sigma.common.server.service.system;

/**
 * encryptor sederhana untuk encryp konfigurasi di dalam m_system_parameter. encryption key menjadi tanggung jawab implementer
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface ISimpleParameterEncryption {
	
	/**
	 * encrypt data
	 */
	public String encrypt(String data);
	
	
	/**
	 * decrypt data
	 */
	public String decrypt(String data);

}
