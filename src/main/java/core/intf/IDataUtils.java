package core.intf;

public interface IDataUtils {
    boolean checkIfTableExists(String schemaName, String tableName);

    void createTable(String schemaName, String tableName);
}
