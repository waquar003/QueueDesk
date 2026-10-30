package com.queuedesk;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TicketAnalytics {
    public TicketAnalytics() {}

    public Map<Priority, Long> countByPriority(List<Ticket> tickets) {
        return tickets.stream()
                .collect(Collectors.groupingBy(Ticket::getPriority, Collectors.counting()));
    }

    public double averageEffortHours(List<Ticket> tickets) {
        return tickets.stream()
                .mapToDouble(Ticket::estimateEffortHours)
                .average().orElse(0.0);
    }

    public double totalEffortHours(List<Ticket> tickets) {
        return tickets.stream()
                .map(Ticket::estimateEffortHours)
                .reduce(0.0, Double::sum, Double::sum);
    }

    public Optional<Ticket> oldestOpenTicket(List<Ticket> tickets) {
        return tickets.stream()
                .filter(t -> t.getStatus() == Status.OPEN)
                .min(Comparator.comparing(Ticket::getCreatedAt));
    }

    public String tagLine(List<Ticket> tickets) {
        return tickets.stream()
                .flatMap(ticket -> ticket.getTags().stream())
                .distinct()
                .sorted()
                .collect(Collectors.joining(", "));
    }
}
