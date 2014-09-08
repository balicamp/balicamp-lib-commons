package id.co.sigma.common.server.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class DelimitedTextFileUtil {
	
	private static final String SYSTEM_LINE_TERMINATOR = System.getProperty("line.separator");
	private static final String CSV_FIELD_SEPARATOR = ";";
	
	/**
	 * Fungsi utama dari method ini adalah untuk membaca delimited file (CSV) dg data yg rusak (Blank Line), jika blank line ditemukan,
	 * maka line tersebut akan dilewati dan tidak ditulis kembali ke file output. Hasil dari output file adalah file CSV yg valid atau
	 * tanpa blank line.
	 * 
	 * @param in File Input.
	 * @param out File Output
	 * 
	 * @throws Exception
	 */
	public void validateAndCopy(File in, File out) throws Exception {
		
		if(in == null || out == null) return;
		
		BufferedReader reader = new BufferedReader( new FileReader(in) );
		BufferedWriter writer = new BufferedWriter( new FileWriter(out, false) );
		
		String swap = null;
		
		StringBuffer exceptionMessages = new StringBuffer();
		
		
		int line = 1;
		int exceptionCounter = 0;
		
		// skip line pertama
		reader.readLine();
		
		while((swap = reader.readLine()) != null) {
			
			if(!swap.trim().isEmpty()) {
				
				StringBuffer internalExceptionMsg = new StringBuffer("Baris "+line+" :\n");
				
				String[] splitted = swap.split(CSV_FIELD_SEPARATOR);
				if(splitted.length == 5) {
					
					String kodeBilling = splitted[0];
					//null kode billing
					if(kodeBilling == null || kodeBilling.isEmpty()) {
						internalExceptionMsg.append("Kode Billing kosong, ");
						exceptionCounter++;
					} else {
						//panjang kode billing tidak valid 
						if(kodeBilling.length() != 15) {
							internalExceptionMsg.append("Kode billing tidak valid, ");
							exceptionCounter++;
						}
					}
					
					String currCode = splitted[1];
					//null currency
					if(currCode == null || currCode.isEmpty()) {
						internalExceptionMsg.append("Currency kosong, ");
						exceptionCounter++;
					} else {
						//invalid currency 
						if(currCode.length() != 3 || !(currCode.equals("IDR") || currCode.equals("USD") ) ) {
							internalExceptionMsg.append("Currency harus USD atau IDR, ");
							exceptionCounter++;
						}
					}
					
					
					String noRekening = splitted[2];
					// null no. rekening.
					if(noRekening == null || noRekening.isEmpty()) {
						internalExceptionMsg.append("Nomor Rekening kosong, ");
						exceptionCounter++;
					} else {
						// panjang kode billing kurang.
						if(noRekening.length() != 10) {
							internalExceptionMsg.append("Nomor Rekening tidak valid, ");
							exceptionCounter++;
						}
					}
					
					
					String kodeCabang = splitted[3];
					// kode cabang null atau kosong 
					if(kodeCabang == null || kodeCabang.isEmpty()) {
						internalExceptionMsg.append("Kode Cabang kosong, ");
						exceptionCounter++;
					} else {
						
						// length kode cabang tidak valid
						if(kodeCabang.length() != 4) {
							internalExceptionMsg.append("Kode Cabang tidak valid, ");
							exceptionCounter++;
						}
					}
					
					String costCenter = splitted[4];
					// cost center null 
					if(costCenter == null || costCenter.isEmpty()) {
						internalExceptionMsg.append("Kode Cost Center kosong, ");
						exceptionCounter++;
					} else {
						
						// length kode cabang tidak valid
						if(costCenter.length() != 4) {
							internalExceptionMsg.append("Kode Cost Center tidak valid, ");
							exceptionCounter++;
						}
					}
					
					
					
					if(exceptionCounter == 0) {
						writer.write(swap+SYSTEM_LINE_TERMINATOR);
					} else {
						exceptionMessages.append(internalExceptionMsg.substring(0, internalExceptionMsg.length()-2)+"\n");
					}
					
					
					
				} else {
					exceptionMessages.append("Data tidak valid pada beris berikut.");
				}
				
			}
			
			line++;
			exceptionCounter = 0;
		}
		
		writer.flush();
		writer.close();
		
		reader.close();
		
		if(!exceptionMessages.toString().isEmpty()) {
			throw new Exception(exceptionMessages.toString());
		}
	}

	
	
	
}
