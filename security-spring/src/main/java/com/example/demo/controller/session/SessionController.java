package com.example.demo.controller.session;
import javax.servlet.http.Cookie; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;
@RestController
public class SessionController {
	
	@Autowired
	HttpSession httpSession;
	
    @GetMapping("/getjsessionid")
    public String getJessionID(HttpServletRequest request) {
    	httpSession = request.getSession();
    	httpSession.setMaxInactiveInterval(1); // default 1800 seconds = 30 minutes    	
        return "JSESSIONID: " + httpSession.getId() + "<br>" + 
        		"1 秒後再次訪問 '/jsessionid' 則更換 JSESSIONID (*請開 F12)";
    }
    
    @GetMapping("/invalidjsessionid")
    public String invalidSessionID (HttpServletRequest request) {
    	httpSession = request.getSession(); 
    	httpSession.invalidate(); 
    	return "JSESSIONID: " + httpSession.getId() + "<br>" + 
		"每次訪問 '/invalidjsessionid' 取得 JSESSIONID 後，即刻失效! 因此再次訪問，則 JSESSIONID 皆異 (*請開 F12)";
    }
    
    @GetMapping("/getSession4SpecificPath")
    public String getSession4SpecificPath(HttpServletRequest request, HttpServletResponse response) {    	
    	UUID uuid = UUID.randomUUID();
    	Cookie cookie = new Cookie("specific_session", uuid.toString());
    	cookie.setMaxAge(5);
    	cookie.setPath("/getSession4SpecificPath");
    	cookie.setDomain("localhost");
    	cookie.setHttpOnly(true);
    	cookie.setSecure(true);
    	int maxAge = cookie.getMaxAge();
    	response.addCookie(cookie);
    	return "specific_session: " + cookie.getValue() + "<br>" +  
    		   "maxAge: " + maxAge + " seconds" + "<br>" + 
    		   "特定 path 才有此 session 且有效期 5 秒";
    }
  
    
}
