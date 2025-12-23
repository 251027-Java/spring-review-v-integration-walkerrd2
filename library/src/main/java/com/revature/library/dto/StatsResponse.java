package com.revature.library.dto;

public class StatsResponse {

    private long totalBooks;
    private long availableBooks;
    private long totalPatrons;
    private long activeLoans;
    
    public StatsResponse() {
    }
    
    public StatsResponse(long totalBooks, long availableBooks, long totalPatrons, long activeLoans) {
        this.totalBooks = totalBooks;
        this.availableBooks = availableBooks;
        this.totalPatrons = totalPatrons;
        this.activeLoans = activeLoans;
    }
    
    public long getTotalBooks() {
        return totalBooks;
    }
    
    public void setTotalBooks(long totalBooks) {
        this.totalBooks = totalBooks;
    }
    
    public long getAvailableBooks() {
        return availableBooks;
    }
    
    public void setAvailableBooks(long availableBooks) {
        this.availableBooks = availableBooks;
    }
    
    public long getTotalPatrons() {
        return totalPatrons;
    }
    
    public void setTotalPatrons(long totalPatrons) {
        this.totalPatrons = totalPatrons;
    }
    
    public long getActiveLoans() {
        return activeLoans;
    }
    
    public void setActiveLoans(long activeLoans) {
        this.activeLoans = activeLoans;
    }


}
