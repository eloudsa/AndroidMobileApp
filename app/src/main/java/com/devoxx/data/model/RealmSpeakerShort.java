package com.devoxx.data.model;

import com.devoxx.connection.model.SpeakerShortApiModel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmSpeakerShort extends RealmObject {

	@PrimaryKey
	private String uuid;
	private String firstName;
	private String lastName;
	private String avatarURL;

	public static RealmSpeakerShort fromApi(SpeakerShortApiModel apiModel) {
		final RealmSpeakerShort result = new RealmSpeakerShort();
		result.setAvatarURL(apiModel.avatarURL);
		result.setFirstName(apiModel.firstName);
		result.setLastName(apiModel.lastName);
		result.setUuid(apiModel.uuid);
		return result;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAvatarURL() {
		return avatarURL;
	}

	public void setAvatarURL(String avatarURL) {
		this.avatarURL = avatarURL;
	}
}
