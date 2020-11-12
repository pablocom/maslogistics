package masstack.maslogistics.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(
		exclude = HibernateJpaAutoConfiguration.class,
		scanBasePackages = "masstack.maslogistics"
)
public class MaslogisticsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaslogisticsApiApplication.class, args);
	}

}
