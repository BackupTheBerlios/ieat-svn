package cgh.ieat.model;

import java.io.Serializable;

public class Settings implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public boolean welcome;
	public String username;
	public Settings(boolean welcome, String username) {
		super();
		this.welcome = welcome;
		this.username = username;
	}
	
	public String toString()
	{
		StringBuilder b = new StringBuilder();
		b.append("welcome=" + welcome);
		b.append("\n");
		b.append("user=" + username);
		return b.toString();
	}
}
