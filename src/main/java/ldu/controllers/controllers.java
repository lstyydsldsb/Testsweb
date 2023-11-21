package ldu.controllers;

import ldu.ldu.entity.Branch;
import ldu.ldu.entity.Users;
import ldu.service.Branchservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class controllers {
    @Autowired
    Branchservice svc;

       @RequestMapping(value = "selectall")
    public ModelAndView findall(){
        ModelAndView a = new ModelAndView("index2_334");
        List<Branch> bset = svc.findall();
        a.addObject("stu", bset);
        return a;
    }



    @RequestMapping(value = "/select")
    public ModelAndView select(@RequestParam("name") String name) {
        ModelAndView mav = new ModelAndView("index2_334");
        List<Branch> stu = new ArrayList();
        stu = svc.findstu(name);
        mav.addObject("stu", stu);
        return mav;

    }

    @RequestMapping(value = "/selecbycity")
    public ModelAndView selecbycity(@RequestParam("city") String name) {
        ModelAndView mav = new ModelAndView("index2_334");
        List<Branch> stu = new ArrayList();
        stu = svc.findbycity(name);
        mav.addObject("stu", stu);
        return mav;
    }


    @RequestMapping(value = "/insert")
    public ModelAndView insertstu(@RequestParam("branch_name") String branch_name, @RequestParam("branch_city") String branch_city, @RequestParam("assets") int assets) {
        ModelAndView mav = new ModelAndView("index2_334");
        Branch stu = new Branch(branch_name, branch_city, assets);
        List<Branch> stus = new ArrayList();
        int res = svc.insertstu(stu);

        if(res >= 1 ){
            mav.addObject("addSuccess", true);
        }else{
            mav.addObject("addSuccess", false);
        }

        stus = svc.findall();
        mav.addObject("stu", stus);
       return  mav;
    }

    @RequestMapping(value = "/delete")
    public ModelAndView deletestu(@RequestParam("name") String name) {
        ModelAndView mav = new ModelAndView("index2_334");
        List<Branch> stu = new ArrayList();
        int res = svc.deletestu(name);
        stu = svc.findall();
        mav.addObject("stu", stu);
        if(res >= 1){
            System.out.println("删除成功");
            mav.addObject("deleteSuccess", true);
        }else{
            System.out.println("删除失败");
            mav.addObject("deleteSuccess", false);
        }
        return mav;
    }
    public static ModelAndView showpikture(int res){
        ModelAndView mav = new ModelAndView("index2_334");
        if(res >= 1){
            mav.addObject("deleteSuccess", true);
        }else{
            mav.addObject("deleteSuccess", false);
        }
        return mav;
    }

//    @RequestMapping(value = "/loading")
//    public ModelAndView loading(@RequestParam("username") String usernmae, @RequestParam("password") String password) {
//        ModelAndView mav = new ModelAndView("index_334");
//        ModelAndView mav1 = new ModelAndView("Loding");
//        List<Users>  use = svc.findalluser();
//        int flag1 = 0,flag2 = 0;
//        Users curUser = null;
//        for(Users u : use){
//            System.out.println(u.getUsername().equals(usernmae));
//
//            if(u.getUsername().equals(usernmae)){
//                flag1 = 1;
//                curUser = u;
//            }
//        }
//        if(flag1 == 1){
//            if(curUser.getPassword().equals(password)){
//                System.out.println("登录成功");
//                return  mav;
//            }else{
//                mav1.addObject("shows","1");
//                System.out.println("密码错误");
//            }
//        }else{
//            mav1.addObject("shows","2");
//            System.out.println("没有改用户");
//        }
//        return  mav1;
//    }




    @RequestMapping("/profile")
    public ModelAndView userProfile(HttpSession session) {
        // 获取当前登录的用户对象
        Users currentUser = (Users) session.getAttribute("currentUser");

        if (currentUser != null) {
            // 用户已登录，显示用户信息页面
            ModelAndView mav = new ModelAndView("index_334");
            mav.addObject("user", currentUser);
            return mav;
        } else {
            // 用户未登录，重定向到登录页面
            return new ModelAndView("redirect:/load");
        }
    }


    @RequestMapping(value = "/loading")
    public ModelAndView loading(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session
    ) {
      List<Users> users = svc.findalluser();
        for (Users user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                // 用户名和密码匹配，将用户对象存储到Session中表示用户已登录
                session.setAttribute("currentUser", user);
                System.out.println(session.toString());
                ModelAndView mav = new ModelAndView("index_334");
                Users currentUser = new Users(username,password);
                mav.addObject("currentUser", currentUser);
                return mav;
            }
        }
         //用户名和密码不匹配，返回登录页面并显示错误信息
        ModelAndView mav = new ModelAndView("Loding");
        mav.addObject("shows", "1"); // 可能是密码错误或者用户名不存在
        return mav;
    }

//        @RequestMapping(value = "/loading",method = RequestMethod.POST)
//        public ModelAndView loading(@RequestBody Users users) {
//            System.out.println(users.getPassword()+ " " + users.getUsername());
//        ModelAndView mav = new ModelAndView("index_334");
//        ModelAndView mav1 = new ModelAndView("Loding");
//        List<Users>  use = svc.findalluser();
//        int flag1 = 0,flag2 = 0;
//        Users curUser = null;
//        for(Users u : use){
//            System.out.println(u.getUsername().equals(users.getUsername()));
//            if(u.getUsername().equals(users.getUsername())){
//                flag1 = 1;
//                curUser = u;
//            }
//        }
//        if(flag1 == 1){
//            if(curUser.getPassword().equals(users.getPassword())){
//                System.out.println("登录成功");
//                mav.setViewName("index_334"); // 设置目标页面的视图名称
//                return mav;
//            }else{
//
//                System.out.println(users.getPassword());
//                mav1.addObject("shows","1");
//                System.out.println("密码错误");
//
//            }
//        }else{
//            mav1.addObject("shows","2");
//            System.out.println("没有改用户");
//        }
//        return  mav1;
//    }

    @RequestMapping(value = "/load")
    public ModelAndView load() {
        ModelAndView mav1 = new ModelAndView("Loding");

        return  mav1;
    }

    @RequestMapping(value = "/update")
    public ModelAndView updatestu(@RequestParam("branch_name") String
                                              branch_name, @RequestParam("branch_city") String branch_city, @RequestParam("assets") int assets) {
        Branch branch = new Branch(branch_name, branch_city, assets);
        int res = svc.updatestu(branch);
        List<Branch> stu = new ArrayList();
        stu = svc.findall();
        ModelAndView mav = new ModelAndView("index2_334");
        mav.addObject("stu", stu);

        return  mav;

//        return svc.updatestu(stu);
    }

    @RequestMapping("mains1")
    public String hello(){
        return "Index_334"; // 返回模板的名称，而非实际内容
    }

    @RequestMapping(value = "/nav")
    public ModelAndView nav(){
    ModelAndView mav = new ModelAndView("nav");
    return mav;
    }

    @RequestMapping(value = "/leftTree")
    public ModelAndView leftTree(){
        ModelAndView mav = new ModelAndView("leftTree");
        return mav;
    }

    @RequestMapping(value = "/hello")
    public ModelAndView hello1(){
        ModelAndView mav = new ModelAndView("index2_334");
        return mav;
    }

    @RequestMapping(value = "/deletechoose")
    public ModelAndView deletechoose(@RequestParam("select_check") List<String>
                                                 select_check){

       int count=0;
        for(String name:select_check) {
            System.out.println(name);
            int res = svc.deletestu(name);
            count += res;
        }


        ModelAndView mav = new ModelAndView("index2_334");
        List<Branch> stu = new ArrayList();
        stu = svc.findall();
        mav.addObject("stu", stu);

        if(count == select_check.size()){
            System.out.println("删除成功");
            mav.addObject("deleteSuccess", true);
        }else{
            System.out.println("删除失败");
            mav.addObject("deleteSuccess", false);
        }
        return mav;
    }

    @RequestMapping(value = "/slectanyway")
    public ModelAndView slectanyway(@RequestParam("name") String name,@RequestParam("city") String city){
        ModelAndView mav = new ModelAndView("index2_334");
       // System.out.println(name + " " + city);
        if(name.equals("")){
           // System.out.println("name is null");
            List<Branch> stu = new ArrayList();
            stu = svc.findbycity(city);
            mav.addObject("stu", stu);
            return mav;
        }
        if(city.equals("")){
//            System.out.println("city is null");
            List<Branch> stu = new ArrayList();
            stu = svc.findstu(name);
            mav.addObject("stu", stu);
            return mav;
        }
        return mav;
    }


}
