package com.pwskills.tanay;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestApp1 {
    public static void main(String[] args)throws Exception {
        String indianUser = "05-MAY-2004";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        java.util.Date utilDate = sdf.parse(indianUser);
        long inputMs = utilDate.getTime();
        java.sql.Date sqlDate = new java.sql.Date(inputMs);
        System.out.println("SQLDate information as: "+sqlDate);

        //Converting to java.sql.Date directly if the user input is like the China case (yyyy-MM-dd)
        String chinaUser = "2004-04-05";
        java.sql.Date sqlOutput = java.sql.Date.valueOf(chinaUser);
        System.out.println("SQLDate information is: "+sqlOutput);

    }
}
