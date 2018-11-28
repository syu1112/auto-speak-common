package com.allen.auto.speak.utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class ClipboardUtil {

    /**
     * 向剪贴板中添加内容
     *
     * @param contents
     */
    public static void setClipbordContents(String contents) {
        StringSelection stringSelection = new StringSelection( contents );
        // 系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}
