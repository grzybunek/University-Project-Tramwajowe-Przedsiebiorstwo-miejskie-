package bdbt2;

import java.sql.Date;

public class Pracownicy {

	/* Database field */
	private int nr_pracownika;
	private String imie;
	private String nazwisko;
	private Date data_urodzenia;
	private String plec;
	private String pesel;
	private Date data_zatrudnienia;
	private Date data_zwolnienia;
	private int nr_biura;
	private int nr_adresu;
	private int nr_stanowiska;

	/* constructor with field */
	public Pracownicy(int nr_pracownika, String imie, String nazwisko, Date data_urodzenia, String plec, String pesel,
			Date data_zatrudnienia, Date data_zwolnienia, int nr_biura, int nr_adresu, int nr_stanowiska) {
		super();
		this.nr_pracownika = nr_pracownika;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.data_urodzenia = data_urodzenia;
		this.plec = plec;
		this.pesel = pesel;
		this.data_zatrudnienia = data_zatrudnienia;
		this.data_zwolnienia = data_zwolnienia;
		this.nr_biura = nr_biura;
		this.nr_adresu = nr_adresu;
		this.nr_stanowiska = nr_stanowiska;
	}

	public Pracownicy(int nr_pracownika, String imie, String nazwisko, Date data_urodzenia, String plec, String pesel,
			Date data_zatrudnienia, String data_zwolnienia, int nr_biura, int nr_adresu, int nr_stanowiska) {
		super();
		this.nr_pracownika = nr_pracownika;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.data_urodzenia = data_urodzenia;
		this.plec = plec;
		this.pesel = pesel;
		this.data_zatrudnienia = data_zatrudnienia;
		this.data_zwolnienia = null;
		this.nr_biura = nr_biura;
		this.nr_adresu = nr_adresu;
		this.nr_stanowiska = nr_stanowiska;
	}

	/* constructor from superclass */
	public Pracownicy() {
		super();
	}

	/* Getters and setters */
	public int getNr_pracownika() {
		return nr_pracownika;
	}

	public void setNr_pracownika(int nr_pracownika) {
		this.nr_pracownika = nr_pracownika;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public Date getData_urodzenia() {
		return data_urodzenia;
	}

	public void setData_urodzenia(Date data_urodzenia) {
		this.data_urodzenia = data_urodzenia;
	}

	public String getPlec() {
		return plec;
	}

	public void setPlec(String plec) {
		this.plec = plec;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public Date getData_zatrudnienia() {
		return data_zatrudnienia;
	}

	public void setData_zatrudnienia(Date data_zatrudnienia) {
		this.data_zatrudnienia = data_zatrudnienia;
	}

	public Date getData_zwolnienia() {
		return data_zwolnienia;
	}

	public void setData_zwolnienia(Date data_zwolnienia) {
		this.data_zwolnienia = data_zwolnienia;
	}

	public int getNr_biura() {
		return nr_biura;
	}

	public void setNr_biura(int nr_biura) {
		this.nr_biura = nr_biura;
	}

	public int getNr_adresu() {
		return nr_adresu;
	}

	public void setNr_adresu(int nr_adresu) {
		this.nr_adresu = nr_adresu;
	}

	public int getNr_stanowiska() {
		return nr_stanowiska;
	}

	public void setNr_stanowiska(int nr_stanowiska) {
		this.nr_stanowiska = nr_stanowiska;
	}

	/* To string method */
	public String toString() {
		return "Pracownicy [nr_pracownika=" + nr_pracownika + ", imie=" + imie + ", nazwisko=" + nazwisko
				+ ", data_urodzenia=" + data_urodzenia + ", plec=" + plec + ", pesel=" + pesel + ", data_zatrudnienia="
				+ data_zatrudnienia + ", data_zwolnienia=" + data_zwolnienia + ", nr_biura=" + nr_biura + ", nr_adresu="
				+ nr_adresu + ", nr_stanowiska=" + nr_stanowiska + "]";
	}

}
