package com.example.kittisak.top20.features.top20;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.kittisak.top20.model.Company;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by kittisak on 9/19/2017 AD.
 */

public class Top20Presenter implements Top20Contract.Top20Presenter {

    @NonNull
    private Context mContext;

    @NonNull
    private final Top20Contract.Top20View mView;

    public Top20Presenter(@NonNull Context context,
                          @NonNull Top20Contract.Top20View view) {
        mContext = context;
        mView = view;
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

            List<Company> beans = rowProcessor.getBeans();
            beans.remove(0);

       //     List<Company> top20 = findTopK(beans, 20);

       //     mView.showList(top20);

            mView.showList(beans);

        } catch (FileNotFoundException e) {
            e.printStackTrace();

           mView.showErrorDialog();
        }

    }

    public List<Company> findTopK(List<Company> companyList, int size) {
        return null;
    }
}
