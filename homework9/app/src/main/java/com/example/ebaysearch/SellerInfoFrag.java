package com.example.ebaysearch;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SellerInfoFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SellerInfoFrag extends android.app.Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SellerInfoFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment sellerInfo.
     */
    // TODO: Rename and change types and number of parameters
    public static SellerInfoFrag newInstance(String param1, String param2) {
        SellerInfoFrag fragment = new SellerInfoFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    JSONObject data;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seller_info, container, false);
        this.view = view;
        return view;
    }

    public void setData(JSONObject data) {
        DetailActivity activity = (DetailActivity) getActivity();
        TextView sellerInfoStr = view.findViewById(R.id.sellerInfoStr);
        try {
            sellerInfoStr.setText(Html.fromHtml(
                    " <ul style=\"margin: 0px;\">\n" +
                            "        <b><p style=\"text-indent: 10px;\">&#8226 Feedback Score: </p></b><br>\n" + this.data.getJSONObject("Seller") +
                            "        <b><p style=\"text-indent: 10px;\">&#8226 User I D: </p></b><br>\n" +
                            "        <b><p style=\"text-indent: 10px;\">&#8226 Positive Feedback Percent: </p></b><br>\n" +
                            "        <b><p style=\"text-indent: 10px;\">&#8226 Feedback Rating Star: </p></b><br><br>\n" +
                            "    </ul>\n" +
                            "    <hr>"
                    , Html.FROM_HTML_MODE_COMPACT));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.data = data;
    }
}