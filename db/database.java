import java.utils.Map;
import commons.logger;

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
    private String[] splitAndCheck(final String command)
    {
        logDebug(String.format("splitting and checking \n%s", command));

        Pattern delim = new Pattern("\s");
        String[] output = delim.split(command);
        if (output.size() < 3)
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
        logger.logDebug(String.format("Check if command: \n%s\n\t- CREATE TABLE\n", command));
        return splittedCommand[0] == 'CREATE' && splittedCommand[1] == 'TABLE';
    }

    /**
     * Check if command is "INSERT INTO"
     */
    private Boolean isInsertInto(final String[] splittedCommand)
    {
        logger.logDebug(String.format("Check if command: \n%s\n\t- INSERT INTO\n", command));
        return splittedCommand[0] == 'INSERT' && splittedCommand[1] == 'INTO';
    }

    /**
     * Check if command is "ALTER TABLE"
     */
    private Boolean isAlterTable(final String[] splittedCommand)
    {
        logger.logDebug(String.format("Check if command: \n%s\n\t- ALTER TABLE\n", command));
        return splittedCommand[0] == 'ALTER' && splittedCommand[1] == 'TABLE';
    }

    /**
     * Take table name from splitted command
     */
    private getTableName(String[] splittedCommand)
    {
        return splittedCommand[2];
    }
    /**
     * Looks like this isnt best function for this. But I really dont want polymorphism here, so will think...
     */
    public Boolean executeCommand(final String command)
    {
        logger.logDebug(String.format("Execute command: \n%s\n", command));
        String[] output = splitAndCheck(command);

        if (isCreateTable(output))
        {
            logger.logDebug(String.format("That's CREATE TABLE: \n%s\n", command));
            tables.put(getTableName(output), new Table(command));
        }

        if (isInsertInto(output) || isAlterTable(output))
            return tables.get(getTableName(output)).executeCommand(command);
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