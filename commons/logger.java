/**
 * Used for colored output to terminal
 */
public static final String ANSI_RESET = "\u001B[0m";
public static final String ANSI_BLACK = "\u001B[30m";
public static final String ANSI_RED = "\u001B[31m";
public static final String ANSI_GREEN = "\u001B[32m";
public static final String ANSI_YELLOW = "\u001B[33m";
public static final String ANSI_BLUE = "\u001B[34m";
public static final String ANSI_PURPLE = "\u001B[35m";
public static final String ANSI_CYAN = "\u001B[36m";
public static final String ANSI_WHITE = "\u001B[37m";

/**
 * LogLevels for logger. Use them for control your logging
 */
enum LogLevel{
    /**
     * Don't log at all
     */
    NO_LOGGING
    /**
     * Only errors will be logged
     */
    Error,
    /**
     * Error + some addition info
     */
    Info,
    /**
     * Info + debug info
     */
    Debug
}

/**
 * Use for smart logging
 */
public class Logger
{
    /**
     * Change log level for this logger.
     */
    public void setLogLevel(final LogLevel newLogLevel)
    {
        logLevel = newLogLevel;
    }

    /**
     * Log some error message if log level is enough
     */
    public void logError(final String errorMessage)
    {
        if (logLevel != LogLevel.NO_LOGGING)
            System.out.println(ANSI_RED + "[ERROR]:\t" + errorMessage + "\n" + ANSI_RESET);
    }

    /**
     * Log some debug message if log level is enough
     */
    public void logDebug(final String debugMessage)
    {
        if (logLevel == LogLevel.Debug)
            System.out.println(ANSI_YELLOW + "[DEBUG]:\t" + debugMessage + "\n" + ANSI_RESET);
    }

    /**
     * Log some info message if log level is enough
     */
    public void logInfo(final String infoMessage)
    {
        if (logLevel == LogLevel.Info || logLevel == LogLevel.Debug)
            System.out.println(ANSI_WHITE + "[INFO]:\t" + infoMessage + "\n" + ANSI_RESET);

    }

    /**
     * Current log level
     */
    private LogLevel logLevel;
}