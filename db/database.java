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
     * Looks like this isnt best function for this. But I really dont want polymorphism here, so will think...
     */
    public Boolean executeCommand(final String command)
    {
        logger.logDebug(String.format("Execute command: \n%s", command));

        Pattern delim = new Pattern("\s");
        String[] output = delim.split(command);
        if (output.size() < 3)
        {
            logger.logError(String.format("Cannot execute command: \n%s\n Maybe it is not supported for now. Check documentation.", command))
        }

        output[0] = output[0].toUpperCase();
        output[1] = output[1].toUpperCase();

        if (output[0] == 'CREATE' && output[1] == 'TABLE')
        {
            logger.logDebug(String.format("That's CREATE TABLE: \n%s", command));
            tables.put(output[2], new Table(command));
        }

        if ((output[0] == 'INSERT' && output[1] == 'INTO')
            || output[0] == 'ALTER' && output[1] == 'TABLE')
            try {
                tables.get(output[2]).executeCommand(command);
            } catch (Exception e) {
                logger.logError(String.format("Cannot execute command: \n%s\n Maybe it is not supported for now. Check documentation.", command))
                logger.logError(String.format("Exception: %s", e.toString));
            }
    }

    Map<String, Table> tables;
    Logger logger;
}