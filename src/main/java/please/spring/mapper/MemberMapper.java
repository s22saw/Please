package please.spring.mapper;

import org.apache.ibatis.annotations.Mapper;
import please.spring.dto.MemberDTO;


@Mapper
public interface MemberMapper {
    void insertMember(MemberDTO memberDTO);
    MemberDTO loginCheck(MemberDTO memberDTO);
    MemberDTO selectMemberDetail(String userID);
    void updateMember(MemberDTO memberDTO);
    void deleteMember(String userID);

}
