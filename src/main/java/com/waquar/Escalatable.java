package com.waquar;

public interface Escalatable {
    void escalate();
    boolean isEscalated();
    int escalationLevel();
    default String escalationBadge() {
        if (isEscalated()) return "Escalated";
        return "Not Escalated";
    }
}
