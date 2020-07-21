package com.quiz.model.request;

public class Request {
    private String fromUser;
    private String toUser;
    private String type;
    private String body;
    private int id;

    public Request(String fromUser, String toUser, String type, String body) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.type = type;
        this.body = body;
        id = -1;
    }

    public Request(String fromUser, String toUser, String type, String body, int id) {
        this(fromUser, toUser, type, body);
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
}
