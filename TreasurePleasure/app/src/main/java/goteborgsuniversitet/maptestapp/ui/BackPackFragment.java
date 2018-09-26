package goteborgsuniversitet.maptestapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import goteborgsuniversitet.maptestapp.R;

//Fragments must be embedded in a host activity

public class BackPackFragment extends Fragment implements View.OnClickListener {

    //make reference to interface in order to check that the containing activity implements given interface
    CallBackMethodsInterface callBackMethodsInterface;

    //must have a constructor
    public BackPackFragment (){
    }

    //ensure container activity has implemented callback interface
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //this makes sure that the host activity has implemented the callback interface
        //if not, it throws an exception
        try {
            callBackMethodsInterface = (CallBackMethodsInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement CallBackMethodsInterface" );
        }
    }


    /**
     * Inflates the fragment layout and sets any image resources
     * if a view already exists, then use findViewById. If not, then create it with a LayoutInflater
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //inflate Android-Me fragment layout
        View rootView = inflater.inflate(R.layout.fragment_backpack, container, false);

        //Get reference to the imageView in the fragment_backpack xml layout file
        /*
        ImageView clickImageView = (ImageView) rootView.findViewById(R.id.imageView11);
        clickImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickBackpack(view);
            }
        });
        */

        //Setup Buttons
        ImageView clickImageView11 = (ImageView) rootView.findViewById(R.id.imageView11);
        ImageView clickImageView12 = (ImageView) rootView.findViewById(R.id.imageView12);
        ImageView clickImageView13 = (ImageView) rootView.findViewById(R.id.imageView13);
        ImageView clickImageView21 = (ImageView) rootView.findViewById(R.id.imageView21);
        ImageView clickImageView22 = (ImageView) rootView.findViewById(R.id.imageView22);
        ImageView clickImageView23 = (ImageView) rootView.findViewById(R.id.imageView23);

        clickImageView11.setOnClickListener(this);
        clickImageView12.setOnClickListener(this);
        clickImageView13.setOnClickListener(this);
        clickImageView21.setOnClickListener(this);
        clickImageView22.setOnClickListener(this);
        clickImageView23.setOnClickListener(this);

        //TODO dynamically populate backpack

        return  rootView;
    }

    public void onClickBackpack(View view, String s) {
        callBackMethodsInterface.receivedCallBack( "backPackFragment view #" + s + " reporting in");
    }


    //this class extends onClickListener. click functionality is handled here
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView11:
                onClickBackpack(v, "1:1");
                break;
            case R.id.imageView12:
                onClickBackpack(v, "1:2");
                break;
            case R.id.imageView13:
                onClickBackpack(v, "1:3");
                break;
            case R.id.imageView21:
                onClickBackpack(v, "2:1");
                break;
            case R.id.imageView22:
                onClickBackpack(v, "2:2");
                break;
            case R.id.imageView23:
                onClickBackpack(v, "2:3");
                break;
            default: onClickBackpack(v, "unexpected behaviour");
                break;
        }
    }
}


