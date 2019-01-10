package db;

import commons.LogLevel;
import commons.Logger;
import commons.Constants;
import java.util.Set;
import java.util.TreeSet;
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
        logger = new Logger("SQLCommandManager", LogLevel.Debug);
        logger.logDebug("Create SQL SQLCommandManager");
    }

    /**
     * Remove empty strings from array
     */
    private String[] removeEmptyStrings(String[] input)
    {
        List<String> values = new ArrayList<String>();
        for(String data: input) {
            if(!data.isEmpty()) {
                values.add(data);
            }
        }
        return values.toArray(new String[values.size()]);
    }

    /**
     * Split input command by whitespaces and uppercase first two words (command).
     */
    private String[] split()
    {
        logger.logDebug(String.format("Splitting and checking \n\t%s", command));

        String delim = "[\\s,()]";
		String[] output = removeEmptyStrings(command.split(delim));



        if (output.length < 3)
        {
            logger.logError(String.format("Cannot execute command: \n\t%s\n Maybe it is not supported for now. Check documentation.", command));
        }

        output[0] = output[0].toUpperCase();
        output[1] = output[1].toUpperCase();
        return output;
    }

    /**
     * Loads command to command manager and prepare it to work
     * @param command
     */
    public void loadCommand(String command)
    {
        this.command = command;
        this.splittedCommand = split();
        logger.logDebug(String.format("Successfull loaded and splitted command \n\t%s", command));
    }

    /**
     * Check if command is "CREATE TABLE"
     */
    public Boolean isCreateTable()
    {
        return splittedCommand[0].equals("CREATE") && splittedCommand[1].equals("TABLE");
    }

    /**
     * Check if command is "INSERT INTO"
     */
    public Boolean isInsertInto()
    {
        return splittedCommand[0].equals("INSERT") && splittedCommand[1].equals("INTO");
    }

    /**
     * Check if command is "ALTER TABLE"
     */
    public Boolean isAlterTable()
    {
        return splittedCommand[0].equals("ALTER") && splittedCommand[1].equals("TABLE");
    }

    /**
     * Get table name from splitted command
     */
    public String getTableName()
    {
        return splittedCommand[2];
    }

    /**
     * Get last loaded command
     */
    public String getCommand()
    {
        return command;
    }

    /**
     * Get cols from a command, like
     * CREATE TABLE <name> ( colname coltype, colname coltype, ...)
     */
    public Set<Pair> getCols()
    {
        Set<Pair> cols = new TreeSet<Pair>();
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
        return cols;
    }
    String[] splittedCommand;
    String command;
    Logger logger;
}