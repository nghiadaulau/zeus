package vn.tdtu.edu.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import vn.tdtu.edu.commons.dto.AdminDTO;
import vn.tdtu.edu.commons.model.Admin;
import vn.tdtu.edu.commons.model.City;
import vn.tdtu.edu.commons.model.Role;
import vn.tdtu.edu.commons.repository.AdminRepository;
import vn.tdtu.edu.commons.repository.CityRepository;
import vn.tdtu.edu.commons.repository.RoleRepository;
import vn.tdtu.edu.commons.service.implement.AdminServiceImpl;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication(scanBasePackages = {"vn.tdtu.edu.commons.*", "vn.tdtu.edu.admin.*"})
@EnableJpaRepositories(value = "vn.tdtu.edu.commons.repository")
@EntityScan(value = "vn.tdtu.edu.commons.model")
public class AdminApplication implements CommandLineRunner {
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private CityRepository cityRepository;


	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role adminRole = new Role();
		Role userRole = new Role();
		adminRole.setId(1L);
		adminRole.setName("ADMIN");
		userRole.setId(2L);
		userRole.setName("USER");
		roleRepository.save(adminRole);
		roleRepository.save(userRole);

		City[] cities = new City[]{
				new City(1L, "Hà Nội"),
				new City(2L, "Hồ Chí Minh"),
				new City(3L, "Đà Nẵng"),
				new City(4L, "Cần Thơ"),
				new City(5L, "Hải Phòng"),
				new City(6L, "Huế"),
				new City(7L, "Hạ Long"),
				new City(8L, "Nha Trang"),
				new City(9L, "Phan Thiết"),
				new City(10L, "Quy Nhơn"),
				new City(11L, "Sapa"),
				new City(12L, "Đà Lạt"),
				new City(13L, "Hội An"),
				new City(14L, "Vũng Tàu"),
				new City(15L, "Mỹ Tho"),
				new City(16L, "Đồng Hới"),
				new City(17L, "Cẩm Phả"),
				new City(18L, "Bắc Ninh"),
				new City(19L, "Bắc Giang"),
				new City(20L, "Bắc Kạn"),
				new City(21L, "Bến Tre"),
				new City(22L, "Bình Dương"),
				new City(23L, "Bình Định"),
				new City(24L, "Bình Phước"),
				new City(25L, "Bình Thuận"),
				new City(26L, "Cà Mau"),
				new City(27L, "Cao Bằng"),
				new City(28L, "Đắk Lắk"),
				new City(29L, "Đắk Nông"),
				new City(30L, "Điện Biên"),
				new City(31L, "Đồng Tháp"),
				new City(32L, "Gia Lai"),
				new City(33L, "Hà Giang"),
				new City(34L, "Hà Nam"),
				new City(35L, "Hà Tĩnh"),
				new City(36L, "Hải Dương"),
				new City(37L, "Hậu Giang"),
				new City(38L, "Hòa Bình"),
				new City(39L, "Khánh Hòa"),
				new City(40L, "Kiên Giang"),
				new City(41L, "Kon Tum"),
				new City(42L, "Lai Châu"),
				new City(43L, "Lâm Đồng"),
				new City(44L, "Lạng Sơn"),
				new City(45L, "Lào Cai"),
				new City(46L, "Long An"),
				new City(47L, "Nam Định"),
				new City(48L, "Nghệ An"),
				new City(49L, "Ninh Bình"),
				new City(50L, "Ninh Thuận"),
				new City(51L, "Phú Thọ"),
				new City(52L, "Quảng Bình"),
				new City(53L, "Quảng Nam"),
				new City(54L, "Quảng Ngãi"),
				new City(55L, "Quảng Ninh")
		};
		cityRepository.saveAll(Arrays.asList(cities));
	}

}
