package bdbt2;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class PracownicyDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/* Constructor */
	public PracownicyDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	/* List */
	public List<Pracownicy> list() {
		String sql = "SELECT * FROM Pracownicy";
		List<Pracownicy> listPracownicy = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Pracownicy.class));
		return listPracownicy;
	}

	/* Create */
	public void save(Pracownicy pracownicy) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		insertActor.withTableName("Pracownicy").usingColumns("imie", "nazwisko", "data_urodzenia", "plec", "pesel",
				"data_zatrudnienia", "data_zwolnienia", "nr_biura", "nr_adresu", "nr_stanowiska");
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(pracownicy);
		insertActor.execute(param);
	}
	/* Create and return the number */
	public Number saveWithNumber(Pracownicy pracownicy) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		insertActor.withTableName("Pracownicy").usingColumns("imie", "nazwisko", "data_urodzenia", "plec", "pesel",
				"data_zatrudnienia", "data_zwolnienia", "nr_biura", "nr_adresu", "nr_stanowiska").usingGeneratedKeyColumns("nr_pracownika");
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(pracownicy);
		return insertActor.executeAndReturnKey(param);
	}

	/* Read */
	public Pracownicy get(int nr_pracownika) {
		Object[] args = { nr_pracownika };
		String sql = "SELECT * FROM Pracownicy WHERE Nr_pracownika= " + args[0];
		Pracownicy pracownicy = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Pracownicy.class));
		return pracownicy;
	}

	/* Update */
	public void update(Pracownicy pracownicy) {
		String sql = "UPDATE Pracownicy SET imie=:imie, nazwisko=:nazwisko, data_urodzenia=:data_urodzenia, "
				+ "plec=:plec, pesel=:pesel, data_zatrudnienia=:data_zatrudnienia, data_zwolnienia=:data_zwolnienia, nr_biura=:nr_biura, "
				+ "nr_adresu=:nr_adresu, nr_stanowiska=:nr_stanowiska WHERE nr_pracownika=:nr_pracownika";
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(pracownicy);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
		template.update(sql, param);
	}
	
	/* Delete */
	public void delete(int nr_pracownika) {
		String sql = "DELETE FROM Pracownicy WHERE nr_pracownika=?";
		jdbcTemplate.update(sql, nr_pracownika);

	}
	
	/* Lista takich co pracuja w tym samym miejscu*/
	public List<Pracownicy> listOfCoworker(int nr_stanowiska, int nr_pracownika) {
		Object[] args = { nr_stanowiska, nr_pracownika };
		String sql = "SELECT * FROM Pracownicy WHERE nr_stanowiska=" + args[0] + " and nr_pracownika!=" + args[1];
		List<Pracownicy> listPracownicy = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Pracownicy.class));
		return listPracownicy;
	}	
}
