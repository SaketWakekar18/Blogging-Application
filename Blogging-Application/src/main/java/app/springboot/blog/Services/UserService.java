package app.springboot.blog.Services;

import app.springboot.blog.Payloads.UserDTO;

import java.util.List;

public interface UserService {
//    public UserDTO createUser(UserDTO user);
    public UserDTO registerUser(UserDTO user);
    public UserDTO updateUser(UserDTO user,Integer user_id);
    public UserDTO getUserByID(Integer user_id);
    public List<UserDTO> getAllUsers();
    public void deleteUser(Integer user_id);
}
