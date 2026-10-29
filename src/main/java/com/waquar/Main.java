package com.waquar;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        try {
            List<Ticket> tickets = TicketFileLoader.load(Path.of("./input.txt"));
            tickets.get(0).setAssignee("Sadiq");
            tickets.get(0).addTag("urgetn");

            TicketAnalytics analytics = new TicketAnalytics();
            System.out.println(analytics.countByPriority(tickets));
            System.out.println(analytics.averageEffortHours(tickets));
            System.out.println(analytics.tagLine(tickets));
            System.out.println(analytics.oldestOpenTicket(tickets));

            Repository<Ticket> repo = new Repository<>();
            tickets.forEach(repo::add);
            repo.findByIdSafe(1).ifPresentOrElse(
                    t -> System.out.println("Found " + t.getTitle()),
                    () -> System.out.println("No ticket found with id 1")
            );
            repo.findByIdSafe(10).ifPresentOrElse(
                    t -> System.out.println("Found " + t.getTitle()),
                    () -> System.out.println("No ticket found with id 10")
            );

            repo.findById(10);

            String report = ReportGenerator.generate(tickets);
            ReportGenerator.writeToFile(report, Path.of("./Output.txt"));
            System.out.println("Report written successfully.");
            System.out.println(report);
        } catch (InvalidTicketDataException e) {
            System.out.println("Bad ticket data on line " + e.getLineNumber() + ": " + e.getMessage());
        } catch (TicketNotFoundException e) {
            System.out.println("Ticket not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
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