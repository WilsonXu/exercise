package org.wilson.lambdasinaction.chap12;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class UnitTest {
    @Test
    public void testZoneId() {
//        ZoneId.getAvailableZoneIds().forEach(System.out::println);
//        ZoneId zoneId = ZoneId.systemDefault();
//        System.out.println(zoneId.getId());
//        System.out.println(zoneId.getRules());
//        System.out.println(zoneId.toString());

//        LocalDate today = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();
//        ZonedDateTime zonedDateTime = today.atStartOfDay(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime zonedDateTime = now.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime);
//        zonedDateTime = today.atStartOfDay(ZoneId.of("Asia/Tokyo"));
//        zonedDateTime = now.atZone(ZoneId.of("Asia/Tokyo"));
        zonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        System.out.println(zonedDateTime);
    }

    public enum MyEnum {
        a, b;

        @Override
        public String toString() {
            return this.ordinal() + "";
        }
    }
}
