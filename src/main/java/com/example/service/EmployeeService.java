package com.example.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Employee;

@RestController
@RequestMapping("/employee")
public class EmployeeService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(method = RequestMethod.POST, path = "/add")
	public String add(@RequestBody Employee employee) {
		String id = UUID.randomUUID().toString();
		employee.setId(id);

		String sql = "INSERT INTO EMPLOYEE " + "(id, name, projectname) VALUES (?, ?, ?)";

		// define query arguments
		Object[] params = new Object[] { employee.getId(), employee.getName(), employee.getProjectName() };

		int row = jdbcTemplate.update(sql, params);

		String json = "{\"id\":\"%s\"}";
		String result = "";

		if (row == 1) {
			result = String.format(json, id);
		} else {
			result = String.format(json, result);
		}
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/all")
	@ResponseBody
	public List<Employee> all() {
		String sql = "SELECT * FROM EMPLOYEE";

		List<Employee> employees = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Employee.class));

		return employees;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/{empId}")
	@ResponseBody
	public Employee fetch(@PathVariable("empId") String empId) {

		String sql = "SELECT * FROM EMPLOYEE where id = ?";
		Object[] params = new Object[] { empId };
		
		List<Employee> empList = jdbcTemplate.query(sql, params, new RowMapper<Employee>() {

			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee emp = new Employee();
				emp.setId(rs.getString("id"));
				emp.setName(rs.getString("name"));
				emp.setProjectName(rs.getString("projectname"));
				return emp;
			}

		});
		/*
		 * Employee employee = (Employee) jdbcTemplate.queryForObject(sql, new
		 * Object[] { empId }, new BeanPropertyRowMapper(Employee.class));
		 * 
		 * return employee;
		 */
		if (empList == null || empList.size() == 0) {
			return null;
		} else {
			return empList.get(0);
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/delete/{empId}")
	@ResponseBody
	public String del(@PathVariable("empId") String empId) {

		String sql = "DELETE FROM EMPLOYEE where id = ?";

		// define query arguments
		Object[] params = new Object[] { empId };

		int row = jdbcTemplate.update(sql, params);

		String json = "{\"status\":%s}";
		String result = null;

		if (row == 1) {
			result = String.format(json, true);
		} else {
			result = String.format(json, false);
		}
		return result;
	}

}
