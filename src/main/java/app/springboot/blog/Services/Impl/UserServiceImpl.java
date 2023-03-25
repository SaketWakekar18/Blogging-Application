package app.springboot.blog.Services.Impl;

import app.springboot.blog.Entity.Role;
import app.springboot.blog.Entity.User;
import app.springboot.blog.Exceptions.GlobalExceptionHandler;
import app.springboot.blog.Exceptions.ResourceNotFoundException;
import app.springboot.blog.Payloads.AppConstants;
import app.springboot.blog.Payloads.UserDTO;
import app.springboot.blog.Repository.RoleRepository;
import app.springboot.blog.Repository.UserRepository;
import app.springboot.blog.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleRepository roleRepository;
//    @Override
//    public UserDTO createUser(UserDTO userDTO) {
//        User user = this.dtoToUser(userDTO);
//        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//        User savedUser = this.userRepository.save(user);
//        return this.userToDto(savedUser);
//    }


    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user = this.modelMapper.map(userDTO,User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Role role = this.roleRepository.findById(AppConstants.NORMAL_USER).get();
        user.getRoles().add(role);
        User newUser = this.userRepository.save(user);
        return this.modelMapper.map(newUser,UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer user_id) {
        User user = this.userRepository.findById(user_id).orElseThrow(()->new ResourceNotFoundException("User","ID",user_id));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setAbout(userDTO.getAbout());
        User updatedUser = this.userRepository.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDTO getUserByID(Integer user_id) {
        User user = this.userRepository.findById(user_id).orElseThrow(()->new ResourceNotFoundException("User","ID",user_id));
        return this.userToDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserDTO> usersDTO = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return usersDTO;
    }

    @Override
    public void deleteUser(Integer user_id) {
        User user = this.userRepository.findById(user_id)
                .orElseThrow(()-> new ResourceNotFoundException("User","ID",user_id));
        //delete all the roles assigned to the user
       // this.userRepository.deleteUserNativeNamedParam(user.getUser_id());
        this.userRepository.delete(user);
    }
    public User dtoToUser(UserDTO userDTO){
        User user = this.modelMapper.map(userDTO,User.class);
//        User user = new User();
//        user.setUser_id(userDTO.getUser_id());
//        user.setName(userDTO.getName());
//        user.setEmail(userDTO.getEmail());
//        user.setPassword(userDTO.getPassword());
//        user.setAbout(userDTO.getAbout());
        return user;
    }

    public UserDTO userToDto(User user){
        UserDTO userDTO = this.modelMapper.map(user,UserDTO.class);
                                            //(kisko karna hai, kisme se karna hai)
//        UserDTO userDTO = new UserDTO();
//        userDTO.setUser_id(user.getUser_id());
//        userDTO.setName(user.getName());
//        userDTO.setEmail(user.getEmail());
//        userDTO.setPassword(user.getPassword());
//        userDTO.setAbout(user.getAbout());
        return userDTO;
    }
}
