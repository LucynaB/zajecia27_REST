package pl.javastart.restoffers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByTitleIgnoreCaseContains(String value);
}
