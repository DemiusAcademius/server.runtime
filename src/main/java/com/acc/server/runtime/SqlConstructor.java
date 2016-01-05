package com.acc.server.runtime;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface SqlConstructor<T> {
	public T construct(ResultSet resultSet) throws SQLException;
	
}
