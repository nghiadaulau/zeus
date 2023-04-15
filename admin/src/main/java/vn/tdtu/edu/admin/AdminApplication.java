package vn.tdtu.edu.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vn.tdtu.edu.commons.model.Role;
import vn.tdtu.edu.commons.repository.RoleRepository;

@SpringBootApplication(scanBasePackages = {"vn.tdtu.edu.commons.*", "vn.tdtu.edu.sneaker"})
@EnableJpaRepositories(value = "vn.tdtu.edu.commons.repository")
@EntityScan(value = "vn.tdtu.edu.commons.model")
public class AdminApplication implements CommandLineRunner {
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello");
		Role adminRole = new Role();
		Role userRole = new Role();
		adminRole.setId(1L);
		adminRole.setName("ADMIN");
		userRole.setId(2L);
		userRole.setName("USER");
		roleRepository.save(adminRole);
		roleRepository.save(userRole);
	}

}
