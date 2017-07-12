package com.fuluola.springboot;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.fuluola.model.PreDomainInfo;

/** 
 * @description 
 * @author  fuzhuan fu.luola@qq.com
 * @date 2017年7月12日 
 */
@Repository
public class DomainRepository {

	private static String insertSQL = "insert into domain (domain,createTime) values(?,now())"; 
	private static String findSQL = "select domain,status,createTime,errorCount from domain where domain=?";
	private JdbcTemplate jdbc;
	
	public DomainRepository(JdbcTemplate jdbc){
		this.jdbc = jdbc;
	}
	
	public PreDomainInfo findByDomain(String data){
		
		
		return null;
	}
	public int insertDomain(String data) {
		
		return jdbc.update(insertSQL,data);
	}
	
}

class PreDomainRowMapper implements RowMapper<PreDomainInfo> {

	@Override
	public PreDomainInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		PreDomainInfo entity = new PreDomainInfo();
		entity.setCreateTime(rs.getDate("createdTime"));
		return entity;
	}
	
}