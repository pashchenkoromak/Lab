package db;

import commons.LogLevel;
import commons.Logger;
import commons.Constants;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import commons.Pair;

/**
 * Manages sql commands.
 */
public class SQLCommandManager
{
    /**
     * Default constructor
     */
    public SQLCommandManager()
    {
        command = new String();
        logger = new Logger("SQLCommandManager", LogLevel.Trace);
        logger.logTraceIn();
        logger.logDebug("Create SQL SQLCommandManager");
        logger.logTraceOut();
    }

    /**
     * Remove empty strings from array
     */
    private String[] removeEmptyStrings(String[] input)
    {
        logger.logTraceIn();
        List<String> values = new ArrayList<String>();
        for(String data: input) {
            if(!data.isEmpty()) {
                values.add(data);
            }
        }
        logger.logTraceOut();
        return values.toArray(new String[values.size()]);
    }

    /**
     * Split input command by whitespaces and uppercase first two words (command).
     */
    private String[] split()
    {
        logger.logTraceIn();
        logger.logDebug(String.format("Splitting and checking \n\t%s", command));

        String delim = "[\\s,()]";
		String[] output = removeEmptyStrings(command.split(delim));

        if (output.length < 3)
        {
            logger.logError(String.format("Cannot execute command: \n\t%s\n Maybe it is not supported for now. Check documentation.", command));
        }

        output[0] = output[0].toUpperCase();
        output[1] = output[1].toUpperCase();
        logger.logTraceOut();
        return output;
    }

    /**
     * Loads command to command manager and prepare it to work
     * @param command
     */
    public void loadCommand(String command)
    {
        logger.logTraceIn();
        this.command = command;
        this.splittedCommand = split();
        logger.logDebug(String.format("Successfull loaded and splitted command \n\t%s", command));
        logger.logTraceOut();
    }

    /**
     * Check if command is "CREATE TABLE"
     */
    public Boolean isCreateTable()
    {
        logger.logTraceIn();
        logger.logTraceOut();
        return splittedCommand[0].equals("CREATE") && splittedCommand[1].equals("TABLE");
    }

    /**
     * Check if command is "INSERT INTO"
     */
    public Boolean isInsertInto()
    {
        logger.logTraceIn();
        logger.logTraceOut();
        return splittedCommand[0].equals("INSERT") && splittedCommand[1].equals("INTO");
    }

    /**
     * Check if command is "ALTER TABLE"
     */
    public Boolean isAlterTable()
    {
        logger.logTraceIn();
        logger.logTraceOut();
        return splittedCommand[0].equals("ALTER") && splittedCommand[1].equals("TABLE");
    }

    /**
     * Get table name from splitted command
     */
    public String getTableName()
    {
        logger.logTraceIn();
        logger.logTraceOut();
        return splittedCommand[2];
    }

    /**
     * Get last loaded command
     */
    public String getCommand()
    {
        logger.logTraceIn();
        logger.logTraceOut();
        return command;
    }

    /**
     * Get cols from a command, like
     * CREATE TABLE <name> ( colname coltype, colname coltype, ...)
     */
    public Vector<Pair> getCols()
    {
        logger.logTraceIn();
        Vector<Pair> cols = new Vector<Pair>();
        int splittedLength = splittedCommand.length;
        for(int wordNum = 3; wordNum < splittedLength; wordNum += 2)
        {
            if (!splittedCommand[wordNum].isEmpty())
            {
                String colName = splittedCommand[wordNum];
                String colType = splittedCommand[wordNum + 1];
                logger.logInfo(String.format("Add column \"%s\" with \"%s\" type.", colName, colType));
                if (Constants.DataTypes.contains(colType))
                    cols.add(new Pair(colName, colType));
            }
        }
        logger.logDebug("Finished getCols method");
        logger.logTraceOut();
        return cols;
    }

    /**
     * Get values (column, value). If columns aren't set - column is empty.
     *
     * For example:
     * INSERT INTO tablename ( col1, col3 ) VALUES ( val1, val2 )
     */
    Vector<Pair> getValues()
    {
        logger.logTraceIn();
        Vector<Pair> values = new Vector<Pair>();

        int splittedLength = splittedCommand.length;
        int wordNum = 3;
        Boolean customValues = !splittedCommand[wordNum].equals("VALUES");

        while (!splittedCommand[wordNum].equals("VALUES") && wordNum < splittedLength)
        {
            values.add(new Pair(splittedCommand[wordNum], ""));
            wordNum++;
        }
        wordNum++;
        for(int i = 0; wordNum < splittedLength; i++)
        {
            logger.logDebug(String.format("i = %d,\n\twordNum = %d,\n\tvalue = %s,\n\tvalues.size = %s", i, wordNum, splittedCommand[wordNum], values.size()));
            if (customValues){
                values.elementAt(i).second = splittedCommand[wordNum++];
            } else
            {
                values.add(new Pair("", splittedCommand[wordNum++]));
            }
        }
        logger.logTraceOut();
        return values;
    }

    /**
     * Contains splitted command (splitting by [\\s(),])
     */

    String[] splittedCommand;
    /**
     * Constains last loaded command
     */

    String command;
    /**
     * Use for logging
     */
    Logger logger;
}