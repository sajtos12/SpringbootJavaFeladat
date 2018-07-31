package com.springboot.feladat.springbootfeladat;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class TaskResource {
	
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/tasks")
	public List<Task> getAll(){
		return taskRepository.findAll();
	}
	
	@GetMapping("/tasks/{id}")
	public Task getById(@PathVariable int id) {
		Optional<Task> task = taskRepository.findById(id);
		
		return task.get();
	}
	
	@PostMapping("/tasks")
	public ResponseEntity<Object> createTask(@RequestBody Task task){
		Task savedTask = taskRepository.save(task);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedTask.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/tasks/{id}")
	public ResponseEntity<Object> modifyTask(@RequestBody Task task, @PathVariable int id){
		if(task.getId()!=null) {
			return ResponseEntity.badRequest().build();
		}
		task.setId(id);
		task.setModifiedAt(new Date());
		taskRepository.save(task);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(task.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/tasks/{id}")
	public void deleteTask(@PathVariable int id) {
		taskRepository.deleteById(id);
	}
	
	@PutMapping("/tasks/{taskid}/users/{userid}")
	public ResponseEntity<Object> modifyUser(@PathVariable int taskid, @PathVariable int userid){
		
		Optional<Task> task = taskRepository.findById(taskid);
		Optional<User> user = userRepository.findById(userid);
		
		task.get().setUser(user.get());		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(taskid).toUri();
		return ResponseEntity.created(location).build();		
	}
	
	
}
