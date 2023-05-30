package lt.viko.eif.vskuder.sql;

import java.sql.*;

public class UniqueMethods {

    private static Connection connection;
    protected static String url = "jdbc:mysql://localhost:3306/db_practice";
    protected static String user = "root";
    protected static String password = "root";

    public UniqueMethods() throws SQLException {
        this.connection = DriverManager.getConnection(url, user, password);
    }

    public String search(String tableName, String columnName, String searchValue) throws SQLException {
        String query = "SELECT * FROM " + tableName + " WHERE " + columnName + " LIKE ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, "%" + searchValue + "%");
        ResultSet rs = stmt.executeQuery();
        return resultSetToString(rs);
    }

    public int count(String tableName) throws SQLException {
        String query = "SELECT COUNT(*) FROM " + tableName;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs.next() ? rs.getInt(1) : 0;
    }

    public boolean exists(String tableName, String columnName, String value) throws SQLException {
        String query = "SELECT EXISTS(SELECT 1 FROM " + tableName + " WHERE " + columnName + " = ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, value);
        ResultSet rs = stmt.executeQuery();
        return rs.next() && rs.getBoolean(1);
    }

    public String join(String table1, String table2, String table1Column, String table2Column) throws SQLException {
        String query = "SELECT * FROM " + table1 + " JOIN " + table2 + " ON " + table1 + "." + table1Column + " = " + table2 + "." + table2Column;
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return resultSetToString(rs);
    }

    public String orderBy(String tableName, String columnName, boolean ascending) throws SQLException {
        String query = "SELECT * FROM " + tableName + " ORDER BY " + columnName + (ascending ? " ASC" : " DESC");
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return resultSetToString(rs);
    }

    private String resultSetToString(ResultSet rs) throws SQLException {
        StringBuilder sb = new StringBuilder();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                sb.append(metaData.getColumnName(i)).append(": ");
                sb.append(rs.getString(i)).append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
