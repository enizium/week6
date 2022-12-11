package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.employee.ServicesImpl.EmployeeService;
import com.employee.ServicesImpl.JwtService;
import com.employee.ServicesImpl.UserDetailsServiceImpl;
import com.employee.model.AuthRequestModel;

@RestController
public class SecurityController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtService jwtService;

    @RequestMapping(value = "info", method = RequestMethod.GET)
    public String info(){
        return "The application is up... \n Every one can access info end point";
    }

    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public String hello(){
        return "Hello Authenticate user!!! ";
    } 

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public String getJwtAuthToken(@RequestBody AuthRequestModel authRequestModel) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestModel.getUserName(), authRequestModel.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect Username or password... Try Again!", e);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequestModel.getUserName());
        final String jwt = jwtService.getJWT(userDetails);
        return jwt;
    }
}
