package com.zlenadmin.email;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
 
public class MailerTest {
 
    public static void main(String[] args)
    {
        //Create the application context
        ApplicationContext context = new FileSystemXmlApplicationContext("servlet-context.xml");
         
        //Get the mailer instance
        ApplicationMailer mailer = (ApplicationMailer) context.getBean("mailSender");
 
        //Send a composed mail
        mailer.sendMail("vivekraj.agrawal@gmail.com", "Test Subject", "Testing body",null);
 
        //Send a pre-configured mail
        //mailer.sendPreConfiguredMail("Exception occurred everywhere.. where are you ????");
        
        //mailer.sendHTMLMail("vivekraj.agrawal@gmail.com", "Tendu Patta: MP & CG Data (Lot wise Report)", "Please find the attachment for Lot wise data. ",null);
    }
}