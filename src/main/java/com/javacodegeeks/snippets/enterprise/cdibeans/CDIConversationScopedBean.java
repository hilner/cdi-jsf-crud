package com.javacodegeeks.snippets.enterprise.cdibeans;

import java.io.Serializable;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "cDIConversationScopedBean")
@ConversationScoped
public class CDIConversationScopedBean implements Serializable {
	private static final long serialVersionUID = -6541718762358561835L;

	@Inject
	private Conversation conversation;

	private String message;

	private String[] words = { "Hello!!", "Have a nice day!!", "Goodbye..", "Hi!", "Goodmorning!", "Bye..",
			"Good evening.." };

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Conversation getConversation() {
		return conversation;
	}

	@PostConstruct
	public void init() {
		message = "Hello from the JavaCodeGeeks..";
	}

	public void initConversation() {
		if (!FacesContext.getCurrentInstance().isPostback() && conversation.isTransient()) {
			conversation.begin();
		}
	}

	public void sendMessage() {
		message = words[new Random().nextInt(7)];
	}

	public String next() {
		return "secondpage?faces-redirect=true";
	}

	public String endConversation() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
		return "firstpage?faces-redirect=true";
	}

}