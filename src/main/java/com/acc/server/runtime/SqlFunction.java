package com.acc.server.runtime;

import java.sql.ResultSet;

@FunctionalInterface
public interface SqlFunction<T> {
	public T apply(ResultSet resultSet) throws Exception;
	
}
