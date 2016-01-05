package com.acc.server.runtime;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface SqlPreparator {
	public void prepare(PreparedStatement statement) throws SQLException;
	
}
