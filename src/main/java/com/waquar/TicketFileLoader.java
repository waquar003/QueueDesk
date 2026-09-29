package com.waquar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TicketFileLoader {
    public static List<Ticket> load(Path file) throws InvalidTicketDataException, IOException {
        List<Ticket> lists = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file.toFile()))){
            int lineNumber = 1;
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length != 4) throw new InvalidTicketDataException("Missing fields on the line number: ", lineNumber);
                Priority priority;
                try {
                    priority = Priority.valueOf(parts[3].trim());
                } catch (IllegalArgumentException e) {
                    throw new InvalidTicketDataException("Invalid priority value on line: ", lineNumber);
                }
                lists.add(new BugTicket(parts[0].trim(), parts[1].trim(), parts[2].trim(), priority, Severity.LOW, ""));
                lineNumber++;
            }
        }

        return lists;
    }
}
