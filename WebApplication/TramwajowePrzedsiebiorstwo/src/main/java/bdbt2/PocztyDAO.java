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
public class PocztyDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/* Constructor */
	public PocztyDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	/* List */
	public List<Poczty> list() {
		String sql = "SELECT * FROM Poczty";
		List<Poczty> listPoczty = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Poczty.class));
		return listPoczty;
	}

	/* Create */
	public void save(Poczty poczty) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		insertActor.withTableName("Poczty").usingColumns("Kod_poczty", "Poczta");
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(poczty);
		insertActor.execute(param);
	}

	/* Create and return the number */
	public Number saveWithNumber(Poczty poczty) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		insertActor.withTableName("Poczty").usingColumns("Kod_poczty", "Poczta").usingGeneratedKeyColumns("Nr_poczty");
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(poczty);
		return insertActor.executeAndReturnKey(param);
	}

	/* Read */
	public Poczty get(int nr_poczty) {
		Object[] args = { nr_poczty };
		String sql = "SELECT * FROM Poczty WHERE Nr_poczty= " + args[0];
		Poczty poczty = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Poczty.class));
		return poczty;
	}

	/* Update */
	public void update(Poczty poczty) {
		String sql = "UPDATE Poczty SET kod_poczty=:kod_poczty, poczta=:poczta WHERE nr_poczty=:nr_poczty";
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(poczty);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
		template.update(sql, param);
	}

	/* Delete */
	public void delete(int nr_poczty) {
		String sql = "DELETE FROM Poczty WHERE nr_poczty=?";
		jdbcTemplate.update(sql, nr_poczty);
	}

	/* Checking unique */
	public boolean isUnique(String kod_poczty, int nr_poczty) {
		Object[] args = { kod_poczty, nr_poczty };
		String sql = "SELECT * FROM Poczty WHERE kod_poczty= '" + args[0]+"'" + "AND nr_poczty!= "+ args[1];
		List<Poczty> listPoczty = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Poczty.class));
		int rozmiarListy = listPoczty.size();
		if (rozmiarListy == 0) {
			return true;

		} else {
			return false;
		}

	}
	
	/* Checking unique */
	public boolean isUnique2(String kod_poczty) {
		Object[] args = { kod_poczty };
		String sql = "SELECT * FROM Poczty WHERE kod_poczty= '" + args[0]+"'";
		List<Poczty> listPoczty = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Poczty.class));
		int rozmiarListy = listPoczty.size();
		if (rozmiarListy == 0) {
			return true;

		} else {
			return false;
		}

	}
	

	/* Read from kod_poczty */
	public int getFromKod(String kod_poczty) {
		Object[] args = { kod_poczty };
		String sql = "SELECT * FROM Poczty WHERE Kod_poczty= '" + args[0]+"'";
		Poczty poczty = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Poczty.class));
		return poczty.getNr_poczty();
	}

}
