package tests;

import db.SQLCommandManager;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestSQLCommandManager {

    @org.junit.Test
    public void testLoadCommandNormal() {
        SQLCommandManager sqlCommandManager = new SQLCommandManager();
        String tableName = new String("testTable");
        String command = String.format("Create table %s ( col1 string, col2 string )", tableName);
        sqlCommandManager.loadCommand(command);
        assertEquals(command, sqlCommandManager.getCommand());
        assertEquals(tableName, sqlCommandManager.getTableName());
        assertTrue(sqlCommandManager.isCreateTable());
        assertFalse(sqlCommandManager.isInsertInto());
        assertFalse(sqlCommandManager.isAlterTable());
    }
    @org.junit.Test
    public void testLoadCommandNull() {
        SQLCommandManager sqlCommandManager = new SQLCommandManager();
        String tableName = null;
        String command = null;
        sqlCommandManager.loadCommand(command);
        assertEquals(command, sqlCommandManager.getCommand());
        assertEquals(tableName, sqlCommandManager.getTableName());
        assertFalse(sqlCommandManager.isCreateTable());
        assertFalse(sqlCommandManager.isInsertInto());
        assertFalse(sqlCommandManager.isAlterTable());
    }
}