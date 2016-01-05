package com.acc.server.runtime;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface SqlStatementConstructor<T> {
	public T construct(PreparedStatement statement) throws SQLException;
	
}
