package osmedile.intellij.stringmanip.styles;

import java.util.Map;

public class ToPascalCaseAction extends AbstractCaseConvertingAction {
    public ToPascalCaseAction() {
    }

    public ToPascalCaseAction(boolean setupHandler) {
        super(setupHandler);
    }

    @Override
    public String transformByLine(Map<String, Object> actionContext, String s) {
        Style from = getStyle(actionContext, s);
        if (from == Style.PASCAL_CASE || from == Style._SINGLE_WORD_CAPITALIZED) {
            return Style.CAMEL_CASE.transform(s);
        } else {
            return Style.PASCAL_CASE.transform(s);
        }
    }

}
