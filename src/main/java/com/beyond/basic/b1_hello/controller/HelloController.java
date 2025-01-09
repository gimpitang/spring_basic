package com.beyond.basic.b1_hello.controller;

import com.beyond.basic.b1_hello.domain.Hello;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//      Component 어노테이션을 통해 별도의 객체를 생성할 필요가 없는, 싱글톤 객체를 생성한다
//      Component 어노테이션은 Controller 어노테이션 안에 있음.
//      Controller 어노테이션을 통해 쉽게 사용자의 http 요청을 처리하고, http 응답을 줄 수 있음.
@Controller
//      클래스 차원에 url 매핑 시에는 requestMapping을 쓴다

@RequestMapping("/hello")
//
public class HelloController {
    //      GetMapping에서 url을 명시함.

    //      case1. 서버가 사용자에게 단순String 데이터 return(get요청) - ResponseBody 있을 때
    //      case2. 서버가 사용자에게 화면을 return(get요청) - ResponseBody 없을 때
    @GetMapping("")
//    @ResponseBody
    //      @ResponseBody 가 없고, return 타입이 String인 경우 서버는 templates 폴더 밑에 helloworld.html 화면을 리턴한다
    public String helloWorld(){
        return "helloworld";
    }

    @GetMapping("/json")
//    //      메서드 차원에서도 RequestMapping 사용 가능하다.
//    //      아래 리퀘스트 매핑은 래거시임. 매서드에서 겟매핑을 쓰는게 최신 방법
//    @RequestMapping (value = "/json", method = RequestMethod.GET)
    @ResponseBody
    //      case3. 서버가 사용자에게 json형식의 데이터를 return(get요청)
//    public String helloJson() throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        Hello h1 = new Hello("hongildong" ,"hongil@naver.com");
//        String value = objectMapper.writeValueAsString(h1);
//    return value;
        //      직접  json으로 직렬화 할 필요 없이, return 타입을 클래스로 지정시에 자동으로 직렬화
    public Hello helloJson() throws JsonProcessingException {
        Hello h1 = new Hello("hongildong" ,"hongil@naver.com");

        return h1;
    }

    //    case4. 사용자가 json data를 요청하되, parameter형식으로 특정개체 요청(get요청)
    //      parameter형식: /hello/pram1?name=hongildong
    @GetMapping("/param1")
    @ResponseBody
    public Hello param1(@RequestParam(value = "name")String inputName){
        Hello h1 = new Hello(inputName, "test@naver.com");
        return h1;
    }
    //      parameter 2개 이상 형식: /hello/pram1?name=hongildong&email=hongildong@naver.com
    @GetMapping("/param2")
    @ResponseBody
    public Hello param2(@RequestParam(value = "name")String inputName,
                        @RequestParam(value = "email")String inputEmail){
        Hello h1 = new Hello(inputName, inputEmail);
        return h1;
    }


    //      case 5: parameter가 많아질 경우, databinding을 통해 input값 처리 (get요청)
    @GetMapping("/param3")
    @ResponseBody
    //      각 parameter의 값이 Hello 클래스의 각 변수에 mapping : new Hello(hongildong, hong@naver.com)
//    public Hello param3(Hello hello){
//        Hello h1 = new Hello(hello.getName(), hello.getEmail());
//        return h1; // 71번 줄(윗줄) 지우고 그냥 72번 줄(해당 줄)의 h1 자리에 hello를 넣어도 상관없음.
//    }

    //      @ModelAttribute를 쓰는 이유는 명시적으로 바인딩했다는 것을 보여주기 위함이다.
    public Hello param3(@ModelAttribute Hello hello){
        return hello;
    }

    //      case 6. 화면을 return해 주되, 특정 변수 값을 동적으로 세팅 -------------MVC를 쓰는 동안 case 6번을 자주 사용할 예정

    @GetMapping("/model-param")
    public String modelParam(@RequestParam(value = "name")String inputName, Model model){       // url에 ?name=최영재 입력
        //      model 객체는 특정 데이터를 화면에 전달해주는 역할을 한다.
        //      modelName라는 키값에 value를 세팅하면 modelName:값 형태로 화면에 전달
        model.addAttribute("modelName", inputName);     //   이 곳의 modelName은 helloworld2의 변수명과 같아야함
        return "helloworld2";
    }       //      http://localhost:8081/hello/model-param?name=%EC%B5%9C%EC%98%81%EC%9E%AC
    //      case 7. 화면을 return해 주되, 객체를 화면에 동적으로 세팅
    @GetMapping("/model-param2")
    public String modelParam2(@ModelAttribute Hello hello, Model model){ //@ModelAttribute를 통해 바인딩을 하는구나 알 수있음
        //      model 객체는 특정 데이터를 화면에 전달해주는 역할을 한다.
        //      modelName라는 키값에 value를 세팅하면 modelName:값 형태로 화면에 전달
        model.addAttribute("modelHello", hello); //      modelName 대신 객체를 받을 수 있음.
        return "helloworld3";
    }       //      http://localhost:8081/hello/model-param2?name=%EC%B5%9C%EC%98%81%EC%9E%AC&email=%EC%95%84%EC%95%84
    //      case 8. pathvariable 방식을 통해 사용자로부터 값을 받아 화면 return
    //      형식: /hello/model-path/hongildong
    //      예시: /author/detail/1 => 1번 데이터를 줘봐
    //      pathvariable 방식은 url을 통해 자원구조를 명확하게 표현할 때 사용.(좀 더 restful한 방식)
    @GetMapping("model-path/{inputName}")
    public String modelPath(@PathVariable String inputName, Model model){      //

        model.addAttribute("modelName", inputName);
        return "helloworld2";
    }



}
