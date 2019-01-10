package tests;

import db.SQLCommandManager;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestSQLCommandManager {

    @org.junit.Test
    public void testLoadCommand() {
        SQLCommandManager sqlCommandManager = new SQLCommandManager();
        String tableName = new String("testTable");
        String command = String.format("Create table %s ( col1 string, col2 string )", tableName);
        sqlCommandManager.loadCommand(command);
        assertEquals(command, sqlCommandManager.getCommand());
        assertEquals(tableName, sqlCommandManager.getTableName());
    }
}