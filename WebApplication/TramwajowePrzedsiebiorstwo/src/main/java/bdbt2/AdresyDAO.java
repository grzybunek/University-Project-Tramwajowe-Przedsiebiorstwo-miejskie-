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
public class AdresyDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	/* Constructor */
	public AdresyDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	/* List */
	public List<Adresy> list() {
		String sql = "SELECT * FROM Adresy";
		List<Adresy> listAdresy = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Adresy.class));
		return listAdresy;
	}
	
	/* Create */
	public void save(Adresy adresy) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		insertActor.withTableName("Adresy").usingColumns("Miasto","Ulica", "Nr_lokalu", "Nr_poczty");
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(adresy);
		insertActor.execute(param);
	}
	
	/* Create and return the number */
	public Number saveWithNumber(Adresy adresy) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		insertActor.withTableName("Adresy").usingColumns("Miasto","Ulica", "Nr_lokalu", "Nr_poczty").usingGeneratedKeyColumns("Nr_adresu");
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(adresy);
		return insertActor.executeAndReturnKey(param);
	}
	
	/* Read */
	public Adresy get(int nr_adresu) {
		Object[] args = { nr_adresu };
		String sql = "SELECT * FROM Adresy WHERE Nr_adresu= " + args[0];
		Adresy adresy = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Adresy.class));
		return adresy;
	}
	
	/* Update */
	public void update(Adresy adresy) {
		String sql = "UPDATE Adresy SET miasto=:miasto, ulica=:ulica, nr_lokalu=:nr_lokalu, nr_poczty=:nr_poczty WHERE nr_adresu=:nr_adresu";
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(adresy);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
		template.update(sql, param);
	}
	
	/* Delete */
	public void delete(int nr_adresy) {
		String sql = "DELETE FROM Adresy WHERE nr_adresu=?";
		jdbcTemplate.update(sql, nr_adresy);
	}
}
