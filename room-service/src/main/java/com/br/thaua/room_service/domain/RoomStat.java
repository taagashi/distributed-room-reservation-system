package com.br.thaua.room_service.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum RoomStat {
    AVAILABLE(1L, "Available"),
    BUSY(2L, "Busy"),
    MAINTENANCE(3L, "Maintenance");

    private final Long id;
    private final String stat;

    private final static Map<Long, RoomStat> BY_ID = Arrays.stream(values())
            .collect(Collectors.toUnmodifiableMap(RoomStat::getId, Function.identity()));

    public static RoomStat fromId(Long roomStatId) {
        return BY_ID.get(roomStatId);
    }
}
