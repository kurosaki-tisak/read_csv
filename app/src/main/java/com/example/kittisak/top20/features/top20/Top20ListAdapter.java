package com.example.kittisak.top20.features.top20;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kittisak.top20.R;
import com.example.kittisak.top20.model.Company;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kittisak on 9/19/2017 AD.
 */

public class Top20ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Company> mCompanyList;

    public void setCompanyList(List<Company> companyList) {
        mCompanyList = companyList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top_twenty, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder) holder;

        Company company = mCompanyList.get(position);

        viewHolder.mTextViewCompanyName.setText(company.getCompany());
        viewHolder.mTextViewSector.setText(company.getSector());
        viewHolder.mTextViewMarketCapital.setText(""+company.getMarketCap());
    }

    @Override
    public int getItemCount() {
        return mCompanyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view_company_name)
        AppCompatTextView mTextViewCompanyName;
        @BindView(R.id.text_view_sector)
        AppCompatTextView mTextViewSector;
        @BindView(R.id.text_view_market_capital)
        AppCompatTextView mTextViewMarketCapital;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
