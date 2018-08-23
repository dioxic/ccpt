package uk.dioxic.ccpt;

import org.springframework.boot.CommandLineRunner;
import uk.dioxic.ccpt.model.Card;
import uk.dioxic.ccpt.repository.CardRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

//@Component
public class CardCommandLineRunner implements CommandLineRunner {

    private final CardRepository repository;

    public CardCommandLineRunner(CardRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        List<Card> cardList = Arrays.asList(
                new Card(1, "4957442748848404", "Bob", BigDecimal.ZERO, BigDecimal.ONE),
                new Card(2, "2221002051552177", "Alice", BigDecimal.ZERO, BigDecimal.ONE)
        );
        repository.saveAll(cardList);
        repository.findAll().forEach(System.out::println);
    }
}