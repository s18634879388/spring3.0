package com.smart.web;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.smart.UserService;
import com.smart.domain.Dept;
import com.smart.domain.User;

@Controller
@RequestMapping("/user")
// @SessionAttributes(value={"user","user1","user2"},types={Dept.class})
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView createUser(User user) {
        userService.createUser(user);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/createSuccess");
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET, params = "!myParam")
    public String register(@ModelAttribute("user") User user) {
        return "user/register";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, params = "userId")
    public String test1(@RequestParam("userId") String userId) {
        // do sth
        return "user/test1";
    }

    @RequestMapping(value = "/show", headers = "content-type=text/*")
    public String test2(@RequestParam("userId") String userId) {
        // do sth
        return "user/test2";
    }

    // ①请求参数按名称匹配的方式绑定到方法入参中，方法返回对应的字符串代表逻辑视图名
    @RequestMapping(value = "/param")
    public String handleRequestParam(@RequestParam("userName") String userName,
                                     @RequestParam("password") String password,
                                     @RequestParam("realName") String realName) {
        return "success";
    }

    // ②将Cooke值及报文头属性绑定到入参中、方法返回ModelAndView
    @RequestMapping(value = "/cookie")
    public ModelAndView handleCookie(@CookieValue("JSESSIONID") String sessionId,
                                     @RequestHeader("Accept-Language") String accpetLanguage) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("success");
        mav.addObject("user", new User());
        return mav;
    }

    // ③请求参数按名称匹配的方式绑定到user的属性中、方法返回对应的字符串代表逻辑视图名
    @RequestMapping(value = "/user")
    public String handleUser(User user) {
        return "success";
    }

    // ④直接将HTTP请求对象传递给处理方法、方法返回对应的字符串代表逻辑视图名
    @RequestMapping(value = "/request")
    public String handleRequest(HttpServletRequest request) {
        return "success";
    }

    @RequestMapping(value = "/requiredParam")
    public String handleRequiredParam(
            @RequestParam(value = "userName", required = false) String userName,
            @RequestParam("age") int age) {
        return "success";
    }

    @RequestMapping(value = "/requiredCookie")
    public String handleRequiredCookie(
            @CookieValue(value = "sessionId", required = false) String sessionId,
            @RequestParam("age") int age) {
        return "success";
    }

    @RequestMapping(value = "/header")
    public String handleHeader(@RequestHeader("Accept-Encoding") String encoding,
                               @RequestHeader("Keep-Alive") long keepAlive) {
        return "success";
    }

    @RequestMapping(value = "/cascade")
    public String handleCascade(User user) {
        return "success";
    }

    @RequestMapping(value = "/servlet1")
    public void handleServlet1(HttpServletRequest request,
                               HttpServletResponse response) {
        String userName = request.getParameter("userName");
        response.addCookie(new Cookie("userName", userName));
    }

    @RequestMapping(value = "/servlet2")
    public ModelAndView handleServlet2(HttpServletRequest request) {
        String userName = WebUtils.findParameterValue(request, "userName");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("success");
        mav.addObject("userName", userName);
        return mav;
    }

    @RequestMapping(value = "/servlet3")
    public String handleServlet3(HttpSession session) {
        session.setAttribute("sessionId", 1234);
        return "success";
    }

    @RequestMapping(value = "/success")
    public String success() {
        return "success";
    }

    @RequestMapping(value = "/fail")
    public String fail() {
        return "fail";
    }

    @RequestMapping(value = "/servlet4")
    public String handleServlet4(HttpServletRequest request,
                                 @RequestParam("userName") String userName) {

        return "success";
    }

    @RequestMapping(value = "/servlet5")
    public String handleServlet5(WebRequest request) {
        String userName = request.getParameter("userName");
        return "success";
    }

    @RequestMapping(value = "/handle31")
    public void handle31(OutputStream os) throws IOException {
        Resource res = new ClassPathResource("/image.jpg");
        FileCopyUtils.copy(res.getInputStream(), os);
    }

    // @RequestMapping(value="/{userId}")
    // public ModelAndView showDetail(@PathVariable("userId") String userId){
    // ModelAndView mav = new ModelAndView();
    // mav.setViewName("user/showDetail");
    // mav.addObject("user", userService.getUserById(userId));
    // return mav;
    // }

    @RequestMapping(value = "/handle41")
    public String handle41(@RequestBody String body) {
        System.out.println(body);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/handle42/{imageId}")
    public byte[] handle42(@PathVariable("imageId") String imageId)
            throws IOException {
        System.out.println("load image of " + imageId);
        Resource res = new ClassPathResource("/image.jpg");
        byte[] fileData = FileCopyUtils.copyToByteArray(res.getInputStream());
        return fileData;
    }

    @RequestMapping(value = "/handle43")
    public String handle43(HttpEntity<String> requestEntity) {
        long contentLen = requestEntity.getHeaders().getContentLength();
        System.out.println("user:" + requestEntity.getBody());
        return "success";
    }

    @RequestMapping(value = "/handle44/{imageId}")
    public ResponseEntity<byte[]> handle44(
            @PathVariable("imageId") String imageId) throws Throwable {
        Resource res = new ClassPathResource("/image.jpg");
        byte[] fileData = FileCopyUtils.copyToByteArray(res.getInputStream());
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(
                fileData, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/handle51", method = RequestMethod.POST)
    public ResponseEntity<User> handle51(HttpEntity<User> requestEntity) {
        User user = requestEntity.getBody();
        user.setUserId("1000");
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/model1")
    public String handleModel1(@ModelAttribute("user") User user) {
        user.setUserId("1000");
        return "/user/createSuccess";
    }

    @RequestMapping(value = "/model2")
    public String handleModel2(@ModelAttribute("user") User user) {
        user.setUserName("tom");
        return "/user/showUser";
    }

    @RequestMapping(value = "/model3")
    public String handleModel3(ModelMap modelMap) {
        User user = (User) modelMap.get("user");
        user.setUserName("tom");
        modelMap.addAttribute("testAttr", "value1");
        return "/user/showUser";
    }

    @ModelAttribute("user")
    public User getUser() {
        User user = new User();
        user.setUserId("1001");
        user.setUserName("<>");
        return user;
    }

    @ModelAttribute("user1")
    public User getUser1() {
        User user = new User();
        user.setUserId("1");
        return user;
    }

    @ModelAttribute("user2")
    public User getUser2() {
        User user = new User();
        user.setUserId("1");
        return user;
    }

    @ModelAttribute("dept")
    public Dept getDept() {
        Dept dept = new Dept();
        return dept;
    }

    @RequestMapping(value = "/model4")
    public String handleModel4(@ModelAttribute("user") User user) {
        user.setUserName("John");
        return "redirect:model4.html";
    }

    @RequestMapping(value = "/model5")
    public String handleModel4(ModelMap modelMap, SessionStatus sessionStatus) {
        User user = (User) modelMap.get("user");
        if (user != null) {
            user.setUserName("Jetty");
            sessionStatus.setComplete();
        }
        return "/user/showUser";
    }

    @RequestMapping(value = "/handle81")
    public String handle81(@RequestParam("user") User user, ModelMap modelMap) {
        modelMap.put("user", user);
        return "/user/showUser";
    }

    @RequestMapping(value = "/register2")
    public String register2(User user) {
        return "/user/register2";
    }

    @RequestMapping(value = "/handle82")
    public String handle82(User user) {
        return "/user/showUser";
    }

    @RequestMapping(value = "/register3")
    public String register3() {
        return "/user/register3";
    }

    @RequestMapping(value = "/register4")
    public String register4() {
        return "/user/register4";
    }

    @RequestMapping(value = "/valid1")
    public String handleValid(@Valid @ModelAttribute("user") User user,
                              BindingResult bindingResult, ModelMap mm) {
        if (bindingResult.hasErrors()) {
            return "/user/register3";
        } else {
            return "/user/showUser";
        }
    }

    @RequestMapping(value = "/valid2")
    public String handleValid2(@ModelAttribute("user") User user,
                               BindingResult bindingResult) {
        ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "userName",
                "required");
        if ("aaaa".equalsIgnoreCase(user.getUserName())) {
            bindingResult.rejectValue("userName", "reserved");
        }
        if (bindingResult.hasErrors()) {
            return "/user/register4";
        } else {
            return "/user/showUser";
        }
    }

    @RequestMapping(value = "/welcome")
    public String handle100() {
        return "/user/welcome";
    }

    @RequestMapping(value = "/showUserList")
    public String showUserList(ModelMap mm) {
        Calendar calendar = new GregorianCalendar();
        List<User> userList = new ArrayList<User>();
        User user1 = new User();
        user1.setUserName("tom");
        user1.setRealName("汤姆");
        calendar.set(1980, 1, 1);
        user1.setBirthday(calendar.getTime());
        User user2 = new User();
        user2.setUserName("john");
        user2.setRealName("约翰");
        user2.setBirthday(calendar.getTime());
        userList.add(user1);
        userList.add(user2);
        mm.addAttribute("userList", userList);
        return "user/userList";
    }

    @RequestMapping(value = "/showUserListByFtl")
    public String showUserListInFtl(ModelMap mm) {
        Calendar calendar = new GregorianCalendar();
        List<User> userList = new ArrayList<User>();
        User user1 = new User();
        user1.setUserName("tom");
        user1.setRealName("汤姆");
        calendar.set(1980, 1, 1);
        user1.setBirthday(calendar.getTime());
        User user2 = new User();
        user2.setUserName("john");
        user2.setRealName("约翰");
        user2.setBirthday(calendar.getTime());
        userList.add(user1);
        userList.add(user2);
        mm.addAttribute("userList", userList);
        return "userListFtl";
    }

    @RequestMapping(value = "/showUserListByXls")
    public String showUserListInExcel(ModelMap mm) {
        Calendar calendar = new GregorianCalendar();

        List<User> userList = new ArrayList<User>();
        User user1 = new User();
        user1.setUserName("tom");
        user1.setRealName("汤姆");
        calendar.set(1980, 1, 1);
        user1.setBirthday(calendar.getTime());
        User user2 = new User();
        user2.setUserName("john");
        user2.setRealName("约翰");
        user2.setBirthday(calendar.getTime());
        userList.add(user1);
        userList.add(user2);
        mm.addAttribute("userList", userList);
        return "userListExcel";
    }

    @RequestMapping(value = "/showUserListByPdf")
    public String showUserListInPdf(ModelMap mm) {
        Calendar calendar = new GregorianCalendar();

        List<User> userList = new ArrayList<User>();
        User user1 = new User();
        user1.setUserName("tom");
        user1.setRealName("汤姆");
        calendar.set(1980, 1, 1);
        user1.setBirthday(calendar.getTime());
        User user2 = new User();
        user2.setUserName("john");
        user2.setRealName("约翰");
        user2.setBirthday(calendar.getTime());
        userList.add(user1);
        userList.add(user2);
        mm.addAttribute("userList", userList);
        return "userListPdf";
    }

    @RequestMapping(value = "/showUserListByXml")
    public String showUserListInXml(ModelMap mm) {
        Calendar calendar = new GregorianCalendar();
        List<User> userList = new ArrayList<User>();
        User user1 = new User();
        user1.setUserName("tom");
        user1.setRealName("汤姆");
        calendar.set(1980, 1, 1);
        user1.setBirthday(calendar.getTime());
        User user2 = new User();
        user2.setUserName("john");
        user2.setRealName("约翰");
        user2.setBirthday(calendar.getTime());
        userList.add(user1);
        userList.add(user2);
        mm.addAttribute("userList", userList);
        return "userListXml";
    }

    @RequestMapping(value = "/showUserListByJson")
    public String showUserListInJson(ModelMap mm) {
        Calendar calendar = new GregorianCalendar();
        List<User> userList = new ArrayList<User>();
        User user1 = new User();
        user1.setUserName("tom");
        user1.setRealName("汤姆");
        calendar.set(1980, 1, 1);
        user1.setBirthday(calendar.getTime());
        User user2 = new User();
        user2.setUserName("john");
        user2.setRealName("约翰");
        user2.setBirthday(calendar.getTime());
        userList.add(user1);
        userList.add(user2);
        mm.addAttribute("userList", userList);
        return "userListJson";
    }

    @RequestMapping(value = "/showUserListByJson1")
    public String showUserListInJson1(ModelMap mm) {
        Calendar calendar = new GregorianCalendar();
        List<User> userList = new ArrayList<User>();
        User user1 = new User();
        user1.setUserName("tom");
        user1.setRealName("汤姆1");
        calendar.set(1980, 1, 1);
        user1.setBirthday(calendar.getTime());
        User user2 = new User();
        user2.setUserName("john");
        user2.setRealName("约翰");
        user2.setBirthday(calendar.getTime());
        userList.add(user1);
        userList.add(user2);
        mm.addAttribute("userList", userList);
        return "userListJson1";
    }

    @RequestMapping(value = "/showUserListByI18n")
    public String showUserListInI18n(ModelMap mm) {
        Calendar calendar = new GregorianCalendar();
        List<User> userList = new ArrayList<User>();
        User user1 = new User();
        user1.setUserName("tom");
        user1.setRealName("汤姆1");
        calendar.set(1980, 1, 1);
        user1.setBirthday(calendar.getTime());
        User user2 = new User();
        user2.setUserName("john");
        user2.setRealName("约翰");
        user2.setBirthday(calendar.getTime());
        userList.add(user1);
        userList.add(user2);
        mm.addAttribute("userList", userList);
        return "userListi18n";
    }

    @RequestMapping(value = "/showUserListMix")
    public String showUserListMix(ModelMap mm) {
        Calendar calendar = new GregorianCalendar();
        List<User> userList = new ArrayList<User>();
        User user1 = new User();
        user1.setUserName("tom");
        user1.setRealName("汤姆1");
        calendar.set(1980, 1, 1);
        user1.setBirthday(calendar.getTime());
        User user2 = new User();
        user2.setUserName("john");
        user2.setRealName("约翰");
        user2.setBirthday(calendar.getTime());
        userList.add(user1);
        userList.add(user2);
        mm.addAttribute("userList", userList);
        return "userListMix";
    }

    @RequestMapping(value = "/uploadPage")
    public String updatePage() {
        return "uploadPage";
    }


    @RequestMapping(value = "/upload")
    public String updateThumb(@RequestParam("name") String name,
                              @RequestParam("file") MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            file.transferTo(new File("d:/temp/" + file.getOriginalFilename()));
            return "redirect:success.html";
        } else {
            return "redirect:fail.html";
        }
    }

    @RequestMapping(value = "/throwException")
    public String throwException() {
        if (2 > 1) {
            throw new RuntimeException("ddd");
        }
        return "success";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException re, HttpServletRequest request) {
        return "forward:/error.jsp";
    }

    @RequestMapping(value = "/notFound")
    public String notFound() {
        return "successdddd";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound(HttpServletRequest request) {
        return "forward:/error.jsp";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // binder.registerCustomEditor(User.class, new UserEditor());
        // binder.setValidator(new UserValidator());
    }

}
