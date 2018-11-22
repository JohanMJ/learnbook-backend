package net.learnbook.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import net.learnbook.entity.Activity;
import net.learnbook.entity.Course;
import net.learnbook.entity.Group;
import net.learnbook.entity.User;
import net.learnbook.service.UserService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value="dev/user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserWebService {

	@Autowired
	private UserService userService;
	
	@PostMapping("insert")
	public ResponseEntity<User> insertUser(@RequestBody User user) {
		try{
			userService.insert(user);  
            return new ResponseEntity<User>(user, HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}   
	}
	
	@PostMapping("/update")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		try{
			userService.update(user);  
            return new ResponseEntity<User>(user, HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}   
	}
	
/*	@GetMapping("list")
	public ResponseEntity<User> getUser() {
		
			User user = new User(null, "ola", " master@gmail.com", "123456", new Date(), true, null, null);
			
			Role userRole = new Role(1, "admin");
			user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
			
			return new ResponseEntity<User>(user, HttpStatus.OK);
                
	}*/
	
/*	@GetMapping("listAll")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> users = new ArrayList<User>();
		users = userService.listAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	            
	}*/
	
	@GetMapping(value="listAll")
	public List<User> getAllUser() {
		List<User> users = new ArrayList<User>();
		users = userService.listAll();
		return users;
	            
	}
	
//	@GetMapping(value="/login")
//	public User getUser() {
//	
//		return userService.findById(2);
//	            
//	}
	
	@GetMapping(value="get/{iCodUser}")
	public User getUserById(@PathVariable Integer iCodUser) {
		return userService.findById(iCodUser);
	}
	
	@GetMapping(value="/login/{sLogin}")
	public User getUserDinamic(@PathVariable String sLogin) {
		return userService.findByLogin(sLogin);
	}
	
	@GetMapping(value="/listAll/{iCodUser}")
	public List<User> listAllByCompany(@PathVariable Integer iCodUser) {
		return userService.listAllByCompany(iCodUser);
	}
	
	@GetMapping(value="/listAllCompanies")
	public List<User> listAllByCompany() {
		return userService.listAllCompanies();
	}
	
	@GetMapping(value="/listAllGroup/{iCodGru}")
	public List<User> listAllByGroup(@PathVariable Integer iCodGru) {
		return userService.listAllByGroup(iCodGru);
	}
		
}
