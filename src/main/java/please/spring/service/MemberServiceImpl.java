package please.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import please.spring.dto.MemberDTO;
import please.spring.mapper.MemberMapper;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    @Transactional
    public void insertMember(MemberDTO memberDTO)  {
        memberMapper.insertMember(memberDTO);
    }

    @Override
    public MemberDTO loginCheck(MemberDTO memberDTO) {
        return memberMapper.loginCheck(memberDTO);
    }

    @Override
    public MemberDTO selectMemberDetail(String userID) {
        return memberMapper.selectMemberDetail(userID);
    }


    @Override
    @Transactional
    public void updateMember(MemberDTO memberDTO)  {
        memberMapper.updateMember(memberDTO);
    }

    @Override
    @Transactional
    public void deleteMember(String userID)  {
        memberMapper.deleteMember(userID);
    }
}
