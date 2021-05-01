package bdbt2;

public class Stanowiska {
	
	/* Database field */
	private int nr_stanowiska;
	private String nazwa;
	private String Opis;
	
	/* constructor with field */
	public Stanowiska(int nr_stanowiska, String nazwa, String opis) {
		super();
		this.nr_stanowiska = nr_stanowiska;
		this.nazwa = nazwa;
		Opis = opis;
	}
	/* constructor from superclass */
	public Stanowiska() {
		super();
	}
	
	/* Getters and setters */
	public int getNr_stanowiska() {
		return nr_stanowiska;
	}
	public void setNr_stanowiska(int nr_stanowiska) {
		this.nr_stanowiska = nr_stanowiska;
	}
	public String getNazwa() {
		return nazwa;
	}
	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}
	public String getOpis() {
		return Opis;
	}
	public void setOpis(String opis) {
		Opis = opis;
	}
	
	/* To string method */
	@Override
	public String toString() {
		return "Stanowiska [nr_stanowiska=" + nr_stanowiska + ", nazwa=" + nazwa + ", Opis=" + Opis + "]";
	}
	
	
	
	
	
	
	

}
