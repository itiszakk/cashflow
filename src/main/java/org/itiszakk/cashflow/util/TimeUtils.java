package org.itiszakk.cashflow.util;

import java.time.OffsetDateTime;

public class TimeUtils {

    public static OffsetDateTime getOrNow(OffsetDateTime dt) {
        return dt == null ? OffsetDateTime.now() : dt;
    }
}
