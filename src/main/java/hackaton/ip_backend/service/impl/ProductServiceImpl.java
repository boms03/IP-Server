package hackaton.ip_backend.service.impl;

import hackaton.ip_backend.domain.Member;
import hackaton.ip_backend.domain.Product;
import hackaton.ip_backend.dto.response.MemberResponseDto;
import hackaton.ip_backend.repository.MemberRepository;
import hackaton.ip_backend.repository.ProductRepository;
import hackaton.ip_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Override
    public MemberResponseDto.LeafDto purchaseLeaf(Long memberId, List<Long> productIdList)
    {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.orElseThrow(()-> new RuntimeException("Member not found"));

        Long totalIpAmount = member.getIpAmount();
        for (Long productId : productIdList) {
            Optional<Product> optionalProduct = productRepository.findById(productId);
            Product product = optionalProduct.orElseThrow(()-> new RuntimeException("wrong purchaseId"));
            totalIpAmount += product.getIpAmount();
        }

        member.setIpAmount(totalIpAmount);

        return MemberResponseDto.LeafDto.builder()
                .ipAmount(member.getIpAmount())
                .usedIpAmount(member.getUsedIpAmount())
                .build();
    }
}
