package com.agrofarm.backend.dto;

public class ChatRequest {
    private String message;
    private String polygonId;

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getPolygonId() { return polygonId; }
    public void setPolygonId(String polygonId) { this.polygonId = polygonId; }
}
