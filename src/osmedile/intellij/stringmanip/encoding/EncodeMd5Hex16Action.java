package osmedile.intellij.stringmanip.encoding;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import osmedile.intellij.stringmanip.AbstractStringManipAction;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author Olivier Smedile
 * @version $Id: EncodeMd5Hex16Action.java 32 2008-03-24 10:15:55Z osmedile $
 */
public class EncodeMd5Hex16Action extends AbstractStringManipAction<Object> {

    @Override
    protected String transformSelection(Editor editor, Map<String, Object> actionContext, DataContext dataContext, String selectedText, Object additionalParam) {
        try {

            byte[] hash = MessageDigest.getInstance("md5").digest(selectedText.getBytes("UTF-8"));


            StringBuffer hashString = new StringBuffer();

            for (byte aHash : hash) {
                String hex = Integer.toHexString(aHash);

                if (hex.length() == 1) {
                    hashString.append("0");
                    hashString.append(hex.charAt(hex.length() - 1));
                } else {
                    hashString.append(hex.substring(hex.length() - 2));

                }
            }

            return hashString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String transformByLine(Map<String, Object> actionContext, String s) {
        throw new UnsupportedOperationException();
    }
}