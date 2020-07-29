package com.quiz.model.request;

import org.junit.Test;

import static org.junit.Assert.*;

public class RequestTest {

    @Test
    public void testConstructor(){
        Request request = new Request("from","to",RequestType.NOTE, "body", false);
        assertEquals("from", request.getFromUser());
        assertEquals("to", request.getToUser());
        assertEquals("body", request.getBody());
        assertEquals(RequestType.NOTE, request.getType());
        assertEquals(-1, request.getId());
    }

}