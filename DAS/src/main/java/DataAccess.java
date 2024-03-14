import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataAccess {

    // #region DB execute query methods

    protected static List<Map<String, Object>> sqlConnectAndRunQuery(String query) {
        String connectionUrl = "jdbc:mysql://localhost:3306/donations_manager";

        List<Map<String, Object>> resultList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(connectionUrl, "starrp", "ABCD****1234");
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {
            ResultSetMetaData metaData = rs.getMetaData();
            Integer columnCount = metaData.getColumnCount();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), rs.getObject(i));
                }
                resultList.add(row);
            }
        } catch (SQLException e) {
            // for now just print out SQL Exception contents, but in real app, do
            // appropriate action...
            System.out.println("SQLException experienced: " + e.getErrorCode() + "; " + e.getLocalizedMessage());
        }

        return resultList;
    }

    protected static boolean sqlConnectAndExecute(String query) {
        String connectionUrl = "jdbc:mysql://localhost:3306/donations_manager";
        boolean result = false;
        try {
            Connection connection = DriverManager.getConnection(connectionUrl, "starrp", "ABCD****1234");
            PreparedStatement ps = connection.prepareStatement(query);
            result = ps.execute();
        } catch (SQLException e) {
            // for now just print out SQL Exception contents, but in real app, do
            // appropriate action...
            System.out.println("SQLException experienced: " + e.getErrorCode() + "; " + e.getLocalizedMessage());
        }
        return result;
    }

    protected static List<String> sqlConnectAndRunSimpleQuery(String query) {
        String connectionUrl = "jdbc:mysql://localhost:3306/donations_manager";

        List<String> resultList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(connectionUrl, "starrp", "ABCD****1234");
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery()) {
            ResultSetMetaData metaData = rs.getMetaData();
            Integer columnCount = metaData.getColumnCount();
            while (rs.next()) {
                String row = "";
                for (int i = 1; i <= columnCount; i++) {
                    row = (rs.getObject(i)).toString();
                    resultList.add(row);
                }
            }
        } catch (SQLException e) {
            // for now just print out SQL Exception contents, but in real app, do
            // appropriate action...
            System.out.println("SQLException experienced: " + e.getErrorCode() + "; " + e.getLocalizedMessage());
        }

        return resultList;
    }
    // #endregion
}
