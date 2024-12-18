package lshh.pollservice.domain.entity.auth.google;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lshh.pollservice.domain.entity.auth.ThirdPartyAuthentication;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class GoogleAuthentication implements ThirdPartyAuthentication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

}
