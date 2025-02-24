package storage.zanzik01;

import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.ContextConfiguration;
import storage.zanzik01.repository.PlaceRepository;
import storage.zanzik01.repository.ThingRepository;
import storage.zanzik01.service.ThingService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(
        classes = StorageApplication.class
)
public class BaseIntegrationTest {

    protected TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    protected ThingRepository thingRepository;

    @Autowired
    protected PlaceRepository placeRepository;

    @Autowired
    protected ThingService thingService;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @BeforeEach
    void setup() {
        thingRepository.deleteAll();
    }

    @PostConstruct
    public void init() {
        RestTemplateBuilder restTemplate = restTemplateBuilder.rootUri("http://localhost:" + port);
        this.testRestTemplate = new TestRestTemplate(restTemplate);
    }
}
