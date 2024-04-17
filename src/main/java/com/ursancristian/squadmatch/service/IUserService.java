package com.ursancristian.squadmatch.service;

import com.ursancristian.squadmatch.dto.UserDTO;
import com.ursancristian.squadmatch.exceptions.UserAlreadyExistException;
import com.ursancristian.squadmatch.model.User;

public interface IUserService {
    User registerNewUserAccount(UserDTO userDto) throws UserAlreadyExistException;

    ;
}
