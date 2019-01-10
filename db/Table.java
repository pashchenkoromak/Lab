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
        logger = new Logger("Table", LogLevel.Trace);
        logger.logTraceIn();
        logger.logInfo(String.format("Create table from command:\n\t%s", sqlCommandManager.getCommand()));
        cols = new TreeSet<Pair>();
        rows = new Vector<Vector<String>>();

        name = sqlCommandManager.getTableName();
        cols = sqlCommandManager.getCols();
        logger.logTraceOut();
    }

    /**
     * Get name of table
     */
    public final String getName()
    {
        logger.logTraceIn();
        logger.logTraceOut();
        return name;
    }

    Vector<String> createRow(Vector<Pair> colVal)
    {
        Vector<String> newRow = new Vector<Pair>();
        newRow.setSize(cols.length);
        Boolean useDefaultColOrder = record.first().isEmpty();
        for (Pair record : colVal) {
            if (useDefaultColOrder)
            {
                newRow.add(record.second());
            }
        }
    }

    public Boolean insertInto(SQLCommandManager sqlCommandManager)
    {
        logger.logTraceIn();
        Boolean returnValue = false;
        logger.logInfo(String.format("Inserting into %s", sqlCommandManager.getTableName()));
        Vector<Pair> colVal = sqlCommandManager.getValues();
        Vector<String> newRow = createRow(colVal);
        if (checkFormat(newRow)){
            rows.add(newRow);
            returnValue = true;
        } else
        {
            returnValue = false;
        }
        logger.logTraceOut();
        return returnValue;
    }

    /**
     * Parse and execute command to table.
     * Available commands for now:
     *
     */
    public Boolean executeCommand(SQLCommandManager sqlCommandManager)
    {
        logger.logTraceIn();
        Boolean returnValue = false;
        if (sqlCommandManager.isInsertInto())
        {
            returnValue = insertInto(sqlCommandManager);
        } else {
            logger.logError(String.format("This command is unsupported yet:\n%s", sqlCommandManager.getCommand()));
        }
        logger.logTraceOut();
        return returnValue;
    }

    /**
     * Check, if format of row is OK for name/datatypes
     */
    private Boolean checkFormat(Vector<String> row)
    {
        logger.logTraceIn();
        logger.logTraceOut();
        return true;
    }

    /**
     * Used for INSERT INTO
     */
    private Boolean addRow(Vector<String> row)
    {
        logger.logTraceIn();
        if (!checkFormat(row)){
            logger.logError(String.format("Wrong ROW format for row: %s and columns %s", row, cols.toString()));
            return false;
        }
        logger.logInfo(String.format("Add to %s table new row: %s", name, row));
        logger.logTraceOut();
        return rows.add(row);
    }

    public String toString()
    {
        logger.logTraceIn();
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
        logger.logTraceOut();
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
