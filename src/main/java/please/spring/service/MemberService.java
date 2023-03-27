package please.spring.service;

import please.spring.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    void insertMember(MemberDTO memberDTO);
    MemberDTO loginCheck(MemberDTO memberDTO);
    MemberDTO selectMemberDetail(String userID);
    void updateMember(MemberDTO memberDTO);
    void deleteMember(String userID);
}
