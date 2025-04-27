package io.stackoftheweek.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

}

@Configuration
class CorsConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.allowCredentials(false)
				.maxAge(3600);
	}
}

@RestController
@RequestMapping("/api")
class StackOfTheWeekController {
	private final ChatClient chatClient;


	public StackOfTheWeekController(ChatClient.Builder builder) {
		this.chatClient = builder
				.defaultAdvisors(
						new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
				.build();
	}

	@GetMapping("/username")
	public String user(@AuthenticationPrincipal OAuth2User oAuth2User) {
		return oAuth2User.getAttribute("login");
	}

	@GetMapping("/user_id")
	public String userId(@AuthenticationPrincipal OAuth2User oAuth2User) {
		return oAuth2User.getName();
	}

	@GetMapping("/avatar_url")
	public String url(@AuthenticationPrincipal OAuth2User oAuth2User) {
		return oAuth2User.getAttribute("avatar_url");
	}

	@GetMapping("/user_profile")
	public UserProfile userProfile(@AuthenticationPrincipal OAuth2User oAuth2User) {
		return new UserProfile(oAuth2User.getName(),oAuth2User.getAttribute("login"), oAuth2User.getAttribute("avatar_url"));
	}

	@GetMapping("/questionGet")
	public Answer questionGet(@RequestParam(defaultValue = "Can you please tell me a joke about people that do live streams?") String message) {
		return chatClient.prompt()
				.user(message)
				.call()
				.entity(Answer.class);
	}

	@PostMapping("/question")
	public Answer questionPost(@RequestParam(defaultValue = "Can you please tell me a joke about people that do live streams?") String message) {
		return chatClient.prompt()
				.user(message)
				.call()
				.entity(Answer.class);
	}
}

record UserProfile(String user_id, String user_name, String avatar_url){}
record Answer(String answer){}