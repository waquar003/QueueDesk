package com.waquar;

public class AccessRequestTicket extends Ticket {
    private final String systemName;
    private final String accessLevel;


    public AccessRequestTicket(String title, String description, String requester, Priority priority, String systemName, String accessLevel) {
        super(title, description, requester, priority);
        this.systemName = systemName;
        this.accessLevel = accessLevel;
    }

    public AccessRequestTicket(String title, String requester, String systemName, String accessLevel) {
        super(title, requester);
        this.systemName = systemName;
        this.accessLevel = accessLevel;
    }

    @Override
    public double estimateEffortHours() {
        return 5;
    }

    public String getSystemName() {
        return systemName;
    }

    public String getAccessLevel() {
        return accessLevel;
    }
}
