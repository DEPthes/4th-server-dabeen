package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional // 테스트 종료 후 자동 롤백
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("springDB");

        // when
        Long saveId = memberService.join(member);

        // then
        Member result = memberService.findOne(saveId).get();
        assertThat(result.getName()).isEqualTo("springDB");
    }

    @Test
    void 전체회원조회() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberService.join(member2);

        // when
        List<Member> members = memberService.findMembers();

        // then
        assertThat(members.size()).isEqualTo(2);
    }

    @Test
    void 회원아이디조회() {
        // given
        Member member = new Member();
        member.setName("spring");
        Long savedId = memberService.join(member);

        // when
        Optional<Member> result = memberService.findOne(savedId);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("spring");
    }
}