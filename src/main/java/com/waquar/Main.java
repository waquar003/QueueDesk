package com.waquar;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        List<Ticket> tickets = new ArrayList<>();

        tickets.add(new AccessRequestTicket("Grant Access to S3", "Waquar Ahmad", "S3", AccessLevel.Admin));
        tickets.add(new BugTicket("Pdf link opening in new tab", "Waquar Ahmad"));
        tickets.add(new FeatureRequestTicket("Add option to export the doc", "Waquar Ahmad", "People can view the doc locally without need to be online"));

        //printTriageBoard(tickets);

        ((Escalatable) tickets.get(1)).escalate();
        ((Escalatable) tickets.get(1)).escalate();
        ((Escalatable) tickets.get(0)).escalate();

        //printTriageBoard(tickets);

        PriorityQueue<Ticket> pqDefault = new PriorityQueue<>();
        for (Ticket ticket: tickets) {
            pqDefault.offer(ticket);
        }

        System.out.println("Priority Queue Default");
        while (!pqDefault.isEmpty()) {
            System.out.println(pqDefault.poll());
        }

        PriorityQueue<Ticket> pqCustom = new PriorityQueue<>(TicketComparators.BY_STATUS_THEN_AGE);
        for (Ticket ticket: tickets) {
            pqCustom.offer(ticket);
        }

        System.out.println("Priority Queue Custom");
        while (!pqCustom.isEmpty()) {
            System.out.println(pqCustom.poll());
        }
    }

    private static void printTriageBoard(List<Ticket> tickets) {
        for (Ticket ticket: tickets) {
            System.out.println("Estimated Effort in Hours: " + ticket.estimateEffortHours());
            System.out.println(ticket);

            if (ticket instanceof Escalatable e) {
                System.out.println("Escalation Level: " + e.escalationLevel());
            }
        }
    }
}