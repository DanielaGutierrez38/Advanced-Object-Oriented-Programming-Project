/**
 * Custom exception for errors encountered while writing the density report.
 */
package com.spaceweb.model;
public class DensityReportException extends Exception{

    public DensityReportException(String message){
        super(message);
    }

    public DensityReportException(String message, Throwable cause){
        super(message, cause);
    }
}
