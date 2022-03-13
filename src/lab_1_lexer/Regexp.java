package lab_1_lexer;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regexp
{
    private static SortedMap<String, Pattern> lexems = new TreeMap<>();
    static
    {
        lexems.put("VAR", Pattern.compile("^[a-z][a-z0-9]{0,}$"));
        lexems.put("STRING", Pattern.compile("\".*\""));
        lexems.put("DIGIT", Pattern.compile("^0|([1-9][0-9]*)$"));
        lexems.put("ASSIGN_OP", Pattern.compile("^=$"));
        lexems.put("OP", Pattern.compile("^\\+|-|/|%|\\*$"));
        lexems.put("1KW_IF", Pattern.compile("^(if)$"));
        lexems.put("2KW_ELSE", Pattern.compile("^(else)$"));
        lexems.put("3KW_WHILE", Pattern.compile("^(while)$"));
        lexems.put("4KW_FOR", Pattern.compile("^(for)$"));
        lexems.put("SPACE", Pattern.compile("^( )$"));
        lexems.put("EXCLAMATION_MARK", Pattern.compile("^(!)$"));
        lexems.put("L_BRACKET", Pattern.compile("^\\($"));
        lexems.put("R_BRACKET", Pattern.compile("^\\)$"));
        lexems.put("LESS_MARK", Pattern.compile("^(<)$"));
        lexems.put("GREATER_MARK", Pattern.compile("^(>)$"));
        lexems.put("L_BRACE", Pattern.compile("^\\{$"));
        lexems.put("R_BRACE", Pattern.compile("^(})$"));
    }
    public void genTokens(String srcExample, List<Token> tokens)
    {
        String unitsP1 = "";
        String unitsP2 = "";
        for (int i = 0 ; i < srcExample.length(); i++) {
            unitsP1 += srcExample.substring(i, i + 1);    //набираем подстроку по символу
            if (i < srcExample.length() - 1)
            {
                unitsP2 = unitsP1 + srcExample.substring(i + 1, i + 2); //тоже подстрока, но на символ вперед
            }
            for (String lexName : lexems.keySet())
            {
                Matcher match1 = lexems.get(lexName).matcher(unitsP1);
                Matcher match2 = lexems.get(lexName).matcher(unitsP2);
                if (match1.matches())
                {
                    if (!match2.matches() || i == srcExample.length()-1) {        //правило отката
                        tokens.add(new Token(lexName, unitsP1));
                        unitsP1 = "";
                    }
                    break;
                }
            }
        }
    }

    public void printTokens(List<Token> tokens)
    {
        for(Token token: tokens)
        {
            System.out.println(token);
        }
    }

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        String srcExample = "test=555+84 \"Привет\" ifelsewhilefor{}()";
        List<Token> tokens = new LinkedList<>();
        Regexp obj = new Regexp();
        obj.genTokens(srcExample, tokens);
        obj.printTokens(tokens);
    }
}
