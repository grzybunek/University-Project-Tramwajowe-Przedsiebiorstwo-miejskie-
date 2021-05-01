package bdbt2;

import java.sql.Date;

public class Wynagrodzenia {
	/* Database field */
	private int nr_wynagrodzenia;
	private Date data;
	private int kwota_podstawowa;
	private Integer kwota_dodatkowa;
	private int nr_pracownika;
	
	/* constructor with field */
	public Wynagrodzenia(int nr_wynagrodzenia, Date data, int kwota_podstawowa, Integer kwota_dodatkowa,
			int nr_pracownika) {
		super();
		this.nr_wynagrodzenia = nr_wynagrodzenia;
		this.data = data;
		this.kwota_podstawowa = kwota_podstawowa;
		this.kwota_dodatkowa = kwota_dodatkowa;
		this.nr_pracownika = nr_pracownika;
	}
	
	/* constructor from superclass */
	public Wynagrodzenia() {
	}

	
	/* Getters and setters */
	public int getNr_wynagrodzenia() {
		return nr_wynagrodzenia;
	}

	public void setNr_wynagrodzenia(int nr_wynagrodzenia) {
		this.nr_wynagrodzenia = nr_wynagrodzenia;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getKwota_podstawowa() {
		return kwota_podstawowa;
	}

	public void setKwota_podstawowa(int kwota_podstawowa) {
		this.kwota_podstawowa = kwota_podstawowa;
	}

	public Integer getKwota_dodatkowa() {
		return kwota_dodatkowa;
	}

	public void setKwota_dodatkowa(Integer kwota_dodatkowa) {
		this.kwota_dodatkowa = kwota_dodatkowa;
	}

	public int getNr_pracownika() {
		return nr_pracownika;
	}

	public void setNr_pracownika(int nr_pracownika) {
		this.nr_pracownika = nr_pracownika;
	}
	
	/* To string method */	
	public String toString() {
		return "Wynagrodzenia [nr_wynagrodzenia=" + nr_wynagrodzenia + ", data=" + data
				+ ", kwota_podstawowa=" + kwota_podstawowa + ", kwota_dodatkowa=" + kwota_dodatkowa + ", nr_pracownika="
				+ nr_pracownika + "]";
	}


}
