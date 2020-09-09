package com.woc.util;

import java.io.File;

public class TestSlash {
    public static void main(String[] args) {
        String path = "reports"+ File.separator +"extent-report.html";
        System.out.println(path);
    }
}
