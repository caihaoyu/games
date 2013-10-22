package com.chy.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.chy.model.Story.Item;
import com.chy.model.Story.Story;
@Entity
@Table(name="user")
public class User extends BaseObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -363189361955314854L;

	@Id
	@Column(name = "userid", length = 32, nullable = false, unique = true)
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@GeneratedValue(generator = "generator")
	private String userId;
	
	@Column(length=100)
	private String nickname;
	
	@ManyToMany(fetch = FetchType.LAZY,targetEntity=com.chy.model.User.class)
	@JoinTable(
	            name = "friends_",
	            joinColumns = { @JoinColumn(name = "person_id") },
	            inverseJoinColumns = @JoinColumn(name = "friend_id")
	    )
	private Set<User> friends=new HashSet<User>(0);
	
	@ManyToMany(fetch = FetchType.LAZY, targetEntity=com.chy.model.User.class, mappedBy = "friends")
	private Set<User> friendOf=new HashSet<User>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	private Set<Story> authorStories=new HashSet<Story>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	private Set<Item> authorItmes=new HashSet<Item>(0);
	
	@ManyToMany(fetch = FetchType.LAZY, targetEntity=com.chy.model.Story.Story.class, mappedBy = "userList")
	private Set<Story> stories=new HashSet<Story>(0);
	
	@Column
	private Date createDate;

	@Column
	private String passWord;
	
	@Column
	private String account;
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Set<User> getFriends() {
		return friends;
	}

	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}

	public Set<User> getFriendOf() {
		return friendOf;
	}

	public void setFriendOf(Set<User> friendOf) {
		this.friendOf = friendOf;
	}

	public Set<Story> getAuthorStories() {
		return authorStories;
	}

	public void setAuthorStories(Set<Story> authorStories) {
		this.authorStories = authorStories;
	}

	public Set<Item> getAuthorItmes() {
		return authorItmes;
	}

	public void setAuthorItmes(Set<Item> authorItmes) {
		this.authorItmes = authorItmes;
	}

	public Set<Story> getStories() {
		return stories;
	}

	public void setStories(Set<Story> stories) {
		this.stories = stories;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 不确定是否要保留
	 */
	public String getPassWord() {
		return passWord;
	}

	/**
	 * 不确定是否要保留
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		 if(this == o) {
	            return true;
	        }
	        if(!(o instanceof User)){
	            return false;
	        }
	        final User user = (User)o;
	        
	        return !(userId != null ? !(user.getUserId().equals( userId)) : user.getUserId() != null);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return (userId != null ? userId.hashCode() : 0);
	}

}
