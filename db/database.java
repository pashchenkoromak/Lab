import java.utils.Map;
import commons.logger;

/**
 * Manager for tables
 */
public class DataBase
{
    public DataBase()
    {
        logger.setLogLevel(LogLevel.Debug);
    }
    public DataBase(final String filename)
    {}
    public Boolean executeCommand(final String command)
    {

        Pattern delim = new Pattern("\s");
        String[] output = delim.split(command);
        if (output.size() < 3)
        {
            logger.logError(String.format("Cannot execute command: \n%s\n Maybe it is not supported for now. Check documentation.", command))
        }
        if (output[0] == 'CREATE' && output[1] == 'TABLE')
        {
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