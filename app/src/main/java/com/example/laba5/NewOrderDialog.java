package com.example.laba5;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class NewOrderDialog extends DialogFragment {

    private EditText etCompany;
    private EditText etPackage;
    private EditText etFrom;
    private EditText etTo;
    private EditText etCost;

    public interface OnNewOrderListener {
        void onNewOrderCreated(Order newOrder);
    }

    private OnNewOrderListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnNewOrderListener) {
            mListener = (OnNewOrderListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnNewOrderListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_new_order, null);

        etCompany = view.findViewById(R.id.etCompany);
        etPackage = view.findViewById(R.id.etPackage);
        etFrom = view.findViewById(R.id.etFrom);
        etTo = view.findViewById(R.id.etTo);
        etCost = view.findViewById(R.id.etCost);

        builder.setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Company company = new Company(etCompany.getText().toString(), "");
                        SmallPackage pack = new SmallPackage(etPackage.getText().toString(), false, etFrom.getText().toString(), etTo.getText().toString());
                        Order newOrder = new Order(company, pack, etFrom.getText().toString(), etTo.getText().toString(), Integer.parseInt(etCost.getText().toString()));

                        mListener.onNewOrderCreated(newOrder);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        NewOrderDialog.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
