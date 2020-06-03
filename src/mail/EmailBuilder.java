package mail;

public interface EmailBuilder {
    EmailBuilder from(String email);
    EmailBuilder to(String email);
    EmailBuilder subject(String subject);
    EmailBuilder body(String body);
    EmailBuilder ccTo(String email);
    Email make() throws EmailBuilderException;
}
