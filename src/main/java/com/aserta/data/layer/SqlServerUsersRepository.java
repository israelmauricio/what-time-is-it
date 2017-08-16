package com.aserta.data.layer;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.aserta.business.entities.UserSignIn;
import com.aserta.business.entities.UserSignUp;
import com.aserta.data.interfaces.IUsersRepository;

public class SqlServerUsersRepository implements IUsersRepository {

	private JdbcTemplate jdbcTemplate;

	public SqlServerUsersRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean exists(String email) throws Exception {
		String sql = "SELECT COUNT(Id) FROM Users WHERE Email = ?";
		Object[] parameters = new Object[] { email };
		int count = (Integer) jdbcTemplate.queryForObject(sql, int.class, parameters);
		return count == 1;
	}

	@Override
	public int create(UserSignUp userSignUp) {
		DataSource dataSource = jdbcTemplate.getDataSource();
		SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource);
		insert.withTableName("Users").usingGeneratedKeyColumns("Id");
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("Email", userSignUp.getEmail());
		parameters.addValue("Fullname", userSignUp.getFullname());
		parameters.addValue("Password", userSignUp.getPassword());
		parameters.addValue("Birthdate", userSignUp.getBirthdate());

		return insert.executeAndReturnKey(parameters).intValue();
	}

	@Override
	public String verify(UserSignIn userSignIn) {
		// TODO Auto-generated method stub
		return null;
	}

}
