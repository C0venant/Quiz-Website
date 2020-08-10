package com.quiz.model.request;

public class Request implements Comparable<Request> {
    private String fromUser;
    private String toUser;
    private String type;
    private String body;
    private boolean seen;
    private int id;

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public Request(String fromUser, String toUser, String type, String body, boolean seen) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.type = type;
        this.body = body;
        this.seen = seen;
        id = -1;
    }

    public Request(String fromUser, String toUser, String type, String body, boolean seen, int id) {
        this(fromUser, toUser, type, body, seen);
        this.id = id;
    }

    public String getFromUser() {return fromUser;}

    public void setFromUser(String fromUser) {this.fromUser = fromUser;}

    public String getToUser() {return toUser;}

    public void setToUser(String toUser) {this.toUser = toUser;}

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

    public String getBody() {return body;}

    public void setBody(String body) {this.body = body;}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    @Override
    public String toString() {
        String s = "";
        s+="fromUser: "+fromUser+"\n"+"toUser: "
                +toUser+"\n"+ "type: " + type + "\n"+"body: "+body+"\n"+"id: "+id;
        return s;
    }

    @Override
    public int compareTo(Request o) {
        return this.id - o.getId();
    }
}
