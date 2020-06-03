package mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Email {
    private final String from, subject, body;
    private final List<String> to, ccTo;

    public Email(String from, String subject, String body, List<String> to, List<String> ccTo) {
        this.from = from;
        this.subject = subject;
        this.body = body;
        this.to = to;
        this.ccTo = ccTo;
    }

    public Email(String from, String subject, String body, List<String> to) {
        this(from, subject, body, to, new ArrayList<>());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(from, email.from) &&
                Objects.equals(subject, email.subject) &&
                Objects.equals(body, email.body) &&
                Objects.equals(to, email.to) &&
                Objects.equals(ccTo, email.ccTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, subject, body, to, ccTo);
    }

    public static EmailBuilder builder() {
        return new EmailBuilderImpl();
    }

    @Override
    public String toString() {
        return "Email{" +
                "from='" + from + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", to=" + to +
                ", ccTo=" + ccTo +
                '}';
    }
}
