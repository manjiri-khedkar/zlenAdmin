package com.zlenadmin;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Example {
    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
    public static final String ACCOUNT_SID = "AC6023e57f17d45f721c4abd244c35bd82";//System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN ="3ba8a15a8cbdcf10c932919c5bcf8148";// System.getenv("TWILIO_AUTH_TOKEN");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+919657277656"),
                "zlen.tech",
                "Where's Wallace?")
            .create();
        
        System.out.println(message.getSid());
    }
}