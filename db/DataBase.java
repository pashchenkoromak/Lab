package db;

import java.util.Map;
import java.util.TreeMap;
import commons.Logger;
import commons.LogLevel;
import db.Table;
import db.SQLCommandManager;

/**
 * Manager for tables
 */
public class DataBase
{
    /**
     * Create new database <don't forget to delete this> with blackjack and whores </>
     */
    public DataBase()
    {
        logger = new Logger("DataBase", LogLevel.Trace);
        logger.logTraceIn();
        logger.logDebug("Create new DataBase");
        sqlCommandManager = new SQLCommandManager();
        tables = new TreeMap<String, Table>();
        logger.logTraceOut();
    }
    /**
     * Used for loading database from file
     */
    public DataBase(final String filename)
    {}

    /**
     * Save whole database to file
     */
    public void saveToFile(final String filename)
    {
        logger.logTraceIn();
        logger.logTraceOut();
    }

    /**
     * Looks like this isnt best function for this. But I really dont want polymorphism here, so will think...
     */
    public Boolean executeCommand(final String command)
    {
        logger.logTraceIn();
        logger.logDebug(String.format("Execute command: \n\t%s", command));

        Boolean res = false;

        sqlCommandManager.loadCommand(command);
        if (sqlCommandManager.isCreateTable())
        {
            logger.logDebug(String.format("That's CREATE TABLE: \n\t%s", command));
            tables.put(sqlCommandManager.getTableName(), new Table(sqlCommandManager));
            res = true;
        } else if (sqlCommandManager.isInsertInto() || sqlCommandManager.isAlterTable())
            res = tables.get(sqlCommandManager.getTableName()).executeCommand(sqlCommandManager);
        else {
            res = false;
            logger.logError(String.format("This command is unsupported yet:\n%s", command));
        }
        logger.logTraceOut();
        return res;
    }

    public String toString()
    {
        logger.logTraceIn();
        StringBuilder res = new StringBuilder();
        for (Map.Entry<String, Table> table : tables.entrySet())
        {
            res.append(String.format("%s\n", table.getValue().toString()));
        }
        logger.logTraceOut();
        return res.toString();
    }

    SQLCommandManager sqlCommandManager;
    /**
     * Name/table map.
     */
    Map<String, Table> tables;
    /**
     * Use it for logging
     */
    Logger logger;
}