package dev.alexanderbell.ticketmaster.config.resttemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        final List<String> urlFromRequest = logRequest(request, body);
        final ClientHttpResponse response = execution.execute(request, body);
        response.getHeaders().remove(HttpHeaders.TRANSFER_ENCODING);
        logResponse(response, urlFromRequest);
        return response;
    }

    private List<String> logRequest(HttpRequest request, byte[] body) {
        log.info("===========================request begin================================================");
        log.info("URI         : {}", request.getURI());
        log.info("Method      : {}", request.getMethod());
        final String requestBody = new String(body, StandardCharsets.UTF_8);
        log.info("==========================request end================================================");

        final List<String> requestInfo = new ArrayList<>();
        requestInfo.add(requestBody);
        requestInfo.add(request.getURI().getRawPath());
        if (request.getHeaders().get("Content-Type") != null) {
            final List<String> contentTypes = request.getHeaders().get("Content-Type");
            assert contentTypes != null;
            if (contentTypes.isEmpty()) {
                requestInfo.addAll(contentTypes);
            }
        }

        return requestInfo;
    }

    private void logResponse(ClientHttpResponse response, List<String> requestInfo) throws IOException {
        if (response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError()) {
            final String responseBody = StreamUtils.copyToString(response.getBody(), Charset.defaultCharset());
            log.info("============================response begin==========================================");
            log.info("URL: " + requestInfo.get(1));
            log.info("Status code  : {}", response.getStatusCode());
            log.info("Status text  : {}", response.getStatusText());
            log.info("Request body: {}", requestInfo.get(0));
            log.info("Response body: {}", responseBody);
        } else {
            log.info("============================response begin==========================================");
            log.info("URL: " + requestInfo.get(1));
            log.info("Status code  : {}", response.getStatusCode());
            log.info("Status text  : {}", response.getStatusText());
        }
        log.info("=======================response end=================================================");
    }
}
