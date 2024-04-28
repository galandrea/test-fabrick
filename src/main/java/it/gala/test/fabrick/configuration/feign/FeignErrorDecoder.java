package it.gala.test.fabrick.configuration.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import it.gala.test.fabrick.client.feign.model.FabrickResponse;
import it.gala.test.fabrick.exception.ApplicationException;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());

        String message = MessageFormatter.format("Error: status={}, message={}", response.status(), response).getMessage();

        if (response.headers().containsKey(HttpHeaders.CONTENT_TYPE)) {
            List<String> contentTypeList = response.headers().get(HttpHeaders.CONTENT_TYPE).stream().toList();

            if (contentTypeList.stream().anyMatch(contentType -> contentType.contains(MediaType.APPLICATION_JSON_VALUE))) {

                try (InputStream bodyIs = response.body().asInputStream()) {
                    ObjectMapper mapper = new ObjectMapper();
                    FabrickResponse<?> resBody = mapper.readValue(bodyIs, FabrickResponse.class);
                    return handleResponse(responseStatus, resBody);

                } catch (IOException e) {
                    return new ApplicationException(responseStatus, message);
                }

            }
        }

        return new ApplicationException(responseStatus, message);
    }

    private ApplicationException handleResponse(HttpStatus httpStatus, FabrickResponse<?> response) {
        return new ApplicationException(
                httpStatus,
                response.getErrors().stream()
                        .map(e -> "[code:\"" + e.getCode() + "\" description:\"" + e.getDescription() + "\" params:\"" + e.getParams() + "\"]")
                        .collect(Collectors.joining(";"))
        );
    }
}
