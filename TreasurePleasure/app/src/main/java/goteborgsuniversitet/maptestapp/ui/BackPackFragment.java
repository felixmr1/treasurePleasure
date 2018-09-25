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

public class BackPackFragment extends Fragment {

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

        //Get reference to the TextView in the fragment_backpack xml layout file
        ImageView clickImageView = (ImageView) rootView.findViewById(R.id.imageView11);
        clickImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickBackpack(view);
            }
        });

        //TODO populate backpack

        return  rootView;
    }

    public void onClickBackpack(View view) {
        callBackMethodsInterface.receivedCallBack( "backPackFragment" + " reporting in");
    }

}
