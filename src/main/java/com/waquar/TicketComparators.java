package com.waquar;

import java.util.Comparator;

public class TicketComparators {
    private TicketComparators() {}

    public static final Comparator<Ticket> BY_PRIORITY_THEN_AGE =
            Comparator.comparing(Ticket::getPriority).reversed()
                    .thenComparing(Ticket::ageInHours);

    public static final Comparator<Ticket> BY_ASSIGNEE_THEN_PRIORITY =
            Comparator.comparing(Ticket::getAssignee, Comparator.nullsLast(Comparator.naturalOrder()))
                    .thenComparing(Comparator.comparing(Ticket::getPriority).reversed());

    public static final Comparator<Ticket> BY_STATUS_THEN_AGE =
            Comparator.comparing(Ticket::getStatus).reversed()
                    .thenComparing(Ticket::ageInHours);
}
