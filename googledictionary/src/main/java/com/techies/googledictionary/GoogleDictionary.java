package com.techies.googledictionary;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by Chizoba Ogbonna on 2/17/2017.
 */

public class GoogleDictionary {

    public void setDictionaryEnabled(final TextView textView, final FragmentManager fm) {

        textView.setCustomSelectionActionModeCallback(new ActionMode.Callback() {

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return true;
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Called when action mode is first created. The menu supplied
                // will be used to generate action buttons for the action mode

                int min = 0;
                int max = textView.getText().length();
                if (textView.isFocused()) {
                    final int selStart = textView.getSelectionStart();
                    final int selEnd = textView.getSelectionEnd();

                    min = Math.max(0, Math.min(selStart, selEnd));
                    max = Math.max(0, Math.max(selStart, selEnd));
                }

                // Perform your definition lookup with the selected text
                CharSequence selectedText = textView.getText().subSequence(min, max);

                BottomSheetDialogFragment bottomSheetDialogFragment = new MyBSDF();
                Bundle bundle = new Bundle();
                bundle.putString("TEXT", selectedText.toString());

                bottomSheetDialogFragment.setArguments(bundle);

                bottomSheetDialogFragment.show(fm, bottomSheetDialogFragment.getTag());

                return true;

            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // Called when an action mode is about to be exited and
                // destroyed
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

        });

    }
}
