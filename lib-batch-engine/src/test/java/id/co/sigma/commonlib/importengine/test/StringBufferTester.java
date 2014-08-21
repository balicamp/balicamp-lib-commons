package id.co.sigma.commonlib.importengine.test;

public class StringBufferTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StringBuffer buff = new StringBuffer(); 
		buff.append("hahaha,");
		System.out.println(buff.delete(buff.length()-1, buff.length()).toString());

	}

}
