package vn.skymapglobal.smartcheckingapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import vn.skymapglobal.smartcheckingapp.R;
import vn.skymapglobal.smartcheckingapp.models.Checking_Log;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
        View view =  inflater.inflate(R.layout.fragment_home, container, false);


        Realm realm = Realm.getDefaultInstance();
        Bundle bundle = getArguments();
        String userName = bundle.getString("username");

        Button checkInButton = view.findViewById(R.id.btnCheckin);
        Button checkOutButton = view.findViewById(R.id.btnCheckout);

        checkInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thời gian hiện tại của hệ thống
                Date currentTime = Calendar.getInstance().getTime();

                // Tạo một đối tượng Checking_Logs và thêm vào Realm
                realm.beginTransaction();
                Checking_Log checkInLog = realm.createObject(Checking_Log.class,nextId());
                checkInLog.setUsername(userName);
                checkInLog.setTime(currentTime);
                checkInLog.setCheckingTypeId(1); // 1 có thể là mã loại check-in của bạn
                realm.commitTransaction();

            }
        });

        checkOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thời gian hiện tại của hệ thống
                Date currentTime = Calendar.getInstance().getTime();

                // Tạo một đối tượng Checking_Logs và thêm vào Realm
                realm.beginTransaction();
                Checking_Log checkOutLog = realm.createObject(Checking_Log.class,nextId());
                checkOutLog.setUsername(userName);
                checkOutLog.setTime(currentTime);
                checkOutLog.setCheckingTypeId(2); // 2 có thể là mã loại check-out của bạn
                realm.commitTransaction();

            }
        });


        return view;
    }
    public long nextId() {
        Realm realm = Realm.getDefaultInstance();
        Number currentMaxId = realm.where(Checking_Log.class)
                .max("id");

        long nextId = (currentMaxId != null) ? currentMaxId.longValue() + 1 : 1;

        realm.close();

        return nextId;
    }
}

