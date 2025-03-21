package com.example.wedlessInvite.common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class VarConst {
    private VarConst() {
    }

    public static final String S3_UPLOAD_FOLDER = "uploads/";
    public static final long MAX_FILE_SIZE = 1 * 1024 * 1024; // 1MB
    public static final Set<String> VALID_FILE_EXTENSIONS = new HashSet<>(Arrays.asList("png", "jpg", "jpeg"));
}
