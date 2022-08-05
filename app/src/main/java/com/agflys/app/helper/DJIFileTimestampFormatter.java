/*
 * Decompiled with CFR 0.0.
 * 
 * Could not load the following classes:
 *  java.io.File
 *  java.lang.CharSequence
 *  java.lang.String
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
package com.agflys.app.helper;

import com.agflys.app.helper.ManufacturerFileDateFormatter;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.temporal.ChronoField;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DJIFileTimestampFormatter
extends ManufacturerFileDateFormatter {
    public static DateTimeFormatter formatter;

    DJIFileTimestampFormatter() {
    }

    private static void initFormatter() {
        formatter = new DateTimeFormatterBuilder().appendValue(ChronoField.YEAR).appendLiteral('-').appendValue(ChronoField.MONTH_OF_YEAR).appendLiteral('-').appendValue(ChronoField.DAY_OF_MONTH).appendLiteral("_[").appendValue(ChronoField.HOUR_OF_DAY).appendLiteral("-").appendValue(ChronoField.MINUTE_OF_HOUR).appendLiteral("-").appendValue(ChronoField.SECOND_OF_MINUTE).appendLiteral(']').toFormatter();
    }

    @Override
    public LocalDateTime getTimestamp(File file) {
        Matcher matcher;
        if (formatter == null) {
            DJIFileTimestampFormatter.initFormatter();
        }
        if ((matcher = Pattern.compile((String)"DJIFlightRecord_(\\d\\d\\d\\d-\\d\\d-\\d\\d_\\[\\d\\d-\\d\\d-\\d\\d\\])\\.txt").matcher((CharSequence)file.getName())).find()) {
            return LocalDateTime.parse(matcher.group(1), formatter);
        }
        return null;
    }
}

