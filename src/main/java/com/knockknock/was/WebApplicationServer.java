package com.knockknock.was;


import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class WebApplicationServer {
    private final int port;

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public WebApplicationServer(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)){
            log.info("[WasApplicationServer] started {} port", port);

            Socket clientSocket;
            log.info("[WasApplicationServer] waiting for client");


            while ((clientSocket = serverSocket.accept()) != null) {
                log.info("[WasApplicationServer] client connected!");

                /**
                 * Thread Pool을 적용해 안정적인 서비스가 가능하도록 한다.
                 */
                executorService.execute(new ClientRequestHandler(clientSocket));

                /**
                 * 사용자 요청이 들어올 때마다 Thread 를 새로 생성해서 사용자 요청을 처리하도록 한다.
                 */
                // new Thread(new ClientRequestHandler(clientSocket)).start();

                /**
                 * 사용자의 요청이 들어올 때마다 Main Thread 에서 사용자 요청이 처리 되어진다.
                 */
//                try (InputStream in = clientSocket.getInputStream(); OutputStream out = clientSocket.getOutputStream()) {
//                    BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
//                    DataOutputStream dos = new DataOutputStream(out);
//
//                    HttpRequest httpRequest = new HttpRequest(br);

//                    if (httpRequest.isGetRequest() && httpRequest.matchPath("/calculate")) {
//                        QueryStrings queryStrings = httpRequest.getQueryStrings();
//
//                        // 비지니스 로직을 실행 수
//                        byte[] body = String.valueOf("body").getBytes();
//                        HttpResponse response = new HttpResponse(dos);
//                        response.response200Header("application/json", body.length);
//                        response.responseBody(body);
//                    }
//                    String line;
//                    while ((line = br.readLine()) != "") {
//                        System.out.println(line);
//                    }
//                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
