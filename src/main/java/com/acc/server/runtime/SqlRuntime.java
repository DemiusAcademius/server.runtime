package com.acc.server.runtime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SqlRuntime {

    public static <T> T fetchOne(Connection connection, String sql, SqlConstructor<T> constructor, SqlPreparator preparator) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            if (preparator != null) preparator.prepare(st);
            try (ResultSet rs = st.executeQuery()) {
                return rs.next() ? constructor.construct(rs) : null;
            }
        }
    }

    public static <T> T preparedFetchOne(PreparedStatement st, SqlConstructor<T> constructor, SqlPreparator preparator) throws SQLException {
        if (preparator != null) preparator.prepare(st);
        try (ResultSet rs = st.executeQuery()) {
            return rs.next() ? constructor.construct(rs) : null;
        }
    }

    public static <T> List<T> fetchList(Connection connection, String sql, SqlConstructor<T> constructor, SqlPreparator preparator, int fetchSize) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            if (preparator != null) preparator.prepare(st);
            if (fetchSize > 0) st.setFetchSize(fetchSize);
            try (ResultSet rs = st.executeQuery()) {
                List<T> list = new ArrayList<>();
                while (rs.next()) list.add(constructor.construct(rs));
                return list;
            }
        }
    }

    public static <T> List<T> preparedFetchList(PreparedStatement st, SqlConstructor<T> constructor, SqlPreparator preparator, int fetchSize) throws SQLException {
        if (preparator != null) preparator.prepare(st);
        if (fetchSize > 0) st.setFetchSize(fetchSize);
        try (ResultSet rs = st.executeQuery()) {
            List<T> list = new ArrayList<>();
            while (rs.next()) list.add(constructor.construct(rs));
            return list;
        }
    }

    public static <T> T fetch(Connection connection, String sql, SqlFunction<T> consumer, SqlPreparator preparator, int fetchSize) throws Exception {
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            if (preparator != null) preparator.prepare(st);
            if (fetchSize > 0) st.setFetchSize(fetchSize);
            try (ResultSet rs = st.executeQuery()) {
                return consumer.apply(rs);
            }
        }
    }

    public static <T> T preparedFetch(PreparedStatement st, SqlFunction<T> consumer, SqlPreparator preparator, int fetchSize) throws Exception {
        if (preparator != null) preparator.prepare(st);
        if (fetchSize > 0) st.setFetchSize(fetchSize);
        try (ResultSet rs = st.executeQuery()) {
            return consumer.apply(rs);
        }
    }

    public static <T> void fetchStream(Connection connection, String sql, Consumer<T> consumer, SqlConstructor<T> constructor, SqlPreparator preparator, int fetchSize) throws SQLException {
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            if (preparator != null) preparator.prepare(st);
            if (fetchSize > 0) st.setFetchSize(fetchSize);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) consumer.accept(constructor.construct(rs));
            }
        }
    }

    public static String readCLOB(Connection connection, String sql, SqlPreparator preparator) throws SQLException, IOException {
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            if (preparator != null) preparator.prepare(st);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Clob clob = rs.getClob(1);
                    final StringBuilder sb = new StringBuilder();
                    final Reader reader = clob.getCharacterStream();

                    try (BufferedReader br = new BufferedReader(reader)) {
                        int b;
                        while(-1 != (b = br.read())) sb.append((char)b);
                    }
                    return sb.toString();
                } else return null;
            }
        }
    }

    public static long fetchSequence(Connection connection, String sequence) throws SQLException {
        return fetchOne(connection, "select " + sequence + ".nextval from dual",  rs -> rs.getLong(1), null);
    }

    public static void callProcedure(Connection connection, String sql, SqlPreparator preparator) throws SQLException {
        try (CallableStatement st = connection.prepareCall(sql)) {
            if (preparator != null) preparator.prepare(st);
            st.execute();
        }
    }

    public static <T> T callFunction(Connection connection, String sql, SqlStatementConstructor<T> constructor, SqlPreparator preparator) throws SQLException {
        try (CallableStatement st = connection.prepareCall(sql)) {
            if (preparator != null) preparator.prepare(st);
            st.execute();
            return constructor.construct(st);
        }
    }

}
