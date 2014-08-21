package id.co.sigma.commonlib.journal;

public class Metadata {
	
	private String kodeTipeFile ;
	private String kodeUnit; 
	private Integer totalBarisData;
	
	private Integer totalLineActual;
	private boolean totalBarisDataValid;
	
	public Metadata() {
	}
	
	public boolean isTotalBarisDataValid() {
		return totalBarisData == totalLineActual;
	}


	public String getKodeTipeFile() {
		return kodeTipeFile;
	}
	
	public void setKodeTipeFile(String kodeTipeFile) {
		this.kodeTipeFile = kodeTipeFile;
	}
	
	public String getKodeUnit() {
		return kodeUnit;
	}
	
	public void setKodeUnit(String kodeUnit) {
		this.kodeUnit = kodeUnit;
	}
	
	public Integer getTotalBarisData() {
		return totalBarisData;
	}
	
	public void setTotalBarisData(Integer totalBarisData) {
		this.totalBarisData = totalBarisData;
	}

	public Integer getTotalLineActual() {
		return totalLineActual;
	}

	public void setTotalLineActual(Integer totalLineActual) {
		this.totalLineActual = totalLineActual;
	}
	
	

}
