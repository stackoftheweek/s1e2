package io.stackoftheweek.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
class StackOfTheWeekController {
	private final ChatClient chatClient;

	public StackOfTheWeekController(ChatClient.Builder builder) {
		this.chatClient = builder
				.defaultAdvisors(
						new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
				.build();
	}

	@GetMapping("/questionGet")
	public Answer questionGet(@RequestParam(value = "message", defaultValue = "Can you please tell me a joke about people that do live streams?") String message) {
		return chatClient.prompt()
				.user(message)
				.call()
				.entity(Answer.class);
	}

	@PostMapping("/question")
	public Answer questionPost(@RequestParam(value = "message", defaultValue = "Can you please tell me a joke about people that do live streams?") String message) {
		return chatClient.prompt()
				.user(message)
				.call()
				.entity(Answer.class);
	}
}

record Answer(String answer){}