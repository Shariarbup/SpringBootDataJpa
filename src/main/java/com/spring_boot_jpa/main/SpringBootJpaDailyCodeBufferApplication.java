package com.spring_boot_jpa.main;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.startup.ListenerCreateRule.OptionalListener;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.query.Param;

import com.spring_boot_jpa.main.entity.Course;
import com.spring_boot_jpa.main.entity.CourseMaterial;
import com.spring_boot_jpa.main.entity.Gurdian;
import com.spring_boot_jpa.main.entity.Student;
import com.spring_boot_jpa.main.entity.Teacher;
import com.spring_boot_jpa.main.repository.CourseMaterialRepository;
import com.spring_boot_jpa.main.repository.CourseRepository;
import com.spring_boot_jpa.main.repository.StudentRepository;
import com.spring_boot_jpa.main.repository.TeacherRepository;

@SpringBootApplication
public class SpringBootJpaDailyCodeBufferApplication implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private CourseMaterialRepository courseMaterialRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaDailyCodeBufferApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		startApp();
	}

	void startApp() {
		
		Gurdian gurdian = Gurdian.builder()
				.email("Jahangir@gmail.com")
				.mobile("123456")
				.name("Jahangir")
				.build();
		
		Student student = Student.builder()
				.emailId("itmasjoy@gmail.com")
				.firstName("Shariar")
				.lastName("Joy")
				.gurdian(gurdian)
				.build();
		//saving the student
		studentRepository.save(student);
		
		//Find All stuent
		List<Student> students = studentRepository.findAll();
		System.out.println(students);
		
		//Find Student By ID
		Optional<Student> student2 = studentRepository.findById(1L);
		System.out.println("Stuednt whose first id is 1:"+student2);
		
		// Find by Student First Name
		Optional<Student> student3 = studentRepository.findByFirstName("Shariar");
		System.out.println("Stuednt whose first name is Shariar:"+student3);
		
		// Find student based on gurdian name
		Optional<Student> studentGurdianName = studentRepository.findByGurdianName("Jahangir");
		System.out.println(studentGurdianName);
		
		// Find Student using first name and last name
		Optional<Student> studentByFirstAndLastName = studentRepository.findByFirstNameAndLastName("Shariar", "Joy");
		System.out.println(studentByFirstAndLastName);
		
		//find student by email ID
		Student student4 = studentRepository.getByEmailAddress("itmasjoy@gmail.com");
		System.out.println(student4);
		
		//Get Student First name by email address
		String student5 = studentRepository.getFirstNameByEmailAddress("itmasjoy@gmail.com");
		System.out.println(student5);
		
		// Get student by email address using the native query
		Student student6 =studentRepository.getStudentByEmailAddressNativeQuery("itmasjoy@gmail.com");
		System.out.println(student6);
		
		// Get student by email address using the native query with param
		Student student7 = studentRepository.getStudentByEmailAddressNativeQueryWithParam("itmasjoy@gmail.com");
		System.out.println(student7);
		
		// updatve gurdian mobile number where student email id is "itmasjoy@gmail.com"
		studentRepository.updateStudentNameByEmailId("1010","itmasjoy@gmail.com");
		
		
		// Performing Course and Course Material Topic
		Course course = Course.builder().
				title("DSA").
				credit(6).build();
		
		CourseMaterial courseMaterial = CourseMaterial.builder().url("www.google.com").course(course).build();
		
		courseMaterialRepository.save(courseMaterial);
		
		//Find All Course Material
		List<CourseMaterial> courseMaterials= courseMaterialRepository.findAll();
		System.out.println(courseMaterials);
		
		// Find All Course
		List<Course> courses1 = courseRepository.findAll();
		System.out.println(courses1);
		
		//
		
		Course courseDSA = Course.builder().
				title("DSA").
				credit(6).build();
		Course courseDBA = Course.builder().
				title("DBA").
				credit(6).build();
		
		Teacher teacher = Teacher.builder()
				.firstName("shakib")
				.lastName("khan")
				.courses(List.of(courseDSA,courseDBA)).build();
		teacherRepository.save(teacher);
//		List<Teacher> teachersList = teacherRepository.findAll();
//		System.out.println(teachersList);
				
		
	}

}
