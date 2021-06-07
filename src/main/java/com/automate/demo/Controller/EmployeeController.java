package com.automate.demo.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.automate.demo.Model.Course;
import com.automate.demo.Service.EmployeeService;


@RestController
@RequestMapping("/profile/v1/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees/{employeeId}/courses")
	public List<Course> retrieveCoursesForEmployee(@PathVariable String employeeId) {
		return employeeService.retrieveCourses(employeeId);
	}
	
	@GetMapping("/employees/{employeeId}/courses/{courseId}")
	public Course retrieveDetailsForCourse(@PathVariable String employeeId,
			@PathVariable String courseId) {
		return employeeService.retrieveCourse(employeeId, courseId);
	}
	
	@PostMapping("/employees/{employeeId}/courses")
	public ResponseEntity<Void> registerEmployeeForCourse(
			@PathVariable String employeeId, @RequestBody Course newCourse) {

		Course course = employeeService.addCourse(employeeId, newCourse);

		if (course == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(course.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

}