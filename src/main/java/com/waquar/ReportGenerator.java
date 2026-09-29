package com.waquar;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportGenerator {
    public static String generate(List<Ticket> tickets) {
        StringBuilder sb = new StringBuilder();
        Map<Status, Integer> countPerStatus = new HashMap<>();
        Map<Priority, Integer> countPerPriority = new HashMap<>();
        List<Ticket> criticalTickets = new ArrayList<>();
        Ticket oldestTicket = Repository.oldest(tickets);

        for (Ticket ticket: tickets) {
            countPerStatus.put(ticket.getStatus(), countPerStatus.getOrDefault(ticket.getStatus(), 0) + 1);
            countPerPriority.put(ticket.getPriority(), countPerPriority.getOrDefault(ticket.getPriority(), 0) + 1);
            if (ticket.getPriority() == Priority.CRITICAL) criticalTickets.add(ticket);
        }

        sb.append("Counts per Status: \n");
        sb.append(countPerStatus.toString());

        sb.append("Counts per Priority: \n");
        sb.append(countPerPriority.toString());

        sb.append("Critical Tickets: \n");
        sb.append(criticalTickets.toString());

        sb.append("Oldest Ticket: \n");
        sb.append(oldestTicket.toString());

        return sb.toString();
    }

    public static void writeToFile (String report, Path path) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toFile()))){
            writer.write(report);
        }
    }
}
