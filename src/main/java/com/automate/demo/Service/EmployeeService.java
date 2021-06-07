package com.automate.demo.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.automate.demo.Model.Course;
import com.automate.demo.Model.Employee;


@Component
public class EmployeeService {

	private static List<Employee> employees = new ArrayList<>();

	static {
		//Initialize Data
		Course course1 = new Course("Course1", "Spring", "10 Steps", Arrays
				.asList("Learn Maven", "Import Project", "First Example",
						"Second Example"));
		Course course2 = new Course("Course2", "Spring MVC", "10 Examples",
				Arrays.asList("Learn Maven", "Import Project", "First Example",
						"Second Example"));
		Course course3 = new Course("Course3", "Spring Boot", "6K Employees",
				Arrays.asList("Learn Maven", "Learn Spring",
						"Learn Spring MVC", "First Example", "Second Example"));
		Course course4 = new Course("Course4", "Maven",
				"Most popular maven course on internet!", Arrays.asList(
						"Pom.xml", "Build Life Cycle", "Parent POM",
						"Importing into Eclipse"));

		Employee kid = new Employee("Employee1", "jitendra Andela",
				"Hiker, Back-End Programmer ", new ArrayList<>(Arrays
						.asList(course1, course2, course3, course4)));

		Employee Naturo = new Employee("Employee2", "jitendra",
				"Hiker, Programmer ", new ArrayList<>(Arrays
						.asList(course1, course2, course3, course4)));

		employees.add(kid);
		employees.add(Naturo);
	}

	public List<Employee> retrieveAllEmployees() {
		return employees;
	}

	public Employee retrieveEmployee(String employeeId) {
		for (Employee employee : employees) {
			if (employee.getId().equals(employeeId)) {
				return employee;
			}
		}
		return null;
	}

	public List<Course> retrieveCourses(String employeeId) {
		Employee employee = retrieveEmployee(employeeId);

		if (employee == null) {
			return null;
		}

		return employee.getCourses();
	}

	public Course retrieveCourse(String employeeId, String courseId) {
		Employee employee = retrieveEmployee(employeeId);

		if (employee == null) {
			return null;
		}

		for (Course course : employee.getCourses()) {
			if (course.getId().equals(courseId)) {
				return course;
			}
		}

		return null;
	}

	private SecureRandom random = new SecureRandom();

	public Course addCourse(String employeeId, Course course) {
		Employee employee = retrieveEmployee(employeeId);

		if (employee == null) {
			return null;
		}

		String randomId = new BigInteger(130, random).toString(32);
		course.setId(randomId);

		employee.getCourses().add(course);

		return course;
	}
}