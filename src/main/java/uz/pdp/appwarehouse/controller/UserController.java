package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.payload.ApiResponse;
import uz.pdp.appwarehouse.payload.MemberDto;
import uz.pdp.appwarehouse.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/member")
public class UserController {
    @Autowired
    UserService userService;

    @PutMapping("/completed/{id}")
    public HttpEntity<?> completed(@PathVariable Long id){
        ApiResponse apiResponse =  userService.completedUser(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping("/get")
    public HttpEntity<?> getAll(){
        List<MemberDto> memberList = userService.getMemberAll();
        return ResponseEntity.ok(memberList);
    }
    @GetMapping("/get/{id}")
    public HttpEntity<?> getOne(@PathVariable Long id){
        MemberDto memberDto = userService.getOneMember(id);
        return ResponseEntity.ok(memberDto);
    }
}
