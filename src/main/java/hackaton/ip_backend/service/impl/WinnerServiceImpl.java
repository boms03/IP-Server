package hackaton.ip_backend.service.impl;

import hackaton.ip_backend.domain.Member;
import hackaton.ip_backend.domain.Winner;
import hackaton.ip_backend.domain.enums.Status;
import hackaton.ip_backend.dto.response.MemberResponseDto;
import hackaton.ip_backend.repository.MemberRepository;
import hackaton.ip_backend.repository.WinnerRepository;
import hackaton.ip_backend.service.WinnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WinnerServiceImpl implements WinnerService {

    private final MemberRepository memberRepository;
    private final WinnerRepository winnerRepository;

    @Override
    public MemberResponseDto.SignNoticeDto checkWin(String token, String email) {
        String title = null;
        Long ipAmount = null;
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        Member member = optionalMember.orElseThrow(()-> new RuntimeException("Member not found"));

        Winner winner = winnerRepository.findByMemberAndStatus(member,Status.ACTIVE);

        if(!(winner==null))
        {
            title = winner.getSurvey().getTitle();
            ipAmount = winner.getSurvey().getIpAmount();
        }
        return MemberResponseDto.SignNoticeDto.builder()
                .token(token)
                .title(title)
                .ipAmount(ipAmount)
                .build();
    }
}
