package dumpBackground2.model.base.entity;

import java.io.Serializable;

public class Token implements Serializable, Comparable<Token>, Cloneable{

	private String clientId;
	private String accessToken;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public int compareTo(Token o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
