package hackaton.ip_backend.service.impl;

import hackaton.ip_backend.domain.Member;
import hackaton.ip_backend.domain.Purchase;
import hackaton.ip_backend.dto.response.MemberResponseDto;
import hackaton.ip_backend.repository.MemberRepository;
import hackaton.ip_backend.repository.PurchaseRepository;
import hackaton.ip_backend.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final MemberRepository memberRepository;
    private final PurchaseRepository purchaseRepository;

    @Override
    public MemberResponseDto.LeafDto purchaseLeaf(Long memberId, List<Long> productIdList)
    {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.orElseThrow(()-> new RuntimeException("Member not found"));

        Long totalIpAmount = member.getIpAmount();
        for (Long purchaseId : productIdList) {
            Optional<Purchase> optionalPurchase = purchaseRepository.findById(purchaseId);
            Purchase purchase = optionalPurchase.orElseThrow(()-> new RuntimeException("wrong purchaseId"));
            totalIpAmount += purchase.getTotalIpAmount();
        }

        member.setIpAmount(totalIpAmount);

        MemberResponseDto.LeafDto leafDto = MemberResponseDto.LeafDto.builder()
                .ipAmount(member.getIpAmount())
                .usedIpAmount(member.getUsedIpAmount())
                .build();

        return leafDto;
    }
}
