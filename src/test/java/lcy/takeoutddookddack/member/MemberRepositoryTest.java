package lcy.takeoutddookddack.member;


import lcy.takeoutddookddack.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testMember() {
        Member member1 = new Member();
        member1.setUsername("memberA");
        Long savedId = memberRepository.save(member1);

        Member member2 = new Member();
        member2.setUsername("memberB");
        Long savedId2 = memberRepository.save(member2);

        Member findMember = memberRepository.find(savedId);
        Member findMember2 = memberRepository.find(savedId2);

        Assertions.assertThat(findMember.getId()).isEqualTo(member1.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member1.getUsername());
        Assertions.assertThat(findMember2.getId()).isEqualTo(member2.getId());
        Assertions.assertThat(findMember2.getUsername()).isEqualTo(member2.getUsername());
    }

}