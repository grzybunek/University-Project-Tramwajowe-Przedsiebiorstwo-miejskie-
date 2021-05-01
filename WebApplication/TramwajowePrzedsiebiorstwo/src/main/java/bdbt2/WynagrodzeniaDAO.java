package bdbt2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;


@Repository
public class WynagrodzeniaDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/* Constructor */
	public WynagrodzeniaDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/* List */
	public List<Wynagrodzenia> list() {
		String sql = "SELECT * FROM Wynagrodzenia";
		List<Wynagrodzenia> listWynagrodzenia = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Wynagrodzenia.class));
		return listWynagrodzenia;
	}
	
	/* Create */
	public void save(Wynagrodzenia wynagrodzenia) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		insertActor.withTableName("Wynagrodzenia").usingColumns("Data","Kwota_podstawowa", "Kwota_dodatkowa", "Nr_pracownika");
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(wynagrodzenia);
		insertActor.execute(param);
	}
	
	/* Read */
	public Wynagrodzenia get(int nr_wynagrodzenia) {
		Object[] args = { nr_wynagrodzenia };
		String sql = "SELECT * FROM Wynagrodzenia WHERE Nr_wynagrodzenia= " + args[0];
		Wynagrodzenia wynagrodzenia = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Wynagrodzenia.class));
		return wynagrodzenia;
	}
	
	/* Update */
	public void update(Wynagrodzenia wynagrodzenia) {
		String sql = "UPDATE Wynagrodzenia SET data=:data, kwota_podstawowa=:kwota_podstawowa, kwota_dodatkowa=:kwota_dodatkowa, "
				+ "nr_pracownika=:nr_pracownika WHERE nr_wynagrodzenia=:nr_wynagrodzenia";
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(wynagrodzenia);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
		template.update(sql, param);
	}
	
	/* Delete */
	public void delete(int nr_wynagrodzenia) {
		String sql = "DELETE FROM Wynagrodzenia WHERE nr_wynagrodzenia=?";
		jdbcTemplate.update(sql, nr_wynagrodzenia);
	}
	
	/* Lista wynbagrodzen jednego pracownika */
	public List<Wynagrodzenia> listOfMoney(int nr_pracownika) {
		Object[] args = { nr_pracownika };
		String sql = "SELECT * FROM Wynagrodzenia WHERE nr_pracownika=" + args[0];
		List<Wynagrodzenia> listWynagrodzenia = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Wynagrodzenia.class));
		return listWynagrodzenia;
	}

}
