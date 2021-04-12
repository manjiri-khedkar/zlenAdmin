package mp.procurement.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import mp.procurement.model.Appuser;
import mp.procurement.model.Party;
import mp.procurement.model.SessionUser;
import mp.procurement.repository.AppuserRepository;

@Component("authenticationSuccessHandler")
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

	@Autowired
	private AppuserRepository appuserRepository ;
	
	@Autowired
	 private SessionUser sessionUser;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2)
			throws IOException, ServletException {
        response.sendRedirect("./loginError");
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse response, Authentication arg2)
			throws IOException, ServletException {
		HttpSession session= arg0.getSession(false);
		User springuser = (User)arg2.getPrincipal();
		String username = springuser.getUsername();
		
		Appuser user =null;
		try {
			user=appuserRepository.findByUserId(username);
			
			if (user!=null){
				sessionUser.setUserId(user.getId());
				String uname = user.getFirstName()+" "+user.getLastName();
				sessionUser.setUser_name(uname);
				sessionUser.setRole("customer");
				
			}
			session.setAttribute("sessionUser", sessionUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("./dashboard");
	}
	
}
