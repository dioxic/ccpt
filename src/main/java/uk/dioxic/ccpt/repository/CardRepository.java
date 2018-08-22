package uk.dioxic.ccpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.dioxic.ccpt.model.Card;

//@RepositoryRestResource(collectionResourceRel = "card", path = "card")
@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

}