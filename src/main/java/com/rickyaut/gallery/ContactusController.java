package com.rickyaut.gallery;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ContactusController {
	private static final Logger logger = Logger.getLogger(ContactusController.class.getName());
	@RequestMapping(value="/contact-us", method=RequestMethod.POST)
	public ModelAndView submitContactUs(@RequestParam String name, @RequestParam String email, @RequestParam String phone, @RequestParam String content){
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

		ModelAndView modelAndView = new ModelAndView("contactus.result", "success", true);
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("service.allmotorsgallery@gmail.com", "Service"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress("service.allmotorsgallery@gmail.com", "Service"));
            msg.setSubject("contact us information");
            StringBuffer buf = new StringBuffer();
            buf.append("Name: ").append(name).append("\n");
            buf.append("eMail: ").append(email).append("\n");
            buf.append("Phone: ").append(phone).append("\n\n");
            buf.append(content);
            msg.setText(buf.toString());
            Transport.send(msg);

        } catch (AddressException e) {
            logger.log(Level.WARNING, e.getMessage(), e);
            modelAndView = new ModelAndView("contactus");
        } catch (MessagingException e) {
            logger.log(Level.WARNING, e.getMessage(), e);
            modelAndView = new ModelAndView("contactus");
        } catch (UnsupportedEncodingException e) {
            logger.log(Level.WARNING, e.getMessage(), e);
            modelAndView = new ModelAndView("contactus");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/contact-us", method=RequestMethod.GET)
	public ModelAndView requestContactUs(){
		ModelAndView mv = new ModelAndView("contactus");
		mv.addObject("meta_title", "AllMotorsGallery - "+ "Contact us");
		mv.addObject("meta_description", "You can contact us by filling the form");
		return mv;
	}
	
}
