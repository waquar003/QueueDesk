package com.queuedesk.repository;

import com.queuedesk.enums.Priority;
import com.queuedesk.enums.Severity;
import com.queuedesk.enums.AccessLevel;
import com.queuedesk.exceptions.InvalidTicketDataException;
import com.queuedesk.models.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TicketFileLoader {
    public static List<Ticket> load(Path file) throws InvalidTicketDataException, IOException {
        List<Ticket> tickets = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file.toFile()))) {
            int lineNumber = 1;
            String line;

            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                try {
                    String[] parts = line.split("\\|", -1);

                    if (parts.length < 5) throw new IllegalArgumentException("Missing common ticket fields");

                    String type = parts[0].trim();
                    String title = parts[1].trim();
                    String description = parts[2].trim();
                    String requester = parts[3].trim();
                    Priority priority = Priority.valueOf(parts[4].trim().toUpperCase());

                    Ticket ticket = switch (type.toLowerCase()) {
                        case "bug" -> {
                            if (parts.length != 7) throw new IllegalArgumentException("Bug ticket requires 7 fields");

                            Severity severity = Severity.valueOf(parts[5].trim().toUpperCase());
                            String steps = parts[6].trim();
                            yield new BugTicket(title, description, requester, priority, severity, steps);
                        }

                        case "feature" -> {
                            if (parts.length != 7) throw new IllegalArgumentException("Feature ticket requires 7 fields");

                            int votes = Integer.parseInt(parts[5].trim());
                            String justification = parts[6].trim();
                            yield new FeatureRequestTicket(title, description, requester, priority, votes, justification);
                        }

                        case "access" -> {
                            if (parts.length != 7) throw new IllegalArgumentException("Access ticket requires 7 fields");

                            String systemName = parts[5].trim();
                            AccessLevel accessLevel = AccessLevel.valueOf(parts[6].trim());
                            yield new AccessRequestTicket(title, description, requester, priority, systemName, accessLevel);
                        }

                        default -> throw new IllegalArgumentException("Unknown ticket type: " + type);
                    };

                    tickets.add(ticket);
                    lineNumber++;

                } catch (IllegalArgumentException e) {
                    throw new InvalidTicketDataException("Invalid ticket data: " + e.getMessage(), lineNumber);
                }
            }
        }

        return tickets;
    }
}
