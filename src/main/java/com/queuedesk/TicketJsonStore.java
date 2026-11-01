package com.queuedesk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class TicketJsonStore {
    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .enable(SerializationFeature.INDENT_OUTPUT);

    public static void save(List<Ticket> tickets, Path file) throws IOException, InvalidTicketDataException{
        try {
            mapper.writerFor(new TypeReference<List<Ticket>>(){}).writeValue(file.toFile(), tickets);
        } catch (JsonProcessingException e) {
            throw new InvalidTicketDataException("Could not write to json : " + e.getMessage(), e.getLocation().getLineNr());
        }
    }

    public static List<Ticket> load(Path file) throws IOException, InvalidTicketDataException {
        try {
            return mapper.readValue(file.toFile(), new TypeReference<List<Ticket>>(){});
        } catch (JsonProcessingException e) {
            throw new InvalidTicketDataException("Could not read from json : " + e.getMessage(), e.getLocation().getLineNr());
        }
    }
}
