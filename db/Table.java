package db;

import java.util.Vector;
import java.util.Set;
import java.util.TreeSet;
import commons.Pair;
import commons.Logger;
import commons.Constants;
import commons.LogLevel;
import db.SQLCommandManager;

/**
 * @brief Class for managing tables
 */
public class Table
{
    /** @brief
     * "CREATE TABLE name (
     *      col1 datatype,
     *      ...
     *  )"
     * @param[in] command - in form as below.
     */
    Table(final SQLCommandManager sqlCommandManager)
    {
        logger = new Logger("Table", LogLevel.Debug);
        logger.logInfo(String.format("Create table from command:\n\t%s", sqlCommandManager.getCommand()));
        cols = new TreeSet<Pair>();
        rows = new Vector<Vector<String>>();

        name = sqlCommandManager.getTableName();
        cols = sqlCommandManager.getCols();
    }

    /**
     * Get name of table
     */
    public final String getName()
    {
        return name;
    }

    /**
     * Parse and execute command to table.
     * Available commands for now:
     *
     */
    public Boolean executeCommand(final SQLCommandManager sqlCommandManager)
    {
        //if (sqlCommandManager.isInsertInto)
        logger.logError(String.format("This command is unsupported yet:\n%s", sqlCommandManager.getCommand()));
        return false;
    }

    /**
     * Check, if format of row is OK for name/datatypes
     */
    private Boolean checkFormat(Vector<String> row)
    {
        return true;
    }

    /**
     * Used for INSERT INTO
     */
    private Boolean addRow(Vector<String> row)
    {
        if (!checkFormat(row)){
            logger.logError(String.format("Wrong ROW format for row: %s and columns %s", row, cols.toString()));
            return false;
        }
        logger.logInfo(String.format("Add to %s table new row: %s", name, row));
        return rows.add(row);
    }

    public String toString()
    {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Table %s\n", getName()));
        for (Pair col : cols) {
            res.append(String.format("%s\t", col.toString()));
        }
        for (Vector<String> row : rows) {
            res.append('\n');
            for (String record : row) {
                res.append(String.format("%s\t", record));
            }
        }
        return res.toString();
    }

    /**
     * Column name as key, and datatype as value.
     */
    private Set<Pair> cols;

    /**
     * Records in database
     */
    private Vector<Vector<String>> rows;

    /**
     * Use it for logging
     */
    private Logger logger;

    /**
     * Table name
     */
    private String name;
}
