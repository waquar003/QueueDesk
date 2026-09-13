package com.waquar;

public class BugTicket extends Ticket implements Escalatable {
    private Severity severity;
    private String stepsToReproduce;
    private boolean escalated = false;
    private int escalationLevel = 0;

    public BugTicket(String title, String description, String requester, Priority priority, Severity severity, String stepsToReproduce) {
        super(title, description, requester, priority);
        this.severity = severity;
        this.stepsToReproduce = stepsToReproduce;
    }

    public BugTicket(String title, String requester) {
        this(title, "", requester, Priority.LOW, Severity.LOW, "");
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public String getStepsToReproduce() {
        return stepsToReproduce;
    }

    public void setStepsToReproduce(String stepsToReproduce) {
        this.stepsToReproduce = stepsToReproduce;
    }

    @Override
    public double estimateEffortHours() {
        switch (this.severity) {
            case LOW -> {
                return 2;
            }
            case MEDIUM -> {
                return 6;
            }
            case HIGH -> {
                return 15;
            }
            case CRITICAL -> {
                return 40;
            }
            default -> {
                return 0;
            }
        }
    }

    @Override
    public void escalate() {
        this.escalated = true;
        this.escalationLevel++;
    }

    @Override
    public boolean isEscalated() {
        return this.escalated;
    }

    @Override
    public int escalationLevel() {
        return this.escalationLevel;
    }
}
