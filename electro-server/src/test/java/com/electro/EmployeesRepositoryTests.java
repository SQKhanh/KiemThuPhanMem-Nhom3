/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.electro;

import com.electro.entity.address.Address;
import com.electro.entity.authentication.User;
import com.electro.entity.employee.Employee;
import com.electro.repository.address.AddressRepository;
import com.electro.repository.address.DistrictRepository;
import com.electro.repository.address.ProvinceRepository;
import com.electro.repository.authentication.UserRepository;
import com.electro.repository.employee.DepartmentRepository;
import com.electro.repository.employee.EmployeeRepository;
import com.electro.repository.employee.JobLevelRepository;
import com.electro.repository.employee.JobTitleRepository;
import com.electro.repository.employee.JobTypeRepository;
import com.electro.repository.employee.OfficeRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.transaction.Transactional;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeesRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private JobTypeRepository jobTypeRepository;
    
    @Autowired
    private JobLevelRepository jobLevelRepository;

    @Autowired
    private JobTitleRepository jobTitleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DistrictRepository districtRepository;

    @Test
    @Transactional
    public void testSaveMultipleEmployees() {

        var offices = officeRepository.findAll();
        var departments = departmentRepository.findAll();
        var jobTypes = jobTypeRepository.findAll();
        var jobLevels = jobLevelRepository.findAll();
        var jobTitles = jobTitleRepository.findAll();
        var provincies = this.provinceRepository.findAll();
        var districts = this.districtRepository.findAll();

        for (int i = 0; i < 1000; i++) { // Tạo 1000 test case

            // Tạo dữ liệu ngẫu nhiên cho User
            User user = new User();
            user.setUsername(generateRandomUsername());
            user.setPassword(generateRandomPassword());
            user.setFullname(generateRandomFullName());
            user.setEmail(generateRandomEmail());
            user.setPhone(generateRandomPhone());
            user.setGender(generateRandomGender());
            var address = new Address(generateRandomStreetName(), getRandomObject(provincies), getRandomObject(districts), null);
            user.setAddress(address);
            user.setStatus(1);

            // Tạo đối tượng Employee
            Employee employee = new Employee()
                    .setUser(user)
                    .setOffice(getRandomObject(offices))
                    .setDepartment(getRandomObject(departments))
                    .setJobType(getRandomObject(jobTypes))
                    .setJobLevel(getRandomObject(jobLevels))
                    .setJobTitle(getRandomObject(jobTitles));

            var addressSave = addressRepository.save(address);
            assertNotNull(addressSave);

            var userSave = userRepository.save(user);
            assertNotNull(userSave);

            var savedEmployee = employeeRepository.save(employee);
            assertNotNull(savedEmployee);
        }
    }

    Random r = new Random();
    // Danh sách tiền tố cho tên đường
    private static final String[] PREFIXES = {
        "Đường", "Phố", "Ngõ", "Quốc lộ", "Hẻm", "Thôn", "Tổ"
    };

    // Danh sách tên chính cho đường
    private static final String[] MAIN_NAMES = {
        "Hà Nội", "Hồ Chí Minh", "Lý Thường Kiệt", "Nguyễn Trãi", "Lê Lợi",
        "Trần Hưng Đạo", "Quang Trung", "Bạch Đằng", "Nguyễn Du", "Phạm Ngũ Lão"
    };

    // Danh sách hậu tố
    private static final String[] SUFFIXES = {
        "", "1", "2", "3", "4", "5", "A", "B", "C"
    };

    // Tạo ngẫu nhiên tên đường
    private String generateRandomStreetName() {

        // Lấy ngẫu nhiên các phần của tên đường
        String prefix = PREFIXES[r.nextInt(PREFIXES.length)];
        String mainName = MAIN_NAMES[r.nextInt(MAIN_NAMES.length)];
        String suffix = SUFFIXES[r.nextInt(SUFFIXES.length)];

        // Ghép lại tên đường
        return prefix + " " + mainName + (suffix.isEmpty() ? "" : " " + suffix);
    }

    private final Set<String> generatedUsernames = new HashSet<>();
    private final Set<String> generatedEmails = new HashSet<>();

    // Tạo tên người dùng ngẫu nhiên
    public String generateRandomUsername() {
        String username;
        do {
            username = "user" + r.nextInt(1000000);
        } while (!generatedUsernames.add(username)); // Chỉ thêm nếu chưa tồn tại
        return username;
    }

    // Tạo email ngẫu nhiên
    public String generateRandomEmail() {
        String email;
        do {
            email = "user" + r.nextInt(1000000) + "@example.com";
        } while (!generatedEmails.add(email)); // Chỉ thêm nếu chưa tồn tại
        return email;
    }

    // Tạo mật khẩu ngẫu nhiên
    private String generateRandomPassword() {
        return "pass" + r.nextInt(1000000);
    }

    // Tạo tên đầy đủ ngẫu nhiên
    private String generateRandomFullName() {
        String[] firstNames = {"John", "Jane", "Mike", "Anna", "Chris"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones"};
        return firstNames[r.nextInt(firstNames.length)] + " " + lastNames[r.nextInt(lastNames.length)];
    }

    // Tạo số điện thoại ngẫu nhiên
    private String generateRandomPhone() {
        return "123-456-" + (1000 + r.nextInt(9000));
    }

    // Tạo giới tính ngẫu nhiên (M hoặc F)
    private String generateRandomGender() {
        return r.nextBoolean() ? "M" : "F";
    }

    private <Obj> Obj getRandomObject(List<Obj> objects) {
        if (objects.isEmpty()) {
            return null;
        }
        return objects.get(r.nextInt(objects.size()));
    }
}
