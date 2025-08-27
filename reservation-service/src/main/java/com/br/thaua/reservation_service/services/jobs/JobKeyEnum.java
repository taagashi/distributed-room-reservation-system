package com.br.thaua.reservation_service.services.jobs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum JobKeyEnum {
    FINISH_KEY("finish"),
    START_KEY("start");

    private final String type;
}
