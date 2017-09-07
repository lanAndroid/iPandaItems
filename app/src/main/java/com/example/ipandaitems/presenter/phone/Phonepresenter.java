package com.example.ipandaitems.presenter.phone;

import com.example.ipandaitems.model.Callback;
import com.example.ipandaitems.model.phone.PhoneModelImpl;
import com.example.ipandaitems.view.registerandlogin.fragment.PhoneIView;

/**
 * Created by 张豫耀 on 2017/9/6.
 */

public class Phonepresenter implements phonepersenter {
    private PhoneIView phoneIView;
    private PhoneModelImpl phoneModel;

    public Phonepresenter(PhoneIView phoneIView) {
        this.phoneIView = phoneIView;
        phoneModel = new PhoneModelImpl();
    }

    @Override
    public void get(final String phone, String phoneyanzhengma,String cookie) {
        phoneModel.RetrofitYanzhengMa(phone, phoneyanzhengma, cookie,new Callback<String>() {
            @Override
            public void succeed(String s) {
                phoneIView.OnSucess(s);
            }

            @Override
            public void nothing(String str) {

            }
        });
    }

    @Override
    public void getzhuce(String phone, String psw, String phoneyanzhengma) {

    }


}
