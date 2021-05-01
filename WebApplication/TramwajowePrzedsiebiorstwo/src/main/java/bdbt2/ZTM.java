package bdbt2;

import java.sql.Date;

public class ZTM {
	/*Database field */
	private int Nr_biura;
	private String Nazwa;
	private String Miasto;
	private Date Data_zalozenia;
	private int Nr_adresu;
	
	/* constructor with field */
	public ZTM(int Nr_biura, String Nazwa, String Miasto, Date Data_zalozenia, int Nr_adresu) {
		super();
		this.Nr_biura = Nr_biura;
		this.Nazwa = Nazwa;
		this.Miasto = Miasto;
		this.Data_zalozenia = Data_zalozenia;
		this.Nr_adresu = Nr_adresu;
	}
	
	/* constructor from superclass */
	public ZTM() {
	}
	
	
	/*Getters and setters */
	public int getNr_biura() {
		return Nr_biura;
	}
	public void setNr_biura(int nr_biura) {
		Nr_biura = nr_biura;
	}
	public String getNazwa() {
		return Nazwa;
	}
	public void setNazwa(String nazwa) {
		Nazwa = nazwa;
	}
	public String getMiasto() {
		return Miasto;
	}
	public void setMiasto(String miasto) {
		Miasto = miasto;
	}
	public Date getData_zalozenia() {
		return Data_zalozenia;
	}
	public void setData_zalozenia(Date data_zalozenia) {
		Data_zalozenia = data_zalozenia;
	}
	public int getNr_adresu() {
		return Nr_adresu;
	}
	public void setNr_adresu(int nr_adresu) {
		Nr_adresu = nr_adresu;
	}

	/* To string method */
	public String toString() {
		return "ZTM [Nr_biura=" + Nr_biura + ", Nazwa=" + Nazwa + ", Miasto=" + Miasto + ", Data_zalozenia="
				+ Data_zalozenia + ", Nr_adresu=" + Nr_adresu + "]";
	}

	
	
	
}
