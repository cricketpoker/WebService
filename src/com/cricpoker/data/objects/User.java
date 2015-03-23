package com.cricpoker.data.objects;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.DateTime;

/**
 * This class hold the database representation 
 * of the user entity
 *
 */
@XmlRootElement
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int userId;

	@Column(name = "last_logged_in_time")
	private Timestamp lastLoggedInTime;

	@Column(name = "display_name")
	private String displayName;

	@Column(name = "tokens_left")
	private int tokensLeft;

	@Column(name = "fav_team_id")
	private int favTeamId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public DateTime getLastLoggedInTime() {
		return new DateTime(lastLoggedInTime);
	}

	public void setLastLoggedInTime(DateTime lastLoggedIn) {
		this.lastLoggedInTime = new Timestamp(lastLoggedIn.getMillis());
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getTokensLeft() {
		return tokensLeft;
	}

	public void setTokensLeft(int tokensLeft) {
		this.tokensLeft = tokensLeft;
	}

	public int getFavTeamId() {
		return favTeamId;
	}

	public void setFavTeamId(int favTeamId) {
		this.favTeamId = favTeamId;
	}

	@Override
	public String toString() {
		StringBuffer userString = new StringBuffer();
		userString.append("User Id " + userId);
		userString.append(" Display Name " + displayName);
		userString.append(" Last logged in time " + lastLoggedInTime);
		userString.append(" Tokens left " + tokensLeft);
		userString.append(" Fav team id " + favTeamId);

		return userString.toString();
	}

}