package com.chy.view.bean;

import com.chy.model.User;
import com.chy.service.UserService;
import com.chy.util.Md5Util;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("view")
public class LoginBean
{

  @Autowired
  private UserService userService;
  private String account;
  private String password;
  private boolean loginIn;
  private User user;

  public void login()
  {
    this.password = Md5Util.getMd5(this.password);
    this.user = this.userService.login(this.account, this.password);
    if (this.user != null) {
      try {
        this.loginIn = true;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession)externalContext.getSession(false);
        session.setAttribute("user", this.user);
        HttpServletResponse response = (HttpServletResponse)externalContext.getResponse();
        Cookie cookie = new Cookie("com.story.user.account", this.account);
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);
        Cookie cookie2 = new Cookie("com.story.user.password", this.password);
        cookie2.setMaxAge(-1);
        cookie2.setPath("/");
        response.addCookie(cookie2);
        externalContext
          .redirect("/games/xhtml/story/list/view.xhtml");
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
        "提示信息", "帐号或者密码错误");
      FacesContext.getCurrentInstance().addMessage(null, msg);
    }
  }

  public void loginOut() {
    this.loginIn = false;
  }

  public UserService getUserService() {
    return this.userService;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isLoginIn() {
    return this.loginIn;
  }

  public void setLoginIn(boolean loginIn) {
    this.loginIn = loginIn;
  }

  public String getAccount() {
    return this.account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}