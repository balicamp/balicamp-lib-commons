package id.co.sigma.common.server.service.system;

import java.util.Date;

import id.co.sigma.common.server.data.security.UserLoginNotificationData;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;





/**
 * controller untuk handler masalah login
 **/
@Controller
public class LoginRelatedController {
	
	
	@Autowired
	private ICoreServerUserService sigmaUserService;
	
	@Autowired
	private HttpServletRequest request ; 
	
	
	
	
	
	/**
	 * channel untuk komunikasi dengan security controller
	 * 
	 **/
	@RequestMapping(value="/security-communication-channel/notifyLoginSuccedService.sigma", method=RequestMethod.GET)
	public @ResponseBody String notifyLoginSuccedService(
			@RequestParam("userName" )  String userName , 
			@RequestParam("uuid") String uuid,
			@RequestParam("userHost") String userComputerHostName ,  
			@RequestParam("fullName")String fullName, 
			@RequestParam("email")String email  ){
		UserLoginNotificationData notif = new UserLoginNotificationData();
		notif.setEmail(email);
		notif.setFullName(fullName);
		notif.setNotificationTime(new Date());
		notif.setUserHost(userComputerHostName);
		notif.setUserName(userName);
		notif.setUuid(uuid);
		sigmaUserService.pushRegisteredUser(notif);
		return "success";	
	}
	
	
	
	

}
