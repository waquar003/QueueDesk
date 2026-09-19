package com.waquar;

import java.util.*;

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
        return null;
    }

    public Map<String, List<T>> groupByAssignee() {
        Map<String, List<T>> map = new HashMap<>();

        for (T item: items) {
            map.computeIfAbsent(item.getAssignee(), k -> new ArrayList<>())
                    .add(item);
        }

        return map;
    }

    public static <T extends Ticket> T oldest(List<T> tickets) {
        T earliestTicket = null;
        for (T ticket: tickets) {
            if (earliestTicket == null || earliestTicket.getCreatedAt().isBefore(ticket.getCreatedAt())) {
                earliestTicket = ticket;
            }
        }

        return earliestTicket;
    }
}
