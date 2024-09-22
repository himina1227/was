package com.knockknock.was;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class RequestLine {
    //GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1
    private final String method; // GET

    private final String urlPath; // /calculate

    private QueryStrings queryStrings;  // operand1=11&operator=*&operand2=55

    public RequestLine(String method, String urlPath, String quertString) {
        this.method = method;
        this.urlPath = urlPath;
        this.queryStrings = new QueryStrings(quertString);
    }

    public RequestLine(String requestLine) {
//        GET /calculate ? operand1=11&operator=*&operand2=55 HTTP/1.1

        String[] tokens = requestLine.split(" ");
        this.method = tokens[0];
        String[] urlPathTokens = tokens[1].split("\\?");
        this.urlPath = urlPathTokens[0];

        if (urlPathTokens.length == 2) {
            this.queryStrings = new QueryStrings(urlPathTokens[1]);
        }
    }

    public boolean isGetRequest() {
        return "GET".equals(method);
    }

    public boolean mathPath(String requestPath) {
        return requestPath.equals(urlPath);
    }
}
