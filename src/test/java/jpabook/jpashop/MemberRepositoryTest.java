package jpabook.jpashop;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;
    
    @Test
    @Transactional
    @Rollback(true)
    public void testMember() throws Exception{
        //given
        Member member = new Member();
        member.setUsername("memberA");
        
        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);
        
        //then
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        System.out.println("findMember: " + findMember);
        System.out.println("Member: " + member);
        System.out.println("findMember == member: " + (findMember == member));
        assertThat(findMember).isEqualTo(member);
    }

}