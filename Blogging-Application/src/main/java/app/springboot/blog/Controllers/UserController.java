package app.springboot.blog.Controllers;

import app.springboot.blog.Payloads.APIResponse;
import app.springboot.blog.Payloads.UserDTO;
import app.springboot.blog.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

//    @PostMapping("/")
//    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
//        UserDTO createdUserDTO = this.userService.createUser(userDTO);
//        return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
//    }

    @PutMapping("/{user_id}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable int user_id){
        UserDTO updatedUserDTO = this.userService.updateUser(userDTO,user_id);
        return ResponseEntity.ok(updatedUserDTO);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{user_id}")
    public ResponseEntity<APIResponse> deleteUser(@PathVariable("user_id") Integer user_id){
        this.userService.deleteUser(user_id);
        return new ResponseEntity(new APIResponse("User deleted successfully",true), OK);
    }


    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserDTO> getUserByID(@PathVariable Integer user_id){
        return ResponseEntity.ok(this.userService.getUserByID(user_id));
    }
}
