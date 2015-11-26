package priv.mymemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class NewMemoFragment extends DialogFragment implements View.OnClickListener {

    private String currentEdit;
    private View classView;

    public static NewMemoFragment newInstance(String param1, String param2) {
        NewMemoFragment fragment = new NewMemoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_new_memo, null);
        classView = view;
        builder.setView(classView);
        ImageButton img = (ImageButton) classView.findViewById(R.id.dialogImgButton);
        img.setOnClickListener(this);


        EditText et = (EditText) classView.findViewById(R.id.textEditor);

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                ImageButton img = (ImageButton) classView.findViewById(R.id.dialogImgButton);
                if (s.toString().trim().length() > 0) {
                    img.setImageResource(R.drawable.ic_checked);
                } else {
                    img.setImageResource(R.drawable.ic_down);
                }
            }
        });
        return builder.create();
    }

    public void onClick(View v) {
        addToString(classView);
    }

    public void addToString(View v) {
        EditText et = (EditText) classView.findViewById(R.id.textEditor);
        currentEdit = et.getText().toString();
        if (currentEdit != null && !currentEdit.equals("") && !currentEdit.isEmpty()) {
            ((MainActivity) getActivity()).save(currentEdit);
        }
        currentEdit = "";
        dismiss();


    }


}
