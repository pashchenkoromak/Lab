package commons;

import commons.LogLevel;

/**
 * Use for smart logging
 */
public class Logger
{
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

    public Logger(String name, LogLevel logLevel)
    {
        loggerName = name;
        this.logLevel = logLevel;
    }
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
        if (logLevel == LogLevel.Debug
            || logLevel == LogLevel.Error
            || logLevel == LogLevel.Info)
            System.out.println(ANSI_RED + loggerName + "\t[ERROR]:\t" + errorMessage + '\n' + ANSI_RESET);
    }

    /**
     * Log some debug message if log level is enough
     */
    public void logDebug(final String debugMessage)
    {
        if (logLevel == LogLevel.Debug)
            System.out.println(ANSI_YELLOW + loggerName + "\t[DEBUG]:\t" + debugMessage + '\n' + ANSI_RESET);
    }

    /**
     * Log some info message if log level is enough
     */
    public void logInfo(final String infoMessage)
    {
        if (logLevel == LogLevel.Debug
            || logLevel == LogLevel.Error
            || logLevel == LogLevel.Info)
            System.out.println(ANSI_WHITE + loggerName + "\t[INFO]:\t" + infoMessage + '\n' + ANSI_RESET);

    }

    /**
     * Current log level
     */
    private LogLevel logLevel;

    /**
     * Logger name
     */
    private String loggerName;
};
