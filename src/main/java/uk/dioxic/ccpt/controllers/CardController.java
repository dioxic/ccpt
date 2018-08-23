package uk.dioxic.ccpt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import uk.dioxic.ccpt.CardNotFoundException;
import uk.dioxic.ccpt.model.Card;
import uk.dioxic.ccpt.repository.CardRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/card")
@CrossOrigin(origins = "http://localhost:3000")
public class CardController {

    @Autowired
    private CardRepository repository;

    @GetMapping
    public List<Card> getAllCards() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Card getCard(@PathVariable long id) {
        Optional<Card> card = repository.findById(id);

        if (!card.isPresent())
            throw new CardNotFoundException("id-" + id);

        return card.get();
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable long id) {
        repository.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<Object> createCard(@Valid @RequestBody Card card) {
        Card savedCard = repository.save(card);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedCard.getId()).toUri();

        return ResponseEntity.ok(savedCard);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCard(@Valid @RequestBody Card card, @PathVariable long id) {
        Optional<Card> optionalCard = repository.findById(id);

        if (!optionalCard.isPresent())
            return ResponseEntity.notFound().build();

        card.setId(id);

        repository.save(card);

        return ResponseEntity.accepted().build();
    }
}
