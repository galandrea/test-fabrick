package it.gala.test.fabrick.client.feign.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FabrickResponse<T> {

    private String status;
    private List<Error> errors;
    private T payload;

    @Getter
    @Setter
    public static class Error {
        private String code;
        private String description;
        private String params;
    }
}

