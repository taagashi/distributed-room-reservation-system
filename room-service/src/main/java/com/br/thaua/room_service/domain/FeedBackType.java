package com.br.thaua.room_service.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum FeedBackType {
    POSITIVE,
    MODERATE,
    NEGATIVE;
}
