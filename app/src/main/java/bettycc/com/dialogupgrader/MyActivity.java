package bettycc.com.dialogupgrader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.betty.dialogupgrader.library.LDialogBuilder;

/**
 * Created by ccheng on 8/22/14.
 */
public class MyActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }

    public void onAlertDialog(View view) {
        new LDialogBuilder(this).setTitle(R.string.reader_add_book_title).setMessage(R.string.add_book_hint)
                .setPositiveButton(R.string.add_book, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setNegativeButton(R.string.add_book_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        finish();
                    }
                }).build().show();

    }

    public void onListDialog(View view) {
        AlertDialog listDialog = new LDialogBuilder(this).setTitle(R.string.more)
                .setItems(new String[]{"a", "b", "c"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        System.out.println("TestActivity.onClick");
                    }
                }).create();
        listDialog.show();
    }

    public void onCustomViewAlertDialog(View view) {
        View v = getLayoutInflater().inflate(R.layout.test_custom_view, null);
        new LDialogBuilder(this).setTitle(R.string.reader_add_book_title)
                .setView(v)
                .setPositiveButton(R.string.add_book, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setNegativeButton(R.string.add_book_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                        finish();
                    }
                }).build().show();
    }
}
