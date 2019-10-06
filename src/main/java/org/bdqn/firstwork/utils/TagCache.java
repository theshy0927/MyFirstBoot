package org.bdqn.firstwork.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bdqn.firstwork.dto.TagDTO;

public class TagCache {

	public static List<TagDTO> get(){
		List<TagDTO> tagsDTO = new ArrayList<TagDTO>();
		
		TagDTO language = new TagDTO();
		language.setCategoryName("开发语言");
		language.setTagId("language");
		language.setTags(Arrays.asList("javascript","php","css","html","html5","java","node.js","python","c++","c","golang","objective-c","typescript","shell","c#","swift","sass","bash","ruby","less","asp.net","lua","scala","coffeescript","actionscript","rust","erlang","perl"));
		
		TagDTO framework = new TagDTO();
		framework.setCategoryName("开发框架");
		framework.setTagId("framework");
		framework.setTags(Arrays.asList("laravel","spring","express","django","flask","yii","ruby-on-rails","tornado","koa","struts"));
		
		TagDTO server = new TagDTO();
		server.setCategoryName("服务器");
		server.setTagId("server");
		server.setTags(Arrays.asList("linux","nginx","docker","apache","ubuntu","centos","缓存","tomcat","负载均衡","unix","hadoop","windows-server"));
		
		TagDTO tools = new TagDTO();
		tools.setCategoryName("开发工具");
		tools.setTagId("tools");
		tools.setTags(Arrays.asList("git","github","visual-studio-code","vim","sublime-text","xcode","intellij-idea","eclipse","maven","ide","svn","visual-studio","atom","emacs","textmate","hg"));
		
		tagsDTO.add(language);
		tagsDTO.add(framework);
		tagsDTO.add(server);
		tagsDTO.add(tools);
		return tagsDTO;
	}
	
	public static String errorTag(String tagsStr) {
		String[] tags = tagsStr.split(",");
		List<String> collect = get().stream().flatMap(tag->tag.getTags().stream()).collect(Collectors.toList());
		String error = Arrays.stream(tags).filter(t->!collect.contains(t)).collect(Collectors.joining(","));
		System.out.println(error);
		return error ;
	}
	
}
