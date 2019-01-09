package db;

import javafx.util.Pair;
import java.util.Vector;
import commons.Logger;
import commons.Constants;

/**
 * @brief Class for managing tables
 */
public class Table
{
    private String[] split(final String command)
    {
        String delim = "[\\s,()]";
        String[] output = command.split(delim);

        output[0] = output[0].toUpperCase();
        output[1] = output[1].toUpperCase();
        return output;
    }
    /** @brief
     * "CREATE TABLE name (
     *      col1 datatype,
     *      ...
     *  )"
     * @param[in] command - in form as below.
     */
    Table(final String command)
    {
        logger.logInfo(String.format("Create table from command:\n%s", command));
        String[] splitted = split(command);

        name = splitted[2];
        int splittedLength = splitted.length;
        for(int wordNum = 3; wordNum < splittedLength; wordNum += 2)
        {
            if (!splitted[wordNum].isEmpty())
            {
                String colName = splitted[wordNum];
                String colType = splitted[wordNum + 1];
                if (Constants.DataTypes.contains(colType))
                    cols.add(new Pair<>(colName, colType));
            }
        }
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
    public Boolean executeCommand(final String command)
    {
        logger.logError(String.format("This command is unsupported yet:\n%s", command));
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

    /**
     * Column name as key, and datatype as value.
     */
    private Vector<Pair<String, String>> cols;

    /**
     * Records in database
     */
    private Vector<Vector<String>> rows;

    /**
     * Use it for logging
     */
    private Logger logger;
    private String name;
}