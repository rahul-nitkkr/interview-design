package loader.sink;

import loader.model.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class SnowflakeSink implements Sink {

    private final String tableName;
    private final Connection snowflakeConnection;

    public SnowflakeSink(String accountName, String schemaName, String databaseName, String tableName) throws SQLException {

        this.tableName = tableName;
        Properties properties = new Properties();
        properties.put("user", "royrah");
        properties.put("password", "Hello@1992");
        properties.put("account", accountName);
        properties.put("db", databaseName);
        properties.put("schema", schemaName);
        properties.put("warehouse", "COMPUTE_WH");
        properties.put("role", "ACCOUNTADMIN");

        String connectStr = String.format("jdbc:snowflake://%s.snowflakecomputing.com/", "jo40229.europe-west4.gcp");

        this.snowflakeConnection = DriverManager.getConnection(connectStr, properties);
    }

    @Override
    public void loadData(Data data) throws SQLException {
        StringBuilder valuesBuilder = new StringBuilder();
        List<String> values = data.getData().stream().map(dataRow -> String.format("(%s)", dataRow)).collect(Collectors.toList());
        for (String value : values) {
            valuesBuilder.append(value);
            valuesBuilder.append(',');
        }

        String formattedValues = valuesBuilder.substring(0, valuesBuilder.toString().length() - 1);
        String sql = String.format("INSERT INTO %s VALUES %s", tableName, formattedValues);
        System.out.println("Executing SQL => " + sql);
        Statement statement = snowflakeConnection.createStatement();
        statement.execute(sql);
    }
}
