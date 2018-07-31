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
public class UserResource {
	
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TaskRepository taskRepository;
	
	@GetMapping("/users")
	public List<User> retrieveAll(){
		return userRepository.findAll();		
	}
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		return user.get();
	}
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		
		
		return ResponseEntity.created(location).build();
		
	}
	@PutMapping("users/{id}")
	public ResponseEntity<Object> modifyUser(@RequestBody User user,@PathVariable int id){
		if(user.getId()!=null) {
			return ResponseEntity.badRequest().build();
		}
		
		user.setId(id);
		user.setModifiedAt(new Date());
		userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(location).body("id not null");
	}
	@DeleteMapping("users/{id}")
	public void deleteUser(@PathVariable int id){
		userRepository.deleteById(id);	
	}
	
	@GetMapping("/users/{id}/tasks")
	public List<Task> retrieveTask(@PathVariable int id){
		Optional<User> userOptional = userRepository.findById(id);
		
		return userOptional.get().getTasks();		
	}
	
	@PostMapping("/users/{id}/tasks")
	public ResponseEntity<Object> createTask(@PathVariable int id,@RequestBody Task task){
		Optional<User> userOptional = userRepository.findById(id);
		
		User user = userOptional.get();
		
		task.setUser(user);
		
		taskRepository.save(task);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(task.getId()).toUri();
		return ResponseEntity.created(location).build();		
	}
	
	
	
	
	
}
