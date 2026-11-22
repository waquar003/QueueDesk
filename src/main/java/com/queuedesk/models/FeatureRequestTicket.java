package com.queuedesk.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.queuedesk.enums.Priority;
import com.queuedesk.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@ToString(callSuper = true)
@JsonTypeName("feature")
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

    @JsonCreator
    public FeatureRequestTicket(@JsonProperty("id") int id,
                                @JsonProperty("title") String title,
                                @JsonProperty("description") String description,
                                @JsonProperty("requester") String requester,
                                @JsonProperty("priority") Priority priority,
                                @JsonProperty("status") Status status,
                                @JsonProperty("createdAt") LocalDateTime createdAt,
                                @JsonProperty("assignee") String assignee,
                                @JsonProperty("tags") Set<String> tags,
                                @JsonProperty("votes") int votes,
                                @JsonProperty("businessJustification") String businessJustification) {
        super(id, title, description, requester, priority, status, createdAt, assignee, tags);
        this.votes = votes;
        this.businessJustification = businessJustification;
    }

    @Override
    public double estimateEffortHours() {
        return (double) votes / 10 + 5;
    }
}
