package br.com.exemplo.dataingestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "br.com.exemplo")
public class DataingestionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataingestionApplication.class, args);
	}

}
