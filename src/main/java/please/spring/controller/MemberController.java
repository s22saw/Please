package please.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import please.spring.dto.MemberDTO;
import please.spring.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
public class MemberController {

    private final String uploadDir = "D:/spring/please-spring/src/main/resources/static/memberFileUpload/";
    private String picFileName = null;
    private int picFileSize = 0;

    @Autowired
    private MemberService memberService;

    @GetMapping("/memberJoin")
    public String memberJoin() {
        return "member/memberJoin";
    }

    @PostMapping("/memberJoin")
    public String memberJoin(HttpServletRequest request, @RequestParam("picFile") MultipartFile file) throws Exception {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserID(request.getParameter("userID"));
        memberDTO.setUserPW(request.getParameter("userPW"));
        memberDTO.setUserName(request.getParameter("userName"));
        memberDTO.setPicFileName(file.getOriginalFilename());
        memberDTO.setPicFileSize(Integer.parseInt(String.valueOf(file.getSize())));
        memberDTO.setUserEmail(request.getParameter("userEmail"));
        memberDTO.setUserContact(request.getParameter("userContact"));
        memberDTO.setUserAddress(request.getParameter("userAddress"));
        memberDTO.setUserDiv(Integer.parseInt(request.getParameter("userDiv")));
        memberDTO.setInterest1(request.getParameter("interest1"));
        memberDTO.setInterest2(request.getParameter("interest2"));

        if (file.getOriginalFilename().trim().isEmpty() || file.getOriginalFilename() == null) {
        } else {
            picFileName = file.getOriginalFilename();
            String fullPath = uploadDir + picFileName;
            file.transferTo(new File(fullPath));
        }
        memberService.insertMember(memberDTO);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @PostMapping("/login")
    public String login(MemberDTO memberDTO, HttpSession session) {
        memberDTO = memberService.loginCheck(memberDTO);
        if (memberDTO == null) {
            return "redirect:/login";
        } else {
            session.setAttribute("memberDTO", memberDTO);
            session.setAttribute("sessionUserID", memberDTO.getUserID());
            return "redirect:/";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping("/memberDetail")
    public ModelAndView memberDetail(@SessionAttribute("memberDTO") MemberDTO memberDTO) throws Exception {
        ModelAndView mv = new ModelAndView("/member/memberDetail");
        MemberDTO selectMemberDetail = memberService.selectMemberDetail(memberDTO.getUserID());
        mv.addObject("selectMemberDetail", selectMemberDetail);
        return mv;
    }

    @GetMapping("/memberModify")
    public ModelAndView memberModify(HttpServletRequest request, @SessionAttribute("memberDTO") MemberDTO memberDTO) {
        ModelAndView mv = new ModelAndView("member/memberModify");
        MemberDTO selectMemberDetail = memberService.selectMemberDetail(memberDTO.getUserID());
        mv.addObject("selectMemberDetail", selectMemberDetail);
        return mv;
    }

    @PostMapping("/memberModify")
    public String memberModify(@SessionAttribute("memberDTO") MemberDTO memberDTO, HttpServletRequest request,
                               @RequestParam("modPicFile") MultipartFile file) throws Exception {
        MemberDTO orgMemberDetail = memberService.selectMemberDetail(memberDTO.getUserID());
        String fileName = null;
        int fileSize = 0;

        if(file.isEmpty()) {
            fileName = orgMemberDetail.getPicFileName();
            fileSize = orgMemberDetail.getPicFileSize();
        } else {
            fileName = file.getOriginalFilename();
            fileSize = Integer.parseInt(String.valueOf(file.getSize()));
            String fullPath = uploadDir + fileName;
            file.transferTo(new File(fullPath));
        }

        memberDTO = new MemberDTO();
        memberDTO.setUserID(request.getParameter("modUserID"));
        memberDTO.setUserPW(request.getParameter("modUserPW"));
        memberDTO.setUserName(request.getParameter("modUserName"));
        memberDTO.setPicFileName(fileName);
        memberDTO.setPicFileSize(fileSize);
        memberDTO.setUserEmail(request.getParameter("modUserEmail"));
        memberDTO.setUserContact(request.getParameter("modUserContact"));
        memberDTO.setUserAddress(request.getParameter("modUserAddress"));
        memberDTO.setUserDiv(Integer.parseInt(request.getParameter("modUserDiv")));
        memberDTO.setInterest1(request.getParameter("modInterest1"));
        memberDTO.setInterest2(request.getParameter("modInterest2"));
        memberDTO.setSessionUserID(memberDTO.getUserID());

        memberService.updateMember(memberDTO);
        return "redirect:/memberDetail";

    }

    @RequestMapping("/memberDelete")
    public String memberDelete(HttpServletRequest request, @SessionAttribute("memberDTO") MemberDTO memberDTO) {
        System.out.println("memberDTO.getUserID() = " + memberDTO.getUserID());
        memberService.deleteMember(memberDTO.getUserID());

        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }

}
