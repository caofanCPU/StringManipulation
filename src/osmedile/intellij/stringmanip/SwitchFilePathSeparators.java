package osmedile.intellij.stringmanip;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class SwitchFilePathSeparators extends AbstractStringManipAction<Object> {

    private SwitchTo replaceOperation = SwitchTo.op_Unknown;

    protected SwitchFilePathSeparators() {
    }


    protected SwitchFilePathSeparators(boolean setupHandler) {
        super(setupHandler);
    }

    @NotNull
    @Override
    public Pair beforeWriteAction(Editor editor, DataContext dataContext) {
        replaceOperation = SwitchTo.op_Unknown;
        return super.beforeWriteAction(editor, dataContext);
    }

    @Override
    public String transformByLine(Map<String, Object> actionContext, String s) {
        String s1;
        if (replaceOperation == SwitchTo.op_Unknown) {
            int fslash = s.indexOf('/');
            int bslash = s.indexOf('\\');

            boolean slashDetected = (bslash >= 0) || (fslash >= 0);
            if (slashDetected) {
                if ((fslash >= 0) && (bslash >= 0)) {
                    replaceOperation = (bslash < fslash) ? SwitchTo.op_BackslashToSlash : SwitchTo.op_SlashToBackslash;
                } else {
                    replaceOperation = (bslash >= 0) ? SwitchTo.op_BackslashToSlash : SwitchTo.op_SlashToBackslash;
                }
            }
        }
        switch (replaceOperation) {
            case op_SlashToBackslash:
                s1 = s.replace("/", "\\");
                break;
            case op_BackslashToSlash:
                s1 = s.replace("\\", "/");
                break;
            case op_Unknown:
            default:
                s1 = s;
                break;
        }
        return s1;
    }

    enum SwitchTo {
        op_Unknown,
        op_SlashToBackslash,
        op_BackslashToSlash
    }
}
