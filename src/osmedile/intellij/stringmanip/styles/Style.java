package osmedile.intellij.stringmanip.styles;

import static osmedile.intellij.stringmanip.utils.StringUtil.Constants;
import static osmedile.intellij.stringmanip.utils.StringUtil.capitalizeFirstWord;
import static osmedile.intellij.stringmanip.utils.StringUtil.capitalizeFirstWord2;
import static osmedile.intellij.stringmanip.utils.StringUtil.containsSeparatorBetweenLetters;
import static osmedile.intellij.stringmanip.utils.StringUtil.containsUpperCase;
import static osmedile.intellij.stringmanip.utils.StringUtil.containsUpperCaseAfterLowerCase;
import static osmedile.intellij.stringmanip.utils.StringUtil.isCapitalizedFirstButNotAll;
import static osmedile.intellij.stringmanip.utils.StringUtil.noLowerCase;
import static osmedile.intellij.stringmanip.utils.StringUtil.noSeparatorsBetweenLetters;
import static osmedile.intellij.stringmanip.utils.StringUtil.noUpperCase;
import static osmedile.intellij.stringmanip.utils.StringUtil.removeBorderQuotes;
import static osmedile.intellij.stringmanip.utils.StringUtil.replaceSeparator;
import static osmedile.intellij.stringmanip.utils.StringUtil.startsWithUppercase;
import static osmedile.intellij.stringmanip.utils.StringUtil.toCamelCase;
import static osmedile.intellij.stringmanip.utils.StringUtil.wordsAndHyphenAndCamelToConstantCase;

public enum Style {
    KEBAB_LOWERCASE("kebab-case", "foo-bar") {
        @Override
        public String transform(Style style, String s) {
            String s1 = wordsAndHyphenAndCamelToConstantCase(s).toLowerCase();
            return replaceSeparator(s1, '_', '-');
        }
    },
    KEBAB_UPPERCASE("KEBAB-UPPERCASE", "FOO-BAR") {
        @Override
        public String transform(Style style, String s) {
            String s1 = wordsAndHyphenAndCamelToConstantCase(s);
            return replaceSeparator(s1, '_', '-');
        }
    },
    SNAKE_CASE("snake_case", "foo_bar") {
        @Override
        public String transform(Style style, String s) {
            return wordsAndHyphenAndCamelToConstantCase(s).toLowerCase();
        }
    },
    SCREAMING_SNAKE_CASE("SCREAMING_SNAKE_CASE", "FOO_BAR") {
        @Override
        public String transform(Style style, String s) {
            return wordsAndHyphenAndCamelToConstantCase(s);
        }
    },
    PASCAL_CASE("PascalCase", "FooBar", "FooBar") {
        @Override
        public String transform(Style style, String s) {
            if (style != CAMEL_CASE) {
                s = CAMEL_CASE.transform(s);
            }
            return capitalizeFirstWord2(s);
        }
    },
    CAMEL_CASE("camelCase", "fooBar", "fooBar") {
        @Override
        public String transform(Style style, String s) {
            if (style == CAMEL_CASE) {
                return s.trim();
            }
            return toCamelCase(s);
        }
    },
    DOT("dot.case", "foo.bar", "foo.Bar") {
        @Override
        public String transform(Style style, String s) {
            String s1 = wordsAndHyphenAndCamelToConstantCase(s).toLowerCase();
            return replaceSeparator(s1, '_', '.');
        }
    },
    WORD_LOWERCASE("words lowercase", "foo bar") {
        @Override
        public String transform(Style style, String s) {
            String s1 = wordsAndHyphenAndCamelToConstantCase(s).toLowerCase();
            return replaceSeparator(s1, '_', ' ');
        }
    },
    SENTENCE_CASE("First word capitalized", "Foo bar") {
        @Override
        public String transform(Style style, String s) {
            if (style != WORD_LOWERCASE) {
                s = WORD_LOWERCASE.transform(style, s);
            }

            return capitalizeFirstWord(s, Constants.DELIMITERS);
        }
    },
    WORD_CAPITALIZED("Words Capitalized", "Foo Bar") {
        @Override
        public String transform(Style style, String s) {
            if (style != WORD_LOWERCASE && style != WORD_CAPITALIZED) {
                s = WORD_LOWERCASE.transform(style, s);
            }
//			return WordUtils.capitalize(s, Constants.DELIMITERS);
            return capitalizeFirstWord2(s);
        }
    },
    /**
     * never use that to transform
     */
    _SINGLE_WORD_CAPITALIZED("<Singlewordcapitalized>", "Foobar") {
        @Override
        public String transform(Style style, String s) {
            return s;
        }
    },
    /**
     * never use that to transform
     */
    _ALL_UPPER_CASE("<ALLUPPERCASE>", "FOOBAR") {
        @Override
        public String transform(Style style, String s) {
            return s;
        }
    },
    /**
     * never use that to transform
     */
    _UNKNOWN("<unknown>") {
        @Override
        public String transform(Style style, String s) {
            return s;
        }
    },
    ;


    /**
     * first one is how it should look after transformation, others are variations which follows the rule
     */
    public String[] example;
    private String presentableName;

    Style(String presentableName, String... example) {
        this.presentableName = presentableName;
        this.example = example;
    }

    public static Style from(String s) {
        s = removeBorderQuotes(s).trim();
        boolean underscore = containsSeparatorBetweenLetters(s, '_');
        boolean noUpperCase = noUpperCase(s);
        boolean noLowerCase = noLowerCase(s);
//		boolean containsOnlyLettersAndDigits = containsOnlyLettersAndDigits(s);
        boolean noSeparators = noSeparatorsBetweenLetters(s, '.', '-', '_', ' ');
        boolean noSpecialSeparators = noSeparatorsBetweenLetters(s, '.', '-', '_');
        boolean containsUpperCaseAfterLowerCase = containsUpperCaseAfterLowerCase(s);
        boolean noSpace = noSeparatorsBetweenLetters(s, ' ');

        if (underscore && noUpperCase && noSpace) {
            return SNAKE_CASE;
        }
        if (underscore && noLowerCase && noSpace) {
            return SCREAMING_SNAKE_CASE;
        }

        boolean hyphen = containsSeparatorBetweenLetters(s, '-');
        if (hyphen && noUpperCase && noSpace) {
            return KEBAB_LOWERCASE;
        }
        if (hyphen && noLowerCase && noSpace) {
            return KEBAB_UPPERCASE;
        }

        boolean containsDot = containsSeparatorBetweenLetters(s, '.');
        if (containsDot && noSpace) {
            return DOT;
        }

        if (noLowerCase) {
            return _ALL_UPPER_CASE;
        }

        boolean startsWithUppercase = startsWithUppercase(s);
        if (startsWithUppercase && noSeparators && !containsUpperCase(s.substring(1))) {
            return _SINGLE_WORD_CAPITALIZED;
        }
        if (startsWithUppercase && noSeparators) {
            return PASCAL_CASE;
        }
        if (containsUpperCaseAfterLowerCase && noSeparators) {
            return CAMEL_CASE;
        }

        if (noUpperCase && noSpecialSeparators) {
            return WORD_LOWERCASE;
        }
        if (isCapitalizedFirstButNotAll(s) && !noSpace) {
            return SENTENCE_CASE;
        }
        if (startsWithUppercase && !noSpace) {
            return WORD_CAPITALIZED;
        }
        return _UNKNOWN;
    }

    public String getPresentableName() {
        return presentableName;
    }

    protected abstract String transform(Style style, String s);

    public String transform(String s) {
        Style from = from(s);
        return this.transform(from, s);
    }


}
