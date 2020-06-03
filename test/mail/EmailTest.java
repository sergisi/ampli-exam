package mail;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailTest {

    @Test
    void correctBuilder() {
        assertTrue(Email.builder() instanceof EmailBuilderImpl);
    }

}