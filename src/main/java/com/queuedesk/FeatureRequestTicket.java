package com.queuedesk;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
public class FeatureRequestTicket extends Ticket {
    private @Getter @Setter int votes;
    private @Getter final String businessJustification;

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

    @Override
    public double estimateEffortHours() {
        return (double) votes / 10 + 5;
    }
}
