package uk.dioxic.ccpt.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import uk.dioxic.ccpt.Application;
import uk.dioxic.ccpt.model.Card;
import uk.dioxic.ccpt.repository.CardRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class CardControllerTest {
    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private List<Card> cardList = new ArrayList<>();

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertThat(this.mappingJackson2HttpMessageConverter)
                .as("the JSON message converter must not be null")
                .isNotNull();
    }

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(webApplicationContext).build();

        cardRepository.deleteAllInBatch();
        cardList = Arrays.asList(
                new Card(1, "4957442748848404", "Bob", BigDecimal.ZERO, BigDecimal.ONE),
                new Card(2, "2221002051552177", "Alice", BigDecimal.ZERO, BigDecimal.ONE)
        );
        cardRepository.saveAll(cardList);
    }

    @Test
    public void userNotFound() throws Exception {
        mockMvc.perform(get("/api/card/999")
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }

    @Test
    public void readSingleCard() throws Exception {
        Card expectedCard = cardList.get(0);

        mockMvc.perform(get("/api/card/" + expectedCard.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(Long.valueOf(expectedCard.getId()).intValue())))
                .andExpect(jsonPath("$.name", is(expectedCard.getName())))
                .andExpect(jsonPath("$.number", is(expectedCard.getNumber())))
                .andExpect(jsonPath("$.limit", is(expectedCard.getLimit().doubleValue())))
                .andExpect(jsonPath("$.balance", is(expectedCard.getBalance().doubleValue())));
    }

    @Test
    public void readCards() throws Exception {
        mockMvc.perform(get("/api/card"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void createCard() throws Exception {
        String cardJson = json(new Card(3, "2221002051552177", "Kevin", BigDecimal.ZERO, BigDecimal.ZERO));

        this.mockMvc.perform(post("/api/card")
                .contentType(contentType)
                .content(cardJson))
                .andExpect(status().isOk());
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
