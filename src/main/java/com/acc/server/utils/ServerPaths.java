package com.acc.server.utils;

/**
 * Created by demius on 27.05.2015.
 */
public class ServerPaths {

    public static final String FILE_WORKING_DIR = System.getProperty("java.io.tmpdir");

    public static final String msmWorkPath;
    static {
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("win")) msmWorkPath = "\\msm\\MSM_Work\\";
        else msmWorkPath = "/opt/wildfly/shares/msm_work/";
    }

}
