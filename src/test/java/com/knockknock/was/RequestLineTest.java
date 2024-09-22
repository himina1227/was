package com.knockknock.was;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RequestLineTest {

    void createSuccess() {
        RequestLine requestLine = new RequestLine("GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1");

        assertThat(requestLine).isNotNull();
        assertThat(requestLine).isEqualTo(new RequestLine("GET", "/calculate", "operand1=11&operator=*&operand2=55"));

    }
}
