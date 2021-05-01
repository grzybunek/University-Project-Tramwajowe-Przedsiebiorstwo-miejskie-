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
public class StanowiskaDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	/* Constructor */
	public StanowiskaDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	/* List */
	public List<Stanowiska> list() {
		String sql = "SELECT * FROM Stanowiska";
		List<Stanowiska> listStanowiska = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Stanowiska.class));
		return listStanowiska;
	}
	
	/* Create */
	public void save(Stanowiska stanowiska) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		insertActor.withTableName("Stanowiska").usingColumns("Nazwa","Opis");
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(stanowiska);
		insertActor.execute(param);
	}
	
	/* Read */
	public Stanowiska get(int nr_stanowiska) {
		Object[] args = { nr_stanowiska };
		String sql = "SELECT * FROM Stanowiska WHERE Nr_stanowiska= " + args[0];
		Stanowiska stanowiska = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Stanowiska.class));
		return stanowiska;
	}
	
	/* Update */
	public void update(Stanowiska stanowiska) {
		String sql = "UPDATE Stanowiska SET nazwa=:nazwa, opis=:opis WHERE nr_stanowiska=:nr_stanowiska";
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(stanowiska);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
		template.update(sql, param);
	}
	
	/* Delete */
	public void delete(int nr_stanowiska) {
		String sql = "DELETE FROM Stanowiska WHERE nr_stanowiska=?";
		jdbcTemplate.update(sql, nr_stanowiska);
	}
	

}
