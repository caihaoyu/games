package com.chy.view.bean.story;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.chy.model.User;
import com.chy.model.Story.Item;
import com.chy.model.Story.Story;
import com.chy.service.ItemService;
import com.chy.service.StoryService;
import com.chy.view.bean.BaseBean;

@Component
@Scope("view")
public class AddStoryBean extends BaseBean {

	private static final long serialVersionUID = 7974728161746999694L;

	private String title;

	private String header;

	private String rule;

	private User user;
	
	private int mode;

	@PostConstruct
	public void setup() {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(false);
		user = (User) session.getAttribute("user");
	}

	public void save() {
		storyService.addStory(title, header, rule, user);
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("$('#addStoryDilog').modal('hide');");

	}
	
	public void clean(){
		title=null;
		header=null;
		rule=null;
		RequestContext rc = RequestContext.getCurrentInstance();
		rc.execute("$('#addStoryDilog').modal();");
	}

	@Autowired
	private StoryService storyService;

	@Autowired
	private ItemService itemService;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}
	

}
