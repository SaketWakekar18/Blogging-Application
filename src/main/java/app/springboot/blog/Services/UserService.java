package app.springboot.blog.Services;

import app.springboot.blog.Payloads.UserDTO;

import java.util.List;

public interface UserService {
    //    public UserDTO createUser(UserDTO user);
    UserDTO registerUser(UserDTO user);

    UserDTO updateUser(UserDTO user, Integer user_id);

    UserDTO getUserByID(Integer user_id);

    List<UserDTO> getAllUsers();

    void deleteUser(Integer user_id);

}
