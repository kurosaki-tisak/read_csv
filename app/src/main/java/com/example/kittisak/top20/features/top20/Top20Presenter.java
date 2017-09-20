package com.example.kittisak.top20.features.top20;

import android.support.annotation.NonNull;

import com.example.kittisak.top20.model.Company;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by kittisak on 9/19/2017 AD.
 */

public class Top20Presenter implements Top20Contract.Top20Presenter {


    @NonNull
    private final Top20Contract.Top20View mView;

    private List<Company> mCompanyList;

    public Top20Presenter(@NonNull Top20Contract.Top20View view) {
        mView = view;

        mCompanyList = new ArrayList<>();
    }

    @Override
    public void subscribe() {
        //Todo
    }

    @Override
    public void unsubscribe() {
        //Todo
    }

    @Override
    public void getCSVFile(String filePath) {

        try {

            FileReader fileReader = new FileReader(filePath);

            BeanListProcessor<Company> rowProcessor = new BeanListProcessor<>(Company.class);

            CsvParserSettings parserSettings = new CsvParserSettings();
            parserSettings.setHeaders("Code", "Company", "Sector", "Market Cap", "Weight(%)");
            parserSettings.selectFields("Code", "Company", "Sector", "Market Cap", "Weight(%)");
            parserSettings.setHeaderExtractionEnabled(true);
            parserSettings.setProcessor(rowProcessor);

            CsvParser parser = new CsvParser(parserSettings);
            parser.parse(fileReader);

            mCompanyList = rowProcessor.getBeans();
            mCompanyList.remove(0);

            mView.showList(mCompanyList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();

           mView.showErrorDialog();
        }

    }

    @Override
    public void getList(boolean isFilter) {

        List<Company> result;

        if (isFilter) {

            result = findTopK(mCompanyList, 20);
            mView.showList(result);

        } else {

            mView.showList(mCompanyList);

        }
    }

    public List<Company> findTopK(List<Company> companyList, int size) {

        PriorityQueue<Company> priorityQueue = new PriorityQueue<>(size, mComparator);
        for (Company company : companyList) {
            Company peak = priorityQueue.peek();
            Company newCompany = company;

            if (priorityQueue.size() < size)
                priorityQueue.add(newCompany);
            else if (mComparator.compare(newCompany, peak) > 0) {
                priorityQueue.poll();
                priorityQueue.add(newCompany);
            }
        }

        List<Company> result = new ArrayList<>(size);
        while (!priorityQueue.isEmpty()) {
            result.add(priorityQueue.poll());
        }

        Collections.sort(result, new Comparator<Company>() {
            @Override
            public int compare(Company a, Company b) {
                double compA = Double.parseDouble(a.getWeight());
                double compB = Double.parseDouble(b.getWeight());

                return compA > compB ? -1 : (compA < compB) ? 1 : 0;
            }
        });

        return result;
    }

    private Comparator<Company> mComparator = new Comparator<Company>() {
        @Override
        public int compare(Company a, Company b) {
            double compA = Double.parseDouble(a.getWeight());
            double compB = Double.parseDouble(b.getWeight());
            if (compA > compB)
                return 1;
            else
                return 0;
        }
    };


}
