package com.sample;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class SampleAPITest {
    @Test
    void parseFrom() {
        SampleAPI.Date birthday = SampleAPI.Date.newBuilder()
                .setYear(2020).setMonth(1).setDay(1)
                .build();

        SampleAPI.ClientRs rs = SampleAPI.ClientRs.newBuilder()
                .setName("Test")
                .setBirthday(birthday)
                .setXml("data")
                .build();

        byte[] bytes = rs.toByteArray();

        try {
            SampleAPI.ClientRs parsed = SampleAPI.ClientRs.newBuilder()
                    .mergeFrom(bytes)
                    .build();

            assertEquals(rs, parsed);
        } catch (Exception e) {
            log.error("Check *.proto file. Cannot parse", e);
        }
    }
}
