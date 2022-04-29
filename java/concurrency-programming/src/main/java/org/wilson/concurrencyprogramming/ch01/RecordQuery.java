package org.wilson.concurrencyprogramming.ch01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class RecordQuery {
    private final Connection connection;

    public RecordQuery(Connection connection) {
        this.connection = connection;
    }

    public <T> T query(RowHandler<T> handler, String sql, Object... params) throws SQLException {
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            int index = 1;
            for (Object param: params) {
                stmt.setObject(index++, param);
            }
            return handler.handle(stmt.executeQuery());
        }
    }
}
