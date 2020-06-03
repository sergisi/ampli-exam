package mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class EmailBuilderImpl implements EmailBuilder {

    private String from, subject, body;
    private List<String> to, ccTo;

    public EmailBuilderImpl() {
        to = List.of();
        ccTo = List.of();
    }

    private EmailBuilderImpl(EmailBuilderImpl impl) {
        from = impl.from;
        subject = impl.subject;
        body = impl.body;
        to = impl.to;
        ccTo = impl.ccTo;
    }

    private static void validateStrings(String ref, String toput) throws EmailBuilderException {
        if (ref != null) {
            throw new EmailBuilderException(
                    "Error: parameter was already set"
            );
        }
        validateInput(toput);
    }


    @Override
    public EmailBuilder from(String email) {
        validateStrings(from, email);
        var newBuilder =  new EmailBuilderImpl(this);
        newBuilder.from = email;
        return newBuilder;
    }

    @Override
    public EmailBuilder to(String email) {
        validateInput(email);
        var newBuilder =  new EmailBuilderImpl(this);
        newBuilder.to = getStrings(email, this.to);
        return newBuilder;
    }

    private static void validateInput(String email) throws EmailBuilderException {
        if (email == null) {
            throw new EmailBuilderException(
                    "Error: parameter passed was null!"
            );
        }
    }

    @Override
    public EmailBuilder subject(String subject) throws EmailBuilderException {
        validateStrings(this.subject, subject);
        var newBuilder =  new EmailBuilderImpl(this);
        newBuilder.subject = subject;
        return newBuilder;
    }

    @Override
    public EmailBuilder body(String body) throws EmailBuilderException {
        validateStrings(this.body, body);
        var newBuilder =  new EmailBuilderImpl(this);
        newBuilder.body = body;
        return newBuilder;
    }

    @Override
    public EmailBuilder ccTo(String email) throws EmailBuilderException {
        validateInput(email);
        var newBuilder =  new EmailBuilderImpl(this);
        newBuilder.ccTo = getStrings(email, this.ccTo);
        return newBuilder;
    }

    private ArrayList<String> getStrings(String email, List<String> listToExtend) {
        var extendedList = new ArrayList<>(listToExtend);
        extendedList.add(email);
        return extendedList;
    }

    @Override
    public Email make() throws EmailBuilderException {
        throwsForMessages(to.size() < 1, "destiny");
        throwsForMessages(from == null, "sender");
        throwsForMessages(body == null, "body");
        throwsForMessages(subject == null, "subject");
        return new Email(from, subject, body, to, ccTo);
    }

    private static void throwsForMessages(boolean predicate, String message) {
        if (predicate) {
            throw new EmailBuilderException(
                    "Error: tried to make a message without " +
                            "a " + message + "!"
            );
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailBuilderImpl that = (EmailBuilderImpl) o;
        return Objects.equals(from, that.from) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(body, that.body) &&
                Objects.equals(to, that.to) &&
                Objects.equals(ccTo, that.ccTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, subject, body, to, ccTo);
    }


    @Override
    public String toString() {
        return "EmailBuilderImpl{" +
                "from='" + from + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", to=" + to +
                ", ccTo=" + ccTo +
                '}';
    }
}
