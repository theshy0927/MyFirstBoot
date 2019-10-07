package org.bdqn.firstwork.util;

import java.io.IOException;

import org.bdqn.firstwork.dto.AccessTokenDTO;
import org.bdqn.firstwork.dto.GithubUser;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Component
public class GitHubProvider {
	// 获取一个用户访问令牌
	public String getAccessToken(AccessTokenDTO dto) {
		MediaType mediaType = MediaType.get("application/json; charset=utf-8");

		OkHttpClient client = new OkHttpClient();

		RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(dto));
		Request request = new Request.Builder().url("https://github.com/login/oauth/access_token").post(body).build();
		try (Response response = client.newCall(request).execute()) {
			String access_token = response.body().string();
			return access_token.split("&")[0];
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	// 通过用户访问令牌创建一个对象
	public GithubUser createUserByAccessToken(String AccessToken) {

		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url("https://api.github.com/user?"+AccessToken).build();
		Response response;
		try {
			response = client.newCall(request).execute();
			String string = response.body().string();
			GithubUser user = JSON.parseObject(string, GithubUser.class);
			return user;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
