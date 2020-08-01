package com.sample;

import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class SampleAPITest {
    @Test
    public void test() {
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
            SampleAPI.ClientRs parsed = SampleAPI.ClientRs.parseFrom(bytes);

            assertEquals(rs, parsed);
        } catch (InvalidProtocolBufferException e) {
            log.error("error", e);
        }
    }
}
