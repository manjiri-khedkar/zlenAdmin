package com.zlenadmin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Component
public class SmsSender {
    // Find your Account SID and Auth Token at twilio.com/console
    // and set the environment variables. See http://twil.io/secure
	
	@Value("${sendgrid.accountsid}")
    private String  ACCOUNT_SID;
	
	@Value("${sendgrid.token}")
    private String AUTH_TOKEN ;
	
	@Value("${sendgrid.mobile}")
    private String AUTH_MOBILE ;

    public void sendSms(String to, String text) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String[] arr_numbers = to.split(",");
        for (String curNumber : arr_numbers) {
        	Message message = Message.creator(
                    new com.twilio.type.PhoneNumber(curNumber),
                    new com.twilio.type.PhoneNumber(AUTH_MOBILE),
                    text)
                .create();
            System.out.println(String.format("%s: %s", curNumber +  message.getSid()));	
        }
        
    }
}