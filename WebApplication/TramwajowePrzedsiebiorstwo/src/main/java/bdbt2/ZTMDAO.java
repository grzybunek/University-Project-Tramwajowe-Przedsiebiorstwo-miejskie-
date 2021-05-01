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
public class ZTMDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/* Constructor */
	public ZTMDAO(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	/* List */
	public List<ZTM> list() {
		String sql = "SELECT * FROM ZTM";
		List<ZTM> listZTM = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(ZTM.class));
		return listZTM;
	}

	/* Create */
	public void save(ZTM ztm) {
		SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
		insertActor.withTableName("ZTM").usingColumns("Nazwa", "Miasto", "Data_zalozenia", "Nr_adresu");
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(ztm);
		insertActor.execute(param);

	}

	/* Read */
	public ZTM get(int nr_biura) {
		Object[] args = { nr_biura };
		String sql = "SELECT * FROM ZTM WHERE Nr_biura= " + args[0];
		ZTM ztm = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(ZTM.class));
		return ztm;
	}

	/* Update */
	public void update(ZTM ztm) {
		String sql = "UPDATE ZTM SET nazwa=:nazwa, miasto=:miasto, data_zalozenia=:data_zalozenia, nr_adresu=:nr_adresu WHERE nr_biura=:nr_biura";
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(ztm);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
		template.update(sql, param);
	}

	/* Delete */
	public void delete(int nr_biura) {
		String sql = "DELETE FROM ZTM WHERE nr_biura=?";
		jdbcTemplate.update(sql, nr_biura);

	}

}
