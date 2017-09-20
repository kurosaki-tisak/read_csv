package com.example.kittisak.top20.features.top20;

import com.example.kittisak.top20.common.BasePresenter;
import com.example.kittisak.top20.common.BaseView;
import com.example.kittisak.top20.model.Company;

import java.util.List;

/**
 * Created by kittisak on 9/19/2017 AD.
 */

public interface Top20Contract {

    interface Top20View extends BaseView<Top20Presenter>{
        void showList(List<Company> companyList);
        void showErrorDialog();
    }

    interface Top20Presenter extends BasePresenter {
        void getCSVFile(String filePath);
        void getList(boolean isFilter);
    }
}
