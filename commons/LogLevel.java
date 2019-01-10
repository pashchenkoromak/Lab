package commons;

/**
 * LogLevels for logger. Use them for control your logging
 */
public enum LogLevel{
    /**
     * Don't log at all
     */
    NO_LOGGING,
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
    Debug,
    /**
     * Log when go in\out of each function
     */
    Trace
}