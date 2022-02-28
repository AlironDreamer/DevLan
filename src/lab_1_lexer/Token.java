package lab_1_lexer;

public class Token {
    private String type;
    private String value;

    public Token(String type, String value)
    {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return  "Token [type = \"" + this.type + "\", value = \"" + this.value + "\"]";
    }
}
