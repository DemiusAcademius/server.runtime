package com.acc.server.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by demius on 04.01.2016.
 */
public class TranslateRomUtf {

    	/*
	 *
create or replace function trans_rom_utf(a_nume in varchar2)
  return varchar2 is
begin
  return replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(a_nume,
                                                                                                 '~',
                                                                                                 '~~'),
                                                                                         chr(186),
                                                                                         '~s'),
                                                                                 chr(170),
                                                                                 '~S'),
                                                                         chr(227),
                                                                         '~a'),
                                                                 chr(195),
                                                                 '~A'),
                                                         chr(254),
                                                         '~t'),
                                                 chr(222),
                                                 '~T'),
                                         chr(238),
                                         '~i'),
                                 chr(206),
                                 '~I'),
                         chr(226),
                         '^a'),
                 chr(194),
                 '^A');
end trans_rom_utf;
	 *
	 */

    // uses function COMMON.trans_rom_utf
    // decode symbols ~S etc to Ş etc
    // symbol ~~ to ~
    public static String translate(String source) {
        Pattern p = Pattern.compile("(~.)|(\\^a)|(\\^A)");
        Matcher m = p.matcher(source);

        return StringUtils.replaceAll(m, s -> {
            String out;
            if (s.charAt(0) == '~')
                switch (s.charAt(1)) {
                    case '~': out = "~"; break;
                    case 's': out = "ş"; break;
                    case 't': out = "ţ"; break;
                    case 'a': out = "ă"; break;
                    case 'i': out = "î"; break;
                    case 'S': out = "Ş"; break;
                    case 'T': out = "Ţ"; break;
                    case 'A': out = "Ă"; break;
                    case 'I': out = "Î"; break;
                    default: out = s;
                }
            else if (s.equals("^a"))
                out = "â";
            else if (s.equals("^A"))
                out = "Â";
            else out = s;

            return out;
        });
    }

    public static void main(String ... args) {
        if (args.length > 0) {
            System.out.println("source string is: " + args[0]);
            System.out.println("result string is: " + translate(args[0]));
        }
    }

}
