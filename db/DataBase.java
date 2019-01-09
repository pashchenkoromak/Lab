package db;

import java.util.Map;
import commons.Logger;
import commons.LogLevel;
import db.Table;
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
        logger.setLogLevel(LogLevel.Debug);
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
    {}

    /**
     * Split input command by whitespaces and uppercase first two words (command).
     */
    private String[] split(final String command)
    {
        logger.logDebug(String.format("Splitting and checking \n%s", command));

        String delim = "[\\s,()]";
		String[] output = command.split(delim);
        if (output.length < 3)
        {
            logger.logError(String.format("Cannot execute command: \n%s\n Maybe it is not supported for now. Check documentation.", command));
        }

        output[0] = output[0].toUpperCase();
        output[1] = output[1].toUpperCase();
        return output;
    }

    /**
     * Check if command is "CREATE TABLE"
     */
    private Boolean isCreateTable(final String[] splittedCommand)
    {
        return splittedCommand[0].equals("CREATE") && splittedCommand[1].equals("TABLE");
    }

    /**
     * Check if command is "INSERT INTO"
     */
    private Boolean isInsertInto(final String[] splittedCommand)
    {
        return splittedCommand[0].equals("INSERT") && splittedCommand[1].equals("INTO");
    }

    /**
     * Check if command is "ALTER TABLE"
     */
    private Boolean isAlterTable(final String[] splittedCommand)
    {
        return splittedCommand[0].equals("ALTER") && splittedCommand[1].equals("TABLE");
    }

    /**
     * Take table name from splitted command
     */
    private final String getTableName(String[] splittedCommand)
    {
        return splittedCommand[2];
    }
    /**
     * Looks like this isnt best function for this. But I really dont want polymorphism here, so will think...
     */
    public Boolean executeCommand(final String command)
    {
        logger.logDebug(String.format("Execute command: \n%s", command));
        String[] output = split(command);

        if (isCreateTable(output))
        {
            logger.logDebug(String.format("That's CREATE TABLE: \n%s", command));
            tables.put(getTableName(output), new Table(command));
            return true;
        }

        if (isInsertInto(output) || isAlterTable(output))
            return tables.get(getTableName(output)).executeCommand(command);

        logger.logError(String.format("This command is unsupported yet:\n%s", command));
        return false;
    }

    /**
     * Name/table map.
     */
    Map<String, Table> tables;
    /**
     * Use it for logging
     */
    Logger logger;
}