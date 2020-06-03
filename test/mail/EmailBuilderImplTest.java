package mail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmailBuilderImplTest {

    private EmailBuilder embuild;

    @BeforeEach
    void setUp() {
        embuild = new EmailBuilderImpl();
    }

    @Test
    void testBuilderWithMultipleToCCTo() {
        assertEquals(
                new Email("me@me.com", "subject", "body",
                        List.of("1@1.com", "2@2.com"),
                        List.of("3@3.com", "4@4.com")),
                embuild.from("me@me.com")
                        .subject("subject")
                        .body("body")
                        .to("1@1.com")
                        .to("2@2.com")
                        .ccTo("3@3.com")
                        .ccTo("4@4.com")
                        .make()
        );
    }

    @Test
    void testBuilder() {
        testMakeError("destiny");
        embuild = embuild.to("mat@mat.com");
        testMakeError("sender");
        embuild = embuild.from("me@me.com");
        testMakeError("body");
        embuild = embuild.body("BODY");
        testMakeError("subject");
        embuild = embuild.subject("subject");
        assertEquals(new Email("me@me.com", "subject", "BODY",
                List.of("mat@mat.com")), embuild.make());

    }

    @Test
    void testTwoCalls() {
        embuild = embuild.body("body")
                .subject("subject")
                .from("me@me.com");

        testError("already set", () -> embuild.body("bd"));
        testError("already set", () -> embuild.subject("testomg"));
        testError("already set", () -> embuild.from("teet"));
    }

    @Test
    void testNullCalls() {
        testError("null", () -> embuild.body(null));
        testError("null", () -> embuild.subject(null));
        testError("null", () -> embuild.from(null));

    }

    private void testError(String message, Executable execute) {
        var th = assertThrows(EmailBuilderException.class, execute);
        assertTrue(th.getMessage().contains(message),
                "Expected: " + message +
                        "\nActual: " + th.getMessage());

    }

    private void testMakeError(String message) {
        testError(message, () -> embuild.make());
    }

}