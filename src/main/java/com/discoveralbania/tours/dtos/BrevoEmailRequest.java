package com.discoveralbania.tours.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrevoEmailRequest {

    private Sender sender;
    private List<To> to;
    private String subject;
    private String textContent;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Sender {
        private String email;
        private String name;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class To {
        private String email;
    }
}
