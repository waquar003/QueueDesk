package com.queuedesk;

import java.util.*;
import java.util.stream.Collectors;

public class Repository<T extends Ticket> {
    private List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public List<T> all() {
        return Collections.unmodifiableList(items);
    }

    public T findById(int id) {
        for (T item: items) {
            if (item.getId() == id) return item;
        }
        throw new TicketNotFoundException("There is no matching ticket with this id.");
    }

    public Map<String, List<T>> groupByAssignee() {
        return items.stream()
                .collect(Collectors.groupingBy(t -> t.getAssignee() == null ? "Unassigned" : t.getAssignee()));
    }

    public static <T extends Ticket> T oldest(List<T> tickets) {
        T earliestTicket = null;
        for (T ticket: tickets) {
            if (earliestTicket == null || earliestTicket.getCreatedAt().isAfter(ticket.getCreatedAt())) {
                earliestTicket = ticket;
            }
        }

        return earliestTicket;
    }

    public Optional<T> findByIdSafe(int id) {
        return items.stream()
                .filter(item -> item.getId() == id)
                .findFirst();
    }
}
