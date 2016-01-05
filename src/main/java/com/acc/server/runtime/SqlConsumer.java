package com.acc.server.runtime;

import java.sql.ResultSet;

@FunctionalInterface
public interface SqlConsumer {
	public void consume(ResultSet resultSet) throws Exception;
	
}
