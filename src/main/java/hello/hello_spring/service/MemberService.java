package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.Optional;
import java.util.List;

/* Service는 비지니스적으로 함수명을 작성하던지 해야함 :. Service가 비지니스 처리를 하는 곳이기도 하고 추후에 어떤 문제가 생겨서 오류를 찾을 때도 찾기 편하기 때문에 */
public class MemberService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();     // MemberRepository는 인터페이스이므로 new ...로 인스턴스화를 하지 못함
    // 동일한 memberRepository 를 사용하기 위해서 위 코드에서 아래 코드로 수정
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원X ( command+option+v: 리턴 자동 작성해주는 단축키 )
        // 요즘에는 null로 반환될 수 있을법한 로직에는 "변수 != null" 이렇게 처리하지 않고 Optional로 감싸서 처리
        // Optional<Member> result = memberRepository.findByName(member.getName());

        validateDuplicateMember(member);    // 중복 회원 검증, ctrl+t(코드에 리팩토링할 내용을 알려줌) -> Extract Method... 클릭하면 드레그한 부분 메서드로 빼줌
        memberRepository.save(member);

        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // isPresent(): Optional이 값을 포함하고 있는지 확인만 하는 메서드, if인데 is로 오타가 나서 오류가 났었음..
        // ifPresent()는 Optional에 값이 존재하면 람다식을 실행하는 메서드이므로, 여기서 예외를 던지는 로직을 넣을 수 있습니다.
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
