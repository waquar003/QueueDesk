package com.waquar;

public class FeatureRequestTicket extends Ticket {
    private int votes;
    private final String businessJustification;

    public FeatureRequestTicket(String title, String description, String requester, Priority priority, int votes, String businessJustification) {
        super(title, description, requester, priority);
        this.votes = votes;
        this.businessJustification = businessJustification;
    }

    public FeatureRequestTicket(String title, String requester, String businessJustification) {
        super(title, requester);
        this.votes = 0;
        this.businessJustification = businessJustification;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public String getBusinessJustification() {
        return businessJustification;
    }

    @Override
    public double estimateEffortHours() {
        return (double) votes / 10 + 5;
    }
}
