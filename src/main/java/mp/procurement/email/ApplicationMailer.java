package mp.procurement.email;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
 
@Service("mailService")
public class ApplicationMailer
{
    @Autowired
    private JavaMailSender mailSender;
     
    @Value( "${emailFolder}")
    private String emailFolder;
     
    @Autowired
    private SimpleMailMessage preConfiguredMessage;
 
    /**
     * This method will send compose and send the message
     * */
    public void sendMail(final String to, String subject, String body,File att_file)
    {
    	 try{  
    	        MimeMessage message = mailSender.createMimeMessage();  
    	  
    	        MimeMessageHelper helper = new MimeMessageHelper(message, true);  
    	        helper.setFrom("infosane.nagpur@gmail.com");  
    	        helper.setTo(to);  
    	        helper.setSubject(subject);  
    	        helper.setText(body);  
    	        
    	        // attach the file  
    	        FileSystemResource file = new FileSystemResource(att_file);  
    	        helper.addAttachment(att_file.getName(), file);//image will be sent by this name  
    	        System.out.println(to);
    	        mailSender.send(message);  
    	        }catch(MessagingException e){e.printStackTrace();} 
    }
    
    public void sendHTMLMail(String to, String subject, String body,List <File> list_file){
    	List<String> list_to = new ArrayList<String>();
    	list_to.add(to);
    	sendHTMLMail(list_to, subject, body, list_file,null);;
    }
    
    public void sendHTMLMail(List<String> list_to, String subject, String body,List <File> list_file){
    	sendHTMLMail(list_to, subject, body, list_file,null);;
    }
    
    public void sendHTMLMail(List<String> list_to, String subject, String body,List <File> list_file, HashMap<String, String> values)
    {
    	 try{  
    	        MimeMessage message = mailSender.createMimeMessage();  
    	  
    	        MimeMessageHelper helper = new MimeMessageHelper(message, true);  
    	        helper.setFrom("admin@infosane.co.in");  
    	        //
    	        
    	        for (String to : list_to){
    	        	helper.setTo(to);
    	        }
    	       
    	        helper.setSubject(subject);  
    	        	        
    	    
    	        //replace the params
    	        if (values!=null){
    	        	for (String cur_key : values.keySet()){
    	        		body = body.replaceAll("##"+cur_key+"##", values.get(cur_key));
    	        	}
    	        }
    	        message.setContent(body, "text/html; charset=utf-8");
    	        // attach the file
    	        if (list_file!=null && list_file.size()>0){
    	        	for (File att_file: list_file){
    	        		FileSystemResource file = new FileSystemResource(att_file);  
    	        		helper.addAttachment(att_file.getName(), file);	
    	        	}
    	        }
    	        
    	        File outFile = new File(emailFolder+ "email_"+System.currentTimeMillis());
    	        FileOutputStream os = new FileOutputStream(outFile);
    	        		
    	        message.writeTo(os);
    	       // mailSender.send(message);
    	        
    	        }catch(Exception e)
    	 		{
    	        	System.out.println(e);
    	 		} 

    }
   
    /**
     * This method will send a pre-configured message
     * */
    public void sendPreConfiguredMail(String message)
    {
        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
        mailMessage.setText(message);
        mailSender.send(mailMessage);      
    }
    
}