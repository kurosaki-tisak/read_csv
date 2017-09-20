package com.example.kittisak.top20.features.top20;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kittisak.top20.R;
import com.example.kittisak.top20.model.Company;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by kittisak on 9/19/2017 AD.
 */

public class Top20Fragment extends Fragment implements Top20Contract.Top20View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    Unbinder unbinder;

    private Top20Contract.Top20Presenter mPresenter;
    private Top20ListAdapter mAdapter;
    private String mFilepath;

    public static Top20Fragment newInstance(String filePath) {
        Top20Fragment fragment = new Top20Fragment();
        fragment.mFilepath = filePath;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new Top20ListAdapter();

        mPresenter = new Top20Presenter(getContext(), this);
        mPresenter.getCSVFile(mFilepath);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_top_twenty, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.item_divider));

        mRecyclerView.addItemDecoration(dividerItemDecoration);

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setPresenter(@NonNull Top20Contract.Top20Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void showList(List<Company> companyList) {
        mAdapter.setCompanyList(companyList);
    }

    @Override
    public void showErrorDialog() {
        Log.v("DEV", "Error");
    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
