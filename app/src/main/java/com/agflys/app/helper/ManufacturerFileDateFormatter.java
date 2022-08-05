package com.agflys.app.helper;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;

import java.io.File;

abstract class ManufacturerFileDateFormatter {
    ManufacturerFileDateFormatter() {
    }

    public abstract LocalDateTime getTimestamp(File var1);

    protected LocalDateTime toLocalDateTime(long l) {
        return Instant.ofEpochMilli((long)l).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}

