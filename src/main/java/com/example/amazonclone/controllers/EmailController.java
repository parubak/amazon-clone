//package com.example.amazonclone.controllers;
//
//import com.example.amazonclone.services.EmailService;
//import jakarta.mail.MessagingException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mail.MailException;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//@RequestMapping("/email")
//public class EmailController {
//
//    private static final Logger LOG = LoggerFactory.getLogger(EmailController.class);
//
//    @Autowired
//    EmailService emailService;
//
////    @PostMapping(value = "/query")
////    public @ResponseBody ResponseEntity sendSimpleEmail(Query query) {
////
////        try {
////            emailService.sendMail(query);
////            System.out.println("ok");
//////            emailService.sendSimpleEmail(email, "Welcome", "This is a welcome email for your!!");
////        } catch (MailException mailException) {
////            System.out.println("1");
////            LOG.error("Error while sending out email..{}", mailException.getStackTrace());
////            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
////        } catch (MessagingException e) {
////            System.out.println("12");
////            throw new RuntimeException(e);
////        }
////
////        return new ResponseEntity<>( HttpStatus.OK);
////    }
//
//    @PostMapping(value = "/contact")
//    public String sendSimpleEmail(@RequestParam(value = "message") String message, @RequestParam(value = "email") String email, @RequestParam(value = "name") String name) {
//
//        try {
//            System.out.println(email);
//            emailService.sendSimpleEmail(email,name,message);
////            emailService.sendSimpleEmail(email, "Welcome", "This is a welcome email for your!!");
//        } catch (MailException mailException) {
//            System.out.println(1);
//            LOG.error("Error while sending out email..{}", mailException.getStackTrace());
////            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
//        } catch (MessagingException e) {
//            System.out.println(12);
//            throw new RuntimeException(e);
//        }
//
////        return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
//        return "redirect:/contact";
//    }
//
//}