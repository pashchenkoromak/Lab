import java.util.Map;
import java.util.Vector;
import commons.logger;

/**
 * @brief Class for managing tables
 */
public class Table
{
    /** @brief
     * CREATE TABLE name (
     *      col1 datatype,
     *      ...
     *  )
     * @param[in] inputCommand - in form as below.
     */
    Table(final String inputCommand)
    {}

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
        Pattern delim = new Pattern("\s");
        String[] output = delim.split(command);
    }

    /**
     * Used for INSERT INTO
     */
    private Boolean addRow(Vector<String> row)
    {
        if (!checkFormat(row)){
            logger.logError(String.format("Wrong ROW format for row: %s and columns %s", row, cols.toString());
            return FALSE;
        }
        logger.logInfo(String.format("Add to %s table new row: %s", name, row));
        return rows.add(row);
    }

    /**
     * Column name as key, and datatype as value.
     */
    private Map<String, String> cols;

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