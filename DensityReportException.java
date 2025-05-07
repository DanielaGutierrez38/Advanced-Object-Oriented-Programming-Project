/**
 * Custom exception for errors encountered while writing the density report.
 * @author Caitlin Gregory
 */
public class DensityReportException extends Exception{

    /**
     * Constructor with message
     * @param message exception's message
     */
    public DensityReportException(String message){
        super(message);
    }

    /**
     * Constructor with message and cause
     * @param message exception's message
     * @param cause cause of exception
     */
    public DensityReportException(String message, Throwable cause){
        super(message, cause);
    }
}
