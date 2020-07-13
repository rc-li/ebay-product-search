package com.example.ebaysearch;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

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

    RequestQueue mQueue;
    JSONObject data;
    View view;
    private static final String TAG = "SellerInfoFrag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        mQueue = Volley.newRequestQueue(this.getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seller_info, container, false);
        this.view = view;
        DetailActivity activity = (DetailActivity) getActivity();
        String url = activity.makeURL();
        getJson(url);
        return view;
    }

    public void getJson(String url) {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: got response: " + response);
                        ProgressBar progressBar = view.findViewById(R.id.progressBar);
                        progressBar.setVisibility(View.GONE);
                        try {
                            setData(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }

    public void setData(JSONObject data) throws JSONException {
        JSONObject item = data.getJSONObject("Item");
        if (item.has("Seller")) {
            String returnStr = "";
            JSONObject seller = item.getJSONObject("Seller");
            if (seller.has("FeedbackScore")) {
                returnStr += "<p><b> &#8226 Feedback Score: </b>" + seller.getString("FeedbackScore") + "</p>";
            }
            if (seller.has("UserID")) {
                returnStr += "<p><b> &#8226 User ID: </b>" + seller.getString("UserID") + "</p>";
            }
            if (seller.has("PositiveFeedbackPercent")) {
                returnStr += "<p><b> &#8226 Positive Feedback Percent: </b>" + seller.getString("PositiveFeedbackPercent") + "</p>";
            }
            if (seller.has("FeedbackRatingStar")) {
                returnStr += "<p><b> &#8226 Feedback Rating Star: </b>" + seller.getString("FeedbackRatingStar") + "</p>";
            }
            if (seller.has("TopRatedSeller")) {
                returnStr += "<p><b> &#8226 Top Rated Seller: </b>" + "Yes" + "</p>";
            }
            TextView sellerInfoStr = view.findViewById(R.id.sellerInfoStr);
            sellerInfoStr.setText(Html.fromHtml(returnStr));
        }
        else {
            TextView sellerInfoHeading = view.findViewById(R.id.sellerInfoHeading);
            TextView sellerInfoStr = view.findViewById(R.id.sellerInfoStr);
            sellerInfoHeading.setVisibility(View.GONE);
            sellerInfoStr.setVisibility(View.GONE);
        }


        if (item.has("ReturnPolicy")) {
            String returnStr = "";
            JSONObject returnPolicy = item.getJSONObject("ReturnPolicy");
            if (returnPolicy.has("Description")) {
                returnStr += "<p><b> &#8226 Description: </b>" + returnPolicy.getString("Description") + "</P>";
            }
            if (returnPolicy.has("InternationalRefund")) {
                returnStr += "<p><b> &#8226 International Refund: </b>" + returnPolicy.getString("InternationalRefund") + "</P>";
            }
            if (returnPolicy.has("InternationalReturnsAccepted")) {
                returnStr += "<p><b> &#8226 International Returns Accepted: </b>" + returnPolicy.getString("InternationalReturnsAccepted") + "</P>";
            }
            if (returnPolicy.has("InternationalReturnsWithin")) {
                returnStr += "<p><b> &#8226 International Returns Within: </b>" + returnPolicy.getString("InternationalReturnsWithin") + "</P>";
            }
            if (returnPolicy.has("InternationalShippingCostPaidBy")) {
                returnStr += "<p><b> &#8226 International Shipping Cost Paid By: </b>" + returnPolicy.getString("InternationalShippingCostPaidBy") + "</P>";
            }
            if (returnPolicy.has("Refund")) {
                returnStr += "<p><b> &#8226 Refund: </b>" + returnPolicy.getString("Refund") + "</P>";
            }
            if (returnPolicy.has("ReturnsWithin")) {
                returnStr += "<p><b> &#8226 Returns Within: </b>" + returnPolicy.getString("ReturnsWithin") + "</P>";
            }
            if (returnPolicy.has("ShippingCostPaidBy")) {
                returnStr += "<p><b> &#8226 Shipping Cost Paid By: </b>" + returnPolicy.getString("ShippingCostPaidBy") + "</P>";
            }
            if (returnPolicy.has("ReturnsAccepted")) {
                returnStr += "<p><b> &#8226 Returns Accepted: </b>" + returnPolicy.getString("ReturnsAccepted") + "</P>";
            }
            TextView returnPoliciesStr = view.findViewById(R.id.returnPoliciesStr);
            returnPoliciesStr.setText(Html.fromHtml(returnStr));
        }
        else {
            TextView returnPoliciesStr = view.findViewById(R.id.returnPoliciesStr);
            TextView returnPolicyHeading = view.findViewById(R.id.returnPolicyHeading);
            returnPolicyHeading.setVisibility(View.GONE);
            returnPoliciesStr.setVisibility(View.GONE);
        }
    }
}