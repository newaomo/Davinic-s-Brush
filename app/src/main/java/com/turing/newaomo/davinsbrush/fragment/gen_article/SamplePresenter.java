package com.turing.newaomo.davinsbrush.fragment.gen_article;

/**
 * Created by andy (https://github.com/andyxialm)
 * Creation time: 16/11/2 18:32
 * Description  :
 */
public class SamplePresenter implements SampleContract.SamplePresenter {

    private SampleContract.View mView;

    public SamplePresenter(SampleContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void loadTextData(String text) {
        mView.setTextData(text);
        mView.startTyper();
    }
}
