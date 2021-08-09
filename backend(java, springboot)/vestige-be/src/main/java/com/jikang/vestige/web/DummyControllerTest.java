package com.jikang.vestige.web;

import com.jikang.vestige.model.RoleType;
import com.jikang.vestige.model.User;
import com.jikang.vestige.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

// html파일이 아니라 data를 리턴해주는 controller = RestController
@RestController
public class DummyControllerTest {

    // 타입을 참고하여 객체를 주입한다. 의존성 주입(DI)
    @Autowired
    private UserRepository userRepository;

    // save 함수는 id를 전달하지 않으면 insert를 해주고
    // save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
    // save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 한다.

    @DeleteMapping("/dummy/user/{number}")
    public String delete(@PathVariable int number){
        try {
            userRepository.deleteById(number);
        } catch (EmptyResultDataAccessException e){
            return "삭제에 실패하였습니다. 해당 number는 DB에 없습니다.";
        }
        return "삭제되었습니다." + number;
    }

    // json 데이터를 요청 => java Object(MessageConverter의 Jackson라이브러리가 변환해서 받는다)
    @Transactional // 함수 종료시에 자동 commit이 된다.
    @PutMapping("/dummy/user/{number}")
    public User updateUser(@PathVariable int number, @RequestBody User requestUser){
        System.out.println("number: " + number);
        System.out.println("password: " + requestUser.getPassword());
        System.out.println("email: " + requestUser.getEmail());

        User user = userRepository.findById(number).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
        // userRepository.save(user);
        // 더티 체킹
        return user;
    }

    // http://localhost:8000/vestige/dummy/user/{number}
    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    // 한페이지당 2건의 데이터를 리턴받아볼 예정
   @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size=2, sort="id", direction= Sort.Direction.DESC) Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);
        /*if(pagingUser.isLast()){

        }*/
        List<User> users = pagingUser.getContent();
        return users;
    }

    /*@GetMapping("/dummy/user")
    public Page<User> pageList(@PageableDefault(size=2, sort="id", direction= Sort.Direction.DESC) Pageable pageable){
        Page<User> users = userRepository.findAll(pageable);
        return users;
    }*/

    // {id} = 주소로 파라미터를 전달 받을수 있다.
    // http://localhost:8000/vestige/dummy/user/{number}
    @GetMapping("/dummy/user/{number}")
    public User detail(@PathVariable int number){
        // user/4를 찾을때 db에 존재하지 않으면 user가 null이 되고 null이 리턴된다.
        // Optional로 User객체를 감싼후 null인지 아닌지 판단 후 리턴한다.
        User user = userRepository.findById(number).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 사용자가 없습니다.");
            }
        });

        //람다식
        /*User user = userRepository.findById(number).orElseThrow(()->{
           return new IllegalArgumentException(("해당 사용자가 없습니다."))
        });*/

        // 요청 = 웹브라우저
        // user객체 = 자바 object
        // 웹 브라우저가 이해할 수 있는 데이터로 변환해야 한다. ex) json(Gson 라이브러리)
        // 스프링부트는 MessageConverter가 응답시에 자동으로 작동한다.
        // 만약 자바 오브젝트가 리턴된다면 MessageConverter가 Jackson라이브러리를 호출해서
        // user 오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
        return user;
    }

    // http://localhost:8000/blog/dummy/join(요청)
    // http의 body에 id, password, email 데이터를 가지고(요청)
    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println("number: " + user.getNumber());
        System.out.println("id: " + user.getId());
        System.out.println("password: " + user.getPassword());
        System.out.println("email: " + user.getEmail());
        System.out.println("role: " + user.getRole());
        System.out.println("createDate: " + user.getSignupDate());
        System.out.println("withdrawalData: " + user.getWithdrawalDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}
